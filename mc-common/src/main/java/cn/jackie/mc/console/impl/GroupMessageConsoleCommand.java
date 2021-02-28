package cn.jackie.mc.console.impl;

import cn.jackie.mc.console.ConsoleCommand;
import cn.jackie.mc.protocol.packet.request.GroupMessageRequestPacket;
import cn.jackie.mc.utils.SessionUtil;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 发送群聊信息指令
 * @author Jackie Ke
 */
public class GroupMessageConsoleCommand implements ConsoleCommand {

    @Override
    public void execute(Scanner scanner, Channel channel) {
        System.out.println("请输入groupId: ");
        String groupId = scanner.nextLine();
        System.out.println("请输入信息: ");
        String message = scanner.nextLine();

        GroupMessageRequestPacket groupMessageRequestPacket = new GroupMessageRequestPacket();
        groupMessageRequestPacket.setGroupId(groupId);
        groupMessageRequestPacket.setMessage(message);
        groupMessageRequestPacket.setFromUserId(SessionUtil.getSession(channel).getUserId());
        groupMessageRequestPacket.setUsername(SessionUtil.getSession(channel).getUsername());
        channel.writeAndFlush(groupMessageRequestPacket);
    }

}
