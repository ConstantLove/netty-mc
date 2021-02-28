package cn.jackie.mc.protocol.packet.response;

import cn.jackie.mc.protocol.Command;
import cn.jackie.mc.protocol.packet.Packet;

/**
 * @author Jackie Ke
 */
public class JoinGroupResponsePacket extends Packet {

    private Integer status;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_RESPONSE;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
