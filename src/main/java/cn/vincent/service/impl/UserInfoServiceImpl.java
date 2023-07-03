package cn.vincent.service.impl;

import cn.vincent.entity.UserInfo;
import cn.vincent.mapper.UserInfoMapper;
import cn.vincent.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 孟子铭
 * @since 2023-07-03
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
