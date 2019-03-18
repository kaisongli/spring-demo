package com.lks.util;

import com.lks.proxy.MySqlSession;
import com.lks.proxy.SqlSessionFactory;
import com.lks.proxy.SqlSessionFactoryByCGLib;
import com.lks.proxy.UserImpl;
import org.testng.annotations.Test;

/**
 * Created by likaisong on 2019/3/18.
 */
public class TestProxy {
    @Test
    public void testMySqlSession() throws InstantiationException, IllegalAccessException {
        MySqlSession session = SqlSessionFactory.getSqlSession(new UserImpl());
        session.update("UPDATE Person SET FirstName = 'Fred' WHERE LastName = 'Wilson'  ");
    }

    @Test
    public void testMySqlSessionByCGLib() throws InstantiationException, IllegalAccessException {
        MySqlSession session = SqlSessionFactoryByCGLib.getSqlSessionByCGLib(new UserImpl());
        session.update("UPDATE Person SET FirstName = 'Fred' WHERE LastName = 'Wilson'  ");
    }
}
