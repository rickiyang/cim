package com.rim.rimserver;

/**
 * @author rickiyang
 * @date 2020-07-19
 * @Desc TODO
 */
@FunctionalInterface
public interface TestDoSomething<T,M> {

    M doSomething(T t);

}
