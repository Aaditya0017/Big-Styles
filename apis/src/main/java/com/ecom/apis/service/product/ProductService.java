package com.ecom.apis.service.product;

import com.ecom.apis.entity.ImageData;
import com.ecom.apis.entity.Products;
import com.ecom.apis.model.ImageModel;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.NoSuchElementException;

public interface ProductService {
    String addProducts(Products products);

    List<Products> allProducts();

    List<Products> sellerProducts(Long id);

    String removeProduct(Long id);

    String addProductImage(ImageModel imageData) throws IOException;

    byte[] showImage(Long id) throws NoSuchElementException;

}
