package com.example.thymeleaf_examples.service;


import com.example.thymeleaf_examples.domain.DemoItem;
import com.example.thymeleaf_examples.repository.DemoItemRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoItemService {

    private final DemoItemRepository demoItemRepository;

    public DemoItemService(DemoItemRepository demoItemRepository) {
        this.demoItemRepository = demoItemRepository;
    }

    public List<DemoItem> findAllOrdered() {
        return demoItemRepository.findAllByOrderByIdAsc();
    }
}
