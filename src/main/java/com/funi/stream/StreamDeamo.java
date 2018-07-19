package com.funi.stream;

import java.util.Arrays;
import java.util.List;

public class StreamDeamo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        list.stream().filter(v -> (v & 1) == 1).forEach(v -> System.out.println(v));
    }
}
