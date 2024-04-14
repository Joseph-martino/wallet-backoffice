package com.prestigeWallet.backoffice.repositories;

import com.prestigeWallet.backoffice.entities.Image;

public interface IImageRepository {

    Image getById(Integer id);
    Image getByName(String name);
    void addImage(Image image);
}
