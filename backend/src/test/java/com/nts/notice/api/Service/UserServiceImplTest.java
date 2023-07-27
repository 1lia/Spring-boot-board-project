package com.nts.notice.api.Service;

import com.nts.notice.db.entity.User;
import com.nts.notice.db.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@DisplayName("User 테스트")
class UserServiceImplTest {
    UserRepository userRepository;

    @Autowired
    public UserServiceImplTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    @Transactional
    @DisplayName("회원가입 테스트")
    void insertUser() {
        User user = User.builder()
                .id("testid")
                .password("testpass")
                .name("testname")
                .build();
        userRepository.save(user);
        log.debug("PK = {}", user.getUserId());
        log.debug("회원가입테스트 : id = {} , pass = {} name = {}", user.getId(), user.getPassword(), user.getName());
    }
}