package com.prestigeWallet.backoffice.repositories;

import com.prestigeWallet.backoffice.EntityManagerHolder;
import com.prestigeWallet.backoffice.entities.Image;
import com.prestigeWallet.backoffice.entities.Material;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class ImageRepositoryImpl implements IImageRepository{

    private final EntityManager entityManager;
    public ImageRepositoryImpl(){
        this.entityManager = EntityManagerHolder.getCurrentEntityManager();
    }



    @Override
    public Image getById(Integer id) {
        EntityManager entityManager = EntityManagerHolder.getCurrentEntityManager();
        Image image = entityManager.find(Image.class,id);
        return image;
    }

    @Override
//    public Image getByName(String name) {
//        EntityManager entityManager = EntityManagerHolder.getCurrentEntityManager();
//        try {
//            TypedQuery<Image> query = entityManager.createQuery("SELECT i FROM Image i WHERE i.name LIKE :imageName", Image.class);
//            query.setParameter("imageName", "%" + name + "%");
//            return query.getSingleResult();
//        } catch (NoResultException e) {
//            // Aucune image trouvée avec le nom spécifié
//            return null; // Ou lancez une exception appropriée / faites un traitement spécifique selon votre logique métier
//        } finally {
//            if (entityManager != null) {
//                entityManager.close();
//            }
//        }
//    }

    public Image getByName(String imageName) {
        try {
            TypedQuery<Image> query = this.entityManager.createQuery(
                    "SELECT i FROM Image i WHERE i.name LIKE :imageName", Image.class);
            query.setParameter("imageName", "%" + imageName + "%");
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            if(this.entityManager != null){
                this.entityManager.close();
            }
        }
    }


    @Override
    public void addImage(Image image) {
        EntityManager entityManager = EntityManagerHolder.getCurrentEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            System.out.println("Enregistrement de l'image...");

            entityManager.merge(image);

            transaction.commit();

            System.out.println("Enregistrement de la image réussie !");
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Erreur lors de l'enregistrement de l'image : " + e.getMessage());
            throw e;
        } finally {
            entityManager.close();
        }
    }
}
