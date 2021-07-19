package com.yqz.yqzdemojdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@SpringBootTest
class YqzdemojdbcApplicationTests {

    @Autowired
    private DataSource dataSource;
    @Test
    public void test1(){
        System.out.println(dataSource.getClass());
    }
}
