package org.tutorial.app.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.tutorial.app.model.Tutorial;

@Repository
@RequiredArgsConstructor
public class TutorialRepositoryImpl implements DemoRepository {
    private final JdbcTemplate jdbcTemplate;
//    private final TutorialRepository tutorialRepository;

    @Override
    public void update(Tutorial book, Long id) {
        jdbcTemplate.update("UPDATE tutorials_list  set title=?,name=?,subject=?,published=? WHERE id=?",
                book.getTitle(), book.getName(), book.getSubject(), book.getPublished(), id);
    }
}
