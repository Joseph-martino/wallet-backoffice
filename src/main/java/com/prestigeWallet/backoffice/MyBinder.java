package com.prestigeWallet.backoffice;

import com.prestigeWallet.backoffice.repositories.CategoryRepositoryImpl;
import com.prestigeWallet.backoffice.repositories.ICategoryRepository;
import com.prestigeWallet.backoffice.services.CategoryServiceImpl;
import com.prestigeWallet.backoffice.services.ICategoryService;
import com.prestigeWallet.backoffice.services.IModelMapperProvider;
import com.prestigeWallet.backoffice.services.ModelMapperProvider;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class MyBinder extends AbstractBinder {

    @Override
    protected void configure() {
        // Liaison entre ICategoryRepository et CategoryRepositoryImpl
        bind(CategoryRepositoryImpl.class).to(ICategoryRepository.class);

        // Liaison entre ICategoryService et CategoryServiceImpl
        bind(CategoryServiceImpl.class).to(ICategoryService.class);

        // Liaison du ModelMapperProvider
        bind(ModelMapperProvider.class).to(IModelMapperProvider.class);
    }
}
