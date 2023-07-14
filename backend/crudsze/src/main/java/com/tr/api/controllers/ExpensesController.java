package com.tr.api.controllers;

import com.tr.domain.entities.Expenses;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.services.ExpensesService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expenses")
public class ExpensesController {

  @Autowired private ExpensesService service;

  @GetMapping
  public List<Expenses> findAllByExpenses() {
    return service.findAllByExpenses();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{expensesId}")
  public Expenses getByExpensesId(@PathVariable Long expensesId) {
    return service.findExpensesById(expensesId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{expensesId}")
  public Expenses update(@PathVariable Long expensesId, @RequestBody Expenses expenses) {
    Expenses expensesCurrent = service.findExpensesById(expensesId);
    BeanUtils.copyProperties(expenses, expensesCurrent, "expensesId");
    return service.createExpenses(expensesCurrent);
  }

  @PostMapping
  public ResponseEntity<Expenses> createExpenses(@RequestBody Expenses expenses) {
    try {
      expenses = service.createExpenses(expenses);
      return ResponseEntity.status(HttpStatus.CREATED).body(expenses);

    } catch (EntityNotFoundException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{expensesId}")
  public void deleteExpenses(@PathVariable Long expensesId) {

    service.deleteExpesenses(expensesId);
  }
}
