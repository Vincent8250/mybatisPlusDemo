package cn.vincent.controller;

import cn.vincent.service.ITreeTableService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.sun.corba.se.spi.ior.ObjectKey;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 孟子铭
 * @since 2023-09-08
 */
@RestController
@RequestMapping("/treeTable")
public class TreeTableController {

    @Autowired
    ITreeTableService service;

    @SneakyThrows
    @GetMapping("/getTree")
    public String getTree() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(service.getTree());
    }

    @PostMapping("/putTree")
    public List putTree(@RequestBody List<Map> list) {
        return service.putTree(list);
    }
}
