package cn.jackie.mc.protocol.packet.request;

import cn.jackie.mc.protocol.Command;
import cn.jackie.mc.protocol.packet.Packet;

/**
 * @author Jackie Ke
 */
public class HeartBeatRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.HEART_BEAT_REQUEST;
    }

}
