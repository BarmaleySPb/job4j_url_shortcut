package ru.job4j.job4j_url_shortcut.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AccountDTO {
    @NotBlank(message = "Site name must be not empty")
    String site;

    public AccountDTO() {

    }

    public AccountDTO(String site) {
        this.site = site;
    }
}
