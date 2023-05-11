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
    IngredientService ingredientService;
    @GetMapping("/getIngredientById/{id}")
    public IngredientDto getIngredientById(@PathVariable Long id){
        return ingredientService.getIngredientById(id);
    }
    @GetMapping("/getAllIngredients")
    public List<IngredientDto> getAllIngredients(){
        return ingredientService.getAllIngredients();
    }
    @PostMapping("/addNewIngredient")
    public IngredientDto addNewIngredient(@RequestBody IngredientDto dto){
        return ingredientService.addNewIngredient(dto);
    }
    @PutMapping("/updateIngredient/{id}")
    public IngredientDto updateIngredient(@PathVariable Long id,
                                          @RequestBody IngredientDto dto){
        return ingredientService.updateIngredient(id, dto);
    }
    @DeleteMapping("/deleteIngredient/{id}")
    public String deleteIngredient(@PathVariable Long id){
        return ingredientService.deleteIngredient(id);
    }
}
