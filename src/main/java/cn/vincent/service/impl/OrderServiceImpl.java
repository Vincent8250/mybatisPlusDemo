package cn.vincent.service.impl;

import cn.vincent.entity.Order;
import cn.vincent.mapper.OrderMapper;
import cn.vincent.service.IOrderService;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
