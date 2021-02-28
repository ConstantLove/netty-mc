package cn.jackie.mc.entity;

/**
 * 用户会话
 * @author Jackie Ke
 */
public class Session {

    private String userId;

    private String username;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Session(String userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public Session() {
    }
}
