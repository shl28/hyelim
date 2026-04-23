package com.example.crud1.service;

import com.example.crud1.dto.DoDto;
import com.example.crud1.entity.DoIt;
import com.example.crud1.repository.DoRepository;
import io.micrometer.observation.ObservationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class DoService {
    private final DoRepository doRepository;

    @Transactional
    public DoIt create(DoDto dto) {
        DoIt entity = new DoIt(null, dto.getTitle(), dto.getContent());
        return doRepository.save(entity);
    }

    public List<DoIt> findAll() {
        return doRepository.findAll();
    }

    public Optional<DoIt> findById(Long num) {
        return doRepository.findById(num);
    }

    @Transactional
    public Optional<DoIt> update(DoDto dto) {
        if (dto.getNum() == null) {
            return Optional.empty();
        }

        return doRepository.findById(dto.getNum()).map(existing -> {
            DoIt merged = new DoIt(dto.getNum(), dto.getTitle(), dto.getContent());
            return doRepository.save(merged);
        });
    }

    @Transactional
    public boolean delete(Long num) {
        return doRepository.findById(num).map(entity -> {
            doRepository.delete(entity);
            return true;
        }).orElse(false);
    }
}
