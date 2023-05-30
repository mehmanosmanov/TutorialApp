package org.tutorial.app.repository.impl;

import org.springframework.stereotype.Repository;
import org.tutorial.app.model.Tutorial;

@Repository
public interface DemoRepository {
    void update(Tutorial book, Long id);

}
