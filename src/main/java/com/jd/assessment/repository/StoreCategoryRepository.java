package com.jd.assessment.repository;

import com.jd.assessment.entity.StoreCategory;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author Jiade.Xu
 * @Date 2024/5/11 11:02
 */
public interface StoreCategoryRepository extends MongoRepository<StoreCategory, Integer> {

    StoreCategory findByCategoryId(String categoryId);
}
