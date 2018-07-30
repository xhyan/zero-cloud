package com.xhyan.zero.cloud.account.controller;

import com.xhyan.zero.cloud.account.dto.TaskDTO;
import com.xhyan.zero.cloud.account.service.TaskService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 任务信息控制器
 *
 * @author xhyan
 */
@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * 查询有效的任务列表
     *
     * @return List<TaskDTO>
     */
    @GetMapping(value = "/task/effective")
    public List<TaskDTO> queryEffectiveTask() {
        return taskService.queryEffectiveTask();
    }

}
