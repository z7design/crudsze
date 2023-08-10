package com.tr.domain.Payment;

import com.tr.domain.AccountPayble.AccountPaybleService;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.exception.ResourceNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {

  private static final String MSG_PAYMENT_NOT_FOUND = "There is no payment registration with the code %d";
  private static final String MSG_PAYMENT_IN_USE = "Code payment %d cannot be removed as it is in use";

  @Autowired private PaymentRepository repository;
  @Autowired private AccountPaybleService AccountPaybleService;

  @Transactional
  public PaymentEntity createPayment(PaymentEntity payment) {
    return repository.save(payment);
  }

  @Transactional
  public PaymentEntity findPaymentById(final Long paymentId) {
    return repository
        .findById(paymentId)
        .orElseThrow(() -> new EntityNotFoundException(String.format(MSG_PAYMENT_NOT_FOUND, paymentId)));
  }

  @Transactional
  public PaymentEntity updatePayment(final PaymentEntity payment) {
    PaymentEntity entity =
        repository
            .findById(payment.getPaymentId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));

    entity.setDuteDate(entity.getDuteDate());
    entity.setPaymentDate(entity.getPaymentDate());
    entity.setPaymentAmount(entity.getPaymentAmount());
    entity.setMonthReference(entity.getMonthReference());
    entity.setDescription(entity.getDescription());
    entity.setInstallmentNumber(entity.getInstallmentNumber());

    return repository.save(payment);
  }
  
  public void deletePayment(Long paymentId) {
    try {
      repository.deleteById(paymentId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_PAYMENT_NOT_FOUND, paymentId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_PAYMENT_IN_USE, paymentId));
    }
  }

  public List<PaymentEntity> findAllByPayment() {
    return repository.findAll();
  }
}
