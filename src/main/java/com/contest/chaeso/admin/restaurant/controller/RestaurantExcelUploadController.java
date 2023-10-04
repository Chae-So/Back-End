package com.contest.chaeso.admin.restaurant.controller;


import com.contest.chaeso.admin.restaurant.application.RestaurantExcelUploadService;
import com.contest.chaeso.admin.restaurant.controller.dto.RestaurantExcelDto;
import com.contest.chaeso.admin.restaurant.controller.dto.RestaurantMenuExcelDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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


    @ApiOperation(value = "레스토랑 관련 엑셀 업로드 api", notes = "레스토랑 관련 엑셀 업로드를 수행한다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "엑셀 업로드 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 파라미터"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @PostMapping("/rt/excel/upload")
    public ResponseEntity<String> restaurantExcelUpload(@RequestPart("file") MultipartFile file) throws IOException {

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
        Sheet worksheet2 = workbook.getSheetAt(1); // 2번쨰 sheet

        String originalFilename = file.getOriginalFilename();
        log.info("[originalFilename] : {}", originalFilename);

        // sheet1 : restaurant
        for (int i = 2; i < worksheet1.getPhysicalNumberOfRows(); i++) { // 1행부터
            Row row = worksheet1.getRow(i);

            String name = row.getCell(0).getStringCellValue().strip();
            log.info("====== name : " + name);
            String category = row.getCell(1).getStringCellValue().strip();
            log.info("====== category : " + category);
            String phoneNumber = row.getCell(2).getStringCellValue().strip();
            log.info("====== phonenumber" + phoneNumber);
            String address = row.getCell(3).getStringCellValue().strip();
            log.info("======" + address);
            Double corpLat = row.getCell(4).getNumericCellValue();
            log.info("======" + corpLat);
            Double corpLon = row.getCell(5).getNumericCellValue();
            log.info("======" + corpLon);
            int forHere = (int) row.getCell(6).getNumericCellValue();
            log.info("======" + forHere);
            int toGo = (int) row.getCell(7).getNumericCellValue();
            log.info("======" + toGo);
            int delivery = (int) row.getCell(8).getNumericCellValue();
            log.info("======" + delivery);
            String rtImgLink = row.getCell(9).getStringCellValue().strip();
            log.info("======" + rtImgLink);
            String bzh = row.getCell(10).getStringCellValue().strip();
            log.info("======" + bzh);


            RestaurantExcelDto data = new RestaurantExcelDto(name, category, phoneNumber, address, corpLat, corpLon, forHere, toGo, delivery, rtImgLink, bzh);
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


        excelUploadService.saveRestaurantAndMenu(restaurantExcelDtoList, restaurantMenuExcelDtoList);

        return new ResponseEntity<>("success", HttpStatus.OK);

    }


    private boolean isAllowedMIMEType(String mimeType) {
        if (mimeType.equals("application/x-tika-ooxml"))
            return true;
        return false;
    }

}
