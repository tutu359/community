package com.tutu359.community.service;

import com.tutu359.community.dao.UserMapper;
import com.tutu359.community.entity.User;
import com.tutu359.community.util.CommunityConstant;
import com.tutu359.community.util.CommunityUtil;
import com.tutu359.community.util.MailClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class UserService implements CommunityConstant {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${community.path.domain}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    public User findUserById(int id) {
        return userMapper.selectById(id);
    }

    //注册逻辑
    public Map<String,Object> register(User user){
        Map<String,Object> map = new HashMap<>();

        //判断空值
        if(user==null){
            throw new IllegalArgumentException("参数不能为空！");
        }
        if(StringUtils.isBlank(user.getUsername())){
            map.put("usernameMsg","账号不能为空！");
            return map;
        }
        if(StringUtils.isBlank(user.getPassword())){
            map.put("passwordMsg","密码不能为空！");
            return map;
        }
        if(StringUtils.isBlank(user.getEmail())){
            map.put("emailMsg","邮箱不能为空！");
            return map;
        }

        //判断账号是否存在
        User userFromDB = userMapper.selectByName(user.getUsername());
        if(userFromDB!=null){
            map.put("usernameMsg","该账号已存在！");
            return map;
        }
        //判断邮箱是否存在
        userFromDB = userMapper.selectByEmail(user.getEmail());
        if(userFromDB!=null){
            map.put("emailMsg","该邮箱已被注册！");
            return map;
        }

        //数据没有问题，开始注册用户
        user.setSalt(CommunityUtil.generateUUID().substring(0,5));
        user.setPassword(CommunityUtil.getMD5(user.getPassword()+user.getSalt()));
        user.setActivationCode(CommunityUtil.generateUUID());
        user.setHeaderUrl(String.format("http://images.nowcoder.com/head/%dt.png",new Random().nextInt(1000)));
        userMapper.insertUser(user);

        //激活邮件
        Context context = new Context();
        context.setVariable("email",user.getEmail());
        String activationUrl = domain+contextPath+"/activation/"+user.getId()+"/"+user.getActivationCode();
        context.setVariable("activationUrl",activationUrl);
        String content = templateEngine.process("/mail/activation",context);
        mailClient.sendMail(user.getEmail(),"请激活您的论坛账号",content);

        return map;
    }

    //激活用户
    public int activation(int userId, String code) {
        User user = userMapper.selectById(userId);
        if(user.getStatus()==1){
            return ACTIVATION_REPEAT;
        }else if(user.getActivationCode().equals(code)){
            userMapper.updateStatus(userId,1);
            return ACTIVATION_SUCCESS;
        }else{
            return ACTIVATION_FAILURE;
        }
    }
}
