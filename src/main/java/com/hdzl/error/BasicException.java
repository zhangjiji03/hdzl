package com.hdzl.error;


import com.hdzl.result.Result;
import com.hdzl.result.ResultStateEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BasicException extends RuntimeException{

    private Integer code;
    private String msg;

    /**
     * 继承exception，加入错误状态值
     */
    public BasicException(Result result) {
        super(result.getMessage());
        this.msg = result.getMessage();
        this.code = result.getCode();
    }

    /**
     * 自定义错误信息
     */
    public BasicException(Integer code, String message) {
        super(message);
        this.msg = message;
        this.code = code;
    }

    /**
     * 自定义错误信息
     */
    public BasicException(String message) {
        super(message);
        this.code = ResultStateEnum.ERROR.getCode();
        this.msg = message;
    }

}
