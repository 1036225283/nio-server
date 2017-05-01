package com.nitian.test;

import com.nitian.handler.tree.*;
import com.nitian.socket.EngineHandle;
import com.nitian.socket.EngineSocket;
import com.nitian.socket.EngineSocketNIO;
import com.nitian.socket.core.CoreProtocol;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.handler.ExitHandler;
import com.nitian.handler.LoginHandler;
import com.nitian.handler.TestHandler;
import com.nitian.handler.keyValue.GetHandler;
import com.nitian.handler.keyValue.InitHandler;
import com.nitian.handler.keyValue.SetHandler;
import com.nitian.socket.util.protocol.read.ProtocolHttpReadHandler;
import com.nitian.socket.util.protocol.read.ProtocolWebSocketReadHandler;
import com.nitian.socket.util.protocol.read.ProtocolWebSocketUpgradeReadHandler;
import com.nitian.socket.util.protocol.read.ProtocolXwsReadHandler;
import com.nitian.socket.util.protocol.write.ProtocolHttpWriteHandler;
import com.nitian.socket.util.protocol.write.ProtocolWebSocketUpgradeWriteHandler;
import com.nitian.socket.util.protocol.write.ProtocolWebSocketWriteHandler;
import com.nitian.socket.util.protocol.write.ProtocolXwsWriteHandler;
import com.nitian.util.log.LogManager;

import java.util.HashMap;
import java.util.Map;

public class ServerTest {


    public static void main(String[] args) {

        LogManager log = LogManager.getInstance();

        final Map<String, Long> countMap = new HashMap<>();


        try {

            LogManager.setFileLog(true);
            LogManager.setIsConsole(true);

//            log.putType(LogType.debug.toString());
//            log.putType(LogType.error.toString());
//            log.putType(LogType.info.toString());
//            log.putType(LogType.warning.toString());
//
//            log.putType(LogType.time.toString());

            EngineHandle engineHandle = new EngineHandle();
            EngineSocket engineSocket = new EngineSocketNIO(8888);
            engineSocket.setEngineHandle(engineHandle);

            engineSocket.getProtocolReadFactory()
                    .regist(CoreProtocol.HTTP.toString(), new ProtocolHttpReadHandler())
                    .regist(CoreProtocol.WEBSOCKETUPGRADE.toString(), new ProtocolWebSocketUpgradeReadHandler())
                    .regist(CoreProtocol.XWS.toString(), new ProtocolXwsReadHandler())
                    .regist(CoreProtocol.WEBSOCKET.toString(), new ProtocolWebSocketReadHandler())
            ;

            engineSocket.getProtocolWriteFactory()
                    .regist(CoreProtocol.HTTP.toString(), new ProtocolHttpWriteHandler())
                    .regist(CoreProtocol.WEBSOCKETUPGRADE.toString(), new ProtocolWebSocketUpgradeWriteHandler())
                    .regist(CoreProtocol.XWS.toString(), new ProtocolXwsWriteHandler())
                    .regist(CoreProtocol.WEBSOCKET.toString(), new ProtocolWebSocketWriteHandler())
            ;

            countMap.put("count", 0L);

            engineHandle.getHandlerFactory()
                    .regist("/user/login", new LoginHandler())
                    .regist("/exit", new ExitHandler())
                    .regist("/test", new TestHandler())
                    .regist("/love", new Handler() {
                        @Override
                        public void handle(Map<String, String> map) {
                            long count = countMap.get("count");
                            map.put(CoreType.result.toString(), "l love you " + count + " days !!!");
                            count = count + 1;
                            countMap.put("count", count);
                        }
                    })
                    .regist("/fuck", new Handler() {
                        @Override
                        public void handle(Map<String, String> map) {
                            map.put(CoreType.result.toString(), "l fuck you !!!");
                        }
                    })
                    .regist("/key-value/get", new GetHandler())
                    .regist("/key-value/set", new SetHandler())
                    .regist("/redis/get", new com.nitian.handler.redis.GetHandler())
                    .regist("/redis/set", new com.nitian.handler.redis.SetHandler())
                    .regist("/redis/init", new InitHandler())

                    .regist("/avl/get",new AVLGetHandler())
                    .regist("/avl/set",new AVLSetHandler())
                    .regist("/avl/clear",new AVLClearHandler())
                    .regist("/avl/view",new AVLViewHandler())
                    .regist("/avl/remove",new AVLRemoveHandler())

                    .regist("/tree/set",new TreeSetHandler())
                    .regist("/tree/get",new TreeGetHandler())
                    .regist("/tree/clear",new TreeClearHandler())
                    .regist("/tree/view",new TreeViewHandler())
                    .regist("/tree/remove",new TreeRemoveHandler())
            ;

            engineSocket.start();
        } catch (Exception e) {
            // TODO: handle exception
            log.error(e, "");
        }

    }
}
