package com.cydeo.Day11;

import org.junit.jupiter.api.*;

import java.security.PublicKey;

public class TestLifeCycleAnnotations {

    @BeforeAll
    public static void init(){
        System.out.println("Before all is running");
    }

    @BeforeEach
    public void initEach(){
        System.out.println("\tBefore each is running");
    }

    @AfterEach
    public void tearDownEach(){
        System.out.println("\tAfter each is running");
    }

    @Test
    public void test1(){
        System.out.println("Test 1 is running");
    }

    @Test
    public void test2(){
        System.out.println("Test 2 is running");
    }

    @AfterAll
    public static void tearDown(){
        System.out.println("After all is running");
    }
}
