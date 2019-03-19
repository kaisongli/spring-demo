package com.lks.util;

import com.lks.aspectJ.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by likaisong on 2019/3/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-aspectJ.xml")
public class TestAspectJ {

    @Autowired
    private UserService userService;


    @Test
    public void testAspectJByXml() {
        userService.query("select * from tableName");
    }

    @Test
    public void testAspectJByAnno() {
        userService.query("select * from tableName");
    }
}
