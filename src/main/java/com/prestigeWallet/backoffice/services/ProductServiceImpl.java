package com.prestigeWallet.backoffice.services;


import com.prestigeWallet.backoffice.EntityManagerHolder;
import com.prestigeWallet.backoffice.dto.*;
import com.prestigeWallet.backoffice.entities.*;
import com.prestigeWallet.backoffice.repositories.*;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductServiceImpl implements IProductService{

    private IProductRepository productRepository;
    private ICategoryRepository categoryRepository;
    private IMaterialRepository materialRepository;
    private ICoulourRepository coulourRepository;
    private IImageRepository imageRepository;
    private EntityManager entityManager;
    private ModelMapper modelMapper;

    public ProductServiceImpl(IProductRepository productRepository, ICategoryRepository categoryRepository,
                              IMaterialRepository materialRepository, ICoulourRepository coulourRepository,
                              IImageRepository imageRepository, ModelMapperProvider modelMapperProvider){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.materialRepository = materialRepository;
        this.coulourRepository = coulourRepository;
        this.imageRepository = imageRepository;
        this.modelMapper = modelMapperProvider.getModelMapper();
        this.entityManager = EntityManagerHolder.getCurrentEntityManager();
    }

    public ProductDTO getProductById(Integer id){
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        Product product = null;

        try {
            entityManager = EntityManagerHolder.getCurrentEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            product = this.productRepository.getById(id);
        }catch (Exception e){
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            if(entityManager != null){
                entityManager.close();
            }
        }
        return modelMapper.map(product, ProductDTO.class);
    }

    public List<ProductDTO> getAllProducts(){
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        List<Product> products = null;


        try {
            entityManager = EntityManagerHolder.getCurrentEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            products = this.productRepository.getAllProducts();
            transaction.commit();
        } catch (Exception exception){
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
        } finally {
            if(entityManager != null){
                entityManager.close();
            }
        }

        return products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public List<ProductLightDTO> getAllProductsForCatalog(){
        List<Product> products =  this.productRepository.getAllProducts();
        return products.stream()
                .map(product -> modelMapper.map(product, ProductLightDTO.class))
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductsByCategory(CategoryDTO categoryDTO){
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        List<Product> products = null;

        try {
            entityManager = EntityManagerHolder.getCurrentEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            Category category = this.modelMapper.map(categoryDTO, Category.class);
            products = this.productRepository.getProductsByCategory(category);
            transaction.commit();
        } catch (Exception e){
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            if(entityManager != null){
                entityManager.close();
            }
        }
        return products.stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
    }

    public List<ProductLightDTO> getProductsLightDTOByCategory(CategoryDTO categoryDTO){
        Category category = this.modelMapper.map(categoryDTO, Category.class);
        List<Product> products = this.productRepository.getProductsByCategory(category);
        return products.stream().map(product -> modelMapper.map(product, ProductLightDTO.class)).collect(Collectors.toList());
    }


    public List<ProductLightDTO> getWallets() {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        List<Product> wallets = null;

        try {
            entityManager = EntityManagerHolder.getCurrentEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            wallets = this.productRepository.getAllWallets();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }

            return wallets.stream()
                    .map(product -> modelMapper.map(product, ProductLightDTO.class))
                    .collect(Collectors.toList());
        }
    }



    public void addProduct(ProductDTO productDTO){
        //EntityManager entityManager = null;
        //EntityTransaction transaction = null;
        Product product = null;

        try {
            //entityManager = EntityManagerHolder.getCurrentEntityManager();
            this.entityManager.getTransaction().begin();
            //transaction.begin();
            product = this.modelMapper.map(productDTO, Product.class);

            Category category = this.categoryRepository.getByName(productDTO.getCategory().getName());
            if (category == null) {
                category = new Category();
                category.setName(productDTO.getCategory().getName());
                this.categoryRepository.save(category);
            }
            product.setCategory(category);

//            Category category = this.getOrCreateCategory(productDTO.getCategory().getName());
//            product.setCategory(category);

            Material material = this.materialRepository.getByName(productDTO.getMaterial().getName());
            if (material == null) {
                material = new Material();
                material.setName(productDTO.getMaterial().getName());
                this.materialRepository.save(material);
            }
            product.setMaterial(material);

//            Material material = getOrCreateMaterial(productDTO.getMaterial().getName());
//            product.setMaterial(material);
//            System.out.println("mon produit est: " + product.getMaterial().getName() + "son matériel est: " + product.getMaterial().getId());

//            Set<Coulour> coulours = new HashSet<>();
//            for (CoulourDTO coulourDTO : productDTO.getCoulours()) {
//                Coulour coulour = this.coulourRepository.getByName(coulourDTO.getName());
//                if (coulour == null) {
//                    coulour = new Coulour();
//                    coulour.setName(coulourDTO.getName());
//                }
//                coulours.add(coulour);
//            }
//            product.setCoulours(coulours);

//            Set<Coulour> coulours = this.getOrCreateCoulour(productDTO.getCoulours());
//            product.setCoulours(coulours);

//            List<Image> images = new ArrayList<>();
//            for (ImageDTO imageDTO : productDTO.getImages()) {
//                Image image = this.imageRepository.getByName(imageDTO.getName());
//                if (image == null) {
//                    image = new Image();
//                    image.setName(imageDTO.getName());
//                    image.setThumbnail(imageDTO.getThumbnail());
//                    //this.imageRepository.addImage(image);
//                }
//                images.add(image);
//            }
//            product.setImages(images);

//            List<Image> images = this.getOrCreateImage(productDTO.getImages());
//            product.setImages(images);

            //this.productRepository.add(product);
            this.entityManager.persist(product);
            this.entityManager.getTransaction().commit();
        }catch (Exception e){
            if(this.entityManager.getTransaction() != null && this.entityManager.getTransaction().isActive()){
                this.entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }finally {
            if(entityManager != null){
                entityManager.close();
            }
        }
    }
    private Category getOrCreateCategory(String categoryName){
        Category category = this.categoryRepository.getByName(categoryName);
        if (category == null) {
            category = new Category();
            category.setName(categoryName);
            System.out.println("elle n'existe pas");
            this.categoryRepository.save(category);
        }
        System.out.println("la catégorie existe, son nom est: " + category.getName() + "son id est :" + category.getId());
        return category;
    }

    private Material getOrCreateMaterial(String materialName){
        Material material = this.materialRepository.getByName(materialName);
        if (material == null) {
            material = new Material();
            material.setName(materialName);
            System.out.println("la matière n'existe pas");
            this.materialRepository.save(material);
        }
        System.out.println("la matière existe, son nom est: " + material.getName() + "son id est : " + material.getId());
        return material;
    }

    private Set<Coulour> getOrCreateCoulour(Set<CoulourDTO> couloursDTO){
        Set<Coulour> coulours = new HashSet<>();
        for (CoulourDTO coulourDTO : couloursDTO) {
            Coulour coulour = this.coulourRepository.getByName(coulourDTO.getName());
            if (coulour == null) {
                coulour = new Coulour();
                coulour.setName(coulourDTO.getName());
                System.out.println("la couleur n'existe pas");
                this.coulourRepository.addCoulour(coulour);
            }
            System.out.println("la couleur existe, son nom est: " + coulour.getName() + "son id est: " + coulour.getId());
            coulours.add(coulour);
        }
        return coulours;
    }

    private List<Image> getOrCreateImage(List<ImageDTO> imagesDTO){
        List<Image> images = new ArrayList<>();
        for (ImageDTO imageDTO : imagesDTO) {
            Image image = this.imageRepository.getByName(imageDTO.getName());
            if (image == null) {
                image = new Image();
                image.setName(imageDTO.getName());
                image.setThumbnail(imageDTO.getThumbnail());
                System.out.println("l'image n'existe pas");
                this.imageRepository.addImage(image);
            }
            System.out.println("l'image existe, son nom est: " + image.getName() + "son id est: " + image.getId());
            images.add(image);
        }
        return images;
    }
}
