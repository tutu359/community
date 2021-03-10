package com.tutu359.community.controller;

import com.tutu359.community.entity.DiscussPost;
import com.tutu359.community.entity.Page;
import com.tutu359.community.entity.User;
import com.tutu359.community.service.DiscussPostService;
import com.tutu359.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    /**
     * 论坛首页回显帖子
     * @param model 包含帖子及用户信息列表
     * @return index and model
     */
    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page) {
        /* 不用特地将page对象装到model中，在方法初始化的时候它就已经被装载进去了*/

        //为page对象设置最大行数
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index");

        List<DiscussPost> discussPostList = discussPostService.findDiscussPosts(0,page.getOffset(), page.getLimit());

        List<Map<String,Object>> list = new ArrayList<>();

        if(discussPostList!=null){
            for (DiscussPost discussPost : discussPostList) {
                Map<String,Object> map = new HashMap<>();
                User user = userService.findUserById(discussPost.getUserId());
                map.put("post",discussPost);
                map.put("user",user);
                list.add(map);
            }
        }

        model.addAttribute("discussPosts",list);
        return "/index";
    }


}
