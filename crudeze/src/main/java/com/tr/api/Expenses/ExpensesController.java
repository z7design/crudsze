package com.tr.api.Expenses;

import com.tr.domain.Expenses.ExpensesEntity;
import com.tr.domain.Expenses.ExpensesService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/expenses")
public class ExpensesController {

  @Autowired private ExpensesService service;

  @GetMapping
  public List<ExpensesEntity> findAllByExpenses() {
    return service.findAllByExpenses();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{expensesId}")
  public ExpensesEntity getByExpensesId(@PathVariable Long expensesId) {
    return service.findExpensesById(expensesId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{expensesId}")
  public ExpensesEntity update(@PathVariable Long expensesId, @RequestBody ExpensesEntity expenses) {
    ExpensesEntity expensesCurrent = service.findExpensesById(expensesId);
    BeanUtils.copyProperties(expenses, expensesCurrent, "expensesId");
    return service.createExpenses(expensesCurrent);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ExpensesEntity createExpenses(@RequestBody ExpensesEntity expenses) {
    return service.createExpenses(expenses);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{expensesId}")
  public void deleteExpenses(@PathVariable Long expensesId) {

    service.deleteExpesenses(expensesId);
  }
}
