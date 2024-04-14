package com.prestigeWallet.backoffice.services;
import com.prestigeWallet.backoffice.EntityManagerHolder;
import com.prestigeWallet.backoffice.dto.CategoryDTO;
import com.prestigeWallet.backoffice.entities.Category;
import com.prestigeWallet.backoffice.repositories.ICategoryRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class CategoryServiceImpl implements ICategoryService {
    private ICategoryRepository categoryRepository;

    private ModelMapperProvider modelMapperProvider;

    private ModelMapper modelMapper;


    public CategoryServiceImpl(ICategoryRepository categoryRepository, ModelMapperProvider modelMapperProvider){
        this.categoryRepository = categoryRepository;
        modelMapper = modelMapperProvider.getModelMapper();
    }

    public CategoryDTO getCategoryById(Integer id){
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        Category category = null;

        try {
            entityManager = EntityManagerHolder.getCurrentEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            category = this.categoryRepository.getById(id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // Rollback de la transaction en cas d'erreur
            }
            e.printStackTrace(); // Logging de l'exception
        } finally {
            if (entityManager != null) {
                entityManager.close(); // Fermeture de l'EntityManager
            }
        }
        return modelMapper.map(category, CategoryDTO.class);
    }

    public CategoryDTO getCategoryByName(String name){
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        Category category = null;

        try {
            entityManager = EntityManagerHolder.getCurrentEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            category = this.categoryRepository.getByName(name);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // Rollback de la transaction en cas d'erreur
            }
            // e.printStackTrace(); // Logging de l'exception
        } finally {
            if (entityManager != null) {
                entityManager.close(); // Fermeture de l'EntityManager
            }
        }
        return modelMapper.map(category, CategoryDTO.class);
    }

    public void addCategory(CategoryDTO categoryDTO) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = EntityManagerHolder.getCurrentEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            Category category = this.modelMapper.map(categoryDTO, Category.class);
            this.categoryRepository.save(category);
            transaction.commit();
        }catch(Exception e){
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
        }finally {
            if(entityManager != null){
                entityManager.close();
            }
        }
    }
}
