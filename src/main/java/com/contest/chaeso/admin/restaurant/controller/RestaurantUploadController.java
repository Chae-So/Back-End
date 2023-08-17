package com.contest.chaeso.admin.restaurant.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
@RestController
public class RestaurantUploadController {


    @PostMapping("/rt/excel/upload")
    public void restaurantExcelUpload(){

    }

    public void restaurantMenuExcelUpload(){

    }
}
