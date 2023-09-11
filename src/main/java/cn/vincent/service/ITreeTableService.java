package cn.vincent.service;

import cn.vincent.entity.TreeTable;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 孟子铭
 * @since 2023-09-08
 */
public interface ITreeTableService extends IService<TreeTable> {

    List<Map<String, Object>> getTree();

    List putTree(List<Map> list);
}
