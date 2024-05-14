package com.jd.assessment.service;

import com.jd.assessment.entity.StoreCategory;

import java.util.List;

/**
 * @Author Jiade.Xu
 * @Date 2024/5/11 11:01
 */
public interface StoreCategoryService {

    List<StoreCategory> selectAll();

    StoreCategory selectByCategoryId(String categoryId);
}
