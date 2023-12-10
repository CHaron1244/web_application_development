package com.friend.config;

import lombok.Data;

@Data
public class Result<T> {
    // 前后端交互统一相应结果
    private String code;
    private String msg;
    private T data;

    public Result() {
    }

    public Result(T data) {
        this.data = data;
    }

    public static Result success() {
        Result result = new Result<>();
        result.setCode("0");
        result.setMsg("successfully executed");
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result result = new Result<>(data);
        result.setCode("0");
        result.setMsg("successfully executed");
        return result;
    }

    public static Result error(String code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}

