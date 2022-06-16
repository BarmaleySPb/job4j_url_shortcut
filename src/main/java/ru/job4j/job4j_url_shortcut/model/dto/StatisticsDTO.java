package ru.job4j.job4j_url_shortcut.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatisticsDTO {
    private String url;
    private long callCounter;

    public static StatisticsDTO of(String url, long callCounter) {
        StatisticsDTO statisticsDTO = new StatisticsDTO();
        statisticsDTO.setUrl(url);
        statisticsDTO.setCallCounter(callCounter);
        return statisticsDTO;
    }
}
