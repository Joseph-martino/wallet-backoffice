package com.prestigeWallet.backoffice.repositories;


import com.prestigeWallet.backoffice.EntityManagerHolder;
import com.prestigeWallet.backoffice.entities.Category;
import com.prestigeWallet.backoffice.entities.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;


public class ProductRepositoryImpl implements IProductRepository{
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryImpl.class);

    public Product getById(Integer id){
        EntityManager entityManager = EntityManagerHolder.getCurrentEntityManager();
        Product product = entityManager.find(Product.class, id);
        return product;
    }

    public List<Product> getAllProducts(){
        EntityManager entityManager = EntityManagerHolder.getCurrentEntityManager();
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p", Product.class);
        List<Product> products = query.getResultList();
        return products;
    }

    public List<Product> getProductsByCategory(Category category){
            EntityManager entityManager = EntityManagerHolder.getCurrentEntityManager();
            TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p join fetch p.images WHERE p.category.id=?0", Product.class);
            query.setParameter(0, category.getId());
            List<Product> products = query.getResultList();
            return products;
    }

    public List<Product> getAllWallets(){
        EntityManager entityManager = EntityManagerHolder.getCurrentEntityManager();
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p join fetch p.images WHERE p.category.id=1", Product.class);
        List<Product> wallets = query.getResultList();
        return wallets;
    }

    public void add(Product product){
        EntityManager entityManager = EntityManagerHolder.getCurrentEntityManager();
        //EntityTransaction transaction = entityManager.getTransaction();
        //transaction.begin();
        LOGGER.info("Début de l'ajout du produit...");
        entityManager.merge(product);
        //transaction.commit();
        System.out.println("produit enregistré");
        //entityManager.close();
    }
}
