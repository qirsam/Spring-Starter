package com.qirsam.spring.service;

import com.qirsam.spring.database.repository.CompanyRepository;
import com.qirsam.spring.database.repository.CrudRepository;
import com.qirsam.spring.database.repository.UserRepository;
import com.qirsam.spring.entity.Company;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CrudRepository<Integer, Company> companyRepository;

    public UserService(UserRepository userRepository,
                       CrudRepository<Integer, Company> companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }
}
