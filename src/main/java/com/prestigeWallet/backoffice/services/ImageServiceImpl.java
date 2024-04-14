package com.prestigeWallet.backoffice.services;

import com.prestigeWallet.backoffice.EntityManagerHolder;
import com.prestigeWallet.backoffice.dto.ImageDTO;
import com.prestigeWallet.backoffice.entities.Image;
import com.prestigeWallet.backoffice.entities.Product;
import com.prestigeWallet.backoffice.repositories.IImageRepository;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class ImageServiceImpl implements IImageservice{



    private IImageRepository imageRepository;
    private ModelMapper modelMapper;

    public ImageServiceImpl(IImageRepository imageRepository, ModelMapperProvider modelMapperProvider){
        this.imageRepository = imageRepository;
        this.modelMapper = modelMapperProvider.getModelMapper();
    }
    @Override
    public ImageDTO getImageById(Integer id) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        Image image = null;

        try {
            entityManager = EntityManagerHolder.getCurrentEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            image = this.imageRepository.getById(id);
            transaction.commit();
        } catch (Exception e){
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if(entityManager != null){
                entityManager.close();
            }
        }
        return this.modelMapper.map(image, ImageDTO.class);
    }

    @Override
    public ImageDTO getImageByName(String name) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        Image image = null;

        try {
            entityManager = EntityManagerHolder.getCurrentEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            image = this.imageRepository.getByName(name);
            transaction.commit();
        } catch (Exception e){
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if(entityManager != null){
                entityManager.close();
            }
        }
        return this.modelMapper.map(image, ImageDTO.class);
    }

    @Override
    public void addImage(Image image) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = EntityManagerHolder.getCurrentEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(image);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
