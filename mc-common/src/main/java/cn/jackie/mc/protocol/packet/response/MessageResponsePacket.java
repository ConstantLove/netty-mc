package cn.jackie.mc.protocol.packet.response;

import cn.jackie.mc.protocol.Command;
import cn.jackie.mc.protocol.packet.Packet;

/**
 * @author Jackie Ke
 */
public class MessageResponsePacket extends Packet {

    private String fromUserId;

    private String username;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
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
