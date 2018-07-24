package com.mj.controller;


import org.apache.logging.log4j.util.PropertiesUtil;
import org.assertj.core.util.Maps;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

@RestController
@RequestMapping("/v1")
public class TestController {

    @RequestMapping("/hello")
    public String test() {
        return "hello";
    }

    public static void main(String[] args) {
        System.out.println(isOverduedHoliday(
                Timestamp.valueOf("2018-06-22 00:00:00.000"),
                false));
    }


    private static boolean isOverduedHoliday(Date oDate, boolean isHolidayOpen){
        if(!isHolidayOpen){
            String[] holidays = "2018-02-15,2018-02-21,2018-04-05,2018-04-06,2018-04-07,2018-04-29,2018-04-30,2018-05-01,2018-06-16,2018-06-17,2018-06-18,2018-09-22,2018-09-23,2018-09-24,2018-10-01,2018-10-02,2018-10-03,2018-10-04,2018-10-05,2018-10-06,2018-10-07".split(",");
            List<String> holidayList = Arrays.asList(holidays);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(oDate);
            int paraDate = calendar.get(Calendar.DAY_OF_YEAR);

            int minusDate = 0;
            Calendar cal = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            try {
                cal2.setTime(sdf.parse(holidays[2]));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            cal2.add(Calendar.DAY_OF_YEAR, 1);
            String formattedCurrentTime = sdf.format(cal.getTime());
            if(holidayList.contains(formattedCurrentTime)) {
                return false;
//                "test"
            }


            holidayList.sort(Comparator.comparing(String::new));
            Map<String, List<String>> groupHoliday = groupHoliday(holidayList);
            groupHoliday.entrySet().forEach(item -> {
                Optional<String> endOfHoliday = item.getValue().stream().max(Comparator.comparing(String::new));
                Optional<String> startOfHoliday = item.getValue().stream().min(Comparator.comparing(String::new));
                dealHolidayOverDue(startOfHoliday.get(),endOfHoliday.get(),LocalDate.now().toString(),2,calendar);
            });
            cal.add(Calendar.DAY_OF_YEAR, -2);
            return cal.getTimeInMillis() > calendar.getTimeInMillis();
        }

        return false;
    }

    private static Map<String, List<String>> groupHoliday(List<String> holidayList) {
        String firstHoliday = holidayList.get(0);
        Map<String,List<String>> groupHoliday = new HashMap<>();
        List<String> list = new ArrayList<>();
        String groupKey = holidayList.get(0);
        int count = 0;
        for(int i=0;i<holidayList.size();i++) {
            String holiday = holidayList.get(i);
            if(LocalDate.parse(holiday).getDayOfYear() - LocalDate.parse(firstHoliday).getDayOfYear() > i - count) {
                count = i;
                firstHoliday = holiday;
                groupKey = holiday;
                list = new ArrayList<>();
            }
            list.add(holiday);
            groupHoliday.put(groupKey,list);
        }
        return groupHoliday;
    }


    private static boolean dealHolidayOverDue(String startOfHoliday,String endOfHoliday,String currentDay,Integer overDayToSend,Calendar lastCheckTime) {
        int compareTo = LocalDate.parse(currentDay).compareTo(LocalDate.parse(endOfHoliday));
        if(compareTo > 0 && compareTo <= overDayToSend) {
            LocalDate beforeHoliday = LocalDate.parse(startOfHoliday).minus(overDayToSend + 1 - compareTo, ChronoUnit.DAYS);
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date(Timestamp.valueOf(beforeHoliday.atTime(LocalTime.MIN)).getTime()));
            return cal.getTimeInMillis() > lastCheckTime.getTimeInMillis();
        }
        return false;
    }

}
