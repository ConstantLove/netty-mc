package cn.jackie.mc.protocol.packet.request;

import cn.jackie.mc.protocol.Command;
import cn.jackie.mc.protocol.packet.Packet;

/**
 * @author Jackie Ke
 */
public class MessageRequestPacket extends Packet {

    private String fromUserId;

    private String toUserId;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
