package com.tr.api.controllers;

import com.tr.domain.entities.Payment;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.services.PaymentService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

  @Autowired private PaymentService service;

  @GetMapping
  public List<Payment> findAllByPayment() {
    return service.findAllByPayment();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{paymentId}")
  public Payment getByPaymentId(@PathVariable Long paymentId) {
    return service.findPaymentById(paymentId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{paymentId}")
  public Payment updatePayment(@PathVariable Long paymentId, @RequestBody Payment payment) {
    Payment paymentCurrent = service.findPaymentById(paymentId);
    BeanUtils.copyProperties(payment, paymentCurrent, "paymentId");
    return service.findPaymentById(paymentId);
  }

  @PostMapping
  public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
    try {
      payment = service.createPayment(payment);
      return ResponseEntity.status(HttpStatus.CREATED).body(payment);

    } catch (EntityNotFoundException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{paymentId}")
  public void deletePayment(@PathVariable Long paymentId) {
    service.deletePayment(paymentId);
  }
}
