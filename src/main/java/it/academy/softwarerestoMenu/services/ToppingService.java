package it.academy.softwarerestoMenu.services;

import it.academy.softwarerestoMenu.dto.ToppingDTO;
import it.academy.softwarerestoMenu.entity.Topping;
import it.academy.softwarerestoMenu.exceptions.ToppingNotFoundException;
import it.academy.softwarerestoMenu.repository.ToppingRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class ToppingService {

    @Autowired
    private ToppingRepository repository;

    public Topping create(Topping topping) {
        if (repository.findByName(topping.getName()).isPresent()) {
            throw new ToppingNotFoundException(String.format("Топпинг с названием %s уже существует",
                    topping.getName()));
        }
        return repository.save(topping);
    }

    public Topping getById(Long id) {
        Topping topping = repository.findById(id).orElseThrow(null);
        if (topping == null) {
            throw new ToppingNotFoundException(String.format("Топпинг %s не найден", topping.getName()));
        }
        if (topping.getRemoveDateTime() != null) {
            throw new ToppingNotFoundException(String.format("Топпинг %s не удален", topping.getName()));
        }
        return topping;
    }

    public List<ToppingDTO> findAll() {
        List<Topping> toppings = repository.findAllByRemoveDateTimeIsNull();
        List<ToppingDTO> toppingDTOs = new ArrayList<>();

        for (Topping topping : toppings) {
            ToppingDTO toppingDTO = mapperToDTO(topping);
            toppingDTOs.add(toppingDTO);
        }

        return toppingDTOs;
    }

    public ToppingDTO mapperToDTO(Topping topping) {
        return ToppingDTO.builder()
                .name(topping.getName())
                .price(topping.getPrice())
                .build();
    }

    public Long delete(Long id) {
        var topping = getById(id);
        topping.setRemoveDateTime(LocalDateTime.now());
        repository.save(topping);
        return topping.getId();
    }

    public Topping restore(Long id) {
        Topping topping = repository.findById(id)
                .orElseThrow(() -> new ToppingNotFoundException(String.format("Топпинг %s не найден", id)));
        if (topping.getRemoveDateTime() == null) {
            throw new ToppingNotFoundException(String.format("Топпинг с id %s в списке удаленных", id));
        }
        topping.setRemoveDateTime(null);
        return repository.save(topping);
    }

    public Topping update(Topping topping) {
        Topping existingTopping = repository.findById(topping.getId()).orElseThrow(() ->
                new ToppingNotFoundException("Топпинг не найден с ID: " + topping.getId()));
        if (existingTopping.getRemoveDateTime() != null) {
            throw new ToppingNotFoundException(String.format("Топпинг %s в списке удаленных", existingTopping.getName()));
        }
        existingTopping.setName(topping.getName());
        existingTopping.setPrice(topping.getPrice());
        existingTopping.setUpdateDateTime(LocalDateTime.now());

        Topping updatetopping = create(existingTopping);
        return updatetopping;
    }


}
