package com.wxq.parser.filestruct;

public class ImageNTHeader {
	//PE文件头标志，两个字节
	public static String signature;
	//PE文件表头
	public static ImageFileHeader fileHeader;
	//PE文件头可选部分
	public static ImageOptionalHeader optionHeader;
	public static short start=0;//表示此结构在字符串数组中的起始位置
	public static short length=0;//表示该结构对应的数据在字符串中的长度
}
