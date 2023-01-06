package ru.job4j.url.shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.url.shortcut.model.Account;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findBySite(String site);
}
