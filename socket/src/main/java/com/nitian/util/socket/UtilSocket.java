package com.nitian.util.socket;

import java.io.IOException;
import java.io.InputStream;

import com.nitian.util.java.UtilByte;

/**
 * socket工具类
 * 
 * @author 1036225283
 *
 */
public class UtilSocket {

	/**
	 * 读取socket里的内容
	 * 
	 * @param inputStream
	 * @param bs
	 * @return
	 */
	public static String readString(InputStream inputStream, byte[] bs) {
		String value = null;
		byte[] length = new byte[4];
		try {
			inputStream.read(length, 0, 4);
			int size = UtilByte.bytesToInt(length);
			inputStream.read(bs, 0, size);
			value = new String(bs, 0, size);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
}
