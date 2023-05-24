package contentcalendar.repository;

import contentcalendar.model.Content;
import contentcalendar.model.Status;
import contentcalendar.model.Type;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ContentJdbcTemplateRepository {
    private final JdbcTemplate jdbcTemplate;

    public ContentJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static Content mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Content(rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                Status.valueOf(rs.getString("status")),
                Type.valueOf(rs.getString("content_type")),
                rs.getObject("date_created", LocalDateTime.class),
                rs.getObject("date_updated", LocalDateTime.class),
                rs.getString("url"));
    }

    public List<Content> getAllContent() {
        String sql = "SELECT * FROM content";
        List<Content> contents = jdbcTemplate.query(sql, ContentJdbcTemplateRepository::mapRow);
        return contents;
    }

    public Content getContent(Integer id) {
        String sql = "SELECT * FROM content WHERE id = ?";
        Content content = jdbcTemplate.queryForObject(sql, ContentJdbcTemplateRepository::mapRow, id);
        return content;
    }

    public void createContent(Content content) {
        String sql = "INSERT INTO content (title, description, status, content_type, date_created, date_updated, url) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, content.title(), content.description(), content.status().toString(), content.contentType().toString(), content.dateCreated(), content.dateUpdated(), content.url());
    }

    public void updateContent(Content content, Integer id) {
        String sql = "UPDATE content SET title = ?, description = ?, status = ?, content_type = ?, date_created = ?, date_updated = ?, url = ? WHERE id = ?";
        jdbcTemplate.update(sql, content.title(), content.description(), content.status().toString(), content.contentType().toString(), content.dateCreated(), content.dateUpdated(), content.url(), id);
    }

    public void deleteContent(Integer id) {
        String sql = "DELETE FROM content WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }


    public boolean existsById(Integer id) {
        String sql = "SELECT COUNT(*) FROM content WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

}
