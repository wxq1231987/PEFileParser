package com.wxq.parser.filestruct;

import java.io.Serializable;

public class ImageDosHeader implements Serializable{
	//总共64个字节，除去lfanew都是两个字节
	public static String lfanew;//指向的是新exe头在文件中的地址
	public static short start;//字符串数组的开始位置
	public static short length;//指的是字符串数组的长度
	//此处表示字段名称
	public static String[] fields=new String[]{
			"magic","cblp","cp","crlc","cparhdr",
			"minalloc","maxalloc","ss","sp","csum",
			"ip","cs","lfarl","ovno","res",
			"oemid","oeminfo","res2","lfanew"
	};
	//此处表示字段的解释
	public static String[] remarks=new String[]{
			"MZ","文件最后一页的字节数","文件的页数","重定位","段中头的大小",
			"最少的额外段","最多额外段","初始的SS寄存器的值","初始的SP寄存器的值","校验和",
			"初始的IP寄存器值","初始的CS寄存器值","重定位表在文件中的地址","交叠数","保留字，占用8个字节",
			"OEM识别符","OEM信息","保留字，占用两个字节","新exe头在文件中的地址"};
	//此处表示字段在本结构的相对偏移，start最后一个值对应的是此结构的结束位置
	public static short[] offset=new short[]{0,2,4,6,8,10,12,14,
			16,18,20,22,24,26,28,30,32,34,36,38,40,42,
			44,46,48,50,52,54,56,58,60,63};
	/**
	 * @return the fields
	 */
	public String[] getFields() {
		return fields;
	}
	/**
	 * @return the remarks
	 */
	public String[] getRemarks() {
		return remarks;
	}
	/**
	 * @return the start
	 */
	public short[] getStart() {
		return offset;
	}
}
