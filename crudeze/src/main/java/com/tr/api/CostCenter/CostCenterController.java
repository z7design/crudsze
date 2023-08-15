package com.tr.api.CostCenter;

import java.util.List;
import com.tr.domain.CostCenter.CostCenterRequestDTO;
import com.tr.domain.CostCenter.CostCenterService;
import com.tr.domain.CostCenter.CostCenterResponse;
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
@RequestMapping("/api/cost-center")
public class CostCenterController {
    
    @Autowired
    private CostCenterService costCenterService;

    @GetMapping
    public ResponseEntity<List<CostCenterResponse>> findAllCostCenter(){
        return ResponseEntity.ok(costCenterService.findAll());
    }

    @GetMapping("/{costCenterId}")
    public ResponseEntity<CostCenterResponse> getByIdCostCenter(@PathVariable Long costCenterId){
        return ResponseEntity.ok(costCenterService.findById(costCenterId));
    }

    @PostMapping
    public ResponseEntity<CostCenterResponse> createCostCenter(@RequestBody CostCenterRequestDTO dto){
        CostCenterResponse costCenterResponse = costCenterService.save(dto);
        return new ResponseEntity<>(costCenterResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{costCenterId}")
    public ResponseEntity<CostCenterResponse> updasteCostCenter(@PathVariable Long costCenterId, @RequestBody CostCenterRequestDTO dto){
        return ResponseEntity.ok(costCenterService.update(costCenterId, dto));
    }

    @DeleteMapping("/{costCenterId}")
    public ResponseEntity<?> deletar(@PathVariable Long costCenterId){
        costCenterService.delete(costCenterId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
