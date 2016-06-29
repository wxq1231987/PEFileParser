package com.wxq.parser.filestruct;

public class ImageDataDirectory {
	private String virtualAddress;
	private String size;
	public  int start = 0;
	public  int length = 2;//表名字段的个数
	/**
	 * @return the start
	 */
	public  int getStart() {
		return start;
	}
	/**
	 * @return the length
	 */
	public  int getLength() {
		return length;
	}
	/**
	 * @return the virtualAddress
	 */
	public String getVirtualAddress() {
		return virtualAddress;
	}
	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}
	/**
	 * @param virtualAddress the virtualAddress to set
	 */
	public void setVirtualAddress(String virtualAddress) {
		this.virtualAddress = virtualAddress;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}
	
}
