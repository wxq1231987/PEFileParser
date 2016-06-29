package com.wxq.parser.operations;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;

import com.wxq.parser.filestruct.DosStub;
import com.wxq.parser.filestruct.ImageDataDirectory;
import com.wxq.parser.filestruct.ImageDosHeader;
import com.wxq.parser.filestruct.ImageFileHeader;
import com.wxq.parser.filestruct.ImageNTHeader;
import com.wxq.parser.filestruct.ImageOptionalHeader;
/**
 * 按照PE文件的数据结构给字段赋值，
 * 将文件的内容与类对应起来，方便
 * 用户定位
 * @author acer
 *
 */
public class ObjectUtil {
	private static CommonUtil cu;
	
	public ObjectUtil() {
		cu=new CommonUtil();
		
	}
	/**
	 * 将文件中的数据与类的属性关联起来
	 */
	public void generateObject(String str) {
		System.out.println("generateObject->"+"开始解析数据");
		StringReader sr=new StringReader(str);
		createDosPart((short)0,sr);
		//获得PE文件头的开始位置
		String tmp1=cu.reverse(ImageDosHeader.lfanew);
		int tmp2=cu.toInteger(tmp1);
		short start1=(short)tmp2;
		createPEPart((short)(start1*2),sr);
	}
	/**
	 * 将DOS部分的文件数据与ImageDOSHeader和DosStub的静态属性对应起来
	 * @param sr 面向字符的输入流
	 */
	public void createDosPart(short start,StringReader sr) {
		System.out.println("createDosPart->"+"正在执行");
		System.out.println("传入的开始位置"+start);
		char[] buf=null;
		try {
			sr.reset();
			sr.skip(120);
			buf = new char[8];
			sr.read(buf,0,8);
			System.out.println("输出第一次解析的数据内容"+new String(buf,0,8));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String lfanew=String.valueOf(buf);
		String str=cu.reverse(lfanew);
		//给ImageDosHeader的字段赋值
		ImageDosHeader.start=start;//ImageDosHeader结构在数组中的起始位置
		ImageDosHeader.length=40;//标志ImageDosHeader结构中在数组中对应40个元素
		ImageDosHeader.lfanew=lfanew;
		//给DosStub的字段赋值
		DosStub.start=(short) 64;
		System.out.println("反转后"+str);
		DosStub.length=(short) (cu.toInteger(str)-64);
		System.out.println("DOS头部分的重要字段值如下：");
		System.out.println("flanew"+ImageDosHeader.lfanew);
	}
	/**
	 *  将PE文件头部分的数据与ImageNTHeaders的静态属性对应起来
	 * @param start 开始位置
	 * @param sr 面向字符的输入流
	 */
	public void createPEPart(short start,StringReader sr) {
		System.out.println("createPEPart->"+"正在执行");
		System.out.println("传入的开始位置"+start);
		char[] buf=null;
		try {
			sr.reset();
			sr.skip(start);
			buf = new char[8];
			sr.read(buf,0,8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageNTHeader.start=start;
		ImageNTHeader.signature=new String(buf,0,8);
		System.out.println("PE文件头开始位置"+ImageNTHeader.start);
		System.out.println("PE文件头标志"+ImageNTHeader.signature);
		createFileHeader((short) (start+8),sr);//488
		createPEOptExceptDataDir((short)(start+8+40),sr);//528
		ArrayList<ImageDataDirectory> list=createDataDir((short)(start+8+40+192),sr);//
		ImageOptionalHeader.imgDataDirectory=list;
	}
	/**
	 * 构造PE文件表头
	 */
	public void createFileHeader(short start,StringReader sr) {
		System.out.println("createFileHeader->"+"正在执行");
		System.out.println("传入的开始位置"+start);
		char[] buf=null;
		try {
			sr.reset();
			sr.skip(start);
			buf = new char[8];
			sr.read(buf,0,8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * 给ImageFileHeader的重要字段赋值
		 */
		ImageFileHeader.start=start;
		ImageFileHeader.numberOfSections=new String(buf,4,4);
		System.out.println("PE文件表头的重要字段如下：");
		System.out.println("start"+ImageFileHeader.start);
		System.out.println("numberOfSections"+ImageFileHeader.numberOfSections);
	}
	/**
	 * 构造PE文件头除去数据目录表的部分
	 */
	public void createPEOptExceptDataDir(short start,StringReader sr) {
		System.out.println("createPEOptExceptDataDir->"+"正在执行");
		System.out.println("传入的开始位置"+start);
		char[] buf=null;
		try {
			sr.reset();
			sr.skip(start);
			buf = new char[256];
			sr.read(buf,0,256);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * 给ImageOptionalHeader的重要字段赋值
		 */
		ImageOptionalHeader.start=start;
		ImageOptionalHeader.sizeOfCode=new String(buf,8,8);
		ImageOptionalHeader.sizeofInitializedData=new String(buf,16,8);
		ImageOptionalHeader.sizeofUninitializedData=new String(buf,24,8);
		ImageOptionalHeader.addressOfEntryPoint=new String(buf,32,8);
		ImageOptionalHeader.baseOfCode=new String(buf,40,8);
		ImageOptionalHeader.baseofData=new String(buf,48,8);
		ImageOptionalHeader.imageBase=new String(buf,56,8);
		ImageOptionalHeader.sizeOfImage=new String(buf,112,8);
		ImageOptionalHeader.sizeOfHeaders=new String(buf,120,8);
		ImageOptionalHeader.sizeOfStackReserve=new String(buf,144,8);
		ImageOptionalHeader.sizeOfStackCommit=new String(buf,152,8);
		ImageOptionalHeader.sizeOfHeapReserve=new String(buf,160,8);
		ImageOptionalHeader.sizeOfHeapCommit=new String(buf,168,8);
		ImageOptionalHeader.loaderFlags=new String(buf,176,8);
		ImageOptionalHeader.numberOfRvaAndSizes=new String(buf,184,8);
		System.out.println("PF可选头除数据目录部分的重要字段如下：");
		System.out.println("sizeOfCode"+ImageOptionalHeader.sizeOfCode);
		System.out.println("addressOfEntryPoint"+ImageOptionalHeader.addressOfEntryPoint);
		System.out.println("numberOfRvaAndSizes"+ImageOptionalHeader.numberOfRvaAndSizes);
		System.out.println("start"+ImageOptionalHeader.start);
		
	}
	/**
	 * 构造数据目录表
	 */
	public ArrayList<ImageDataDirectory> createDataDir(short start,StringReader sr) {
		System.out.println("createDataDir->"+"正在执行");
		System.out.println("传入的开始位置"+start);
		char[] buf=null;
		try {
			sr.reset();
			sr.skip(start);
			buf = new char[128];
			sr.read(buf,0,128);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("测试代码"+new String(buf,0,128));
		ImageDataDirectory idr;
		ArrayList<ImageDataDirectory> list=new ArrayList<ImageDataDirectory>();
		String temp=cu.reverse(ImageFileHeader.numberOfSections);
		int size=cu.toInteger(temp);
		System.out.println("数据目录表内容如下：");
		for(int i=0;i<size;i++) {
			idr=new ImageDataDirectory();
			idr.setVirtualAddress(new String(buf,i*16,8));
			idr.setSize(new String(buf,i*16+8,8));
			System.out.print(idr.getVirtualAddress()+"\t");
			System.out.print(idr.getSize()+"\t");
			System.out.println();
			list.add(idr);
		}
		return list;
	}
}