package com.dhl.virtual;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FeaturePrepUtil {
    private static FeaturePrepUtil instance;
    List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12);

    public static FeaturePrepUtil getInstance() {
        if(instance == null){
            instance = new FeaturePrepUtil();
        }
        return instance;
    }

    void findLongestString(){
        List<String> words = Arrays.asList("apple", "banana", "kiwi", "orange", "fig");
        String longest = words.stream()
                .max(Comparator.comparingInt(String::length))
                .orElse("");
        System.out.println(longest);
    }

    void findAvgOfGivenNumber(){
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12);
        double average = numbers.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        System.out.println(average);
    }

    void findSumOfSquareOfEven(){
        Integer sum = numbers.stream()
                .filter( n -> n % 2== 0)
                .mapToInt( n -> n*2)
                .sum();

        System.out.println(sum);
    }
}
