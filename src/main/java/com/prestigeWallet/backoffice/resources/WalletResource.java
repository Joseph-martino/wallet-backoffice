package com.prestigeWallet.backoffice.resources;


import com.prestigeWallet.backoffice.dto.*;

import com.prestigeWallet.backoffice.entities.Product;
import com.prestigeWallet.backoffice.repositories.*;
import com.prestigeWallet.backoffice.services.*;
import org.modelmapper.ModelMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("/wallet")
public class WalletResource {

    private ICategoryRepository categoryRepository;
    private IProductRepository productRepository;
    private ICoulourRepository coulourRepository;
    private IMaterialRepository materialRepository;
    private IImageRepository imageRepository;
    private ICategoryService categoryService;
    private ModelMapperProvider modelMapperProvider;
    private IProductService productService;
    private ModelMapper modelMapper;

    public WalletResource() {
        this.productRepository = new ProductRepositoryImpl();
        this.categoryRepository = new CategoryRepositoryImpl();
        this.materialRepository = new MaterialRepositoryImpl();
        this.coulourRepository = new CoulourRepositoryImpl();
        this.imageRepository = new ImageRepositoryImpl();
        this.modelMapperProvider = new ModelMapperProvider();
        this.productService = new ProductServiceImpl(this.productRepository, this.categoryRepository,
                this.materialRepository, this.coulourRepository, this.imageRepository, this.modelMapperProvider);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductLightDTO> getWallets(){
        return this.productService.getWallets();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductDTO getProduct(@PathParam("id") Integer id) {
        return this.productService.getProductById(id);
    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductDTO addProduct(@FormParam("name") String name, @FormParam("description") String description,
                                 @FormParam("price") Double price, @FormParam("reward-point") Integer rewardPoint,
                                 @FormParam("stock") Integer stock, @FormParam("is-new") Boolean isNew,
                                 @FormParam("category-name") String categoryDTOName, @FormParam("material-name") String materialDTOName,
                                 @FormParam("coulour1-name") String coulour1, @FormParam("coulour2-name") String coulour2,
                                 @FormParam("image1-name") String image1, @FormParam("image2-name") String image2){
        CoulourDTO coulourDTO1 = new CoulourDTO();
        coulourDTO1.setName(coulour1);
        CoulourDTO coulourDTO2 = new CoulourDTO();
        coulourDTO2.setName(coulour2);
        Set<CoulourDTO> couloursDto = new HashSet<>();
        couloursDto.add(coulourDTO1);
        couloursDto.add(coulourDTO2);
        ImageDTO imageDTO1 = new ImageDTO();
        imageDTO1.setName(image1);
        imageDTO1.setThumbnail(true);
        ImageDTO imageDTO2 = new ImageDTO();
        imageDTO2.setName(image2);
        imageDTO2.setThumbnail(false);
        List<ImageDTO> images = new ArrayList<>();
        images.add(imageDTO1);
        images.add(imageDTO2);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(name);
        productDTO.setDescription(description);
        productDTO.setPrice(price);
        productDTO.setReward_point(rewardPoint);
        productDTO.setStock(stock);
        productDTO.setNew(isNew);
        productDTO.setCreatedAt(new Date());
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(categoryDTOName);
        productDTO.setCategory(categoryDTO);
        MaterialDTO materialDTO = new MaterialDTO();
        materialDTO.setName(materialDTOName);
        productDTO.setMaterial(materialDTO);
        productDTO.setCoulours(couloursDto);
        productDTO.setImages(images);
        this.productService.addProduct(productDTO);
        return productDTO;
    }



}
