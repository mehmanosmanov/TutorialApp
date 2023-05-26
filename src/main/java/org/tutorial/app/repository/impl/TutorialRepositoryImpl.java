package org.tutorial.app.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.tutorial.app.model.Tutorial;
import org.tutorial.app.repository.TutorialRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TutorialRepositoryImpl implements TutorialRepository {
    private final JdbcTemplate jdbcTemplate;


    @Override
    public int save(Tutorial book) {
        return jdbcTemplate.update("INSERT INTO public.tutorials_list(title,name,subject,published) values (?,?,?,?)",
                book.getTitle(), book.getName(), book.getSubject(), book.getPublished());
    }

    @Override
    public int update(Tutorial book, Long id) {
        return jdbcTemplate.update("UPDATE tutorials_list  set title=?,name=?,subject=?,published=? WHERE id=?",
                book.getTitle(), book.getName(), book.getSubject(), book.getPublished(), id);

    }

    @Override
    public Tutorial findById(Long id) throws RuntimeException {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM tutorials_list where id=?",
                    BeanPropertyRowMapper.newInstance(Tutorial.class), id);
        } catch (RuntimeException ex) {
            return null;
        }
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM tutorials_list where id=?", id);
    }

    @Override
    public List<Tutorial> findAll() {
        return jdbcTemplate.query("SELECT * FROM tutorials_list", BeanPropertyRowMapper.newInstance(Tutorial.class));
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("TRUNCATE TABLE tutorials_list");

    }

    @Override
    public List<Tutorial> findByPublished(boolean published) {
        return jdbcTemplate.query("SELECT * FROM tutorials_list WHERE published=? ",
                BeanPropertyRowMapper.newInstance(Tutorial.class), published);
    }

    @Override
    public List<Tutorial> findByTitleContaining(String title) {
        return jdbcTemplate.query("SELECT * FROM tutorials_list WHERE title=?"
                , BeanPropertyRowMapper.newInstance(Tutorial.class), title);
    }
}
