package it.academy.softwarerestoMenu.controller;

import it.academy.softwarerestoMenu.dto.IngredientDto;
import it.academy.softwarerestoMenu.service.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ingredient")
public class IngredientController {
    IngredientService iService;
    @GetMapping("/getById/{id}")
    public IngredientDto getIngredientById(@PathVariable Long id){
        return iService.getIngredientById(id);
    }
    @GetMapping("/getAll")
    public List<IngredientDto> getAllIngredients(){
        return iService.getAllIngredients();
    }
    @PostMapping("/add")
    public IngredientDto addNewIngredient(@RequestBody IngredientDto dto){
        return iService.addNewIngredient(dto);
    }
    @PutMapping("/update/{id}")
    public IngredientDto updateIngredient(@PathVariable Long id,
                                          @RequestBody IngredientDto dto){
        return iService.updateIngredient(id, dto);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteIngredient(@PathVariable Long id){
        return iService.deleteIngredient(id);
    }
}
