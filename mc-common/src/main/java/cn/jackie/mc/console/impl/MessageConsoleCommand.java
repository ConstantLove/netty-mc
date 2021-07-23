package cn.jackie.mc.console.impl;

import cn.jackie.mc.cons.Attributes;
import cn.jackie.mc.console.ConsoleCommand;
import cn.jackie.mc.protocol.packet.request.MessageRequestPacket;
import cn.jackie.mc.utils.SessionUtil;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 发送信息指令
 * @author Jackie Ke
 */
public class MessageConsoleCommand implements ConsoleCommand {

    @Override
    public void execute(Scanner scanner, Channel channel) {
        System.out.println("请输入用户id");
        String toUserId = scanner.nextLine();
        System.out.println("请输入信息");
        String message = scanner.nextLine();
        String fromUserId = channel.attr(Attributes.SESSION).get().getUserId();

        MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
        messageRequestPacket.setFromUserId(fromUserId);
        messageRequestPacket.setToUserId(toUserId);
        messageRequestPacket.setMessage(message);
        channel.writeAndFlush(messageRequestPacket);
    }

}
