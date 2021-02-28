package cn.jackie.mc.protocol.packet.request;

import cn.jackie.mc.protocol.Command;
import cn.jackie.mc.protocol.packet.Packet;

/**
 * @author Jackie Ke
 */
public class GroupMessageRequestPacket extends Packet {

    private String groupId;

    private String fromUserId;

    private String username;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.GROUP_MESSAGE_REQUEST;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
