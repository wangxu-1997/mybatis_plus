package com.wx;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wx.mapper.UserMapper;
import com.wx.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class ConditionTest {
    @Resource
    UserMapper userMapper;

    @Test
    public void test1() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("name");
        wrapper.isNotNull("email");
        wrapper.ge("age", 22);
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }

    @Test
    public void test2() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "Tom");
        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test3() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age", 23, 30);
        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test4() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.likeRight("name", "o");
        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test5() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.inSql("id", "select id from user where id<4");
        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }

    }
}
