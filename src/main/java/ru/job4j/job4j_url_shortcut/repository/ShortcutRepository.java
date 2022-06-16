package ru.job4j.job4j_url_shortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.job4j_url_shortcut.model.Shortcut;

import java.util.List;
import java.util.Optional;

public interface ShortcutRepository extends CrudRepository<Shortcut, Long> {
    Optional<Shortcut> findByCode(String code);

    List<Shortcut> findAllByAccountId(long id);

    @Modifying
    @Query("update Shortcut s set s.callCounter = s.callCounter + 1 where s.id = :id")
    void updateCallCounter(@Param("id") long id);
}
