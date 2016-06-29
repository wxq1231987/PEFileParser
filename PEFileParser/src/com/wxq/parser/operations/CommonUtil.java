package com.wxq.parser.operations;

import java.io.UnsupportedEncodingException;

/**
 * 基本的工具类
 * @author acer
 *
 */
public class CommonUtil {
	/**
	 * 将byte数组转化成十六进制字符串
	 * @param src 二进制数组
	 * @param offset 起始位置
	 * @param len 进行转化的二进制长度
	 * @return 字符串数组
	 */
	public String bytesToHexStrings(byte[] src,int offset,int len) {
		try {
			String uni=new String(src,"UTF-8");
			System.out.println(uni.substring(0));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(src==null||src.length<=0) {
			return null;
		}
		StringBuffer sbf=new StringBuffer();
		String[] str=new String[src.length];
		for(int i=offset;i<=len+offset-1;i++) {
			int v=src[i]&0XFF;
			String hv=Integer.toHexString(v).toUpperCase();
			if(hv.length()<2) {
				str[i]="00";
			} 
			if(hv.length()==1) {
				hv="0".concat(hv);
			}
			str[i]=hv;
			sbf.append(str[i]);
		}
		return sbf.toString();
	}
	/**
	 * 转化成字符串数组
	 * @param src 二进制数组
	 * @param offset 偏移量
	 * @param len 数组的长度
	 * @return 字符串数组
	 */
	public String[] bytesToHexStringArray(byte[] src,int offset,int len) {
		if(src==null||src.length<=0) {
			return null;
		}
		String[] str=new String[src.length];
		for(int i=offset;i<=len+offset-1;i++) {
			int v=src[i]&0XFF;
			String hv=Integer.toHexString(v).toUpperCase();
			if(hv.length()<2) {
				str[i]="00";
			} 
			if(hv.length()==1) {
				hv="0".concat(hv);
			}
			str[i]=hv;
		}
		return str;
	}
	/**
	 * 因为在windows中高字节存在高位、低字节存在低位，在将十六进制数据转化成整形时，
	 * 需先将字符串反转
	 * @param str 带转化的字符串
	 * @return 转化后的字符串
	 */
	public String reverse(String str) {
		int length=str.length();
		StringBuffer sb = new StringBuffer();
		String[] s=new String[length/2];
		for(int i=0;i<length/2;i++) {
			s[i]=str.substring(length-2*(i+1),length-2*i );
			sb. append(s[i]);
		}
		return sb.toString();
	}
	/**
	 * 将十六进制字符串转化成十进制纳
	 * @param str待转化的十六进制字符串
	 * @return	转化后的结果
	 */
	public int toInteger(String str) {
	    str="0X".concat(str);
		return Integer.decode(str);
	}
	public String insertStr(String src,String insert) {
		StrBinaryTurn sbt=new StrBinaryTurn();
		StringBuffer sbf=new StringBuffer();
		for(int i=0;i<src.length();i+=2) {
			if(i%32==0) {
				if(i>0) {
					sbf.append(" ");
					//sbf.append(sbt.toStringHex(src.substring(i, i+31)));
					sbf.append("\n");
				}
				String offset=String.format("%08d",i/2);
				sbf.append(offset+" ");
			} 
		    String str=src.substring(i, i+2).concat(insert);
		    sbf.append(str);
		}
		return sbf.toString();
	}
}
