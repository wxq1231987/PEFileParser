package com.wxq.parser.filestruct;

public class ImageFileHeader {
	//占用20个字节
	//此结构中的字段名称
	public static String[] fields=new String[]{
			"machine","numberOfSections","timeDateStamp","pointerToSymbolTable",
			"numberOfSymbols","sizeOfOptionalHeader","characteristics"};
	//此结构中字段的解释
	public static String[] remarks=new String[]{"运行平台","节表的数目","表示时间","COFF符号指针",
			"符号数","可选部长度","文件属性"};
	//每个字段相对ImageNTHeader的偏移
	public static short[] offset=new short[]{4,6,8,12,16,20,22,24,26};
	public static short start;
	public short length=20;//对应的是该结构在数组中对应的元素个数
	public static String numberOfSections;
}
