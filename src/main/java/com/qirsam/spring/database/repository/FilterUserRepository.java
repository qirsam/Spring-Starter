package com.qirsam.spring.database.repository;

import com.qirsam.spring.database.entity.User;
import com.qirsam.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter userFilter);
}
