package ru.job4j.url.shortcut.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsDTO {
    private String url;
    private long callCounter;

}
