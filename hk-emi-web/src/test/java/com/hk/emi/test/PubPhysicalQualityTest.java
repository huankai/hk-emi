package com.hk.emi.test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hk.commons.fastjson.JsonUtils;
import com.hk.commons.poi.excel.annotations.ReadExcel;
import com.hk.commons.poi.excel.model.ReadParam;
import com.hk.commons.poi.excel.model.ReadResult;
import com.hk.commons.poi.excel.read.ReadableExcel;
import com.hk.commons.poi.excel.read.SimpleSaxReadExcel;
import com.hk.commons.util.StringUtils;
import com.hk.commons.util.date.DateTimeUtils;
import com.hk.core.query.jdbc.JdbcSession;
import com.hk.core.query.jdbc.SelectArguments;
import org.junit.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 指标导入
 *
 * @author: huangkai
 * @date 2018-04-17 09:32
 */
public class PubPhysicalQualityTest extends BaseTest {


    public static void main(String[] args) {
        System.out.println(DateTimeUtils.dateToString(new Date(1524029234)));
    }

    private static final Map<String, String> SEXS = Maps.newHashMap();

    private static final List<PubPhysicalValueProperty> PROPERTIES = Lists.newArrayList();

    static {
        SEXS.put("男", "1");
        SEXS.put("女", "2");
        PROPERTIES.add(new PubPhysicalValueProperty("G01", "一年级", "gradeOneMin", "gradeOneMax"));
        PROPERTIES.add(new PubPhysicalValueProperty("G02", "二年级", "gradeTwoMin", "gradeTwoMax"));
        PROPERTIES.add(new PubPhysicalValueProperty("G03", "三年级", "gradethreeMin", "gradeThreeMax"));
        PROPERTIES.add(new PubPhysicalValueProperty("G04", "四年级", "gradeFourMin", "gradeFourMax"));
        PROPERTIES.add(new PubPhysicalValueProperty("G05", "五年级", "gradeFiveMin", "gradeFiveMax"));
        PROPERTIES.add(new PubPhysicalValueProperty("G06", "六年级", "gradeSixMin", "gradeSixMax"));
        PROPERTIES.add(new PubPhysicalValueProperty("G07", "初一", "gradeSevenMin", "gradeSevenMax"));
        PROPERTIES.add(new PubPhysicalValueProperty("G08", "初二", "gradeEightMin", "gradeEightMax"));
        PROPERTIES.add(new PubPhysicalValueProperty("G09", "初三", "gradeNineMin", "gradeNineMax"));
        PROPERTIES.add(new PubPhysicalValueProperty("G10", "高一", "gradeTenMin", "gradeTenMax"));
        PROPERTIES.add(new PubPhysicalValueProperty("G11", "高二", "gradeElevenMin", "gradeElevenMax"));
        PROPERTIES.add(new PubPhysicalValueProperty("G12", "高三", "gradeTwelveMin", "gradeTwelveMax"));
    }

    @Autowired
    private JdbcSession jdbcSession;
    //
    @Autowired
    private PubPhysicalValueRepository physicalValueRepository;

    @Test
    public void parseTest() throws FileNotFoundException {
        SelectArguments arguments = new SelectArguments();
        arguments.setFields("physical_quality_id as id ,quality_title as title");
        arguments.setFrom("pub_physical_quality");
        List<PubPhysicalQuality> physicalQualityList = jdbcSession.queryForList(arguments, false, PubPhysicalQuality.class).getResult();


        ReadParam<PubPhysicalValueExcel> readableParam = ReadParam.<PubPhysicalValueExcel>builder()
                .beanClazz(PubPhysicalValueExcel.class)
                .titleRow(1)
                .dataStartRow(2)
                .build();

        ReadableExcel<PubPhysicalValueExcel> readableExcel = new SimpleSaxReadExcel<>(readableParam);
        ReadResult<PubPhysicalValueExcel> readResult = readableExcel.read(new FileInputStream(new File("C:/Users/sjq-278/Desktop/体质健康标准 - 整理后v1.5.xls")));

        List<PubPhysicalValue> list = Lists.newArrayList();
        List<PubPhysicalValueExcel> allSheetData = readResult.getAllSheetData();
        for (int i = 0; i < allSheetData.size(); i++) {
            PubPhysicalValueExcel item = allSheetData.get(i);
            BeanWrapper wrapper = new BeanWrapperImpl(item);
            Optional<PubPhysicalQuality> physicalQuality = physicalQualityList.stream().filter(quality -> StringUtils.equals(quality.getTitle(), item.getTitle())).findFirst();
            if (physicalQuality.isPresent()) {
                for (PubPhysicalValueProperty property : PROPERTIES) {
                    Double maxValue = (Double) wrapper.getPropertyValue(property.getMaxProperty());
                    Double minValue = (Double) wrapper.getPropertyValue(property.getMinProperty());
                    if (null != maxValue && null != minValue) {
                        if (minValue > maxValue) {
                            throw new RuntimeException("row:" + i + ",  " + property.getGradeName() + "最小值大于最大值,最小值为:" + minValue + ",最大值为:" + maxValue);
                        }
                        list.add(createPubPhysicalValue(property.getGradeCode(), item.getLevelCode(), maxValue, minValue,
                                physicalQuality.get().getId(), item.getLevel(), item.getScore(), item.getSex()));
                    }
                }
            } else {
                System.out.println(i + ",Title ::" + item.getTitle());
            }
        }

        System.out.println("list size :" + list.size());
        System.out.println("list :" + JsonUtils.toJSONString(list));
        physicalValueRepository.save(list);
    }

    private PubPhysicalValue createPubPhysicalValue(String grade, String level, Double maxValue, Double minValue, String qualityId,
                                                    String remark, Double score, String sex) {
        PubPhysicalValue qualityValue = new PubPhysicalValue();
        qualityValue.setCreateTime(new Date());
        qualityValue.setLastUpDate(new Date());
        qualityValue.setGrade(grade);
        qualityValue.setLevel(level);
        qualityValue.setMaxValue(maxValue);
        qualityValue.setMinValue(minValue);
        qualityValue.setPhysicalQualityId(qualityId);
        qualityValue.setRemark(remark);
        qualityValue.setScore(score);
        qualityValue.setSex(SEXS.get(sex));
        return qualityValue;
    }

    public static class PubPhysicalValueProperty {

        private String gradeCode;

        private String gradeName;

        private String minProperty;

        private String maxProperty;

        public PubPhysicalValueProperty(String gradeCode, String gradeName, String minProperty, String maxProperty) {
            this.gradeCode = gradeCode;
            this.gradeName = gradeName;
            this.minProperty = minProperty;
            this.maxProperty = maxProperty;
        }

        public String getGradeCode() {
            return gradeCode;
        }

        public void setGradeCode(String gradeCode) {
            this.gradeCode = gradeCode;
        }

        public String getGradeName() {
            return gradeName;
        }

        public void setGradeName(String gradeName) {
            this.gradeName = gradeName;
        }

        public String getMinProperty() {
            return minProperty;
        }

        public void setMinProperty(String minProperty) {
            this.minProperty = minProperty;
        }

        public String getMaxProperty() {
            return maxProperty;
        }

        public void setMaxProperty(String maxProperty) {
            this.maxProperty = maxProperty;
        }
    }


    public static class PubPhysicalValueExcel {

        @ReadExcel(start = 0)
        private String title;

        @ReadExcel(start = 1)
        private String level;

        @ReadExcel(start = 2)
        private String levelCode;

        @ReadExcel(start = 3)
        private String sex;

        @ReadExcel(start = 4)
        private Double score;

        @ReadExcel(start = 5)
        private Double gradeOneMin;

        @ReadExcel(start = 6)
        private Double gradeOneMax;

        @ReadExcel(start = 7)
        private Double gradeTwoMin;

        @ReadExcel(start = 8)
        private Double gradeTwoMax;

        @ReadExcel(start = 9)
        private Double gradethreeMin;

        @ReadExcel(start = 10)
        private Double gradeThreeMax;

        @ReadExcel(start = 11)
        private Double gradeFourMin;

        @ReadExcel(start = 12)
        private Double gradeFourMax;

        @ReadExcel(start = 13)
        private Double gradeFiveMin;

        @ReadExcel(start = 14)
        private Double gradeFiveMax;

        @ReadExcel(start = 15)
        private Double gradeSixMin;

        @ReadExcel(start = 16)
        private Double gradeSixMax;

        @ReadExcel(start = 17)
        private Double gradeSevenMin;

        @ReadExcel(start = 18)
        private Double gradeSevenMax;

        @ReadExcel(start = 19)
        private Double gradeEightMin;

        @ReadExcel(start = 20)
        private Double gradeEightMax;

        @ReadExcel(start = 21)
        private Double gradeNineMin;

        @ReadExcel(start = 22)
        private Double gradeNineMax;

        @ReadExcel(start = 23)
        private Double gradeTenMin;

        @ReadExcel(start = 24)
        private Double gradeTenMax;

        @ReadExcel(start = 25)
        private Double gradeElevenMin;

        @ReadExcel(start = 26)
        private Double gradeElevenMax;

        @ReadExcel(start = 27)
        private Double gradeTwelveMin;

        @ReadExcel(start = 28)
        private Double gradeTwelveMax;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getLevelCode() {
            return levelCode;
        }

        public void setLevelCode(String levelCode) {
            this.levelCode = levelCode;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public Double getScore() {
            return score;
        }

        public void setScore(Double score) {
            this.score = score;
        }

        public Double getGradeOneMax() {
            return gradeOneMax;
        }

        public void setGradeOneMax(Double gradeOneMax) {
            this.gradeOneMax = gradeOneMax;
        }

        public Double getGradeOneMin() {
            return gradeOneMin;
        }

        public void setGradeOneMin(Double gradeOneMin) {
            this.gradeOneMin = gradeOneMin;
        }

        public Double getGradeTwoMax() {
            return gradeTwoMax;
        }

        public void setGradeTwoMax(Double gradeTwoMax) {
            this.gradeTwoMax = gradeTwoMax;
        }

        public Double getGradeTwoMin() {
            return gradeTwoMin;
        }

        public void setGradeTwoMin(Double gradeTwoMin) {
            this.gradeTwoMin = gradeTwoMin;
        }

        public Double getGradeThreeMax() {
            return gradeThreeMax;
        }

        public void setGradeThreeMax(Double gradeThreeMax) {
            this.gradeThreeMax = gradeThreeMax;
        }

        public Double getGradethreeMin() {
            return gradethreeMin;
        }

        public void setGradethreeMin(Double gradethreeMin) {
            this.gradethreeMin = gradethreeMin;
        }

        public Double getGradeFourMax() {
            return gradeFourMax;
        }

        public void setGradeFourMax(Double gradeFourMax) {
            this.gradeFourMax = gradeFourMax;
        }

        public Double getGradeFourMin() {
            return gradeFourMin;
        }

        public void setGradeFourMin(Double gradeFourMin) {
            this.gradeFourMin = gradeFourMin;
        }

        public Double getGradeFiveMax() {
            return gradeFiveMax;
        }

        public void setGradeFiveMax(Double gradeFiveMax) {
            this.gradeFiveMax = gradeFiveMax;
        }

        public Double getGradeFiveMin() {
            return gradeFiveMin;
        }

        public void setGradeFiveMin(Double gradeFiveMin) {
            this.gradeFiveMin = gradeFiveMin;
        }

        public Double getGradeSixMax() {
            return gradeSixMax;
        }

        public void setGradeSixMax(Double gradeSixMax) {
            this.gradeSixMax = gradeSixMax;
        }

        public Double getGradeSixMin() {
            return gradeSixMin;
        }

        public void setGradeSixMin(Double gradeSixMin) {
            this.gradeSixMin = gradeSixMin;
        }

        public Double getGradeSevenMax() {
            return gradeSevenMax;
        }

        public void setGradeSevenMax(Double gradeSevenMax) {
            this.gradeSevenMax = gradeSevenMax;
        }

        public Double getGradeSevenMin() {
            return gradeSevenMin;
        }

        public void setGradeSevenMin(Double gradeSevenMin) {
            this.gradeSevenMin = gradeSevenMin;
        }

        public Double getGradeEightMax() {
            return gradeEightMax;
        }

        public void setGradeEightMax(Double gradeEightMax) {
            this.gradeEightMax = gradeEightMax;
        }

        public Double getGradeEightMin() {
            return gradeEightMin;
        }

        public void setGradeEightMin(Double gradeEightMin) {
            this.gradeEightMin = gradeEightMin;
        }

        public Double getGradeNineMax() {
            return gradeNineMax;
        }

        public void setGradeNineMax(Double gradeNineMax) {
            this.gradeNineMax = gradeNineMax;
        }

        public Double getGradeTenMin() {
            return gradeTenMin;
        }

        public void setGradeTenMin(Double gradeTenMin) {
            this.gradeTenMin = gradeTenMin;
        }

        public Double getGradeTenMax() {
            return gradeTenMax;
        }

        public void setGradeTenMax(Double gradeTenMax) {
            this.gradeTenMax = gradeTenMax;
        }

        public Double getGradeNineMin() {
            return gradeNineMin;
        }

        public void setGradeNineMin(Double gradeNineMin) {
            this.gradeNineMin = gradeNineMin;
        }

        public Double getGradeElevenMax() {
            return gradeElevenMax;
        }

        public void setGradeElevenMax(Double gradeElevenMax) {
            this.gradeElevenMax = gradeElevenMax;
        }

        public Double getGradeElevenMin() {
            return gradeElevenMin;
        }

        public void setGradeElevenMin(Double gradeElevenMin) {
            this.gradeElevenMin = gradeElevenMin;
        }

        public Double getGradeTwelveMax() {
            return gradeTwelveMax;
        }

        public void setGradeTwelveMax(Double gradeTwelveMax) {
            this.gradeTwelveMax = gradeTwelveMax;
        }

        public Double getGradeTwelveMin() {
            return gradeTwelveMin;
        }

        public void setGradeTwelveMin(Double gradeTwelveMin) {
            this.gradeTwelveMin = gradeTwelveMin;
        }
    }


    public static class PubPhysicalQuality {

        private String id;

        private String title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }


    //            if (null != item.getGradeOneMax() && null != item.getGradeOneMin()) {
//                if (item.getGradeOneMin() > item.getGradeOneMax()) {
//                    throw new RuntimeException("一年级最小值大于最大值,最小值为:" + item.getGradeOneMin() + ",最大值为:" + item.getGradeOneMax());
//                }
//                list.add(createPubPhysicalValue("G01", item.getLevelCode(), item.getGradeOneMax(), item.getGradeOneMin(),
//                        physicalQuality.getId(), item.getLevel(), item.getScore(), item.getSex()));
//            }
//            if (null != item.getGradeTwoMax() && null != item.getGradeTwoMin()) {
//                if (item.getGradeTwoMin() > item.getGradeTwoMax()) {
//                    throw new RuntimeException("二年级最小值大于最大值,最小值为:" + item.getGradeTwoMin() + ",最大值为:" + item.getGradeTwoMax());
//                }
//                list.add(createPubPhysicalValue("G02", item.getLevelCode(), item.getGradeTwoMax(), item.getGradeTwoMin(),
//                        physicalQuality.getId(), item.getLevel(), item.getScore(), item.getSex()));
//            }
//
//            if (null != item.getGradeThreeMax() && null != item.getGradethreeMin()) {
//                if (item.getGradethreeMin() > item.getGradeThreeMax()) {
//                    throw new RuntimeException("三年级最小值大于最大值,最小值为:" + item.getGradethreeMin() + ",最大值为:" + item.getGradeThreeMax());
//                }
//                list.add(createPubPhysicalValue("G03", item.getLevelCode(), item.getGradeThreeMax(), item.getGradethreeMin(),
//                        physicalQuality.getId(), item.getLevel(), item.getScore(), item.getSex()));
//            }
//            if (null != item.getGradeFourMax() && null != item.getGradeFourMin()) {
//                if (item.getGradeFourMin() > item.getGradeFourMax()) {
//                    throw new RuntimeException("四年级最小值大于最大值,最小值为:" + item.getGradeFourMin() + ",最大值为:" + item.getGradeFourMax());
//                }
//                list.add(createPubPhysicalValue("G04", item.getLevelCode(), item.getGradeFourMax(), item.getGradeFourMin(),
//                        physicalQuality.getId(), item.getLevel(), item.getScore(), item.getSex()));
//            }
//            if (null != item.getGradeFiveMax() && null != item.getGradeFiveMin()) {
//                if (item.getGradeFiveMin() > item.getGradeFiveMax()) {
//                    throw new RuntimeException("五年级最小值大于最大值,最小值为:" + item.getGradeFiveMin() + ",最大值为:" + item.getGradeFiveMax());
//                }
//                list.add(createPubPhysicalValue("G05", item.getLevelCode(), item.getGradeFiveMax(), item.getGradeFiveMin(),
//                        physicalQuality.getId(), item.getLevel(), item.getScore(), item.getSex()));
//            }
//            if (null != item.getGradeSixMax() && null != item.getGradeSixMin()) {
//                if (item.getGradeSixMin() > item.getGradeSixMax()) {
//                    throw new RuntimeException("六年级最小值大于最大值,最小值为:" + item.getGradeSixMin() + ",最大值为:" + item.getGradeSixMax());
//                }
//                list.add(createPubPhysicalValue("G06", item.getLevelCode(), item.getGradeSixMax(), item.getGradeSixMin(),
//                        physicalQuality.getId(), item.getLevel(), item.getScore(), item.getSex()));
//            }
//            if (null != item.getGradeSevenMax() && null != item.getGradeSevenMin()) {
//                if (item.getGradeSevenMin() > item.getGradeSevenMax()) {
//                    throw new RuntimeException("七年级最小值大于最大值,最小值为:" + item.getGradeSevenMin() + ",最大值为:" + item.getGradeSevenMax());
//                }
//                list.add(createPubPhysicalValue("G07", item.getLevelCode(), item.getGradeSevenMax(), item.getGradeSevenMin(),
//                        physicalQuality.getId(), item.getLevel(), item.getScore(), item.getSex()));
//            }
//            if (null != item.getGradeEightMax() && null != item.getGradeEightMin()) {
//                if (item.getGradeEightMin() > item.getGradeEightMax()) {
//                    throw new RuntimeException("八年级最小值大于最大值,最小值为:" + item.getGradeEightMin() + ",最大值为:" + item.getGradeEightMax());
//                }
//                list.add(createPubPhysicalValue("G08", item.getLevelCode(), item.getGradeEightMax(), item.getGradeEightMin(),
//                        physicalQuality.getId(), item.getLevel(), item.getScore(), item.getSex()));
//            }
//            if (null != item.getGradeNineMax() && null != item.getGradeNineMin()) {
//                if (item.getGradeNineMin() > item.getGradeNineMax()) {
//                    throw new RuntimeException("九年级最小值大于最大值,最小值为:" + item.getGradeNineMin() + ",最大值为:" + item.getGradeNineMax());
//                }
//                list.add(createPubPhysicalValue("G09", item.getLevelCode(), item.getGradeNineMax(), item.getGradeNineMin(),
//                        physicalQuality.getId(), item.getLevel(), item.getScore(), item.getSex()));
//            }
//            if (null != item.getGradeTenMax() && null != item.getGradeTenMin()) {
//                if (item.getGradeTenMin() > item.getGradeTenMax()) {
//                    throw new RuntimeException("十年级最小值大于最大值,最小值为:" + item.getGradeTenMin() + ",最大值为:" + item.getGradeTenMax());
//                }
//                list.add(createPubPhysicalValue("G10", item.getLevelCode(), item.getGradeTenMax(), item.getGradeTenMin(),
//                        physicalQuality.getId(), item.getLevel(), item.getScore(), item.getSex()));
//            }
//            if (null != item.getGradeElevenMax() && null != item.getGradeElevenMin()) {
//                if (item.getGradeElevenMin() > item.getGradeElevenMax()) {
//                    throw new RuntimeException("十一年级最小值大于最大值,最小值为:" + item.getGradeElevenMin() + ",最大值为:" + item.getGradeElevenMax());
//                }
//                list.add(createPubPhysicalValue("G11", item.getLevelCode(), item.getGradeElevenMax(), item.getGradeElevenMin(),
//                        physicalQuality.getId(), item.getLevel(), item.getScore(), item.getSex()));
//            }
//            if (null != item.getGradeTwelveMax() && null != item.getGradeTwelveMin()) {
//                if (item.getGradeTwelveMin() > item.getGradeTwelveMax()) {
//                    throw new RuntimeException("十二年级最小值大于最大值,最小值为:" + item.getGradeTwelveMin() + ",最大值为:" + item.getGradeTwelveMax());
//                }
//                list.add(createPubPhysicalValue("G12", item.getLevelCode(), item.getGradeTwelveMax(), item.getGradeTwelveMin(),
//                        physicalQuality.getId(), item.getLevel(), item.getScore(), item.getSex()));
//            }
}
