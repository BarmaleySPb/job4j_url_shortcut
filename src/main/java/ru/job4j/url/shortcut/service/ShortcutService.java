package ru.job4j.url.shortcut.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.url.shortcut.model.Shortcut;
import ru.job4j.url.shortcut.model.dto.StatisticsDTO;
import ru.job4j.url.shortcut.repository.ShortcutRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShortcutService {
    private final ShortcutRepository shortCutRepository;

    public ShortcutService(ShortcutRepository shortCutRepository) {
        this.shortCutRepository = shortCutRepository;
    }

    public Optional<Shortcut> findByCode(String code) {
        Optional<Shortcut> shortcut = shortCutRepository.findByCode(code);
        shortcut.ifPresent(value -> shortCutRepository.updateCallCounter(value.getId()));
        return shortcut;
    }

    public List<StatisticsDTO> findAllByAccountId(long id) {
        List<StatisticsDTO> statisticList = new ArrayList<>();
        List<Shortcut> urls = shortCutRepository.findAllByAccountId(id);
        for (Shortcut url : urls) {
            statisticList.add(StatisticsDTO.of(url.getUrl(), url.getCallCounter()));
        }
        return statisticList;
    }

    public void save(Shortcut shortcut) {
        shortCutRepository.save(shortcut);
    }
}
