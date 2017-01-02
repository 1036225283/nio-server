package com.nitian.socket.test;

import com.nitian.socket.EngineHandle;
import com.nitian.socket.EngineSocket;
import com.nitian.socket.EngineSocketNIO;
import com.nitian.socket.core.CoreProtocol;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.socket.test.handler.ExitHandler;
import com.nitian.socket.test.handler.LoginHandler;
import com.nitian.socket.util.protocol.read.ProtocolHttpReadHandler;
import com.nitian.socket.util.protocol.read.ProtocolWebSocketReadHandler;
import com.nitian.socket.util.protocol.read.ProtocolWebSocketUpgradeReadHandler;
import com.nitian.socket.util.protocol.read.ProtocolXwsReadHandler;
import com.nitian.socket.util.protocol.write.ProtocolHttpWriteHandler;
import com.nitian.socket.util.protocol.write.ProtocolWebSocketUpgradeWriteHandler;
import com.nitian.socket.util.protocol.write.ProtocolWebSocketWriteHandler;
import com.nitian.socket.util.protocol.write.ProtocolXwsWriteHandler;
import com.nitian.util.keyvalue.KeyValue;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

import java.util.HashMap;
import java.util.Map;

public class ServerTest {


    private static KeyValue keyValue = new KeyValue();


    public static void main(String[] args) {

        LogManager log = LogManager.getInstance();

        final Map<String, Long> countMap = new HashMap<>();


        try {

            LogManager.setFileLog(true);
            LogManager.setIsConsole(true);

            log.putType(LogType.debug.toString());
            log.putType(LogType.error.toString());
            log.putType(LogType.info.toString());
            log.putType(LogType.warning.toString());

            log.putType(LogType.time.toString());

            EngineHandle engineHandle = new EngineHandle();
            EngineSocket engineSocket = new EngineSocketNIO(88);
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
                    .regist("/test", new Handler() {
                        @Override
                        public void handle(Map<String, String> map) {
                            map.put(CoreType.result.toString(), "this is test");
                        }
                    })
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
                    .regist("/key-value/get", new Handler() {
                        @Override
                        public void handle(Map<String, String> map) {
                            String param = map.get(CoreType.param);
                            System.out.println("param = " + param);
//                            keyValue.set("xws", "xws");
                        }
                    })
                    .regist("/key-value/set", new Handler() {
                        @Override
                        public void handle(Map<String, String> map) {
                            String param = map.get(CoreType.param);
                            System.out.println("param = " + param);
                            keyValue.set("xws", "xws");
                        }
                    });

            engineSocket.start();
        } catch (Exception e) {
            // TODO: handle exception
            log.error(e, "");
        }

    }
}
