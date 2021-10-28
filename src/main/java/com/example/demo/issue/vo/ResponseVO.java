package com.example.demo.issue.vo;

import lombok.Data;

/**
 * @author Tan Ke
 * @date 2021/10/27
 */
@Data
public class ResponseVO<T> {

    private Integer code;

    private String message;

    private T data;

    public static <T> ResponseVO<T> success(T t) {
        ResponseVO<T> resp = success();
        resp.setData(t);
        return resp;
    }

    public static <T> ResponseVO<T> success() {
        ResponseVO<T> resp = new ResponseVO<>();
        resp.setCode(CodeAndMsg.Common.OK.getCode());
        resp.setMessage(CodeAndMsg.Common.OK.getMessage());
        return resp;
    }

    public static <T> ResponseVO<T> failed(CodeAndMsg codeAndMsg) {
        ResponseVO<T> resp = new ResponseVO<>();
        resp.setCode(codeAndMsg.getCode());
        resp.setMessage(codeAndMsg.getMessage());
        return resp;
    }

    public static <T> ResponseVO<T> failed(CodeAndMsg codeAndMsg, T t) {
        ResponseVO<T> resp = new ResponseVO<>();
        resp.setCode(codeAndMsg.getCode());
        resp.setMessage(codeAndMsg.getMessage());
        resp.setData(t);
        return resp;
    }

}
