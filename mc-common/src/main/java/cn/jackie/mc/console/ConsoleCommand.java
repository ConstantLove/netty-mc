package cn.jackie.mc.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author Jackie Ke
 */
public interface ConsoleCommand {

    String LOGIN = "login";

    String LOGOUT = "logout";

    String TO_USER = "toUser";

    String GROUP_MESSAGE = "groupMessage";

    String CREATE_GROUP = "createGroup";

    String JOIN_GROUP = "joinGroup";

    String QUIT_GROUP = "quitGroup";

    void execute(Scanner scanner, Channel channel);

}
