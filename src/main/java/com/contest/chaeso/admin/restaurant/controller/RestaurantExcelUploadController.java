package com.contest.chaeso.admin.restaurant.controller;


import com.contest.chaeso.admin.restaurant.application.RestaurantExcelUploadService;
import com.contest.chaeso.admin.restaurant.controller.dto.RestaurantExcelDto;
import com.contest.chaeso.admin.restaurant.controller.dto.RestaurantMenuExcelDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
@RestController
public class RestaurantExcelUploadController {

    private final RestaurantExcelUploadService excelUploadService;


    @PostMapping("/rt/excel/upload")
    public String restaurantExcelUpload(@RequestParam("file") MultipartFile file, Model model) throws IOException {

        List<RestaurantExcelDto> restaurantExcelDtoList = new ArrayList<>(); //restaurant list
        List<RestaurantMenuExcelDto> restaurantMenuExcelDtoList = new ArrayList<>(); //restaurant menu list

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }

        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet1 = workbook.getSheetAt(0); // 1번쨰 sheet
        Sheet worksheet2 = workbook.getSheetAt(1); // 1번쨰 sheet

        String originalFilename = file.getOriginalFilename();
        log.info("[originalFilename] : {}", originalFilename);

        // sheet1 : restaurant
        for (int i = 2; i < worksheet1.getPhysicalNumberOfRows(); i++) { // 1행부터
            Row row = worksheet1.getRow(i);

            String name = row.getCell(0).getStringCellValue();
            String category = row.getCell(1).getStringCellValue();
            String phoneNumber = row.getCell(2).getStringCellValue();
            String address = row.getCell(3).getStringCellValue();
            Float corpLat = (float) row.getCell(4).getNumericCellValue();
            Float corpLon = (float) row.getCell(5).getNumericCellValue();
            int forHere = (int) row.getCell(6).getNumericCellValue();
            int toGo = (int) row.getCell(7).getNumericCellValue();
            int delivery = (int) row.getCell(8).getNumericCellValue();
            String bzh = row.getCell(9).getStringCellValue(); //영업일


            RestaurantExcelDto data = new RestaurantExcelDto(name, category, phoneNumber, address, corpLat, corpLon, forHere, toGo, delivery, bzh);

            restaurantExcelDtoList.add(data);
        }

        // sheet2 : restaurant menu
        for(int i = 2; i < worksheet2.getPhysicalNumberOfRows(); i++){
            Row row = worksheet2.getRow(i);

            String rtName = row.getCell(0).getStringCellValue();
            String menuName = row.getCell(1).getStringCellValue();
            int price = (int)row.getCell(2).getNumericCellValue();
            String imgLink = row.getCell(3).getStringCellValue();

            RestaurantMenuExcelDto restaurantMenuExcelDto = new RestaurantMenuExcelDto(rtName, menuName, price, imgLink);

            restaurantMenuExcelDtoList.add(restaurantMenuExcelDto);

        }

        model.addAttribute("datas", restaurantExcelDtoList); // 이제 여기 db에 넣기

        excelUploadService.saveRestaurantAndMEnu(restaurantExcelDtoList, restaurantMenuExcelDtoList);

        return "/admin/excel-list2";

    }


    private boolean isAllowedMIMEType(String mimeType) {
        if (mimeType.equals("application/x-tika-ooxml"))
            return true;
        return false;
    }

}
