package com.prestigeWallet.backoffice.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductLightDTO {
    private Integer id;
    private String name;
    private Double price;
    private Boolean isNew;
    private Boolean isInSale;
    private MaterialDTO material;
    private CategoryDTO category;
    private Set<CoulourDTO> coulours;
    private List<ImageDTO> images;

    public ProductLightDTO(){

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public Boolean isNew() {
        return isNew;
    }

    public void setNew(Boolean isNew) {
        this.isNew = isNew;
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

    public Boolean isInSale() {
        return isInSale;
    }

    public void setInSale(Boolean inSale) {
        isInSale = inSale;
    }

    public List<ImageDTO> getImages() {
        return images;
    }
    public void setImages(List<ImageDTO> images) {
        this.images = images;
    }
}
