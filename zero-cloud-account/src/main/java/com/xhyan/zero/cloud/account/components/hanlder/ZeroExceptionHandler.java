package com.xhyan.zero.cloud.account.components.hanlder;

import com.xhyan.zero.cloud.account.exception.OperationException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 统一异常处理器
 *
 * @author xhyan
 */
@ControllerAdvice
public class ZeroExceptionHandler {

    @ExceptionHandler(value = OperationException.class)
    public void jsonErrorHandler(HttpServletResponse resp,
        OperationException e) throws Exception {
        resp.setStatus(e.getCode());
    }
}
