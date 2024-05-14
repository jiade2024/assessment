package com.jd.assessment.repository;

import com.jd.assessment.entity.Store;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author Jiade.Xu
 * @Date 2024/5/11 10:29
 */
public interface StoreRepository extends MongoRepository<Store, Integer> {


}
