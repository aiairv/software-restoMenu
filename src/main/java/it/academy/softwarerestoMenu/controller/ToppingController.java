package it.academy.softwarerestoMenu.controller;

import it.academy.softwarerestoMenu.dto.ToppingDTO;
import it.academy.softwarerestoMenu.entity.ResponseMessage;
import it.academy.softwarerestoMenu.entity.Topping;
import it.academy.softwarerestoMenu.enums.ResultCode;
import it.academy.softwarerestoMenu.exceptions.ToppingNotFoundException;
import it.academy.softwarerestoMenu.services.ToppingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/toppings")
public class ToppingController {

    private final ToppingService service;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseMessage<Topping> create(@RequestBody Topping topping) {
        try {
            return new ResponseMessage<>(
                    service.create(topping),
                    ResultCode.SUCCESS,
                    "Топпинг успешно создан",
                    ResultCode.SUCCESS.getHttpCode()
            );
        } catch (Exception exception) {
            log.error("ToppingController: create", exception);
            return new ResponseMessage<>(
                    null,
                    ResultCode.FAIL,
                    exception.getMessage(),
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }

    @PostMapping("/{id}/restore")
    public ResponseMessage<Topping> restore(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    service.restore(id),
                    ResultCode.SUCCESS,
                    "Топпинг успешно восстановлен!",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("ToppingController: restore ", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/{id}")
    public ResponseMessage<Topping> getById(@PathVariable Long id) {
        try {
            Topping topping = service.getById(id);
            if (topping == null) {
                throw new ToppingNotFoundException(String.format("Топпинг с ID %s не найден", id));
            }
            return new ResponseMessage<>(
                    topping,
                    ResultCode.SUCCESS,
                    "Топпинг по ID найден",
                    ResultCode.SUCCESS.getHttpCode()
            );
        } catch (ToppingNotFoundException exception) {
            log.error("ToppingController: getById", exception);
            return new ResponseMessage<>(
                    null,
                    ResultCode.FAIL,
                    exception.getMessage(),
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }

    @GetMapping("/")
    public List<ToppingDTO> getAll() {
        return service.findAll();
    }

    @PutMapping("/update")
    public ResponseMessage<Topping> update(@RequestBody Topping topping) {
        if (topping.getId() == null) {
            throw new ToppingNotFoundException("ToppingController: update() id is null");
        }
        try {
            return new ResponseMessage<>(
                    service.update(topping),
                    ResultCode.SUCCESS,
                    "Топпинг успешно обновлен",
                    ResultCode.SUCCESS.getHttpCode()
            );
        } catch (Exception exception) {
            log.error("ToppingController: update", exception);
            return new ResponseMessage<>(
                    null,
                    ResultCode.FAIL,
                    exception.getMessage(),
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<Void> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return new ResponseMessage<>(
                    null,
                    ResultCode.SUCCESS,
                    "Топпинг успешно удален",
                    ResultCode.SUCCESS.getHttpCode()
            );
        } catch (Exception exception) {
            log.error("ToppingController: delete", exception);
            return new ResponseMessage<>(
                    null,
                    ResultCode.FAIL,
                    exception.getMessage(),
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }
}
