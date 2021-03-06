package com.nitian.test;

import com._1036225283.util.self.encrypt.UtilBase64;
import com._1036225283.util.self.encrypt.UtilMd5;
import com._1036225283.util.self.java.UtilByte;
import com.nitian.socket.util.pool.UtilPoolByte;
import com.nitian.socket.util.pool.UtilPoolMap;
import com.nitian.socket.util.queue.UtilQueueHandle;
import com.nitian.socket.util.websocket.UtilWebSocket;

import java.io.*;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Test {

    public void show() {
        System.out.println("this is show");
    }

    public static void main(String[] args) throws IOException,
            InstantiationException, IllegalAccessException,
            NoSuchAlgorithmException {
//        testMapNullKey();
//        testNio();
        testGetBit();
//        testByteBuffer();
        System.out.println("this is end{ss}");
    }

    public static void testNio() {
        int OP_READ = 1 << 0;
        int OP_WRITE = 1 << 2;
        int OP_CONNECT = 1 << 3;
        int OP_ACCEPT = ((1 << 4) | OP_READ) ^ OP_READ;
        System.out.println(UtilByte.toBin((byte) OP_READ));
        System.out.println(UtilByte.toBin((byte) OP_WRITE));
        System.out.println(UtilByte.toBin((byte) OP_CONNECT));
        System.out.println(UtilByte.toBin((byte) OP_ACCEPT));
    }

    public static void testMapNullKey() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "a");
        System.out.print(map.get(null));
    }

    public static void testByteBuffer() {
        String hello = "hello";
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        byteBuffer.put(hello.getBytes());
        byteBuffer.flip();

        System.out.println(hello);

        byte[] bytes = new byte[10000];
        int length = byteBuffer.remaining();
        System.out.println("第一次 = " + length);


        length = byteBuffer.remaining();
        System.out.println("第二次 = " + length);

        byteBuffer.get(bytes, 0, length);
        System.out.println(new String(bytes, 0, length));


    }

    public static void testRunTimePath() throws IOException {
        String path = Test.class.getProtectionDomain().getCodeSource()
                .getLocation().getPath().trim();
        File file = new File(path + "test.jav");
        file.createNewFile();
        System.out.println("this is test:" + path);
    }

    public static void testGetBit() {
        byte height = (byte) 2;
        for (int i = 8; i > 0; i--) {
            System.out.print(UtilByte.getBit(height, i));
        }
        System.out.println();
        System.out.println(UtilByte.toBin(height));
    }

    public static void testWebSocketAccept() {
        System.out.println(UtilWebSocket
                .getSecWebSocketAccept("ch3uUUC+Yu05yCNTw0sBBQ=="));

    }

    public static void testBase64(String tingting)
            throws UnsupportedEncodingException {
        System.out.println(tingting.length());
        String encode = null;// UtilBase64.encode(tingting);
        System.out.println(encode);
    }

    public static void testSHA() {
        String string = "ibAzXMSXFwTYt6lZeKh7Zw==";
        string = string + "258EAFA5-E914-47DA- 95CA-C5AB0DC85B11";
        byte[] bs = UtilMd5.stringToSHA1_(string);
        String result = UtilBase64.encode(bs);
        System.out.println(result);
    }

    public void testClass() throws InstantiationException,
            IllegalAccessException {
        Class<?> c = new Test().getClass();
        Test t = (Test) c.newInstance();
        t.show();
    }

    public static void testQueueRead() {
        UtilQueueHandle queueRead = new UtilQueueHandle(null);
        for (int i = 0; i < 10000; i++) {
            queueRead.push(new HashMap<String, Object>());
        }
    }

    public static void testFileOutPutStream() throws IOException {
        String test = Test.class.getResource("/").getFile().substring(1);
        OutputStream outputStream = new FileOutputStream(
                "C:\\Users\\1036225283\\Desktop\\test\\dd.json");
        outputStream.write("sjkjlkjk".getBytes());
        System.out.println(test);
    }

    public static void testStringIndexOf() {
        String test = "aabc32234";
        System.out.println(test.indexOf("abc"));
    }

    public static void testInputStream() throws IOException {
        File file = new File("C:/Users/1036225283/Desktop/test/test.txt");
        // file.get
        InputStream inputStream = new FileInputStream(file);
        byte[] bs = new byte[1];
        // for (int i = 0; i < 101; i++) {
        // byte b = (byte) inputStream.read();
        // System.out.println(b);
        // bs[0] = b;
        // System.out.println(UtilStringHex.bytesHexStr(bs));
        // }

        byte[] bs2 = new byte[19];
        int index = inputStream.read(bs2);
        System.out.println(index);
    }

    public static void testPoolByte() {
        UtilPoolByte poolByte = new UtilPoolByte(20, 2, null);
        byte[] bs = poolByte.lend();
        System.out.println(bs.length);
    }

    public static void testPoolMap() {
        UtilPoolMap poolMap = new UtilPoolMap(100, 1);

        for (int i = 0; i < 10; i++) {
            System.out.println("poolSize : " + poolMap.getTotal());
            poolMap.lend();
        }

        Map<String, Object> m = poolMap.lend();
        System.out.println(m);
        m.put("hello", "world");
        System.out.println(m.get("hello"));
    }

    public static void testList() {
        List<String> list = new LinkedList<String>();
        list.add("aaaa");
        list.add("bbbb");
        String c = list.remove(0);
        System.out.println(c);
        System.out.println(list.size());
    }
}
