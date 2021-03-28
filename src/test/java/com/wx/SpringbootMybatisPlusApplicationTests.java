package com.wx;

import com.wx.mapper.UserMapper;
import com.wx.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SpringbootMybatisPlusApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setName("大橙子");
        user.setAge(23);
        user.setEmail("orange@163.com");
        int result = userMapper.insert(user);
        System.out.println(result);
        System.out.println(user);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(9L);
        user.setName("辣椒");
        int result = userMapper.updateById(user);
        System.out.println(result);
    }

    //    测试乐观锁成功
    @Test
    public void testOptimisticLocker1() {
        User user = userMapper.selectById(1L);
        user.setName("godfrey");
        userMapper.updateById(user);
    }

    //    测试乐观锁失败，多线程下
    @Test
    public void testOptimisticLocker2() {
        //线程1
        User user1 = userMapper.selectById(1L);
        user1.setName("godfrey111");
        user1.setEmail("13610506606@163.com");

        //模拟另外一个线程执行插队操作
        User user2 = userMapper.selectById(1L);
        user2.setName("godfrey222");
        user2.setEmail("13610506606@163.com");
        userMapper.updateById(user2);

        //自旋锁多次操作尝试提交
        userMapper.updateById(user1);
    }

    //    测试查询
    @Test
    public void testSelectById() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    //    测试批量查询
    @Test
    public void testSelectByBatchId() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
        for (User user : users) {
            System.out.println(user);
        }
    }

}
