package it.academy.softwarerestoMenu.repository;

import it.academy.softwarerestoMenu.entity.Cart;
import it.academy.softwarerestoMenu.entity.User;
import it.academy.softwarerestoMenu.enums.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserAndStatusAndRemoveDateTimeIsNull(User user, CartStatus status);

    Optional<Cart> findByIdAndRemoveDateTimeIsNull(Long id);
}
