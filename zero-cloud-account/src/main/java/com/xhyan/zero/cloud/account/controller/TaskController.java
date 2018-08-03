package com.xhyan.zero.cloud.account.controller;

import com.xhyan.zero.cloud.account.dto.TaskDTO;
import com.xhyan.zero.cloud.account.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "/task", description = "任务相关服务")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * 查询有效的任务列表
     *
     * @return List<TaskDTO>
     */
    @ApiOperation(notes = "query effective task", httpMethod = "GET", value = "查询全部生效的任务")
    @GetMapping(value = "/task/effective")
    public List<TaskDTO> queryEffectiveTask() {
        return taskService.queryEffectiveTask();
    }

}
