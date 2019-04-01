/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.SuperheroSighting.mapper;

import com.sg.SuperheroSighting.model.Sighting;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author matthewswanberg
 */
public final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException {
            Sighting aSighting = new Sighting();
            aSighting.setId(rs.getInt("id"));
            aSighting.setDate(LocalDate.parse(
                   rs.getString("sightingDate"),
                   DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            aSighting.setDescription(rs.getString("description"));
            return aSighting;
        }
    }
