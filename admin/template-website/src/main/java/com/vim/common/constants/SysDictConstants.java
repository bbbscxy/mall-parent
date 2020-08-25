package com.vim.common.constants;

/**
 * @作者 Administrator
 * @时间 2019-07-29 16:30
 * @版本 1.0
 * @说明 字典变量值
 */
public class SysDictConstants {

    /**
     * 菜单是否显示
     */
    public enum MENU_IS_SHOW {
        YES("1"), NO("0");
        String value;
        MENU_IS_SHOW(String value){
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

    /**
     * 字典是否父级
     */
    public enum DICT_IS_PARENT {
        YES("1"), NO("0");
        String value;
        DICT_IS_PARENT(String value){
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

    /**
     * 字典类型
     */
    public enum DICT_TYPE {
        SYSTEM("0"), BUSINESS("1");
        String value;
        DICT_TYPE(String value){
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

    /**
     * 删除状态
     */
    public enum DEL_FLAG {
        YES("1"), NO("0");
        String value;
        DEL_FLAG(String value){
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

    /**
     * 是否可以登录
     */
    public enum LOGIN_FLAG {
        CAN_LOGIN("1"), CANNOT_LOGIN("0");
        String value;
        LOGIN_FLAG(String value){
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

    /**
     * 消息推送状态
     */
    public enum MSG_PUSH_STATUS {
        NO("0"), YES("1");
        String value;
        MSG_PUSH_STATUS(String value){
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

    /**
     * 消息阅读状态
     */
    public enum MSG_READ_STATUS {
        NO("0"), YES("1");
        String value;
        MSG_READ_STATUS(String value){
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
}
