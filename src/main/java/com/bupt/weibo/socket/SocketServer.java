package com.bupt.weibo.socket;

/**
 * @anthor tanshangou
 * @time 2018/7/17
 * @description
 */
import com.bupt.weibo.socket.domain.TimeMessage;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * socketio 服务器端实现
 *
 * @author pengdh
 * @date: 2017-09-03 1:06
 */
@Component
public class SocketServer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static SocketIOServer server = initServer();
    /**
     * 初始化服务端
     * @return
     */
    private static SocketIOServer initServer() {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9090);
        server = new SocketIOServer(config);
        return server;
    }
    /**
     * 启动服务端
     */
    public void startServer() {
        // 添加连接监听
        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient socketIOClient) {
                logger.info("server 服务端启动成功");
            }
        });
        // 添加断开连接监听
        server.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(SocketIOClient socketIOClient) {
                logger.info("server 服务端断开连接");
            }
        });
        // 添加事件监听
        server.addEventListener("join", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String str,
                               AckRequest ackRequest)
                    throws Exception {
                logger.info("收到客户端加入消息：" + str);
                server.getBroadcastOperations().sendEvent("joinSuccess", "join success");
            }
        });
        // 添加事件监听
        server.addEventListener("chatMessage", TimeMessage.class, new DataListener<TimeMessage>() {
            @Override
            public void onData(SocketIOClient socketIOClient, TimeMessage message,
                               AckRequest ackRequest)
                    throws Exception {
                logger.info("收到客户端消息：" + message.toString());
                server.getBroadcastOperations().sendEvent("return message", message.toString());
            }
        });
        // 启动服务端
        server.start();
    }
    /**
     * 停止服务端
     */
    public void stopServer() {
        server.stop();
    }
}