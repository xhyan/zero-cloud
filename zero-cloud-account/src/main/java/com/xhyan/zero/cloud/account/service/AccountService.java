package com.xhyan.zero.cloud.account.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageSerializable;
import com.xhyan.zero.cloud.account.dto.AccountDTO;
import com.xhyan.zero.cloud.account.dto.TaskDTO;
import com.xhyan.zero.cloud.account.dto.req.AccountQueryReq;
import com.xhyan.zero.cloud.account.enums.VerificationCodeTypeEnum;
import com.xhyan.zero.cloud.account.exception.OperationException;
import com.xhyan.zero.cloud.account.mapper.AccountMapper;
import com.xhyan.zero.cloud.account.mapper.AccountTaskMapper;
import com.xhyan.zero.cloud.account.mapper.TaskMapper;
import com.xhyan.zero.cloud.account.model.Account;
import com.xhyan.zero.cloud.account.model.AccountTask;
import com.xhyan.zero.cloud.account.model.Task;
import com.xhyan.zero.cloud.common.dto.ZeroPageInfo;
import com.xhyan.zero.cloud.common.dto.ZeroPageReq;
import com.xhyan.zero.cloud.common.enums.ErrorCodeEnum;
import java.util.List;
import java.util.stream.Collectors;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.apache.ibatis.session.RowBounds;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.weekend.WeekendSqls;

/**
 * Account Service
 *
 * @author xhyan
 */
@Service
public class AccountService {

    @Autowired
    private ConfigurableMapper copier;
    @Autowired
    private SmsService smsService;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountTaskMapper accountTaskMapper;
    @Autowired
    private TaskMapper taskMapper;

    /**
     * 用户注册
     */
    public void signUp(String mobile, String code) {
        if (!smsService.verify(mobile, VerificationCodeTypeEnum.SIGN_UP, code)) {
            throw new OperationException(ErrorCodeEnum.ERROR_50001.getCode(),
                ErrorCodeEnum.ERROR_50001.getMsg());
        }
        Account account = new Account();
        account.setStatus(1);
        account.setMobile(mobile);
        account.setLoginName(mobile);
        account.setEnergy(0);
        account.setMining(2);
        //保存账户信息
        accountMapper.insertSelective(account);
    }

    public AccountDTO findOne(Long id) {
        Account account = accountMapper.selectByPrimaryKey(id);
        return copier.map(account, AccountDTO.class);
    }

    /**
     * 分页查询账户信息
     */
    public ZeroPageInfo<AccountDTO> pageList(AccountQueryReq req) {
        PageHelper.startPage(req.getPageNumber(), req.getPageSize());
        List<Account> accountList = accountMapper.selectAll();
        PageInfo<Account> accounts = PageInfo.of(accountList);
        return ZeroPageInfo.<AccountDTO>builder().total(accounts.getTotal()).page(req.getPageNumber())
            .data(accounts.getList().stream().map(item -> copier.map(item, AccountDTO.class)).collect(
                Collectors.toList())).build();
    }

    /**
     * 实名认证
     */
    public void certification(Long accountId, String name, String identityCard) {
        Account account = new Account();
        account.setId(accountId);
        account.setName(name);
        account.setIdentityCard(identityCard);
        accountMapper.updateByPrimaryKeySelective(account);
    }

    public List<TaskDTO> queryAccountTasks(Long accountId) {
        List<AccountTask> accountTasks = accountTaskMapper.queryByAccountId(accountId);
        if (accountTasks.size() > 0) {
            Example example = new Example(Task.class);
            Criteria criteria = example.createCriteria();
            criteria.andIn("id",
                accountTasks.stream().map(AccountTask::getAccountId).collect(Collectors.toList()));
            return taskMapper.selectByExample(example).stream()
                .map(item -> copier.map(item, TaskDTO.class))
                .collect(
                    Collectors.toList());
        }
        return Lists.newArrayList();
    }


    @Transactional(rollbackFor = Exception.class)
    public Integer completeTask(Long accountId, Long taskId) {
        //查询任务信息
        Task task = taskMapper.selectByPrimaryKey(taskId);
        //账户任务数据写入新数据
        AccountTask accountTask = new AccountTask();
        accountTask.setAccountId(accountId);
        accountTask.setTaskId(taskId);
        accountTask.setEnergy(task.getEnergy());
        accountTaskMapper.insertSelective(accountTask);
        //账户信息增加能量值
        Account account = accountMapper.selectByPrimaryKey(accountId);
        Integer energy = account.getEnergy() + task.getEnergy();
        account.setEnergy(energy);
        accountMapper.updateByPrimaryKeySelective(account);
        return energy;
    }
}
