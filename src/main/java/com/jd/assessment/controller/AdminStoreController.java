package com.jd.assessment.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import com.jd.assessment.entity.PageData;
import com.jd.assessment.entity.Store;
import com.jd.assessment.entity.StoreCategory;
import com.jd.assessment.enums.ResultCode;
import com.jd.assessment.request.StoreAddRequest;
import com.jd.assessment.response.HttpResponse;
import com.jd.assessment.service.StoreCategoryService;
import com.jd.assessment.service.StoreService;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Jiade.Xu
 * @Date 2024/5/11 10:35
 */
@Tag(name = "AdminStoreController", description = "Store controller api for back-end")
@Controller
@RequestMapping("/admin/store")
public class AdminStoreController {

    @Resource
    private StoreService storeService;
    @Resource
    private StoreCategoryService storeCategoryService;

    @GetMapping("")
    public String listPage(String keyword, String category, int pageIndex, int pageSize, Model model){
        model.addAttribute("storeCategoryList", storeCategoryService.selectAll());
        PageData<List<Store>> pageData = storeService.searchByPage(keyword, category, pageIndex, pageSize);
        model.addAttribute("storeList", pageData.getData());
        model.addAttribute("keyword", keyword == null ? "" : keyword);
        model.addAttribute("category", category == null ? "" : category);
        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("totalPages", pageData.getTotalPages());
        return "adminStoreList";
    }

    @PostMapping("")
    @ResponseBody
    public HttpResponse doAdd(@RequestPart("image") MultipartFile image,
                              @RequestPart("request") @Valid StoreAddRequest request){
        StoreCategory storeCategory = storeCategoryService.selectByCategoryId(request.getCategoryId());
        if(storeCategory == null){
            return HttpResponse.fail(ResultCode.PARAMETER_ERROR, "No store category found by id: " + request.getCategoryId());
        }
        try {
            String base64Image = Base64.getEncoder().encodeToString(image.getBytes());
            storeService.add(request, base64Image, storeCategory.getTitle());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return HttpResponse.success();
    }

    @DeleteMapping("/{storeId}")
    @ResponseBody
    public HttpResponse doDelete(@PathVariable("storeId") String storeId){
        storeService.delete(storeId);
        return HttpResponse.success();
    }

    @PutMapping("/{storeId}")
    @ResponseBody
    public HttpResponse doUpdate(@PathVariable("storeId") String storeId,
                                 @RequestPart(value = "image", required = false) MultipartFile image,
                                 @RequestPart("request") StoreAddRequest request){
        StoreCategory storeCategory = storeCategoryService.selectByCategoryId(request.getCategoryId());
        if(storeCategory == null){
            return HttpResponse.fail(ResultCode.PARAMETER_ERROR, "No store category found by id: " + request.getCategoryId());
        }
        String base64Image = "";
        if(image != null && !image.isEmpty()){
            try {
                base64Image = Base64.getEncoder().encodeToString(image.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        storeService.update(storeId, request, base64Image, storeCategory.getTitle());
        return HttpResponse.success();
    }
}
