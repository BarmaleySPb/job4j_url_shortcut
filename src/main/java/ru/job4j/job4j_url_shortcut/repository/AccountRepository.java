package ru.job4j.job4j_url_shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.job4j_url_shortcut.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findBySite(String site);
}
