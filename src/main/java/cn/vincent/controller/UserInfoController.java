package cn.vincent.controller;

import cn.vincent.entity.UserInfo;
import cn.vincent.mapper.UserInfoMapper;
import cn.vincent.service.IUserInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    public String getUserInfo(){
        List<UserInfo> userInfos = iUserInfoService.list();
        return userInfos.toString();
    }
}
