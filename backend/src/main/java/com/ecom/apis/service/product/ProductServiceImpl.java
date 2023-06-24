package com.ecom.apis.service.product;

import com.ecom.apis.Jwt.JwtAuthFilter;
import com.ecom.apis.entity.ImageData;
import com.ecom.apis.entity.ProductRating;
import com.ecom.apis.entity.Products;
import com.ecom.apis.exceptionHandling.exceptions.NotFoundException;
import com.ecom.apis.model.ImageModel;
import com.ecom.apis.repository.ImageRepository;
import com.ecom.apis.repository.ProductRepository;
import com.ecom.apis.repository.RatingRepository;
import com.ecom.apis.service.user.UserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImageRepository imageRepository ;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private JwtAuthFilter jwtAuthFilter;
    @Autowired
    private UserServiceImplement userServiceImplement;



    private String saveProduct(Products products) {
        if (Objects.nonNull(productRepository.save(products))){
            return "success";
        }else return  "error";}

    public Products findProductById(Long productId) throws NotFoundException {
        Products products= productRepository.findProductsByProductId(productId);
        if(products!=null) return products;
        else throw new NotFoundException("No product found with ProductId "+productId);
    }

    @Override
    public String addProducts(Products products) {
        if(products.getName()==null && products.getPrice()==0 && products.getQuantity()==0 && products.getSpecification()==null)
            return "all details are required";
        else
           return saveProduct(products);
    }

    @Override
    public List<Products> allProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Products> sellerProducts(Long id) {
        return productRepository.findAllBySellerId(id);
    }

    @Override
    public String removeProduct(Long id) {
       if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return "deleted success fully";
    }else return "no such product with id "+id;
    }

    @Override
    public String addProductImage(ImageModel imageModel) throws IOException, NotFoundException {

        if (imageModel != null) {
            ImageData image = ImageData.builder().imageData(ImageModel.compressImage(imageModel.getImage().getBytes()))
                    .name(imageModel.getName())
                    .products(findProductById(imageModel.getProductId()))
                    .build();
            imageRepository.save(image);
            return "success";
        }
        else return "check data";

    }

    public byte[] showImage(Long id) throws NoSuchElementException {
        Optional<ImageData> byId = imageRepository.findById(id);
        if (byId.isEmpty()) throw new NoSuchElementException("No Such related Images");
        else return ImageModel.decompressImage(byId.get().getImageData());
    }

    @Override
    public Products productFromId(Long productId) throws NoSuchElementException, NotFoundException {
        return findProductById(productId);
    }

    @Override
    public String addRating(Long productId, Double rating) throws NotFoundException {
        ProductRating productRating= ProductRating.builder()
                .products(findProductById(productId))
                .rating(rating)
                .user(userServiceImplement.getUserByEmail(jwtAuthFilter.getUser()))
                .build();
        ratingRepository.save(productRating);
        return "success";

    }

    @Override
    public Double getProductRating(Long productId) throws NotFoundException {
        return ratingRepository.averageRating(findProductById(productId));
    }


    public void productQuantityUpdate(List<Products> productsInCart) {
        for (Products product: productsInCart){
            Products productsByProductId = productRepository.findProductsByProductId(product.getProductId());
            productsByProductId.setQuantity(productsByProductId.getQuantity()-product.getQuantity());
            saveProduct(productsByProductId);
        }
    }
}
