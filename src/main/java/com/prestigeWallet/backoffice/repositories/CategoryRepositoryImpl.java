package com.prestigeWallet.backoffice.repositories;

import com.prestigeWallet.backoffice.EntityManagerHolder;
import com.prestigeWallet.backoffice.entities.Category;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class CategoryRepositoryImpl implements ICategoryRepository {

    private EntityManager entityManager;

    public CategoryRepositoryImpl() {
        this.entityManager = EntityManagerHolder.getCurrentEntityManager();
    }

    public Category getById(Integer id) {
        EntityManager entityManager = EntityManagerHolder.getCurrentEntityManager();
        Category category = entityManager.find(Category.class, id);
        return category;
    }

    public Category getByName(String categoryName) {
        try {
            TypedQuery<Category> query = entityManager.createQuery(
                    "SELECT c FROM Category c WHERE c.name = :categoryName", Category.class);
            query.setParameter("categoryName", "%" + categoryName + "%");
            Category category = query.getSingleResult();
            return category;

        } catch (NoResultException e) {

            return null;
        }
    }

    public void save(Category category) {
        entityManager.getTransaction().begin();
        entityManager.persist(category);
        entityManager.getTransaction().commit();
    }
}
