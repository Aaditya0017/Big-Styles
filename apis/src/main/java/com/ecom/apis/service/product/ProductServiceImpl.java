package com.ecom.apis.service.product;

import com.ecom.apis.entity.ImageData;
import com.ecom.apis.entity.Products;
import com.ecom.apis.model.ImageModel;
import com.ecom.apis.repository.CartRepository;
import com.ecom.apis.repository.ImageRepository;
import com.ecom.apis.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ImageRepository imageRepository ;

    @Override
    public String addProducts(Products products) {
        System.out.println(products.getName()+" "+products.getQuantity()+" "+products.getPrice());
        if(products.getName()==null && products.getPrice()==0 && products.getQuantity()==0 && products.getSpecification()==null) return "all detail required";
        else {
        if (Objects.nonNull(productRepository.save(products))){
            return "success";
        }else return  "error";}
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
        if (Objects.isNull(productRepository.findById(id))) return "Product no such product";
        else {
            productRepository.deleteById(id);
            return "deleted success fully";
        }
    }

    @Override
    public String addProductImage(ImageModel imageModel) throws IOException {

        if (imageModel != null) {
            ImageData image = ImageData.builder().imageData(ImageModel.compressImage(imageModel.getImage().getBytes()))
                    .name(imageModel.getName())
                    .products(productRepository.findProductsByProductId(imageModel.getProductId()))
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


}
