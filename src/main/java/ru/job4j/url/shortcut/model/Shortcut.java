package ru.job4j.url.shortcut.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Shortcut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String url;
    private String code;
    private long callCounter;
    @ManyToOne()
    @JoinColumn(name = "account_id")
    private Account account;

    public Shortcut() {
    }
}
