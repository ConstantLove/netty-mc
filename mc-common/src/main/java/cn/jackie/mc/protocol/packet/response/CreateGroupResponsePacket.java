package cn.jackie.mc.protocol.packet.response;

import cn.jackie.mc.protocol.Command;
import cn.jackie.mc.protocol.packet.Packet;

import java.util.List;

/**
 * @author Jackie Ke
 */
public class CreateGroupResponsePacket extends Packet {

    private Integer status;

    private List<String> userNames;

    private String groupId;

    private String createUserId;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<String> getUserNames() {
        return userNames;
    }

    public void setUserNames(List<String> userNames) {
        this.userNames = userNames;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getUserNamesStr() {
        StringBuilder usernameListStr = new StringBuilder();
        userNames.forEach( s -> {
            usernameListStr.append(s).append(", ");
        });

        if (usernameListStr.toString().endsWith(", ")) {
            return usernameListStr.substring(0, usernameListStr.length() - 2);
        }

        return null;
    }
}
