package com.example.webshop.product;


import javax.persistence.*;

@Entity
@Table
public class Product {

    @Id
    @SequenceGenerator(
            name="product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private Integer sold;
    private String img;

    public Product() {
    }

    public Product(Long id,
                   String name,
                   String description,
                   Integer price,
                   Integer sold,
                   String img) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.sold = sold;
        this.img = img;
    }

    public Product(String name,
                   String description,
                   Integer price,
                   Integer sold,
                   String img) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.sold = sold;
        this.img = img;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", sold=" + sold +
                ", img='" + img + '\'' +
                '}';
    }
}
