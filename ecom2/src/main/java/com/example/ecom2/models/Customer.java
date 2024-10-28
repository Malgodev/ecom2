package com.example.ecom2.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "password", nullable = false)
    private String password;
    
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Rating> ratings;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Order> orders;

    @OneToOne(mappedBy = "customer", fetch = FetchType.EAGER)
    private Cart cart;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Comment> comments;
}
