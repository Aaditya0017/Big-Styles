package com.ecom.apis.repository;

import com.ecom.apis.entity.Cart;
import com.ecom.apis.entity.Products;
import com.ecom.apis.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    List<Cart> findAllByUser(UserEntity user);
    Cart findByCartId(Long id);

    @Query("select  c  from Cart c where c.products=:product and c.user=:user ")
    Cart findWithProductUser(@Param(value = "product") Products products, @Param(value = "user") UserEntity user);
}
