package com.jd.assessment.service;

import com.jd.assessment.entity.PageData;
import com.jd.assessment.entity.Store;
import com.jd.assessment.request.StoreAddRequest;

import java.util.List;

/**
 * @Author Jiade.Xu
 * @Date 2024/5/11 10:31
 */
public interface StoreService {

    PageData<List<Store>> searchByPage(String keyword, String category, long pageIndex, int pageSize);

    List<Store> search(String keyword);

    void add(StoreAddRequest request, String imageBase64Url, String category);

    void update(String storeId, StoreAddRequest request, String imageBase64Url, String category);

    void delete(String storeId);
}
