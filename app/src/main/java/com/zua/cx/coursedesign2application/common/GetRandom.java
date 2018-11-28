package com.zua.cx.coursedesign2application.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GetRandom {
    public List getRandom(int min,int max,int length) throws Exception{
        Set<Integer> random = null;
        if(max-min <= length){
            new Exception("length应小于最大值");
        }
        random = new HashSet<>();
        while(random.size()<length){
            Random oneRandom = new Random(System.currentTimeMillis());
            random.add(oneRandom.nextInt(max));
        }
        List<Integer> list = new ArrayList<>();
        for(int number : random){
            list.add(number+min);
        }
        return list;
    }
}
