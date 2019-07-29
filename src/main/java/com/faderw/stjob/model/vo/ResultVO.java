package com.faderw.stjob.model.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author FaderW
 * 2019/7/29
 */
@Getter
@Setter
public class ResultVO<T> {

    private T data;
    private Integer code;
    private String message;

    public static <T> ResultVO<T> resultVO(T data, Integer code, String message) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(code);
        resultVO.setData(data);
        resultVO.setMessage(message);

        return resultVO;
    }

    public static ResultVO success() {
        return resultVO(null, 0, "success");
    }

    public static <T> ResultVO<T> success(T data) {
        return resultVO(data, 1, "success");
    }

    public static ResultVO error(String msg) {
        return resultVO(null, -1, msg);
    }

    public static ResultVO error() {
        return resultVO(null, -1, "error");
    }
}
