package com.prestigeWallet.backoffice.repositories;


import com.prestigeWallet.backoffice.EntityManagerHolder;
import com.prestigeWallet.backoffice.entities.Category;
import com.prestigeWallet.backoffice.entities.Coulour;
import com.prestigeWallet.backoffice.entities.Material;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class MaterialRepositoryImpl implements IMaterialRepository {

    private EntityManager entityManager;

    public MaterialRepositoryImpl() {
        this.entityManager = EntityManagerHolder.getCurrentEntityManager();
    }

    public Material getById(Integer id){
        EntityManager entityManager = EntityManagerHolder.getCurrentEntityManager();
        Material material = entityManager.find(Material.class, id);
        return material;
    }

    @Override
    public Material getByName(String materialName) {
        TypedQuery<Material> query = entityManager.createQuery(
                "SELECT m FROM Material m WHERE m.name = :name", Material.class);
        query.setParameter("name", materialName);
        return query.getSingleResult();
    }

    public void save(Material material) {
        entityManager.getTransaction().begin();
        entityManager.persist(material);
        entityManager.getTransaction().commit();
    }
}
