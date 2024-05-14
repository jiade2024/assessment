package com.jd.assessment.service.impl;

import com.jd.assessment.entity.PageData;
import com.jd.assessment.entity.Store;
import com.jd.assessment.entity.StoreCategory;
import com.jd.assessment.repository.StoreCategoryRepository;
import com.jd.assessment.repository.StoreRepository;
import com.jd.assessment.request.StoreAddRequest;
import com.jd.assessment.service.StoreCategoryService;
import com.jd.assessment.service.StoreService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Author Jiade.Xu
 * @Date 2024/5/11 12:51
 */
@Service
public class StoreServiceImpl implements StoreService {

    @Resource
    private StoreRepository storeRepository;
    @Resource
    private MongoTemplate mongoTemplate;
    @Resource
    private StoreCategoryRepository storeCategoryRepository;

    @Override
    public PageData<List<Store>> searchByPage(String keyword, String category, long pageIndex, int pageSize) {
        PageData<List<Store>> pageData = new PageData<>();

        Query query = new Query();
        if(StringUtils.hasText(keyword)){
            query.addCriteria(Criteria.where("name").regex(keyword));
        }
        if(StringUtils.hasText(category)){
            query.addCriteria(Criteria.where("categoryId").is(category));
        }
        long dataCount = mongoTemplate.count(query, Store.class);

        query.skip((pageIndex - 1) * pageSize).limit(pageSize);

        pageData.setPageIndex(pageIndex);
        pageData.setPageSize(pageSize);
        pageData.setData(mongoTemplate.find(query, Store.class));
        pageData.setTotalPages(dataCount % pageSize == 0 ? dataCount / pageSize : dataCount / pageSize + 1);
        return pageData;
    }

    @Override
    public List<Store> search(String keyword) {
        Query query = new Query();
        if(StringUtils.hasText(keyword)){
            query.addCriteria(Criteria.where("name").regex(keyword));
        }
        return mongoTemplate.find(query, Store.class);
    }

    @Override
    public void add(StoreAddRequest request, String imageBase64Url, String category) {
        Store store = new Store();
        store.setStoreId(UUID.randomUUID().toString());
        store.setName(request.getName());
        store.setDescription(request.getDescription());
        store.setCategory(category);
        store.setCategoryId(request.getCategoryId());
        store.setEmail(request.getEmail());
        store.setImage(imageBase64Url);
        store.setAddress(request.getAddress());
        store.setGeolocation(request.getGeolocation());
        storeRepository.save(store);
    }

    @Override
    public void update(String storeId, StoreAddRequest request, String imageBase64Url, String category) {
        Store store = new Store();
        store.setStoreId(storeId);
        Example<Store> example = Example.of(store);
        Optional<Store> optionalStore = storeRepository.findOne(example);
        if(optionalStore.isPresent()){
            store = optionalStore.get();

            store.setName(request.getName());
            store.setDescription(request.getDescription());
            store.setCategory(category);
            store.setCategoryId(request.getCategoryId());
            store.setEmail(request.getEmail());
            if(StringUtils.hasText(imageBase64Url)){
                store.setImage(imageBase64Url);
            }
            store.setAddress(request.getAddress());
            store.setGeolocation(request.getGeolocation());
            storeRepository.save(store);
        }

    }

    @Override
    public void delete(String storeId) {
        Store store = new Store();
        store.setStoreId(storeId);
        storeRepository.delete(store);
    }

}
