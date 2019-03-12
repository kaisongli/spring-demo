package com.lks.util;

import com.lks.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by likaisong on 2019/3/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-transfer-anno.xml")
public class AccountServiceAnnoTest {

    @Autowired
    private AccountService service;

    @Test
    public void testTransfer() {
        service.transfer("husband", "wife", 100);
    }

    @Test
    public void testTransferAnno() {
        service.transfer("husband", "wife", 100);
    }

}
