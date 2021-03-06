package ru.job4j.url.shortcut.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String site;
    private String password;

    public Account() {

    }

    public static Account of(String login, String password) {
        Account account = new Account();
        account.setSite(login);
        account.setPassword(password);
        return account;
    }
}
