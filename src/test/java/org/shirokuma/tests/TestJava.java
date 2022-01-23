package org.shirokuma.tests;

import java.util.*;
import java.util.function.Function;

public class TestJava {

    static int x = 0;

    public static void main(String[] args) {
        Function<Integer, Integer> squareLambda = x ->  x * x;
    }

}
