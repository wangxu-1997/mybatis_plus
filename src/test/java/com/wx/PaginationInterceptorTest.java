package com.wx;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wx.mapper.UserMapper;
import com.wx.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class PaginationInterceptorTest {

    @Resource
    private UserMapper userMapper;

    //    测试分页查询
    @Test
    public void testPage() {
        Page<User> page = new Page<>(1, 3);
        userMapper.selectPage(page, null);
        List<User> records = page.getRecords();
        for (User record : records) {
            System.out.println(record);
        }
    }

    //    删除操作（id批量）
    @Test
    public void testDeleteBatchId() {
        userMapper.deleteBatchIds(Arrays.asList(9L, 10L));
    }

    //    删除操作（id批量）
    @Test
    public void testDeleteMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "godfrey111");
        userMapper.deleteByMap(map);
    }

    //    通过id删除
    @Test
    public void testDeleteById() {
       userMapper.deleteById(2L);
    }
//    查询所有
    @Test
    public void testSelectAll(){
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }
}
