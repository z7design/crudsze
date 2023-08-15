package com.tr.api.Dashboard;

import com.tr.domain.Dashboard.DashboardResponseDTO;
import com.tr.domain.Dashboard.DashboardService;
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
  public ResponseEntity<DashboardResponseDTO> getCashFlowInDashboard(
      @RequestParam(name = "firstPeriod") String firstPeriod,
      @RequestParam(name = "finishPeriod") String finishPeriod, @RequestParam("userId") Long userId) {
    return ResponseEntity.ok(dashboardService.getCashFlow(firstPeriod, finishPeriod));
  }
}
