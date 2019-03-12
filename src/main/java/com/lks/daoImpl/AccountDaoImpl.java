package com.lks.daoImpl;

import com.lks.dao.AccountDao;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by likaisong on 2019/3/12.
 */
@Repository
public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao{
    @Override
    public void update(String name, double money) {
        Object[] args = {money, name};
        this.getJdbcTemplate().update("UPDATE account SET money = ? WHERE name = ? ", args);
    }

    @Override
    public double queryMoney(String name) {
        Double money = this.getJdbcTemplate().queryForObject("SELECT money FROM account WHERE name = ?", new RowMapper<Double>() {
            @Nullable
            @Override
            public Double mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getDouble("money");
            }
        }, name);
        return money;
    }
}
