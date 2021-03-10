package com.tutu359.community.dao;

import com.tutu359.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {


    /**
     * 查询帖子列表
     * @param userId 传入只查询对应的用户帖子列表
     * @param offset 从哪个下标开始查
     * @param limit  查多少条
     * @return  返回帖子对象的list
     */
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    // @Param注解用于给参数取别名,
    // 如果只有一个参数,并且在<if>里使用,则必须加别名.
    /**
     * 查询帖子数量
     * @param userId 传入只查询对应的用户帖子数量
     * @return 数值
     */
    int selectDiscussPostRows(@Param("userId") int userId);

}
