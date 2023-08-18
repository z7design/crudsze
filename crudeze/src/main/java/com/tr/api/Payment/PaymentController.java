package com.tr.api.Payment;

import com.tr.domain.Payment.PaymentEntity;
import com.tr.domain.Payment.PaymentService;
import com.tr.domain.exception.EntityNotFoundException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {

  @Autowired private PaymentService service;

  @GetMapping
  public List<PaymentEntity> findAllByPayment() {
    return service.findAllByPayment();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{paymentId}")
  public PaymentEntity getByPaymentId(@PathVariable Long paymentId) {
    return service.findPaymentById(paymentId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{paymentId}")
  public PaymentEntity updatePayment(@PathVariable Long paymentId, @RequestBody PaymentEntity payment) {
    PaymentEntity paymentCurrent = service.findPaymentById(paymentId);
    BeanUtils.copyProperties(payment, paymentCurrent, "paymentId");
    return service.findPaymentById(paymentId);
  }

  @PostMapping
  public ResponseEntity<PaymentEntity> createPayment(@RequestBody PaymentEntity payment) {
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
