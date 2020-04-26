package cn.itsource.shiro;

/**
 * 封装了登录返回的信息
 */
public class LoginResult {
    //是否操作成功
    private Boolean success;
    //相应的提示信息
    private String msg;

    public LoginResult(Boolean success, String msg) {
        this.success = success;
        this.msg = msg;
        if (!success) {/*如果登录失败添加一个额外信息*/
            this.msg = msg + ",错误码" + "(" + System.currentTimeMillis() + ")";
        }

    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
