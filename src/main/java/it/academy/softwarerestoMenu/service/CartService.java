//package it.academy.softwarerestoMenu.service;
//
//import it.academy.softwarerestoMenu.dto.CartDto;
//import it.academy.softwarerestoMenu.dto.DishDto;
//import it.academy.softwarerestoMenu.dto.IngredientDto;
//import it.academy.softwarerestoMenu.dto.UserDto;
//import it.academy.softwarerestoMenu.entity.Cart;
//import it.academy.softwarerestoMenu.entity.Ingredient;
//import it.academy.softwarerestoMenu.entity.User;
//import it.academy.softwarerestoMenu.repository.CartRepository;
//import it.academy.softwarerestoMenu.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class CartService {
//    CartRepository cartRepository;
//    UserRepository userRepository;

//    public CartDto addDishToCart(UserDto userDto, CartDto cartDto, DishDto dishDto) {
//        Cart cart = new Cart();
//        User user = userRepository.
//
//                ingredient.setId(ingredientDto.getId());
//        ingredient.setName(ingredientDto.getName());
//        ingredient = ingredientRepository.save(ingredient);
//        ingredientDto.setId(ingredient.getId());
//        return ingredientDto;
//    }



//    public CartDto entityToDto(Cart cart){
//        CartDto dto = new CartDto();
//        dto.setId(cart.getId());
//        return dto;
//    }




//    private final CartRepository cartRepository;
//    private final UserRepository userRepository;
//    private Map<Long, Dish> cart = new HashMap<>();


//    public Cart addToCart(Long userId,Dish dish){
//        Cart cart = new Cart();
//        User user = new User();
//        ArrayList<Dish> dishes = new ArrayList<>();
//        dishes.add(dish);
//        cart.setClient(user);
//        cart.setDishes(dishes);
//        return cartRepository.save(cart);
//    }
//}
