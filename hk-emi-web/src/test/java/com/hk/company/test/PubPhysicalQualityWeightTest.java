package com.hk.company.test;

import com.hk.emi.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: huangkai
 * @date 2018-04-20 10:44
 */
public class PubPhysicalQualityWeightTest extends BaseTest {

    @Autowired
    private PubPhysicalQualityWeightRepository physicalQualityWeightRepository;

//    @Autowired
//    private JdbcSession jdbcSession;

    @Autowired
    private EtStuPhysicalWeightRepository stuPhysicalWeightRepository;

    /**
     * 一键更新学生总权重
     */
    @Test
    public void updteStudentTotalWeight() {
//        SelectArguments arguments = new SelectArguments();
//        arguments.setFrom("pub_physical_quality_weight");
//        List<PubPhysicalQualityWeight> qualityWeightList = jdbcSession.queryForList(arguments, false, PubPhysicalQualityWeight.class).getResult();
//
//        arguments = new SelectArguments();
//        arguments.setFrom("scm_grade");
//        arguments.setFields("grade_id,grade_type");
//        List<Map<String, Object>> gradeList = jdbcSession.queryForList(arguments, false).getResult();
//
//        arguments = new SelectArguments();
//        arguments.setFrom("pub_physical_quality_weight");
//        List<PubPhysicalQualityWeight> hysicalQualityWeightList = jdbcSession.queryForList(arguments, false, PubPhysicalQualityWeight.class).getResult();
//
//        arguments = new SelectArguments();
//        arguments.setFrom("et_stu_physical_weight");
//        List<EtStuPhysicalWeight> stuPhysicalWeight = jdbcSession.queryForList(arguments, false, EtStuPhysicalWeight.class).getResult();
//
//        arguments = new SelectArguments();
//        arguments.setFrom("et_stu_physical");
//        List<Map<String, Object>> stuPhysicalList = jdbcSession.queryForList(arguments, false).getResult();
//
//        Map<Object, List<Map<String, Object>>> objectListMap = stuPhysicalList.stream().collect(Collectors.groupingBy(map -> map.get("student_id")));
//        objectListMap.forEach((Object studentId, List<Map<String, Object>> value) -> {
//            Map<Object, List<Map<String, Object>>> studentSemesterMap = value.stream().collect(Collectors.groupingBy(map -> map.get("semester_id")));
//            for (Map.Entry<Object, List<Map<String, Object>>> entry : studentSemesterMap.entrySet()) {
//                String semesterId = entry.getKey().toString();
//                double totalScore = 0D;
//                for (Map<String, Object> objectMap : entry.getValue()) {
//                    Optional<Map<String, Object>> mapOptional = gradeList.stream().filter(gradeMap -> StringUtils.equals(gradeMap.get("grade_id").toString(), objectMap.get("grade_id").toString())).findFirst();
//                    if (mapOptional.isPresent()) {
//                        String gradeType = mapOptional.get().get("grade_type").toString();
//                        Optional<PubPhysicalQualityWeight> pubPhysicalQualityWeightOptional = hysicalQualityWeightList.stream().filter(weightItem -> StringUtils.equals(gradeType, weightItem.getGrade())
//                                && StringUtils.equals(weightItem.getPhysicalQualityId(), objectMap.get("physical_quality_id").toString())).findFirst();
//                        if (pubPhysicalQualityWeightOptional.isPresent()) {
//                            Integer weight = pubPhysicalQualityWeightOptional.get().getWeight();
//                            totalScore += (double) objectMap.get("score") * weight / 100;
//                        } else {
//                            System.err.println("gradeType :" + gradeType + ",physical_quality_id :" + objectMap.get("physical_quality_id").toString() + "不存在");
//                        }
//                    } else {
//                        System.err.println("grade_id is null :" + objectMap.get("grade_id"));
//                    }//et_stu_totalscore
//                }
//                Optional<EtStuPhysicalWeight> stuPhysicalWeightOptional = stuPhysicalWeight.stream().filter(item -> StringUtils.equals(item.getSemesterId(), semesterId)
//                        && StringUtils.equals(item.getStudentId(), studentId.toString())).findFirst();
//                if (stuPhysicalWeightOptional.isPresent()) {
//                    if (totalScore > 100) {
//                        System.err.println("studentId :" + studentId + ",semesterId : " + semesterId + ",合计总分大于100，值为: " + totalScore);
//                    } else {
//                        EtStuPhysicalWeight physicalWeight = stuPhysicalWeightOptional.get();
//                        physicalWeight.setScore(Double.valueOf(NumberUtils.formatDecimal(totalScore)));
//                        stuPhysicalWeightRepository.save(physicalWeight);
//                    }
//                } else {
//                    System.err.println("studentId :" + studentId + ",semesterId : " + semesterId + ",不存在 ");
//                }
//            }
//        });
    }


    /**
     * 解析Excel 导入权重参数
     */
    @Test
    public void parseExcelTest() {
//        ReadParam<PubPhysicalQualityWeight> param = ReadParam.<PubPhysicalQualityWeight>builder()
//                .beanClazz(PubPhysicalQualityWeight.class)
//                .build();
//
//        ReadableExcel<PubPhysicalQualityWeight> readableExcel = new SimpleDomReadExcel<>(param);
//        ReadResult<PubPhysicalQualityWeight> result = readableExcel.read(new File("C:\\Users\\sjq-278\\Desktop/单项指标与权重.xlsx"));
//
//        SelectArguments arguments = new SelectArguments();
//        arguments.setFrom("pub_physical_quality");
//        arguments.setFields("physical_quality_id,quality_title");
//        List<Map<String, Object>> qualityList = jdbcSession.queryForList(arguments, false).getResult();
//        result.getAllSheetData().forEach(item -> {
//            Optional<Map<String, Object>> objectMap = qualityList.stream().filter(quality -> StringUtils.equals((String) quality.get("quality_title"), item.getPhysicalTitle())).findFirst();
//            if (objectMap.isPresent()) {
//                item.setPhysicalQualityId(objectMap.get().get("physical_quality_id").toString());
//                item.setCreateTime(new Date());
//                item.setLastUpTime(new Date());
//            } else {
//                System.err.println("objectMap is empty :" + item.getPhysicalTitle());
//            }
//        });
//        System.out.println(JsonUtils.toJSONString(result.getAllSheetData()));
//        physicalQualityWeightRepository.save(result.getAllSheetData());
    }


    @Test
    public void toStuTotalScoreTest() {
//        SelectArguments arguments = new SelectArguments();
//        arguments.setFrom("pub_physical_quality_weight");
//        arguments.setFields("*");
//        List<PubPhysicalQualityWeight> physicalQualityWeight = jdbcSession.queryForList(arguments, false, PubPhysicalQualityWeight.class).getResult();
//
//
//        arguments = new SelectArguments();
//        arguments.setFrom("et_stu_physical");
//        arguments.setFields("*");
//        List<Map<String, Object>> qualityList = jdbcSession.queryForList(arguments, false).getResult();
//        List<String> studentIdList = Lists.newArrayList();
//        Map<Object, List<Map<String, Object>>> objectListMap = qualityList.stream().collect(Collectors.groupingBy(map -> map.get("student_id")));
//        List<EtStuPhysicalWeight> list = Lists.newArrayList();
//        for (Map.Entry<Object, List<Map<String, Object>>> entry : objectListMap.entrySet()) {
//            String studentId = (String) entry.getKey();
//            studentIdList.add(studentId);
//            List<Map<String, Object>> studentPhysical = entry.getValue();
//            Map<Object, List<Map<String, Object>>> semesterListMap = studentPhysical.stream().collect(Collectors.groupingBy(map -> map.get("semester_id")));
//            for (List<Map<String, Object>> values : semesterListMap.values()) {
//                String stuName = null, schoolYearId = null, schoolyearName = null, semesterId = null, semesterName = null, gradeId = null, gradeName = null, classId = null, className = null;
//                Double value = 0D;
//                for (Map<String, Object> map : values) {
//                    stuName = (String) map.get("stu_name");
//                    schoolYearId = (String) map.get("schoolyear_id");
//                    schoolyearName = (String) map.get("schoolyear_name");
//                    semesterId = (String) map.get("semester_id");
//                    semesterName = (String) map.get("semester_name");
//                    gradeId = (String) map.get("grade_id");
//                    gradeName = (String) map.get("grade_name");
//                    String gradeName_ = gradeName;
//                    classId = (String) map.get("class_id");
//                    className = (String) map.get("class_name");
//                    double score = (double) map.get("score");
//                    String physicalQualityId = (String) map.get("physical_quality_id");
//                    Optional<PubPhysicalQualityWeight> weightOptional = physicalQualityWeight.stream().filter(item -> StringUtils.equals(item.getPhysicalQualityId(), physicalQualityId) && StringUtils.equals(item.getGradeName(), gradeName_)).findFirst();
//                    if (weightOptional.isPresent()) {
//                        score = score * weightOptional.get().getWeight() / 100;
//                    }
//                    value = value + score;
//                }
//                EtStuPhysicalWeight weight = new EtStuPhysicalWeight();
//                weight.setStudentId(studentId);
//                weight.setStuName(stuName);
//                weight.setSchoolyearId(schoolYearId);
//                weight.setSchoolyearName(schoolyearName);
//                weight.setSemesterId(semesterId);
//                weight.setSemesterName(semesterName);
//                weight.setGradeId(gradeId);
//                weight.setGradeName(gradeName);
//                weight.setClassId(classId);
//                weight.setClassName(className);
//                weight.setScore(value);
//                list.add(weight);
//            }
//        }
//        System.out.println("size -->" + list.size());
//        System.out.println(JsonUtils.toJSONString(list));
//        System.out.println(JsonUtils.toJSONString(studentIdList));
//        stuPhysicalWeightRepository.save(list);
    }

}
