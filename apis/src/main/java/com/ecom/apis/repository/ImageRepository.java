package com.ecom.apis.repository;

import com.ecom.apis.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageData,Long> {
}
