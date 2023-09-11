package cn.vincent.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.vincent.entity.TreeTable;
import cn.vincent.mapper.TreeTableMapper;
import cn.vincent.service.ITreeTableService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 孟子铭
 * @since 2023-09-08
 */
@Service
public class TreeTableServiceImpl extends ServiceImpl<TreeTableMapper, TreeTable> implements ITreeTableService {

    CopyOptions OPTIONS = CopyOptions.create()
            .setIgnoreNullValue(true)  // 忽略源对象属性为空的情况
            .setIgnoreError(true);     // 忽略复制过程中出现的错误

    @Override
    public List<Map<String, Object>> getTree() {
        ArrayList<Map<String, Object>> tree = new ArrayList<>();
        QueryWrapper<TreeTable> wrapper = new QueryWrapper<>();
        wrapper.eq("level", 1);
        List<TreeTable> parentNode = baseMapper.selectList(wrapper);
        if (parentNode.size() > 0)
            parentNode.forEach(parent -> {
                Map<String, Object> parentMap = BeanUtil.beanToMap(parent);
                parentMap.put("children", findAllChild(parent));
                tree.add(parentMap);
            });
        return tree;
    }

    @Override
    public List putTree(List<Map> list) {
        ArrayList<TreeTable> trees = new ArrayList<>();
        list.forEach(map -> {
            TreeTable treeTable = BeanUtil.mapToBean(map, TreeTable.class, false, OPTIONS);
            trees.add(treeTable);
            trees.addAll(findAllChild((List<Map<String, Object>>) map.get("children")));
        });
        //trees.stream().forEach(baseMapper::updateById);
        return trees;
    }

    private List<Map<String, Object>> findAllChild(TreeTable parentNode) {
        ArrayList<Map<String, Object>> childList = new ArrayList<>();
        QueryWrapper<TreeTable> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentNode.getId());
        List<TreeTable> childNode = baseMapper.selectList(wrapper);
        if (childNode.size() > 0)
            childNode.forEach(
                    child -> {
                        Map<String, Object> childMap = BeanUtil.beanToMap(child);
                        childMap.put("children", findAllChild(child));
                        childList.add(childMap);
                    }
            );
        return childList;
    }

    private List<TreeTable> findAllChild(List<Map<String, Object>> childList) {
        ArrayList<TreeTable> trees = new ArrayList<>();
        childList.forEach(child -> {
            TreeTable treeTable = BeanUtil.mapToBean(child, TreeTable.class, false, OPTIONS);
            trees.add(treeTable);
            trees.addAll(findAllChild((List<Map<String, Object>>) child.get("children")));
        });
        return trees;
    }
}
