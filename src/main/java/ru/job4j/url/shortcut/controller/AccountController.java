package ru.job4j.url.shortcut.controller;

import jakarta.validation.Valid;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.url.shortcut.model.Account;
import ru.job4j.url.shortcut.model.Shortcut;
import ru.job4j.url.shortcut.model.dto.ShortcutDTO;
import ru.job4j.url.shortcut.model.dto.StatisticsDTO;
import ru.job4j.url.shortcut.service.AccountService;
import ru.job4j.url.shortcut.service.ShortcutService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class AccountController {

    private final AccountService accountService;


    private final ShortcutService shortcutService;

    public AccountController(AccountService accountService, ShortcutService shortcutService) {
        this.accountService = accountService;
        this.shortcutService = shortcutService;
    }

    @PostMapping("/convert")
    public ResponseEntity<Map<String, String>> convert(@Valid @RequestBody ShortcutDTO shortcutDTO) {
        Shortcut shortcut = new Shortcut();
        String site = SecurityContextHolder.getContext().getAuthentication().getName();
        if (shortcutDTO.getUrl().contains(site)) {
            shortcut.setUrl(shortcutDTO.getUrl());
            shortcut.setCode(RandomStringUtils.randomAlphanumeric(8));
            shortcut.setAccount(accountService.findBySite(site).orElseThrow(
                    () -> new UsernameNotFoundException("Account not found")));
            shortcutService.save(shortcut);
        } else {
            throw new IllegalArgumentException("Please check domain name");
        }
    return new ResponseEntity<>(Map.of("code", shortcut.getCode()), HttpStatus.OK);
    }

    @GetMapping("/redirect/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code) throws URISyntaxException {
        Shortcut shortcut = shortcutService.findByCode(code).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Url with code: " + code + " not found.")
        );
        return ResponseEntity.status(HttpStatus.FOUND).location(new URI(shortcut.getUrl())).build();
    }

    @GetMapping("/statistics")
    public ResponseEntity<List<StatisticsDTO>> statistics() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account site = accountService.findBySite(authentication.getName()).orElseThrow(
                () -> new UsernameNotFoundException("Account not found")
        );
        return new ResponseEntity<>(
                shortcutService.findAllByAccountId(site.getId()),
                HttpStatus.OK
        );
    }

}
