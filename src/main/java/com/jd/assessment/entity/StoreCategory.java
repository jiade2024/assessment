package com.jd.assessment.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @Author Jiade.Xu
 * @Date 2024/5/11 11:01
 */
@Data
public class StoreCategory {

    @Id
    private String categoryId;

    private String title;
}
