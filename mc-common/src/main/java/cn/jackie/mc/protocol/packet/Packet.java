package cn.jackie.mc.protocol.packet;

/**
 * 自定义协议基础抽象类
 * @author Jackie Ke
 */
public abstract class Packet {

    /**
     * 协议版本
     * 预留字段，不常用
     */
    private Byte version = 1;

    /**
     * 操作指令
     */
    public abstract Byte getCommand();

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }
}