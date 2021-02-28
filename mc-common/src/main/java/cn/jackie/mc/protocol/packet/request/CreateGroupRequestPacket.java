package cn.jackie.mc.protocol.packet.request;

import cn.jackie.mc.protocol.Command;
import cn.jackie.mc.protocol.packet.Packet;

import java.util.List;

/**
 * @author Jackie Ke
 */
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIds;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }
}
