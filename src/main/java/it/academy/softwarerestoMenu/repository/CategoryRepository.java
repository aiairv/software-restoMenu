package it.academy.softwarerestoMenu.repository;

import it.academy.softwarerestoMenu.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByRemoveDateTimeIsNull();

    Optional<Object> findByName(String name);
}