package cn.jackie.mc.console.impl;

import cn.jackie.mc.console.ConsoleCommand;
import cn.jackie.mc.protocol.packet.request.JoinGroupRequestPacket;
import cn.jackie.mc.utils.SessionUtil;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 加入群聊指令
 * @author Jackie Ke
 */
public class JoinGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void execute(Scanner scanner, Channel channel) {
        System.out.println("请输入群聊id：");
        String groupId = scanner.nextLine();

        JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket();
        joinGroupRequestPacket.setGroupId(groupId);
        joinGroupRequestPacket.setUserId(SessionUtil.getSession(channel).getUserId());

        channel.writeAndFlush(joinGroupRequestPacket);
    }

}
