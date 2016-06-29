package com.wxq.parser.operations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import javax.swing.JFileChooser;

import org.junit.Test;
/**
 * 文件操作
 * 文件的读取
 * 文件类型的判断
 * @author acer
 *
 */
public class FileUtil {
	private CommonUtil cu=new CommonUtil();;
	private String[] content=null;
	private long fileSize=0;
	/**
	 * 判断文件是否为PE文件
	 * @param filePath 文件路径
	 * @return			结果
	 * @throws IOException IO异常
	 */
	public boolean isPEFile(String filePath) throws IOException {
		boolean result=false;
		//用于存储文件标志值
		String sign=null;
		byte[] buffer=new byte[2];
		int numRead=0;
		File file=new File(filePath);
		FileInputStream fis=new FileInputStream(file);
		//此处判断文件是否为PE文件，如果不是，则回馈失败信息
		if((numRead=fis.read(buffer,0,2))>0) {
			sign=cu.bytesToHexStrings(buffer, 0, 2);
			//判断文件开头是否为"4D5A"
			if("4D5A".equalsIgnoreCase(sign)) {
				//待删除
				System.out.println("该文件是PE文件");
				result=true;
			} else if("5A4D".equalsIgnoreCase(sign)) {
				//待删除
				System.out.println("该文件是PE文件");
				result=true;
			} else{
				//该文件不是PE文件
				System.out.println("该文件不是PE文件");
				result=false;
			}
		} else{
			//读取标志信息出错
			System.out.println("File read failed!");
			result=false;
		}
		return result;
	}
	/**
	 * 获取大文件的二进制数据
	 * @param filePath 文件路径
	 * @return 二进制数组
	 * @throws IOException IO异常
	 */
	public byte[] getBigFile(String filePath) throws IOException{
		  final int BUFFER_SIZE = 0x300000;// 缓冲区大小为3M
		  File file = new File(filePath);
		  fileSize=file.length();
		  //用于存储二进制数据
		  byte[] buffer=new byte[(int)fileSize];
		  byte[] dst = new byte[BUFFER_SIZE];// 每次读出3M的内容
		  /**
		   * 
		   * map(FileChannel.MapMode mode,long position, long size)
		   * 
		   * mode - 根据是按只读、读取/写入或专用（写入时拷贝）来映射文件，分别为 FileChannel.MapMode 类中所定义的
		   * READ_ONLY、READ_WRITE 或 PRIVATE 之一
		   * 
		   * position - 文件中的位置，映射区域从此位置开始；必须为非负数
		   * 
		   * size - 要映射的区域大小；必须为非负数且不大于 Integer.MAX_VALUE
		   * 
		   * 所以若想读取文件后半部分内容，如例子所写；若想读取文本后1/8内容，需要这样写map(FileChannel.MapMode.READ_ONLY,
		   * f.length()*7/8,f.length()/8)
		   * 
		   * 想读取文件所有内容，需要这样写map(FileChannel.MapMode.READ_ONLY, 0,f.length())
		   * 
		   */
		  MappedByteBuffer inputBuffer = new RandomAccessFile(file, "r")
		    .getChannel().map(FileChannel.MapMode.READ_ONLY,
		      0, file.length());
		  ByteArrayInputStream bis=new ByteArrayInputStream(dst,0,BUFFER_SIZE);
		  for (int offset = 0; offset < inputBuffer.capacity(); offset += BUFFER_SIZE) {
			   if (inputBuffer.capacity() - offset >= BUFFER_SIZE) {
				   //将数据暂存在dst中
				   inputBuffer.get(dst,0,BUFFER_SIZE);
				   //将数据赋值给buffer
				   bis.read(buffer,offset,dst.length); 
			   } else {
				   //将数据暂存在dst中
				   inputBuffer.get(dst,0,inputBuffer.capacity() - offset);
				   //将数据赋值给buffer
				   bis.read(buffer,offset,inputBuffer.capacity() - offset);
			   }
				  bis=new ByteArrayInputStream(dst,0,BUFFER_SIZE);
		 }
		  return buffer;
	}
	/**
	 * 读取小文件的二进制内容
	 * @param filePath 文件路径
	 * @return		        处理结果
	 * @throws IOException  IO异常
	 */
	public byte[] getSmallFile(String filePath) throws IOException{
		File file=new File(filePath);
		FileInputStream fis=new FileInputStream(file);
		fileSize=file.length();
		//用于存储二进制数据
		byte[] buffer=new byte[(int)fileSize];
		int offset=0;
		int numRead=0;
		if(fileSize>Integer.MAX_VALUE) {
			//判断文件的大小是否在整数范围内
			System.out.println("File too big!");
			return null;
		}
		//读取文件，当尚未到达文件末尾并且读取成功后，offset后移
		while(offset<buffer.length&&(numRead=fis.read(buffer,offset,buffer.length-offset))>=0) {
			offset+=numRead;
		}
		//确保所有数据均被读取完毕
		if(offset!=buffer.length) {
			throw new IOException("Could not completely read file"+file.getName());
		}
		fis.close();
		return buffer;
	}
	/**
	 * 打开文件并获取文件的路径
	 * @return
	 */
	public String openFile() {
		JFileChooser fd = new JFileChooser();  
		fd.showOpenDialog(null);  
		File f = fd.getSelectedFile();
		String filePath=null;
		if(f != null){
			filePath=f.getAbsolutePath();
			fileSize=f.length();
		}
		return filePath;
	}
	public long getFileSize() {
		return fileSize;
	}
	/**
	 * 获取文件的十六进制数据
	 * @return
	 */
	public String[] getHexString(byte[] buffer) {
		//用于存储十六进制字符串
		content=new String[buffer.length];
		return content;
	}
	/**
	 * 获取文件内容
	 * @return 返回字符串数组
	 */
	public String[] getFileContent() {
		return content;
	}
}
