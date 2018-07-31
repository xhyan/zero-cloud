package com.xhyan.zero.cloud.account.dto.resp;

import com.xhyan.zero.cloud.account.dto.AccountDTO;
import com.xhyan.zero.cloud.account.dto.TaskDTO;
import java.util.List;
import lombok.Builder;

/**
 * 账户已完成任务响应对象
 *
 * @author xhyan
 */
@Builder
public class AccountAllInfo {
    private AccountDTO account;
    private List<TaskDTO> taskList;
}
