package cn.jackie.mc.handler.request;

import cn.jackie.mc.cons.ResponseStatus;
import cn.jackie.mc.protocol.packet.request.CreateGroupRequestPacket;
import cn.jackie.mc.protocol.packet.response.CreateGroupResponsePacket;
import cn.jackie.mc.utils.IDUtil;
import cn.jackie.mc.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建群聊请求处理器，用于服务端
 * @author Jackie Ke
 */
@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();

    private CreateGroupRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        List<String> userIds = createGroupRequestPacket.getUserIds();
        List<String> userNames = new ArrayList<>();

        // 1. 创建 channel group
        ChannelGroup channels = new DefaultChannelGroup(channelHandlerContext.executor());

        // 2. 将userId对应的channel加入group中
        userIds.forEach(userId -> {
            Channel channel = SessionUtil.getChannel(userId);
            if (channel != null) {
                channels.add(channel);
                userNames.add(SessionUtil.getSession(channel).getUsername());
            }
        });

        // 3. 保存Group与用户的映射关系
        String groupId = IDUtil.randomId();
        SessionUtil.createGroup(groupId, channels);

        // 4. 准备响应信息
        CreateGroupResponsePacket createGroupResponsePacket= new CreateGroupResponsePacket();
        createGroupResponsePacket.setStatus(ResponseStatus.SUCCESS);
        createGroupResponsePacket.setUserNames(userNames);
        createGroupResponsePacket.setGroupId(groupId);
        createGroupResponsePacket.setCreateUserId(SessionUtil.getSession(channelHandlerContext.channel()).getUserId());

        // 5. 将响应信息发送给 group中的每一个channel
        channels.writeAndFlush(createGroupResponsePacket);
    }

}
