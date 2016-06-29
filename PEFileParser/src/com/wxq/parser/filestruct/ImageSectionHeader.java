package com.wxq.parser.filestruct;

public class ImageSectionHeader {
	//此结构中的字段名称
	public static String[] fields=new String[]{
			"name","physicalAddress","virtualAddress",
			"virtualAddress","sizeOfRawData","pointerToRawData",
			"pointerToRelocations","pointerToLinenumbers","numberOfRelocations",
			"numberOfLinenumbers","characteristics"};
	//此结构中字段的解释
	public static String[] remarks=new String[]{
			"节表名称","物理地址","真实长度",
			"节区的RVA地址","在文件中对齐后的尺寸","在文件中的偏移量",
			"在OBJ文件中使用，重定位的偏移","行号表的偏移","在OBJ文件中使用，重定位项数目",
			"行号表中行号的数目","节属性如可读、可写、可执行等"
	};
	//各个字段相对本节表的偏移量，以一个字节为单位
	public static int[] start={
			0,1,5, 9,13,17, 21,25,27, 29,33
	};
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
	public int[] getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(int[] start) {
		this.start = start;
	}
	
}
