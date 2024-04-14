package com.prestigeWallet.backoffice.services;

import com.prestigeWallet.backoffice.dto.ImageDTO;
import com.prestigeWallet.backoffice.entities.Image;

public interface IImageservice {

    ImageDTO getImageById(Integer id);
    ImageDTO getImageByName(String name);
    void addImage(Image image);
}
