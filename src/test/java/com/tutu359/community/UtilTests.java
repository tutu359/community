package com.tutu359.community;

import com.tutu359.community.util.CommunityUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class UtilTests {

    @Test
    public void testUUID(){
        System.out.println(CommunityUtil.generateUUID());
    }

    @Test
    public void testMD5(){
        System.out.println(CommunityUtil.getMD5("qweasd11"));
    }
}
