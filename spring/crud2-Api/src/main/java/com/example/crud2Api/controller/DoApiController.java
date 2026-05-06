package com.example.crud2Api.controller;

import com.example.crud2Api.dto.DoDto;
import com.example.crud2Api.entity.DoIt;
import com.example.crud2Api.service.DoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doits")

public class DoApiController {
    private final DoService doService;

    public DoApiController(DoService doService) {
        this.doService = doService;
    }

    @GetMapping
    public List<DoIt> list() {
        return doService.findAll();
    }

    @GetMapping("/{num}")
    public ResponseEntity<DoIt> get(@PathVariable("num") Long num) {
        return doService.findById(num)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // .map : data 존재하면 data 꺼내서 200 ok 상태코드와 ResponseEntity<DoIt> 객체 반환
    // .orElse : data 없을때, 404 Not Found 상태코드 + build(body없이 header만 있는 응답) 반환

    @PostMapping
    public ResponseEntity<DoIt> create(@RequestBody DoDto dto) {
        DoIt saved = doService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    // @RequestBody : json 데이터를 자바객체로 변환
    // HttpStatus.CREATED 201 : 생성 성공

    @PutMapping("/{num}")
    public ResponseEntity<DoIt> update(@PathVariable("num") Long num, @RequestBody DoDto dto) {
        dto.setNum(num);
        return doService.update(dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{num}")
    public ResponseEntity<DoIt> delete(@PathVariable("num") Long num) {
        if(doService.delete(num)) return ResponseEntity.noContent().build();

        return ResponseEntity.notFound().build();
    }
}
