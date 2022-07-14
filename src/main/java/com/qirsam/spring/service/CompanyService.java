package com.qirsam.spring.service;

import com.qirsam.spring.database.repository.CompanyRepository;
import com.qirsam.spring.dto.CompanyReadDto;
import com.qirsam.spring.listener.AccessType;
import com.qirsam.spring.listener.EntityEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional()
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;


    public Optional<CompanyReadDto> findById(Integer id) {
        return companyRepository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                    return new CompanyReadDto(entity.getId());
                });
    }


}
