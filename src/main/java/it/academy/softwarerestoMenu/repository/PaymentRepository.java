package it.academy.softwarerestoMenu.repository;

import it.academy.softwarerestoMenu.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
