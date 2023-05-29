package it.academy.softwarerestoMenu.service;

import it.academy.softwarerestoMenu.entity.Cart;
import it.academy.softwarerestoMenu.entity.Order;
import it.academy.softwarerestoMenu.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {
    CartService cartService;
    OrderRepository orderRepository;

    public Order createOrder(Long cartId) {
        Cart cart = cartService.getCartById(cartId);

        // Создайте новый объект заказа
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setDishes(cart.getDishes());
        // Другие операции по оформлению заказа

        // Сохраните заказ в репозитории
        orderRepository.save(order);

        // Удалите корзину после оформления заказа
        cartService.deleteCart(cartId);

        return order;
    }

}

