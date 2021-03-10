package com.tutu359.community;

import com.tutu359.community.dao.DiscussPostMapper;
import com.tutu359.community.dao.UserMapper;
import com.tutu359.community.entity.DiscussPost;
import com.tutu359.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class DiscussPostMapperTests {

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Test
    public void testSelectDiscussPost() {
        List<DiscussPost> discussPosts = discussPostMapper.selectDiscussPosts(0, 0, 10);
        for (DiscussPost discussPost : discussPosts) {
            System.out.println(discussPost);
        }
    }

    @Test
    public void testSelectDiscussRows() {
        int i = discussPostMapper.selectDiscussPostRows(0);
        System.out.println("共有"+i+"条记录(未被加黑)");
    }


}
