package com.lks.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by likaisong on 2019/3/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-jdbcTemplate.xml")
public class TestJdbcTemplate {
    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testInsert(){
        jdbcTemplate.update("insert into account values (null,?,?)", "小红",1000);
    }

    @Test
    public void testSelect(){
        Map map = jdbcTemplate.queryForMap("select * from account where name =?" , "小红");
        System.out.println(map.toString());
    }

    public void testUpdate(){
        jdbcTemplate.update("UPDATE account SET NAME =? where name =?", "小小", "小红");
    }

    @Test
    public void testDel(){
        jdbcTemplate.update("delete from account where name = ?", "小小");
    }

}
