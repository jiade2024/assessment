package com.jd.assessment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.jd.assessment.service.StoreService;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Jiade.Xu
 * @Date 2024/5/11 17:36
 */
@Tag(name = "StoreController", description = "Store controller api for front-end")
@Controller
@RequestMapping("/store")
public class StoreController {

    @Resource
    private StoreService storeService;

    @Operation(summary = "store list page")
    @GetMapping("")
    public String storesPage(String keyword, Model model){
        model.addAttribute("keyword", keyword);
        model.addAttribute("storeList", storeService.search(keyword));
        return "store";
    }
}
