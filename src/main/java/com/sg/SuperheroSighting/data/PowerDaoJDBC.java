/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSighting.data;

import com.sg.SuperheroSighting.mapper.PowerMapper;
import com.sg.SuperheroSighting.model.Power;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matthewswanberg
 */
@Repository
public class PowerDaoJDBC implements PowerDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    @Transactional
    public Power addPower(Power aPower) {
        final String INSERT_POWER = "INSERT INTO powers(`name`, `description`) "
                + "values(?, ?)";
        jdbc.update(INSERT_POWER,
                aPower.getName(),
                aPower.getDescription());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        aPower.setId(newId);
        return aPower;
    }

    @Override
    public Power getPower(int id) {
        try {
            final String SELECT_POWER_BY_ID = "select * from powers where id=?";
            return jdbc.queryForObject(SELECT_POWER_BY_ID, new PowerMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Power> getAllPowers() {
        final String SELECT_ALL_POWERS = "SELECT * FROM powers";
        return jdbc.query(SELECT_ALL_POWERS, new PowerMapper());
    }

    @Override
    @Transactional
    public void editPower(Power changedPower) {
        final String UPDATE_POWER = "UPDATE powers SET `name` = ?, `description` = ? "
                + "WHERE id = ?";
        jdbc.update(UPDATE_POWER,
                changedPower.getName(),
                changedPower.getDescription(),
                changedPower.getId());
    }

    @Override
    @Transactional
    public void deletePower(int id) {
        final String DELETE_POWER_HEROPOWERS = "delete from heroSuperpowers where powerId = ?";
        jdbc.update(DELETE_POWER_HEROPOWERS, id);
        
        final String DELETE_POWER = "DELETE FROM powers WHERE id = ?";
        jdbc.update(DELETE_POWER, id);
        
    }

}
