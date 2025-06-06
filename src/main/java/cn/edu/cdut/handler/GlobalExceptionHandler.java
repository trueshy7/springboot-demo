package cn.edu.cdut.handler;

import cn.edu.cdut.pojo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Result handleNullPointException(Exception e) {
        System.out.println("发生空指针异常");
        return Result.error("发生了业务错误 " + e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result handleRuntimeException(Exception e) {
        System.out.println("运行时异常");
        return Result.error(e.getMessage());
    }
}
