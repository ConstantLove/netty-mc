package cn.jackie.mc.protocol;

import cn.jackie.mc.protocol.packet.Packet;
import cn.jackie.mc.protocol.packet.request.*;
import cn.jackie.mc.protocol.packet.response.*;
import cn.jackie.mc.serialize.Serializer;
import cn.jackie.mc.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;

/**
 * 序列化处理工具类
 * @author Jackie Ke
 */
public class PacketCodec {

    public static final PacketCodec INSTANCE = new PacketCodec();

    public static final int MAGIC_NUMBER = 0x12345678;

    public void encode(ByteBuf byteBuf, Packet packet, Byte serializeMethod) {

        // 1. 确定序列化方式
        Serializer serializer;
        if (serializeMethod == null) {
            serializer = Serializer.DEFAULT;
        } else {
            serializer = getSerializer(serializeMethod);
        }

        // 2. 序列化对象
        byte[] objBytes = serializer.serialize(packet);

        // 3. 编码
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(serializer.getSerializerMethod());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(objBytes.length);
        byteBuf.writeBytes(objBytes);

    }

    public Packet decode(ByteBuf byteBuf) {

        byteBuf.skipBytes(4); // 协议魔数
        byteBuf.skipBytes(1); // 版本号暂时用不到

        // 1. 确定序列化方式
        byte serializeMethod = byteBuf.readByte();
        Serializer serializer = getSerializer(serializeMethod);

        // 2. 获取请求指令
        byte command = byteBuf.readByte();
        Class<? extends Packet> targetPacket = targetPacket(command);

        // 3. 获取数据
        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        // 4. 反序列化对象
        return serializer.deserialize(targetPacket, bytes);

    }

    private Class<? extends Packet> targetPacket(byte command) {
        switch (command) {
            case Command.LOGIN_REQUEST:
                return LoginRequestPacket.class;
            case Command.LOGIN_RESPONSE:
                return LoginResponsePacket.class;
            case Command.MESSAGE_REQUEST:
                return MessageRequestPacket.class;
            case Command.MESSAGE_RESPONSE:
                return MessageResponsePacket.class;
            case Command.LOGOUT_REQUEST:
                return LogoutRequestPacket.class;
            case Command.LOGOUT_RESPONSE:
                return LogoutResponsePacket.class;
            case Command.CREATE_GROUP_REQUEST:
                return CreateGroupRequestPacket.class;
            case Command.CREATE_GROUP_RESPONSE:
                return CreateGroupResponsePacket.class;
            case Command.JOIN_GROUP_REQUEST:
                return JoinGroupRequestPacket.class;
            case Command.JOIN_GROUP_RESPONSE:
                return JoinGroupResponsePacket.class;
            case Command.QUIT_GROUP_REQUEST:
                return QuitGroupRequestPacket.class;
            case Command.QUIT_GROUP_RESPONSE:
                return QuitGroupResponsePacket.class;
            case Command.GROUP_MESSAGE_REQUEST:
                return GroupMessageRequestPacket.class;
            case Command.GROUP_MESSAGE_RESPONSE:
                return GroupMessageResponsePacket.class;
            case Command.HEART_BEAT_REQUEST:
                return HeartBeatRequestPacket.class;
            case Command.HEART_BEAT_RESPONSE:
                return HeartBeatResponsePacket.class;
        }

        return null;
    }

    private Serializer getSerializer(byte serializeMethod) {
        switch (serializeMethod) {
            default:
                return new JSONSerializer();
        }
    }

}
