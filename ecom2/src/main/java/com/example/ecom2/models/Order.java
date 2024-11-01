package com.example.ecom2.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_id", referencedColumnName = "id", nullable = false)
    private Payment payment;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shipment_id", referencedColumnName = "id", nullable = false)
    private Shipment shipment;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_item", joinColumns = @JoinColumn(name = "order_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "item_id", nullable = false))
    private List<Item> items;

    @Column(name = "total", nullable = false)
    private double total;
}
