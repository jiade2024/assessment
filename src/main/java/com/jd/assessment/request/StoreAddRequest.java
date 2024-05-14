package com.jd.assessment.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @Author Jiade.Xu
 * @Date 2024/5/11 10:52
 */
@Data
public class StoreAddRequest {

    @NotNull(message = "name can not be empty")
    private String name;

    @NotNull(message = "description can not be empty")
    private String description;

    @NotNull(message = "email can not be empty")
    @Email(message = "invalid email")
    private String email;

    @NotNull(message = "categoryId can not be empty")
    private String categoryId;

    @NotNull(message = "address can not be empty")
    private String address;

    @NotNull(message = "geolocation can not be empty")
    private String geolocation;
}
