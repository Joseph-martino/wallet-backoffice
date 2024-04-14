package com.prestigeWallet.backoffice.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Integer id;
    private String name;
    private String description;
    private Double price;
    @Column(name="point")
    private Integer reward_point;
    private Integer stock;
    private Boolean isNew;
    private Boolean isInSale;
    @Column(name="created_at")
    private Date createdAt;

    @ManyToOne (cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
    @JoinColumn(name="material_id")
    private Material material;

    @ManyToOne (cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
    @JoinTable(
            name="product_coulour",
            joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name = "coulour_id")
    )
    private Set<Coulour> coulours;

    @ManyToOne
    @JoinColumn(name="sale_id")
    private Sale sale;

    @OneToMany(mappedBy = "product", cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
    private List<Image> images;

    @OneToMany
    @JoinColumn(name="comment_id")
    private List<Comment> comments;

    public Product(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getReward_point() {
        return reward_point;
    }

    public void setReward_point(Integer reward_point) {
        this.reward_point = reward_point;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Boolean isNew() {
        return isNew;
    }

    public void setNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public Material getMaterial() {
        return material;
    }
    public void setMaterial(Material material) {
        this.material = material;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Coulour> getCoulours() {
        return coulours;
    }

    public void setCoulours(Set<Coulour> coulours) {
        this.coulours = coulours;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Boolean isInSale() {
        return isInSale;
    }
    public void setInSale(Boolean inSale) {
        isInSale = inSale;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
