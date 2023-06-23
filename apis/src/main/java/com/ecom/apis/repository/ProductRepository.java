package com.ecom.apis.repository;

import com.ecom.apis.entity.Products;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products,Long> {
    @Transactional
    void deleteBySellerId(Long sellerId);
    public List<Products> findAllBySellerId(Long id);
    Products findProductsByProductId(Long id);
}
