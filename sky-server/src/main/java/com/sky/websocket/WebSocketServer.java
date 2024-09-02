package com.sky.websocket;

import org.springframework.stereotype.Component;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * WebSocket服務
 */
@Component
@ServerEndpoint("/ws/{sid}")
public class WebSocketServer {

    //存放會話對象
    private static Map<String, Session> sessionMap = new HashMap();

    /**
     * 連接建立成功呼叫的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        System.out.println("客户端：" + sid + "建立连接");
        sessionMap.put(sid, session);
    }

    /**
     * 收到客戶端訊息後呼叫的方法
     *
     * @param message 客戶端發送過來的訊息
     */
    @OnMessage
    public void onMessage(String message, @PathParam("sid") String sid) {
        System.out.println("收到來自客戶端：" + sid + "的訊息:" + message);
    }

    /**
     * 連接關閉呼叫的方法
     *
     * @param sid
     */
    @OnClose
    public void onClose(@PathParam("sid") String sid) {
        System.out.println("連線中斷:" + sid);
        sessionMap.remove(sid);
    }

    /**
     * 群發
     *
     * @param message
     */
    public void sendToAllClient(String message) {
        Collection<Session> sessions = sessionMap.values();
        for (Session session : sessions) {
            try {
                //伺服器向客戶端發送訊息
                session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
