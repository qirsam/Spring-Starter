package com.qirsam.spring.integration.service;

import com.qirsam.spring.database.pool.ConnectionPool;
import com.qirsam.spring.integration.annotation.IT;
import com.qirsam.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

@IT
@RequiredArgsConstructor
public class UserServiceIT {

    private final UserService userService;
    private final ConnectionPool pool1;

    @Test
    void test() {

    }
}
