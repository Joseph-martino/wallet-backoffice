package com.prestigeWallet.backoffice.services;

import com.prestigeWallet.backoffice.EntityManagerHolder;
import com.prestigeWallet.backoffice.dto.MaterialDTO;
import com.prestigeWallet.backoffice.entities.Material;
import com.prestigeWallet.backoffice.repositories.IMaterialRepository;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


public class MaterialServiceImpl implements IMaterialService {
    private IMaterialRepository materialRepository;
    private ModelMapper modelMapper;

    public MaterialServiceImpl(IMaterialRepository materialRepository, ModelMapperProvider modelMapperProvider){
        this.materialRepository = materialRepository;
        this.modelMapper = modelMapperProvider.getModelMapper();
    }

    public MaterialDTO getMaterialById(Integer id){
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        Material material = null;

        try {
            entityManager = EntityManagerHolder.getCurrentEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            material = this.materialRepository.getById(id);
            transaction.commit();
        } catch (Exception e){
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
        } finally {
            if(entityManager != null){
                entityManager.close();
            }
        }
        return this.modelMapper.map(material, MaterialDTO.class);
    }

    public MaterialDTO getMaterialByName(String name){
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        Material material = null;

        try {
            entityManager = EntityManagerHolder.getCurrentEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            material = this.materialRepository.getByName(name);
            transaction.commit();
        } catch (Exception e){
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
        } finally {
            if(entityManager != null){
                entityManager.close();
            }
        }
        return this.modelMapper.map(material, MaterialDTO.class);
    }

    public void addMaterial(MaterialDTO materialDTO){
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = EntityManagerHolder.getCurrentEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            Material material = this.modelMapper.map(materialDTO, Material.class);
            this.materialRepository.save(material);
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
