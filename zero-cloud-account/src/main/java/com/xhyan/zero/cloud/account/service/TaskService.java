package com.xhyan.zero.cloud.account.service;

import com.xhyan.zero.cloud.account.dto.TaskDTO;
import com.xhyan.zero.cloud.account.mapper.AccountTaskMapper;
import com.xhyan.zero.cloud.account.mapper.TaskMapper;
import com.xhyan.zero.cloud.account.model.Task;
import java.util.List;
import java.util.stream.Collectors;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 任务服务
 *
 * @author xhyan
 */
@Service
public class TaskService {

    @Autowired
    private ConfigurableMapper copier;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private AccountTaskMapper accountTaskMapper;

    public List<TaskDTO> queryEffectiveTask() {
        Task task = new Task();
        task.setStatus(1);
        return taskMapper.select(task).stream().map(item -> copier.map(item, TaskDTO.class))
            .collect(
                Collectors.toList());

    }
}
