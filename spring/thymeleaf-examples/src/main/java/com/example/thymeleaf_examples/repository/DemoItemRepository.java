package com.example.thymeleaf_examples.repository;

import com.example.thymeleaf_examples.domain.DemoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemoItemRepository extends JpaRepository<DemoItem, Long> {
    List<DemoItem> findAllByOrderByIdAsc();

}
