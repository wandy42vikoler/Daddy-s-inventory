package com.codecool.dadsinventory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne
    private Category category;
    private String comment;
    private double price;
    private boolean inStock;

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category.getName() +
                ", comment='" + comment + '\'' +
                ", price=" + price +
                ", inStock=" + inStock +
                '}';
    }
}
