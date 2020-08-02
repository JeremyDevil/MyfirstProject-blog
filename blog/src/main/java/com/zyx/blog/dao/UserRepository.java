package com.zyx.blog.dao;

import com.zyx.blog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
            User findByUsernameAndPassword(String name,String password);
}
