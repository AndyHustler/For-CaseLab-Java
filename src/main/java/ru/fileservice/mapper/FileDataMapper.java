package ru.fileservice.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import ru.fileservice.model.FileData;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FileDataMapper implements RowMapper<FileData> {

    @SuppressWarnings("null")
    @Override
    public FileData mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new FileData(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("creation_date"),
                rs.getBytes("file")
        );
    }
}