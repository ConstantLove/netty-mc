# netty-mc

这是一个仿微信(WeChat)的MC(MyChat)系统后台，不包含移动端，主要通过控制台模拟各种移动端的操作。

### 功能包括
- 登录、 登出
- 用户之间收发信息
- 创建群聊
- 加入群聊
- 退出群聊
- 发送群聊消息（向群聊内所有成员发送信息）

### Feature
- 实现自定义协议
- 解决粘包拆包的问题
- 实现自定义编码解码
- 实现心跳检测和空闲检测

### 使用说明
在本地打开项目后，分别运行

服务端启动类：mc-server/src/main/java/cn/jackie/mc/server.java

客户端启动类：mc-client/src/main/java/cn/jackie/mc/client.java
