package com.cwq.core.test;

import org.junit.Test;

public class PriceTest {
    
    @Test
    public void priceTest(){
        String sort="price asc";
        System.out.println(sort.split(" ")[0]+","+sort.split(" ")[1]);
    }
}
