package ru.fileservice.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import ru.fileservice.mapper.FileDataMapper;
import ru.fileservice.model.FileData;
import ru.fileservice.model.ReturnId;

import java.util.Optional;
/*int id,
        String title,
        String description,
        Date creation_date,
        byte[] file */
@Repository
public class FileDataRepositoryImpl implements FileDataRepository {

    private static final String SQL_GET_FILEDATA_BY_ID =
            "select id, title, description, creation_date, file from files where id = :id";

    private static final String SQL_INSERT_FILEDATA =
            "insert into files (title, description, creation_date, file) values (:fileTitle, :fileDescription, :creationDate, :fileData) returning id";

    private static final String SQL_UPDATE_FILEDATA =
            "update files set title = :fileTitle, description = :fileDescription, creation_date = :creationDate, file = :file where id = :id";

    private static final String SQL_DELETE_FILEDATA = "delete from files where id = :id";

    private final FileDataMapper fileDataMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public FileDataRepositoryImpl(
            FileDataMapper fileDataMapper,
            NamedParameterJdbcTemplate jdbcTemplate
    ) {
        this.fileDataMapper = fileDataMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<FileData> getFileDataById(int id) {
        var params = new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbcTemplate.query(
                        SQL_GET_FILEDATA_BY_ID,
                        params,
                        fileDataMapper
                ).stream()
                .findFirst();
    }

    @SuppressWarnings("null")
    @Override
    public ReturnId insertFileData(String title, String description, String creation_date, byte[] file) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        var params = new MapSqlParameterSource();
        params.addValue("fileTitle", title);
        params.addValue("fileDescription", description);
        params.addValue("creationDate", creation_date);
        params.addValue("fileData", file);
        jdbcTemplate.update(SQL_INSERT_FILEDATA, params, generatedKeyHolder);
        return new ReturnId(generatedKeyHolder.getKey().intValue());
    }

    @Override
    public void updateFileData(String title, String description, String creation_date, byte[] file, int id) {
        var params = new MapSqlParameterSource();
        params.addValue("fileTitle", title);
        params.addValue("fileDescription", description);
        params.addValue("creationDate", creation_date);
        params.addValue("fileData", file);
        params.addValue("id", id);
        jdbcTemplate.update(SQL_UPDATE_FILEDATA, params);
    }

    @Override
    public void deleteFileDataById(int id) {
        var params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbcTemplate.update(SQL_DELETE_FILEDATA, params);
    }
}
