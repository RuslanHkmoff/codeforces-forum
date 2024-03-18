package ru.itmo.wp.service;

import ru.itmo.wp.domain.Tag;
import ru.itmo.wp.repository.TagRepository;

public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public void save(Tag tag) {
        tagRepository.save(tag);
    }
}
