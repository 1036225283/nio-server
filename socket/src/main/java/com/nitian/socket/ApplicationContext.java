package com.nitian.socket;

import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

public class ApplicationContext {

    private static ApplicationContext context = new ApplicationContext();

    private EngineHandle engineHandle;


    private EngineSocket engineSocket;

    private LogManager log = LogManager.getInstance();

    public ApplicationContext() {
        // TODO Auto-generated constructor stub

        log.putType(LogType.time.toString());

        engineHandle = new EngineHandle();
        engineSocket = new EngineSocket(88);
        engineSocket.setEngineHandle(engineHandle);

        // log.putType("websocket_frame");
        // 线程池不需要追踪
//        poolSocketThread = new UtilPoolThread(200);

//        poolWebSocketThread = new UtilPoolThread(10);


    }

    public static ApplicationContext getInstance() {
        return context;
    }


    public EngineHandle getEngineHandle() {
        return engineHandle;
    }


    public EngineSocket getEngineSocket() {
        return engineSocket;
    }
}
