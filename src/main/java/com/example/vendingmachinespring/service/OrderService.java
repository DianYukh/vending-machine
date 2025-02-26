package com.example.vendingmachinespring.service;

import com.example.vendingmachinespring.model.Order;
import com.example.vendingmachinespring.model.Product;
import com.example.vendingmachinespring.repository.OrderRepository;
import com.example.vendingmachinespring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Order findById(Long idOrder) {
        return orderRepository.findById(idOrder).get();
    }
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product is not found"));

        if (product.getQuantity() < quantity) {
            throw new RuntimeException("Not enough goods in stock");
        }

        double totalPrice = product.getPrice() * quantity;
        Order order = new Order(product, quantity, totalPrice);

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);

        return orderRepository.save(order);
    }

    public void deleteOrder(Long idOrder) {
        orderRepository.deleteById(idOrder);
    }

}
