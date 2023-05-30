package org.tutorial.app.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.tutorial.app.model.Tutorial;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TutorialMapper implements RowMapper<Tutorial> {

    @Override
    public Tutorial mapRow(ResultSet rs, int rowNum) throws SQLException {

        Tutorial tutorial = Tutorial.builder()
                .name(rs.getString("name"))
                .title(rs.getString("title"))
                .published(rs.getBoolean("published"))
                .id(rs.getLong("id"))
                .subject(rs.getString("subject")).build();
        return tutorial;
    }
}
