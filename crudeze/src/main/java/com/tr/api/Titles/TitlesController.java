package com.tr.api.Titles;

import com.tr.domain.Titles.TitlesRequestDTO;
import com.tr.domain.Titles.TitlesResponse;
import com.tr.domain.Titles.TitlesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/titles")
public class TitlesController {
    
    @Autowired
    private TitlesService titleService;

    @GetMapping
    public ResponseEntity<List<TitlesResponse>> findAllTitles(){
       return ResponseEntity.ok(titleService.findAll());
    }

    @GetMapping("/{titleId}")
    public ResponseEntity<TitlesResponse> findTitleById(@PathVariable Long titleId){
        return ResponseEntity.ok(titleService.findById(titleId));
    }

    @PostMapping
    public ResponseEntity<TitlesResponse> createTitle(@RequestBody TitlesRequestDTO dto){
        TitlesResponse titlesResponse = titleService.save(dto);
        return new ResponseEntity<>(titlesResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{titleId}")
    public ResponseEntity<TitlesResponse> updateTitle(@PathVariable Long titleId, @RequestBody TitlesRequestDTO dto){
        return ResponseEntity.ok(titleService.update(titleId, dto));
    }

    @DeleteMapping("/{titleId}")
    public ResponseEntity<?> deleteTitle(@PathVariable Long titleId){
        titleService.delete(titleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
