
//@Service
//public class OrderService {
//    private CartService cartService;
//    CartRepository cartRepository;
//    UserRepository userRepository;
//    OrderRepository orderRepository;
//
//    public Order createOrder(Long userId) {
//        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
//        Cart cart = user.getCart();
//        if (cart == null) {
//            throw new RuntimeException("Корзины не существует");
//        }
//        List<CartItemDto> cartItems = cartService.getAllDishesFromCart(userId);
//        Order order = new Order();
//        order = cartItems.getAllDishesFromCart(userId);
//
//    }
//}
import org.springframework.stereotype.Service;
