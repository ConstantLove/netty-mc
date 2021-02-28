package cn.jackie.mc.utils;

import cn.jackie.mc.console.ConsoleCommand;
import cn.jackie.mc.console.impl.*;
import io.netty.channel.Channel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 控制台命令处理工具类
 * @author Jackie Ke
 */
public class ConsoleCommandUtil {

    private static final Logger LOGGER = LogManager.getLogger(ConsoleCommandUtil.class);

    private static Map<String, ConsoleCommand> consoleCommandList = new HashMap<>();

    static {
        consoleCommandList.put(ConsoleCommand.LOGIN, new LoginConsoleCommand());
        consoleCommandList.put(ConsoleCommand.LOGOUT, new LogoutConsoleCommand());
        consoleCommandList.put(ConsoleCommand.TO_USER, new MessageConsoleCommand());
        consoleCommandList.put(ConsoleCommand.CREATE_GROUP, new CreateGroupConsoleCommand());
        consoleCommandList.put(ConsoleCommand.JOIN_GROUP, new JoinGroupConsoleCommand());
        consoleCommandList.put(ConsoleCommand.QUIT_GROUP, new QuitGroupConsoleCommand());
        consoleCommandList.put(ConsoleCommand.GROUP_MESSAGE, new GroupMessageConsoleCommand());
    }

    public static void execute(Scanner scanner, String command, Channel channel) {

        if (consoleCommandList.containsKey(command)) {
            ConsoleCommand consoleCommand = consoleCommandList.get(command);
            consoleCommand.execute(scanner, channel);
        } else {
            LOGGER.error("无法识别指令，请重新输入");
        }

    }

}
