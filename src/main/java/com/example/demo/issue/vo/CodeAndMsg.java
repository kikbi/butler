package com.example.demo.issue.vo;

/**
 * 错误信息与错误码
 *
 * @author Tan Ke
 * @date 2021/10/27
 */
public interface CodeAndMsg {

    /**
     * 获取错误码
     * @return
     */
    Integer getCode();

    /**
     * 获取错误信息
     * @return
     */
    String getMessage();

    /**
     * 常规提示
     */
    enum Common implements CodeAndMsg{
        /**
         * 成功
         */
        OK(200,"ok"),

        /**
         * 系统错误
         */
        ERROR(500,"系统错误"),
        ;


        private Integer code;
        private String msg;

        Common(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        @Override
        public Integer getCode() {
            return code;
        }

        @Override
        public String getMessage() {
            return msg;
        }
    }
}
