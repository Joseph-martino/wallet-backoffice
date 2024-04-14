package com.prestigeWallet.backoffice.repositories;

import com.prestigeWallet.backoffice.EntityManagerHolder;
import com.prestigeWallet.backoffice.entities.Category;
import com.prestigeWallet.backoffice.entities.Coulour;
import com.prestigeWallet.backoffice.entities.Image;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CoulourRepositoryImpl implements ICoulourRepository{

    //private static final Logger LOGGER = LoggerFactory.getLogger(CoulourRepositoryImpl.class);

    private final EntityManager entityManager;
    public CoulourRepositoryImpl(){
        this.entityManager = EntityManagerHolder.getCurrentEntityManager();
    }

    public Coulour getById(Integer id){
        EntityManager entityManager = entityManager = EntityManagerHolder.getCurrentEntityManager();
        Coulour coulour = entityManager.find(Coulour.class, id);
        return coulour;
    }

    @Override
//    public Coulour getByName(String coulourName) {
//        EntityManager entityManager = EntityManagerHolder.getCurrentEntityManager();
//        TypedQuery<Coulour> query = entityManager.createQuery("SELECT c FROM Coulour c WHERE name LIKE :coulourName", Coulour.class);
//        query.setParameter("coulourName", "%" + coulourName + "%");
//
//        try {
//            Coulour coulour = query.getSingleResult();
//            return coulour;
//        } catch (NoResultException e) {
//            return null;
//        } finally {
//            entityManager.close();
//        }
//    }

    public Coulour getByName(String coulourName) {
        try {
            TypedQuery<Coulour> query = entityManager.createQuery("SELECT c FROM Coulour c WHERE c.name LIKE :coulourName", Coulour.class);
            query.setParameter("coulourName", "%" + coulourName + "%");
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            if(this.entityManager != null){
                this.entityManager.close();
            }
        }
    }

    public Set<Coulour> getAllCoulours() {
        EntityManager entityManager = EntityManagerHolder.getCurrentEntityManager();
        TypedQuery<Coulour> query = entityManager.createQuery("SELECT c FROM Coulour c", Coulour.class);
        List<Coulour> coulours = query.getResultList();
        Set<Coulour> couloursAsSet = new HashSet<>(coulours);
        return couloursAsSet;
    }

        public void addCoulour(Coulour coulour){
            EntityManager entityManager = EntityManagerHolder.getCurrentEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin(); // Début de la transaction

                System.out.println("Enregistrement de la couleur...");

                entityManager.merge(coulour);

                transaction.commit();
                System.out.println("Enregistrement de la couleur réussie !");
            } catch (RuntimeException e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                System.out.println("Erreur lors de l'enregistrement de la couleur : " + e.getMessage());
                throw e;
            } finally {
                entityManager.close();
            }
    }
}
