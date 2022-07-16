package com.qirsam.spring.service;

import com.qirsam.spring.database.repository.CompanyRepository;
import com.qirsam.spring.database.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
}
