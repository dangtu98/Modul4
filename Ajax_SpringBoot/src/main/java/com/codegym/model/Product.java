package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    @NotEmpty
    private String name;

    @Column(nullable = false)
    @NotNull
    private double price;

    @Column(columnDefinition = "longtext", nullable = false)
    @NotNull
    private String description;

    private String image;

    @ManyToOne
    private Category category;


}
