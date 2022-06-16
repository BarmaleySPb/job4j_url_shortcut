package ru.job4j.job4j_url_shortcut.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.job4j_url_shortcut.model.Account;
import ru.job4j.job4j_url_shortcut.repository.AccountRepository;

import java.util.Optional;

@Service
@Transactional
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Iterable<Account> findAll() {
        return accountRepository.findAll();
    }

    public Optional<Account> findById(long id) {
        return accountRepository.findById(id);
    }

    public Account findBySite(String site) {
        return accountRepository.findBySite(site);
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public void delete(Account account) {
        accountRepository.delete(account);
    }
}
