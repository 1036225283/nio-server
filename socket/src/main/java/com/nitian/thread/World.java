package com.nitian.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.nitian.util.file.UtilFile;
import com.nitian.util.string.UtilStringHex;

public class World {

	public static void main(String[] args) {
		String path = "C:\\Users\\1036225283\\Desktop\\util\\Test.java";
		String value = UtilFile.fileToString(path);
		String[] strings = value.split("\n");
		
		Arrays.sort(strings);
		System.out.println("length:"+strings.length);
//		System.out.println(UtilStringHex.bytesHexStr(UtilStringHex.initByte(strings[0])));
		for (int i = 0; i < strings.length; i++) {
			System.out.println(strings[i]);
		}
	}
}
