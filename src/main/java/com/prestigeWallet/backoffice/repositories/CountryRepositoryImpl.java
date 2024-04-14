package com.prestigeWallet.backoffice.repositories;


import com.prestigeWallet.backoffice.EntityManagerHolder;
import com.prestigeWallet.backoffice.entities.Country;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class CountryRepositoryImpl implements ICountryRepository {

    public Country getById(Integer id) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        Country country = null;

        try {
            entityManager = EntityManagerHolder.getCurrentEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            country = entityManager.find(Country.class, id);
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
        return country;
    }

    public void addCountry(Country country){
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = EntityManagerHolder.getCurrentEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(country);
            transaction.commit();
        }catch (Exception e){
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
