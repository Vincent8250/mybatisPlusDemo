package cn.vincent.tool;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

public class CodeGenerator {

    /**
     * mybatis-Plus 反向代码生成
     *
     * @param args
     */
    public static void main(String[] args) {
        // 下面代码事直接从mybatis-Plus官网粘贴的
        // 相比官网追加了 spring-boot-starter-freemarker 依赖
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/oa?autoReconnect=true&useUnicode=true&createDatabaseIfNotExist=true&characterEncoding=utf8&useSSL=true&serverTimezone=UTC", "vincent", "vincent")
                .globalConfig(builder -> {
                    builder.author("孟子铭") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            .outputDir("D:\\github\\mybatisPlus_demo\\src\\main\\java"); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler(
                        (globalConfig, typeRegistry, metaInfo) -> {
                            int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                            if (typeCode == Types.SMALLINT)// 自定义类型转换
                                return DbColumnType.INTEGER;
                            return typeRegistry.getColumnType(metaInfo);
                        }))
                .packageConfig(builder -> {
                    builder.parent("cn.vincent") // 设置父包名
                            //.moduleName("entity") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\github\\mybatisPlus_demo\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user_info") // 设置需要生成的表名
                            .addTableSuffix("_1")// 设置过滤表后缀
                            .addTablePrefix("_t") // 设置过滤表前缀
                            .entityBuilder().enableFileOverride();//entity生成时覆盖
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
