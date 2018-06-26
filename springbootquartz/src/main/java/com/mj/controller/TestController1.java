package com.mj.controller;


import org.assertj.core.util.Lists;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/v1")
public class TestController1 {

    @RequestMapping("/hello")
    public String test() {
        return "hello";
    }

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        for(int i=0;i<30;i++) {
            list.add(i);
        }

        List<Integer> cancelItems = new ArrayList<>();
        int size = list.size();
        for (int start = 0; start < size; start += 20) {
            int end = Math.min(start + 20, size);
            List<Integer> subList = list.subList(start,end);
            for(int j=0;j<subList.size() ;j++) {
                if(subList.get(j) % 5 == 0) {
                    subList.remove(subList.get(j));
                }
            }
            cancelItems.addAll(subList);
        }


    }

    private static List<Integer> copySubList(List<Integer> tblReserveOrderItems, int start, int end) {
        return Lists.newArrayList(tblReserveOrderItems.subList(start, end));
    }




}
