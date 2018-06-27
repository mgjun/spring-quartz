package com.mj.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


//        List<String> collect = Stream.of(args).filter(item -> !item.equals("test")).toArray(new IntFunction<String>() {
//            @Override
//            public String apply(int value) {
//                return args[value];
//            }
//        });

//        List<Developer> team = new ArrayList<>();
//        Developer polyglot = new Developer("esoteric");
//        polyglot.add("java");
//        polyglot.add("scala");
//        polyglot.add("groovy");
//        polyglot.add("go");
//
//        Developer busy = new Developer("pragmatic");
//        busy.add("java");
//        busy.add("javascript");
//
//        team.add(polyglot);
//        team.add(busy);
//
//        List<String> collect = team.stream()
//                .map(Developer::getLanguages)
//                .flatMap(Collection::stream)
//                .distinct()
//                .map(String::toUpperCase)
//                .collect(Collectors.toList());
//
//        collect.forEach(System.out::println);

        ReserveOrderReport build = ReserveOrderReport.builder()
                .groupTypeName("region")
                .skuName("冰逸")
                .reservationCount(1)
                .forceRedeemedCount(1)
                .forceUnreservedRedeemedCount(1)
                .normalRedeemedCount(1)
                .storeRedeemed(1)
                .sstTmallCount(1)
                .esstWechatCount(1)
                .esstB2bCount(1)
                .storeId("")
                .build();

        ReserveOrderReport build1 = ReserveOrderReport.builder()
                .groupTypeName("region1")
                .skuName("冰逸1")
                .reservationCount(2)
                .forceRedeemedCount(1)
                .forceUnreservedRedeemedCount(1)
                .normalRedeemedCount(1)
                .storeRedeemed(1)
                .sstTmallCount(1)
                .esstWechatCount(1)
                .esstB2bCount(1)
                .storeId("")
                .build();

        List<ReserveOrderReport> list = new ArrayList<>();
        list.add(build);
        list.add(build1);


        Integer collect = list.stream()
                .mapToInt(ReserveOrderReport::getReservationCount).sum();
        System.out.print(collect);


    }


    static class Developer {

        private String name;
        private Set<String> languages;

        public Developer(String name) {
            this.languages = new HashSet<>();
            this.name = name;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void add(String language) {
            this.languages.add(language);
        }

        public Set<String> getLanguages() {
            return languages;
        }
    }

}
