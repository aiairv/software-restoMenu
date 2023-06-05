package it.academy.softwarerestoMenu.services;

import it.academy.softwarerestoMenu.dto.PaymentDTO;
import it.academy.softwarerestoMenu.entity.Payment;
import it.academy.softwarerestoMenu.exceptions.PaymentNotFoundException;
import it.academy.softwarerestoMenu.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentDTO processPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(()-> new PaymentNotFoundException("Платеж не найден с ID: " + paymentId));
        payment.setStatus("Processed");
        paymentRepository.save(payment);

        return mapToDTO(payment);
    }

    private PaymentDTO mapToDTO(Payment payment) {
        return PaymentDTO.builder()
                .id(payment.getId())
                .amount(payment.getAmount())
                .status(payment.getStatus())
                .build();
    }
}
