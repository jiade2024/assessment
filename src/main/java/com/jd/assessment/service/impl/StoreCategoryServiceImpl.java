package com.jd.assessment.service.impl;

import com.jd.assessment.entity.StoreCategory;
import com.jd.assessment.repository.StoreCategoryRepository;
import com.jd.assessment.service.StoreCategoryService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * @Author Jiade.Xu
 * @Date 2024/5/11 11:01
 */
@Service
public class StoreCategoryServiceImpl implements StoreCategoryService {

    @Resource
    private StoreCategoryRepository storeCategoryRepository;

    @Override
    public List<StoreCategory> selectAll() {
        return storeCategoryRepository.findAll();
    }

    @Override
    public StoreCategory selectByCategoryId(String categoryId) {
        return storeCategoryRepository.findByCategoryId(categoryId);
    }
}
