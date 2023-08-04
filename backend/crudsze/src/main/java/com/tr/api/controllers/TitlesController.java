package com.tr.api.controllers;

import com.tr.api.responses.TitlesResponse;
import com.tr.domain.dto.TitlesRequestDTO;
import com.tr.domain.services.TitlesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/titles")
public class TitlesController {

    @Autowired
    private TitlesService service;

    @GetMapping
    private ResponseEntity<List<TitlesResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/titles/{id}")
    private ResponseEntity<TitlesResponse> findById(@PathVariable(value = "titleId", required = true) Long titleId){
        return ResponseEntity.ok(service.findById(titleId));
    }

    @PostMapping
    private ResponseEntity<TitlesResponse> creatTitles(@RequestBody TitlesRequestDTO dto){
        TitlesResponse response =  service.save(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    private ResponseEntity<TitlesResponse> atualizar(@PathVariable Long titleId, @RequestBody TitlesRequestDTO dto){
        return ResponseEntity.ok(service.update(titleId, dto));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?>  deleteTitles(@PathVariable(value = "titleId", required = true) Long titleId){
        service.delete(titleId);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
