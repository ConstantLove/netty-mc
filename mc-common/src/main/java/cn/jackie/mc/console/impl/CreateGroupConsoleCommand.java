package cn.jackie.mc.console.impl;

import cn.jackie.mc.console.ConsoleCommand;
import cn.jackie.mc.protocol.packet.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 创建群聊指令
 * @author Jackie Ke
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void execute(Scanner scanner, Channel channel) {
        System.out.println("请输入群聊成员的userId， 使用 ',' 分隔：");
        String userIdsStr = scanner.nextLine();
        String[] userIdsArray = userIdsStr.split(",");

        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();
        createGroupRequestPacket.setUserIds(Arrays.asList(userIdsArray));

        channel.writeAndFlush(createGroupRequestPacket);
    }

}
