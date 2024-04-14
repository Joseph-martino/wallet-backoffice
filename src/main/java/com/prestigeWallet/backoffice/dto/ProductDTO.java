package com.prestigeWallet.backoffice.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductDTO {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Integer reward_point;
    private Integer stock;
    private Boolean isNew;
    private Date createdAt;
    private MaterialDTO material;
    private CategoryDTO category;
    private Set<CoulourDTO> coulours;
    private SaleDTO sale;
    private List<ImageDTO> images;
    public ProductDTO(){
        this.coulours = new HashSet<>();
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

    public MaterialDTO getMaterial() {
        return material;
    }

    public void setMaterial(MaterialDTO material) {
        this.material = material;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public Set<CoulourDTO> getCoulours() {
        return coulours;
    }

    public void setCoulours(Set<CoulourDTO> coulours) {
        this.coulours = coulours;
    }

    public SaleDTO getSale() {
        return sale;
    }

    public void setSale(SaleDTO sale) {
        this.sale = sale;
    }

    public List<ImageDTO> getImages() {
        return images;
    }
    public void setImages(List<ImageDTO> images) {
        this.images = images;
    }
}
