package com.example.onlineMarket.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"stores\"")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"store_id\"")
    private Long storeId;

    @Column(name = "\"store_name\"")
    private String storeName;

    @Column(name = "\"location\"")
    private String location;

    @ManyToMany(mappedBy = "stores", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"stores", "handler", "hibernateLazyInitializer"}, allowSetters = true)
    private Set<Product> products;

}