package ru.job4j.url.shortcut.model.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ShortcutDTO {
    @NotBlank(message = "Url must be not empty")
    private String url;

    public ShortcutDTO() {
    }
}
