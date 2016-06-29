package com.wxq.parser.filestruct;

import java.util.List;

public class ImageOptionalHeader {
	//此结构中字段名称
	public static String[] fileds=new String[]{
			"magic","majorLinkerVersion","minorLinkerVersion",
			"sizeOfCode","sizeofInitializedData","sizeofUninitializedData",
			"addressOfEntryPoint","baseOfCode","baseofData",
			"imageBase","sectionAlignment","fileAlignment",
			"majorOperatingSystemVersion","minorOperationSystemVersion","majorImageVersion",
			"minorImageVersion","MajorSubsystemVersion","minorSubSystemVersion",
			"win32VersionValue","sizeOfImage","sizeOfHeaders",
			"checkSum","subSystem","dllCharacteristics",
			"sizeOfStackReserve","sizeOfStackCommit","sizeOfHeapReserve",
			"sizeOfHeapCommit","loaderFlags","numberOfRvaAndSizes"};
	//此结构中字段的解释
	public static String[] remarks=new String[]{
			"文件格式","主版本号的链接器","次版本号的链接器",
			"代码段的总大小","已初始化数据的节的大小","未初始化数据的节的大小",
			"程序的入口点","代码段的起始RVA","数据段的起始RVA",
			"程序的建议装载地址","节对齐粒度","文件对齐粒度",
			"操作系统的主版本号","操作系统的副版本号","应用程序的主版本号",
			"应用程序的副版本号","子系统的朱版本号","子系统的副版本号",
			"保留值","程序调用后占用内存的大小","所有头加节表的大小",
			"校验和","文件运行所需的子系统","dll文件的属性值",
			"初始化的堆栈大小","初始化实际提交的堆栈大小","初始化时保留堆的大小",
			"初始化时实际提交的堆的大小","加载器标志值","数据目录结构的数量"
	};
	//表示每个字段相对ImageNTHeader的偏移
	public static short[] offset=new short[]{26,28,29, 30,34,38, 42,46,50, 54,58,62, 
								  66,68,70, 72,74,76, 78,82,86, 90,94,96,
								  98,102,106, 110,114,118, 122};
	public static short start;
	public static short length;
	public static List<ImageDataDirectory> imgDataDirectory;//数据目录结构链
	/**
	 * 关键字段
	 */
	public static String sizeOfCode;
	public static String sizeofInitializedData;
	public static String sizeofUninitializedData;
	public static String addressOfEntryPoint;
	public static String baseOfCode;
	public static String imageBase;
	public static String baseofData;
	public static String sizeOfImage;
	public static String sizeOfHeaders;
	public static String sizeOfStackReserve;
	public static String sizeOfStackCommit;
	public static String sizeOfHeapReserve;
	public static String sizeOfHeapCommit;
	public static String loaderFlags;
	public static String numberOfRvaAndSizes;
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ImageOptionalHeader [toString()=" + super.toString() + "]";
	}
	
	
	
}