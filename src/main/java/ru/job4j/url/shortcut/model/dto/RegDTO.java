package ru.job4j.url.shortcut.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegDTO {
    private boolean registration;
    private String site;
    private String password;

    public RegDTO() {

    }

    public static RegDTO of(boolean registration, String login, String password) {
        RegDTO regDTO = new RegDTO();
        regDTO.setRegistration(registration);
        regDTO.setSite(login);
        regDTO.setPassword(password);
        return regDTO;
    }
}
