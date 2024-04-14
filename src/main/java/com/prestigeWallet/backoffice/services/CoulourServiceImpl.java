package com.prestigeWallet.backoffice.services;


import com.prestigeWallet.backoffice.EntityManagerHolder;
import com.prestigeWallet.backoffice.dto.CoulourDTO;
import com.prestigeWallet.backoffice.entities.Coulour;
import com.prestigeWallet.backoffice.repositories.ICoulourRepository;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CoulourServiceImpl implements ICoulourService {

    private ICoulourRepository coulourRepository;
    private ModelMapper modelMapper;

    public CoulourServiceImpl(ICoulourRepository coulourRepository, ModelMapperProvider modelMapperProvider){
        this.coulourRepository = coulourRepository;
        this.modelMapper = modelMapperProvider.getModelMapper();
    }

    @Override
    public CoulourDTO getCoulourById(Integer id) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        Coulour coulour = null;

        try {
            entityManager = EntityManagerHolder.getCurrentEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            coulour = this.coulourRepository.getById(id);
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
        return this.modelMapper.map(coulour, CoulourDTO.class);
    }

    @Override
    public Coulour getCoulourByName(String name) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        Coulour coulour = null;

        try {
            entityManager = EntityManagerHolder.getCurrentEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            coulour = this.coulourRepository.getByName(name);
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
        return this.modelMapper.map(coulour, Coulour.class);
    }

    @Override
    public Set<CoulourDTO> getAllCoulours() {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        //List<Coulour> coulours = null;
        Set<Coulour> coulours = null;


        try {
            entityManager = EntityManagerHolder.getCurrentEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            coulours = this.coulourRepository.getAllCoulours();
            transaction.commit();
        } catch(Exception e){
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
        } finally {
            if(entityManager != null){
                entityManager.close();
            }
        }
        return coulours.stream().map(coulour -> modelMapper.map(coulour, CoulourDTO.class)).collect(Collectors.toSet());
    }

    public void addCoulour(CoulourDTO coulourDTO){
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = EntityManagerHolder.getCurrentEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            //LOGGER.info("Début de l'ajout de la couleur...");
            Coulour coulour = this.modelMapper.map(coulourDTO, Coulour.class);
            this.coulourRepository.addCoulour(coulour);
            transaction.commit();
        } catch (Exception e){
            //LOGGER.error("Erreur lors de l'ajout de la couleur : {}", e.getMessage());
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
