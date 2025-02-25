package com.example.vendingmachinespring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
@Entity
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;


    private int quantity;

    private double totalPrice;

    @Basic
    @Column(name = "order_time", nullable = false)
    private LocalDateTime orderTime = LocalDateTime.now();



    public Order(Product product, int quantity, double totalPrice) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.orderTime = LocalDateTime.now();
    }


}
