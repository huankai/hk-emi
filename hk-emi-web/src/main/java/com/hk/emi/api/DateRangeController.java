package com.hk.emi.api;

import com.hk.commons.util.*;
import com.hk.commons.util.date.DatePattern;
import com.hk.commons.util.date.DateTimeIntervalUtils;
import com.hk.commons.util.date.DateTimeUtils;
import com.hk.core.query.jdbc.DateRangeCondition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: huangkai
 * @date 2018-05-08 14:03
 */
@RestController
@RequestMapping("api/date")
public class DateRangeController {

    /**
     * 查询日期
     *
     * @param ingores ingores
     * @return result
     */
    @RequestMapping("range")
    public String rangeList(String... ingores) {
        List<EnumDisplayUtils.EnumItem> enumItems = EnumDisplayUtils.getEnumItems(DateRangeCondition.DateRange.class);
        if (ArrayUtils.isNotEmpty(ingores)) {
            enumItems = enumItems.stream().filter(item -> !StringUtils.equalsAny(item.getValue().toString(), ingores))
                    .sorted(Comparator.comparingInt(EnumDisplayUtils.EnumItem::getOrder))
                    .collect(Collectors.toList());
        }
        return JsonUtils.toJSONString(enumItems);
    }

    /**
     * 查询某年所有月份
     *
     * @param year    指定年,默认当前年
     * @param pattern 日期指定的输出格式
     * @param ingores 要忽略的月份
     * @return result
     */
    @RequestMapping("year/month")
    public String yearMonthList(Integer year, @RequestParam(defaultValue = "yyyy-MM-dd HH:mm:ss") String pattern, Integer... ingores) {
        if (null == year) {
            year = Year.now().getValue();
        }
        List<DateTimeIntervalUtils.IntervalDate> yearMonthList = DateTimeIntervalUtils.getMonthListByYear(year);
        return JsonUtils.toJSONString(ingoreAndOrder(yearMonthList), DatePattern.parse(pattern));
    }

    /**
     * 查询指定年的所有周
     *
     * @param year    指定年份
     * @param pattern 日期指定的输出格式
     * @param ingores 要忽略的周
     * @return
     */
    @RequestMapping("year/week")
    public String yearWeekList(Integer year, @RequestParam(defaultValue = "yyyy-MM-dd HH:mm:ss") String pattern, Integer... ingores) {
        if (null == year) {
            year = Year.now().getValue();
        }
        List<DateTimeIntervalUtils.IntervalDate> yearMonthList = DateTimeIntervalUtils.getAllWeekList(DateTimeUtils.getYearMinDay(year), DateTimeUtils.getYearMaxDay(year));
        return JsonUtils.toJSONString(ingoreAndOrder(yearMonthList), DatePattern.parse(pattern));
    }

    /**
     * 忽略并排序
     *
     * @param dateList dateList
     * @param ingores  要忽略的时间
     * @return dateList
     */
    private List<DateTimeIntervalUtils.IntervalDate> ingoreAndOrder(List<DateTimeIntervalUtils.IntervalDate> dateList, Integer... ingores) {
        if (ArrayUtils.isNotEmpty(ingores)) {
            dateList = dateList.stream().filter(item -> !NumberUtils.equalsAny(item.getIndex(), ingores))
                    .sorted(Comparator.comparingInt(DateTimeIntervalUtils.IntervalDate::getIndex))
                    .collect(Collectors.toList());
        }
        return dateList;
    }

    /**
     * 两个时间段的所有周
     *
     * @param start   sart
     * @param end     end
     * @param pattern pattern
     * @return result
     */
    @RequestMapping("week")
    public String weekList(@RequestParam LocalDate start, @RequestParam LocalDate end, @RequestParam(defaultValue = "yyyy-MM-dd HH:mm:ss") String pattern) {
        List<DateTimeIntervalUtils.IntervalDate> yearMonthList = DateTimeIntervalUtils.getAllWeekList(start.atTime(LocalTime.MIN), end.atTime(LocalTime.MAX));
        return JsonUtils.toJSONString(yearMonthList, DatePattern.parse(pattern));
    }
}
