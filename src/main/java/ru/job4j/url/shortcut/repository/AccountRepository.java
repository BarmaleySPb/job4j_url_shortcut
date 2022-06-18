package ru.job4j.url.shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.url.shortcut.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findBySite(String site);
}
