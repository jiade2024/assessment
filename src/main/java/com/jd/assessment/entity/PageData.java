package com.jd.assessment.entity;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @Author Jiade.Xu
 * @Date 2024/5/13 14:49
 */
@Schema(name = "PageData", description = "Pagination response")
@Data
public class PageData<T> {

    @Parameter(description = "page index from request")
    private long pageIndex;

    @Parameter(description = "data count per page")
    private int pageSize;

    @Parameter(description = "total pages for given request condition")
    private long totalPages;

    @Parameter(description = "data collection")
    private T data;

}
