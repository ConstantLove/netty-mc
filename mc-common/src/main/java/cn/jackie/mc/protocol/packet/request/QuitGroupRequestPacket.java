package cn.jackie.mc.protocol.packet.request;

import cn.jackie.mc.protocol.Command;
import cn.jackie.mc.protocol.packet.Packet;

/**
 * @author Jackie Ke
 */
public class QuitGroupRequestPacket extends Packet {

    private String groupId;

    private String userId;

    @Override
    public Byte getCommand() {
        return Command.QUIT_GROUP_REQUEST;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
