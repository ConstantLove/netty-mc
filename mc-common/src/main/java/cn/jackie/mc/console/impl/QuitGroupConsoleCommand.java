package cn.jackie.mc.console.impl;

import cn.jackie.mc.console.ConsoleCommand;
import cn.jackie.mc.protocol.packet.request.QuitGroupRequestPacket;
import cn.jackie.mc.utils.SessionUtil;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 退出群聊指令
 * @author Jackie Ke
 */
public class QuitGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void execute(Scanner scanner, Channel channel) {
        System.out.println("请输入群聊id：");
        String groupId = scanner.nextLine();

        QuitGroupRequestPacket quitGroupRequestPacket = new QuitGroupRequestPacket();
        quitGroupRequestPacket.setGroupId(groupId);
        quitGroupRequestPacket.setUserId(SessionUtil.getSession(channel).getUserId());

        channel.writeAndFlush(quitGroupRequestPacket);
    }

}
