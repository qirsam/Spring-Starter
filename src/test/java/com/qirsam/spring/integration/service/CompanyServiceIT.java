package com.qirsam.spring.integration.service;

import com.qirsam.spring.config.DatabaseProperties;
import com.qirsam.spring.dto.CompanyReadDto;
import com.qirsam.spring.integration.annotation.IT;
import com.qirsam.spring.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
public class CompanyServiceIT {

    private final CompanyService companyService;
    private final DatabaseProperties databaseProperties;
    private static final Integer COMPANY_ID = 1;


    @Test
    void findById() {
        var actualResult = companyService.findById(COMPANY_ID);

        assertTrue(actualResult.isPresent());

        var expectedResult = new CompanyReadDto(COMPANY_ID);
        actualResult.ifPresent(actual ->  assertEquals(expectedResult, actual));
    }
}
