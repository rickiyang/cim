package com.rim.rimserver;

import org.junit.platform.commons.util.StringUtils;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author rickiyang
 * @date 2020-07-19
 * @Desc TODO
 */
public class MainTest {

    public static void main(String[] args) {

        TestDoSomething<String, String> t = td -> {
            if (StringUtils.isBlank(td)) {
                return 10 + "";
            } else {
                return 1 + "";
            }
        };
        t.doSomething("1");
    }
    int v = 1;
    Function<Integer, Integer> fun = (v) -> {
        if(v > 100) {
            return 10;
        }     else {
            return 100;
        }
    };;


}
