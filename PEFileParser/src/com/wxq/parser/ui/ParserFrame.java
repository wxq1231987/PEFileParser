package com.wxq.parser.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import com.wxq.parser.operations.CommonUtil;
import com.wxq.parser.operations.FileUtil;
import com.wxq.parser.operations.ObjectUtil;

public class ParserFrame extends JFrame implements ActionListener{
	/*定义控件*/
	private JMenuBar mb=new JMenuBar();
	private JMenu menu_file=new JMenu("文件");
	private JMenu menu_dos=new JMenu("DOS部分");
	private JMenu menu_pe=new JMenu("PE文件头");
	private JMenu menu_table=new JMenu("节表");
	private JMenu menu_data=new JMenu("节数据");
	private JMenuItem mit_open=new JMenuItem("打开");
	private JMenuItem mit_exit=new JMenuItem("退出");
	private JMenuItem mit_dosHeader=new JMenuItem("DOS MZ文件头");
	private JMenuItem mit_dosStub=new JMenuItem("DOS残余头");
	private JMenuItem mit_peSign=new JMenuItem("PE文件头标志");
	private JMenuItem mit_peHeader=new JMenuItem("PE文件表头");
	private JMenuItem mit_peOpt=new JMenuItem("PE文件头可选部分");
	private JMenuItem mit_dataDir=new JMenuItem("数据目录表");
	private JMenuItem mit_code=new JMenuItem("执行代码段");
	private JMenuItem mit_data=new JMenuItem("数据段");
	private JMenuItem mit_res=new JMenuItem("资源段");
	private JMenuItem mit_impTable=new JMenuItem("导入表");
	private JMenuItem mit_expTable=new JMenuItem("导出表");
	private JMenuItem mit_debug=new JMenuItem("信息调试段");
	JSplitPane jsp_con=new JSplitPane();
	private JTextArea txa_data=new JTextArea("Hello,这里是文本显示区域");
	private JScrollPane jsp=new JScrollPane(txa_data);
	/**
	 * 定义工具类
	 */
	FileUtil fu;
	CommonUtil cu;
	ObjectUtil ou;
	/**
	 * 构造方法初始化控件
	*/
	public ParserFrame(){
		super("PE文件解析器");
		fu=new FileUtil();
		cu=new CommonUtil();
		ou=new ObjectUtil();
		Container cp=this.getContentPane();
		cp.setPreferredSize(new Dimension(945,865));
		cp.setLayout(new FlowLayout(FlowLayout.LEFT));
		cp.add(mb);
		/**
		 * 为菜单项添加事件监听器
		 */
		mit_open.addActionListener(new ActListener());
		mit_exit.addActionListener(new ActListener());
		mit_dosHeader.addActionListener(new ActListener());
		mit_dosStub.addActionListener(new ActListener());
		mit_peSign.addActionListener(new ActListener());
		mit_peHeader.addActionListener(new ActListener());
		mit_peOpt.addActionListener(new ActListener());
		mit_dataDir.addActionListener(new ActListener());
		mit_code.addActionListener(new ActListener());
		mit_data.addActionListener(new ActListener());
		mit_res.addActionListener(new ActListener());
		mit_impTable.addActionListener(new ActListener());
		mit_expTable.addActionListener(new ActListener());
		mit_debug.addActionListener(new ActListener());
		//首先向菜单栏中添加菜单
		mb.add(menu_file);
		mb.add(menu_dos);
		mb.add(menu_pe);
		mb.add(menu_table);
		mb.add(menu_data);
		mb.setBounds(10, 10, 930, 30);
		//然后向菜单中添加菜单项
		menu_file.add(mit_open);
		menu_file.add(mit_exit);
		menu_file.addActionListener(new ActListener());
		menu_dos.add(mit_dosHeader);
		menu_dos.add(mit_dosStub);
		menu_pe.add(mit_peSign);
		menu_pe.add(mit_peHeader);
		menu_pe.add(mit_peOpt);
		menu_pe.add(mit_dataDir);
		menu_pe.addActionListener(new ActListener());
		menu_data.add(mit_code);
		menu_data.add(mit_data);
		menu_data.add(mit_res);
		menu_data.add(mit_expTable);
		menu_data.add(mit_impTable);
		menu_data.add(mit_debug);
	    menu_data.addActionListener(new ActListener());
		//设置中间面板的尺寸
		/*//jsp_con.setSize(width, height);
		jsp.setSize(520, 720);
		jsp.setBorder(new LineBorder(new Color(234,234,234),10));
		cp.add(jsp_con);
		jsp_con.add(txa_data);
		jsp_con.setDividerLocation(10);
		jsp_con.setResizeWeight(0.3);
		jsp_con.setSize(cp.getWidth()-20,cp.getHeight()-50);*/
		//设置JScrollPane参数
		jsp_con.setContinuousLayout(true);
		jsp_con.setPreferredSize(new Dimension(940,730));
		jsp_con.setMinimumSize(new Dimension(700,730));
		jsp_con.setMaximumSize(new Dimension(1379,730));
		jsp_con.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		jsp_con.setLeftComponent(jsp);
		jsp_con.setRightComponent(new JTable());
		jsp_con.setDividerLocation(0.6);
		cp.add(jsp_con);
		//向面板中添加组件
		txa_data.setColumns(90);
		txa_data.setRows(100);
		//设置换行策略
		txa_data.setWrapStyleWord(true);
		//txa_data.setSize( 500, 700);
		//txa_data.setBackground(new Color(125,125,125));
		txa_data.setBorder(new LineBorder(new Color(234,234,234),1));
		txa_data.setFont(new Font("宋体",Font.BOLD,16));
		//jsp_con.add(txa_data);
		/**
		 * 此处添加JTable
		 */
		//添加组件事件监听器
		this.addComponentListener(new MyComponentListener());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(950,870));//设定父窗口内容面板的初始大小
		this.setJMenuBar(mb);
		this.setVisible(true);
		this.pack();//根据布局和组件尺寸自动调整大小
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	//用于监听组件窗口的改变
	class MyComponentListener implements ComponentListener{

		@Override
		public void componentResized(ComponentEvent e) {
			Container cp=(Container)e.getSource();
			int width=cp.getWidth();
			int height=cp.getHeight();
			Dimension mbd=new Dimension();
			mbd.setSize(width*0.70,height*0.20);
			mb.resize(mbd);
			Dimension spd=new Dimension();
			spd.setSize(width*0.98,height*0.80);
			jsp_con.resize(spd);
			jsp_con.setDividerLocation(0.6);//设置JSplitPane的分隔条的位置
			jsp_con.updateUI();//重绘制JSplitPane
			/**
			 * 此处为检测代码，用于测试排版布局
			 */
			System.out.println("内容面板：宽"+cp.getWidth()+",高"+cp.getHeight());
			System.out.println("菜单栏：宽"+mb.getWidth()+",高"+mb.getHeight());
			System.out.println("分割面板：宽"+jsp_con.getWidth()+",高"+jsp_con.getHeight());
		}

		@Override
		public void componentMoved(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentShown(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentHidden(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	class ActListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("菜单被点击");
			//获取事件源
			JMenuItem item=(JMenuItem)e.getSource();
			if(item==mit_open) {
				System.out.println("按下了打开菜单");
				String path=fu.openFile();
				byte[] buf=null;
				try {
					if(!fu.isPEFile(path)) {
						System.out.println("不是PE文件");
					}
					if(fu.getFileSize()>10485760) {
						buf=fu.getSmallFile(path);
					} else {
						buf=fu.getBigFile(path);
					}
					//将二进制数组转化成十六进制字符串
					String tem=cu.bytesToHexStrings(buf, 0, buf.length);
					String con=cu.insertStr(tem, " ");
					txa_data.setText(con);
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			} else if(item==mit_exit) {
				System.out.println("按下了mit_exit菜单");
			} else if(item==mit_dosHeader) {
				System.out.println("按下了DosHeader菜单");
			} else if(item==mit_dosStub) {
				System.out.println("按下了mit_dosStub菜单");
			} else if(item==mit_peSign) {
				System.out.println("按下了mit_peSign菜单");
			} else if(item==mit_peHeader) {
				System.out.println("按下了mit_peHeader菜单");
			} else if(item==mit_peOpt) {
				System.out.println("按下了mit_peOpt菜单");
			} else if(item==mit_dataDir) {
				System.out.println("按下了mit_dataDir菜单");
			} else if(item==mit_code) {
				System.out.println("按下了mit_code菜单");
			} else if(item==mit_data) {
				System.out.println("按下了mit_data菜单");
			} else if(item==mit_res) {
				System.out.println("按下了mit_res菜单");
			} else if(item==mit_impTable) {
				System.out.println("按下了mit_impTable菜单");
			} else if(item==mit_expTable) {
				System.out.println("按下了mit_expTable菜单");
			} else if(item==mit_debug) {
				System.out.println("按下了mit_debug菜单");
			} 
			
		}
	}
}
