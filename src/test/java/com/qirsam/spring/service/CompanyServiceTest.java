package com.qirsam.spring.service;

import com.qirsam.spring.database.entity.Company;
import com.qirsam.spring.database.repository.CompanyRepository;
import com.qirsam.spring.dto.CompanyReadDto;
import com.qirsam.spring.listener.EntityEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    private static final Integer COMPANY_ID = 1;

    @InjectMocks
    private CompanyService companyService;
    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private UserService userService;
    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Test
    void findById() {
        doReturn(Optional.of(new Company(COMPANY_ID, null, Collections.emptyMap())))
                .when(companyRepository).findById(COMPANY_ID);

        var actualResult = companyService.findById(COMPANY_ID);

        assertTrue(actualResult.isPresent());

        var expectedResult = new CompanyReadDto(COMPANY_ID);
        actualResult.ifPresent(actual ->  assertEquals(expectedResult, actual));

        verify(eventPublisher).publishEvent(any(EntityEvent.class));
        verifyNoMoreInteractions(eventPublisher, userService);
    }
}