package com.jd.assessment.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author Jiade.Xu
 * @Date 2024/5/11 10:26
 */
@Data
@Document("store")
public class Store {

    @Id
    private String storeId;

    private String name;

    private String description;

    private String email;

    private String image;

    private String categoryId;

    private String category;

    private String address;

    private String geolocation;
}
