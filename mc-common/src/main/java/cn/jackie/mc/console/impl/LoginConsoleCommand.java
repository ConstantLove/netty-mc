package cn.jackie.mc.console.impl;

import cn.jackie.mc.console.ConsoleCommand;
import cn.jackie.mc.protocol.packet.request.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 登录指令
 * @author Jackie Ke
 */
public class LoginConsoleCommand implements ConsoleCommand {

    @Override
    public void execute(Scanner scanner, Channel channel) {
        System.out.println("请输入用户名");
        String username = scanner.nextLine();
        System.out.println("请输入密码");
        String password = scanner.nextLine();

        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUsername(username);
        loginRequestPacket.setPassword(password);

        channel.writeAndFlush(loginRequestPacket);
    }

}
