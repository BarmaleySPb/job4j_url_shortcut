package ru.job4j.job4j_url_shortcut.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.job4j_url_shortcut.model.Account;
import ru.job4j.job4j_url_shortcut.model.Shortcut;
import ru.job4j.job4j_url_shortcut.model.dto.AccountDTO;
import ru.job4j.job4j_url_shortcut.model.dto.RegDTO;
import ru.job4j.job4j_url_shortcut.model.dto.ShortcutDTO;
import ru.job4j.job4j_url_shortcut.model.dto.StatisticsDTO;
import ru.job4j.job4j_url_shortcut.service.AccountService;
import ru.job4j.job4j_url_shortcut.service.ShortcutService;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class AccountController {

    private final AccountService accountService;

    private final BCryptPasswordEncoder encoder;

    private final ShortcutService shortcutService;

    public AccountController(AccountService accountService, BCryptPasswordEncoder encoder,
                             ShortcutService shortcutService) {
        this.accountService = accountService;
        this.encoder = encoder;
        this.shortcutService = shortcutService;
    }

    @PostMapping("/registration")
    public ResponseEntity<RegDTO> registration(@Valid @RequestBody AccountDTO accountDTO) {
        String site = accountDTO.getSite();
        boolean registration = checkExists(site);
        String password = RandomStringUtils.randomAscii(8);
        RegDTO regDTO = RegDTO.of(registration, site, password);
        if (registration) {
            Account newAccount = Account.of(site, encoder.encode(password));
            accountService.save(newAccount);
        } else {
            throw new IllegalArgumentException("You are already registered");
        }
        return new ResponseEntity<>(regDTO, HttpStatus.CREATED);
    }

    @PostMapping("/convert")
    public ResponseEntity<Map<String, String>> convert(@Valid @RequestBody ShortcutDTO shortcutDTO) {
        Shortcut shortcut = new Shortcut();
        String site = SecurityContextHolder.getContext().getAuthentication().getName();
        if(shortcutDTO.getUrl().contains(site)) {
            shortcut.setUrl(shortcutDTO.getUrl());
            shortcut.setCode(RandomStringUtils.randomAlphanumeric(8));
            shortcut.setAccount(accountService.findBySite(site));
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
        Account site = accountService.findBySite(authentication.getName());
        return new ResponseEntity<>(
                shortcutService.findAllByAccountId(site.getId()),
                HttpStatus.OK
        );
    }

    private boolean checkExists(String site) {
        return this.accountService.findBySite(site) == null;
    }
}
