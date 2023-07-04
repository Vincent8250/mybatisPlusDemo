package cn.vincent.controller;

import cn.vincent.entity.UserInfo;
import cn.vincent.mapper.UserInfoMapper;
import cn.vincent.service.IUserInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 孟子铭
 * @since 2023-07-03
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    IUserInfoService iUserInfoService;

    @ResponseBody
    @GetMapping("/getUserInfo")
    public String getUserInfo() throws JsonProcessingException {
        IPage<UserInfo> iPage = new Page<>();
        iPage.setSize(2);
        IPage<UserInfo> userInfoIPage = iUserInfoService.page(iPage);
        ObjectMapper jacksonMapper = new ObjectMapper();
        return jacksonMapper.writeValueAsString(userInfoIPage);
    }

    @ResponseBody
    @GetMapping("/addUserInfo")
    public String addUserInfo(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("熏晴微穗");
        Boolean flag = iUserInfoService.save(userInfo);
        return flag.toString();
    }
}
