package com.tr.api.controllers;

import com.tr.api.responses.CostCenterResponse;
import com.tr.domain.dto.CostCenterRequestDTO;
import com.tr.domain.services.CostCenterService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cost-center")
public class CostCenterController {

  @Autowired private CostCenterService service;

  @GetMapping
  public ResponseEntity<List<CostCenterResponse>> findAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @GetMapping("{/costCenterId}")
  public ResponseEntity<CostCenterResponse> findAllById(@PathVariable Long costCenterId) {
    return ResponseEntity.ok(service.findById(costCenterId));
  }

  @PostMapping
  public ResponseEntity<CostCenterResponse> createCostCenter(
      @RequestBody CostCenterRequestDTO dto) {
    CostCenterResponse requestDTO = service.save(dto);
    return new ResponseEntity<>(requestDTO, HttpStatus.CREATED);
  }

  @PutMapping("{/costCenterId}")
  public ResponseEntity<CostCenterResponse> updateCostCenter(
      @PathVariable Long costCenterId, @RequestBody CostCenterRequestDTO dto) {
    CostCenterResponse requestDTO = service.update(costCenterId, dto);
    return ResponseEntity.ok(requestDTO);
  }

  @DeleteMapping("{/costCenterId}")
  public ResponseEntity<?> deleteCostCenter(@PathVariable Long costCenterId) {
    service.delete(costCenterId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
