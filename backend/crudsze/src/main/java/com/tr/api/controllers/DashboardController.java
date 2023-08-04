package com.tr.api.controllers;

import com.tr.domain.dto.DashboardResponseDto;
import com.tr.domain.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

  @Autowired
  private DashboardService dashboardService;

  @GetMapping
  public ResponseEntity<DashboardResponseDto> getCashFlowInDashboard(
      @RequestParam(name = "firstPeriod") String firstPeriod,
      @RequestParam(name = "finishPeriod") String finishPeriod) {
    return ResponseEntity.ok(dashboardService.getCashFlow(firstPeriod, finishPeriod));
  }
}
