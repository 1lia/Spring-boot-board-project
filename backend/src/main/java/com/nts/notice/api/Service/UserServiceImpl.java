package com.nts.notice.api.Service;

import com.nts.notice.db.entity.User;
import com.nts.notice.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long insertUser() {
        User user = User.builder()
                .id("회원아이디")
                .password("회원비밀번호")
                .name("회원이름")
                .build();
        userRepository.save(user);
        return user.getUserId();
    }
}
