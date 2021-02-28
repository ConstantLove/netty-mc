package cn.jackie.mc.protocol.packet.response;

import cn.jackie.mc.protocol.Command;
import cn.jackie.mc.protocol.packet.Packet;

/**
 * @author Jackie Ke
 */
public class HeartBeatResponsePacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.HEART_BEAT_RESPONSE;
    }

}
