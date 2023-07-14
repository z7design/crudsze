package com.tr.domain.services;

import com.tr.domain.entities.Category;
import com.tr.domain.entities.Expenses;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.exception.ResourceNotFoundException;
import com.tr.domain.repositories.ExpensesRespository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
@RequiredArgsConstructor
public class ExpensesService {
  
  private static final String MSG_EXPENSES_NOT_FOUND = "There is no city registration with the code %d";
  private static final String MSG_EXPENSES_IN_USE = "Code city %d cannot be removed as it is in use";

  @Autowired
  private ExpensesRespository repository;
  @Autowired 
  private CategoryService categoryService;

  @Transactional
  public Expenses createExpenses(final Expenses expenses) {
    Long categoryId = expenses.getCategory().getCategoryId();
    Category category = categoryService.findCategoryById(categoryId);

    expenses.setCategory(category);
    return repository.save(expenses);
  }

  @Transactional
  public Expenses findExpensesById(final Long expensesId) {
    return repository
        .findById(expensesId)
        .orElseThrow(() -> new EntityNotFoundException(String.format(MSG_EXPENSES_NOT_FOUND, expensesId)));
  }

  @Transactional
  public Expenses updateExpenses(final Expenses expenses) {
    var entity =
        repository
            .findById(expenses.getExpensesId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));
    
    entity.setDueDate(entity.getDueDate());
    entity.setDescription(entity.getDescription());
    entity.setValueExpenses(expenses.getValueExpenses());
    entity.setPayDay(entity.getPayDay());
    entity.setAmountPaid(entity.getAmountPaid());
    entity.setInvoice(entity.getInvoice());
    entity.setLocality(entity.getLocality());
    return repository.save(expenses);
  }

  @DeleteMapping("/{cityId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteExpesenses(Long expensesId) {
    try {
      repository.deleteById(expensesId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_EXPENSES_NOT_FOUND, expensesId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_EXPENSES_IN_USE, expensesId));
    }
  }

  public List<Expenses> findAllByExpenses() {

    return repository.findAll();
  }
}
