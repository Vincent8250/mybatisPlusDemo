package cn.vincent.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Mybatis-Plus 自动填充处理器
 */
@Slf4j
@Component
public class MybatisPlusHandler implements MetaObjectHandler {

    //注意！ 需要自动填充字段 需要标记fill属性
    //@TableField(.. fill = FieldFill.INSERT)
    //private String fillField;

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("数据插入前 注入 ....");
        this.strictInsertFill(metaObject, "userPwd", String.class, "123");
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("数据更新前 注入 ....");
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }
}