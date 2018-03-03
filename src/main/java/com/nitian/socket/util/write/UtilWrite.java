package com.nitian.socket.util.write;

import com.nitian.socket.EngineSocketNIO;
import com._1036225283.util.self.log.LogManager;

import java.util.Map;

/**
 * 抽象写工具类
 */
public abstract class UtilWrite {

    protected static LogManager log = LogManager.getInstance();

    public abstract void write(Map<String, Object> map, EngineSocketNIO engineSocket);
}
