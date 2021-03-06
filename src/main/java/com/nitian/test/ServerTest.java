package com.nitian.test;

import com._1036225283.util.self.log.LogManager;
import com._1036225283.util.self.log.LogType;
import com.nitian.handler.ExitHandler;
import com.nitian.handler.LoginHandler;
import com.nitian.handler.TestHandler;
import com.nitian.handler.tree.data.*;
import com.nitian.handler.tree.view.*;
import com.nitian.socket.EngineHandle;
import com.nitian.socket.EngineSocketNIO;
import com.nitian.socket.core.CoreProtocol;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.socket.util.protocol.read.ProtocolHttpReadHandler;
import com.nitian.socket.util.protocol.read.ProtocolWebSocketReadHandler;
import com.nitian.socket.util.protocol.read.ProtocolWebSocketUpgradeReadHandler;
import com.nitian.socket.util.protocol.read.ProtocolXwsReadHandler;
import com.nitian.socket.util.protocol.ssl.ProtocolHttpsReadHandler;
import com.nitian.socket.util.protocol.ssl.ProtocolHttpsWriteHandler;
import com.nitian.socket.util.protocol.write.ProtocolHttpWriteHandler;
import com.nitian.socket.util.protocol.write.ProtocolWebSocketUpgradeWriteHandler;
import com.nitian.socket.util.protocol.write.ProtocolWebSocketWriteHandler;
import com.nitian.socket.util.protocol.write.ProtocolXwsWriteHandler;

import java.util.HashMap;
import java.util.Map;

public class ServerTest {


    public static void main(String[] args) {

        LogManager log = LogManager.getInstance();

        final Map<String, Long> countMap = new HashMap<>();


        try {

            LogManager.setFileLog(true);
            LogManager.setIsConsole(true);

            log.putType(LogType.debug.toString());
//            log.putType(LogType.error.toString());
//            log.putType(LogType.info.toString());
//            log.putType(LogType.warning.toString());
//
//            log.putType(LogType.time.toString());

            EngineHandle engineHandle = new EngineHandle();
            EngineSocketNIO engineSocket = new EngineSocketNIO(8888);
            engineSocket.engineHandle = engineHandle;

            engineSocket.protocolReadFactory
                    .regist(CoreProtocol.HTTP.toString(), new ProtocolHttpReadHandler())
                    .regist(CoreProtocol.WEBSOCKETUPGRADE.toString(), new ProtocolWebSocketUpgradeReadHandler())
                    .regist(CoreProtocol.XWS.toString(), new ProtocolXwsReadHandler())
                    .regist(CoreProtocol.WEBSOCKET.toString(), new ProtocolWebSocketReadHandler())
                    .regist(CoreProtocol.HTTPS.toString(), new ProtocolHttpsReadHandler())
            ;

            engineSocket.protocolWriteFactory
                    .regist(CoreProtocol.HTTP.toString(), new ProtocolHttpWriteHandler())
                    .regist(CoreProtocol.WEBSOCKETUPGRADE.toString(), new ProtocolWebSocketUpgradeWriteHandler())
                    .regist(CoreProtocol.XWS.toString(), new ProtocolXwsWriteHandler())
                    .regist(CoreProtocol.WEBSOCKET.toString(), new ProtocolWebSocketWriteHandler())
                    .regist(CoreProtocol.HTTPS.toString(), new ProtocolHttpsWriteHandler())
            ;

            countMap.put("count", 0L);

            engineHandle.getHandlerFactory()
                    .regist("/user/login", new LoginHandler())
                    .regist("/exit", new ExitHandler())
                    .regist("/test", new TestHandler())
                    .regist("/love", new Handler() {
                        @Override
                        public void handle(Map<String, Object> map) {
                            long count = countMap.get("count");
                            map.put(CoreType.result.toString(), "l love you " + count + " days !!!");
                            count = count + 1;
                            countMap.put("count", count);
                        }
                    })
                    .regist("/fuck", new Handler() {
                        @Override
                        public void handle(Map<String, Object> map) {
                            map.put(CoreType.result.toString(), "l fuck you !!!");
                        }
                    })
//                    .regist("/key-value/get", new GetHandler())
//                    .regist("/key-value/set", new SetHandler())
//                    .regist("/redis/get", new com.nitian.handler.redis.GetHandler())
//                    .regist("/redis/set", new com.nitian.handler.redis.SetHandler())
//                    .regist("/redis/del", new com.nitian.handler.redis.RemoveHandler())
//                    .regist("/redis/init", new InitHandler())

                    .regist("/avl/set", new AVLDataSetHandler())
                    .regist("/avl/get", new AVLDataGetHandler())
                    .regist("/avl/del", new AVLDataRemoveHandler())

                    .regist("/avl/view/insert", new AVLViewInsertHandler())
                    .regist("/avl/view/remove", new AVLViewRemoveHandler())
                    .regist("/avl/view/delete", new AVLViewClearHandler())


                    .regist("/rbt/set", new RBTDataSetHandler())
                    .regist("/rbt/get", new RBTDataGetHandler())
                    .regist("/rbt/del", new RBTDataRemoveHandler())

                    .regist("/rbt/view/insert", new RBTViewInsertHandler())
                    .regist("/rbt/view/remove", new RBTViewRemoveHandler())
                    .regist("/rbt/view/delete", new RBTViewClearHandler())

            ;

            engineSocket.start();
        } catch (Exception e) {
            // TODO: handle exception
            log.error(e, "");
        }

    }
}
