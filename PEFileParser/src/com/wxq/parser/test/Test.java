 package com.wxq.parser.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.swing.JFileChooser;

import com.wxq.parser.operations.CommonUtil;
import com.wxq.parser.operations.FileUtil;
import com.wxq.parser.operations.ObjectUtil;
import com.wxq.parser.operations.StrBinaryTurn;
import com.wxq.parser.ui.ParserFrame;

public class Test {
	public static void main(String[] args) {
		/*
		 * 测试文件操作类
		 * FileUtil fu=new FileUtil();
		try {
			fu.getBigFile("E:\\program\\Adobe Photoshop CS5\\Adobe Photoshop CS5\\Photoshop.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		/**
		 * 测试UI*/
		new ParserFrame();
		/*FileUtil fu=new FileUtil();
		byte[] buf=null;
		try {
			buf=fu.getBigFile("E:\\program\\Adobe Photoshop CS5\\Adobe Photoshop CS5\\Photoshop.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
		ObjectUtil ou=new ObjectUtil();
		ou.readPEObject(buf);*/
		/**
		 * 测试十六进制与整形的转化
		System.out.println(0X000000E8);
		CommonUtil cu=new CommonUtil();
		System.out.println(cu.toInteger("000000E8"));
		//System.out.println(cu.reverse("50450000"));
		 * 
		 */
		/*
		 * 测试数据对象的构造
		 
		byte[] buf=null;
		FileUtil fu=new FileUtil();
		try {
			buf=fu.getBigFile("E:\\program\\Adobe Photoshop CS5\\Adobe Photoshop CS5\\Photoshop.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(buf.length);
		ObjectUtil ou=new ObjectUtil();
		ou.generateObject(ou.toStringArray(buf));
		List<Object> list=ou.getObjectList();
		System.out.println(list.get(0).toString());
		ou.destroyObject();*/
		/**
		 * 测试StringReader的使用
		 */
		/*StringReader sr=new StringReader("hello,everybody!");
		try {
			sr.skip(1);
			char[] temp=new char[20];
			sr.read(temp,0,5);
			System.out.println((char)sr.read());
			System.out.println(new String(temp));
			System.out.println(String.valueOf(temp));
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		/**
		 * 测试将十六进制字符串转成整形值
		 */
		/*CommonUtil cu=new CommonUtil();
		System.out.println(cu.toInteger("000000F0"));*/
		/**
		 * 测试对象映射是否成功
		 */
		/*ObjectUtil ou=new ObjectUtil();
		FileUtil fu=new  FileUtil();
		CommonUtil cu=new CommonUtil();
		byte[] buffer=null;
		try {
			buffer = fu.getBigFile("C:\\Users\\acer\\Desktop\\lame.exe_downcc\\lame.exe\\lame.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String buffer1=cu.bytesToHexStrings(buffer, 0, buffer.length);
		ou.generateObject(buffer1);*/
		/**
		 * 测试将十六进制字符串转化成字符串
		 */
		/*StrBinaryTurn sbt=new StrBinaryTurn();
		System.out.println(sbt.toStringHex("546869732070726F6772"));*/
		/*CommonUtil cu=new CommonUtil();
		System.out.println(cu.insertStr("4D5A90000300000004000000FFFF0000B8000000000000004000000000000000", " "));
		System.out.println();*/
	}
}
