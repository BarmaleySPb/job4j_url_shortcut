package ru.job4j.url.shortcut.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.url.shortcut.model.Account;
import ru.job4j.url.shortcut.repository.AccountRepository;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AccountRepository users;

    public UserDetailsServiceImpl(AccountRepository users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Account account = users.findBySite(login);
        if (account == null) {
            throw new UsernameNotFoundException(login);
        }
        return new User(account.getSite(), account.getPassword(), emptyList());
    }
}