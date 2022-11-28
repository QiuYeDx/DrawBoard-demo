package homework;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class UI extends JFrame implements ActionListener, ItemListener, Serializable {

	private static final long serialVersionUID = 1L;

	public JFrame jf;
	public JPanel drawJP;// 作为画板使用
	public JPanel toolJP;// toolJP用来存放画画的各种工具
	public JPanel toolJP2;// toolJP2用来存放文本框
	public static String[] jg = {"-请选择-"};
	public static String[] jg2 = {"-请查找-"};
	public static JList jl = new JList(jg);
	public static JList jl2 = new JList(jg2);
	private Color color = Color.white;// 设置画板的初始颜色为白色
	private static JTextField text = new JTextField("", 20); // 单行文本框20列长度
	private static int t = 0, k = 0;// 用来监测“粗体”、“斜体”的复选状态，等于0则表示没有选中。
	private static String textFont = "宋体";// 设置字体默认格式
	private static String selectedType = "ALL";// 设置分类查看类型
	private static int textInt = 5;// 设置字体默认大小
	private static Font fnt = new Font(textFont, 0, textInt);
	private static Graphics g;
	// 设置springLayout弹性布局，方便文本框的部署
	SpringLayout springLayout = new SpringLayout();
	// 在实例化DrawListener类的对象时将获取的画笔对象传递过去
	DrawListener dl;

	// 保存图片时使用--面板重绘
	public Color getdrawJPbackground() {
		return drawJP.getBackground();
	}

	public static String getText() {
		System.out.println("文本框内容为：" + text.getText());
		return text.getText();
	}

	// 修改字体大小
	public static void setTextInt(int testint) {
		textInt = testint;
	}

	// 获取字体大小
	public static int getTextInt() {
		return textInt;
	}

	// 设置字体格式类型
	public static void setFontType(Font f) {
		fnt = f;
	}

	// 修改字体格式
	public static void setTextFont(String testfont) {
		textFont = testfont;
	}

	// 获取字体格式
	public static String getTextFont() {
		return textFont;
	}

	// 修改分类查看类型
	public static void setSelectedType(String selectedType1) {
		selectedType = selectedType1;
	}

	// 获取分类查看类型
	public static String getSelectedType() {
		return selectedType;
	}

	// 获取font用在画图时获取文字的地方
	public static Font getF() {
		return fnt;
	}

	public void showUI(UI ui) {
		// new一个JFrame窗体
		jf = new JFrame("画图工具");
		// 设置窗体大小
		jf.setSize(1500, 1000);
		// 设置窗体的布局为边界布局，分为东南西北中五个方位，可以将组件添加到指定的地方
		jf.setLayout(new BorderLayout());
		// 设置窗体居中显示
		jf.setLocationRelativeTo(null);
		// 给窗体设置退出按钮 关掉即退出程序
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设定不可以改变窗口大小
		jf.setResizable(false);
		// 初始化各种菜单栏和组件
		initBtn(ui);
	}

	// 初始化各种菜单栏和组件
	private void initBtn(UI ui) {
		JMenuBar mBar = new JMenuBar();
		JMenu file = new JMenu("文件(F)");
		// 设置助记符:使用alt+F可以激活相应的事件
		file.setMnemonic('F');
		JMenuItem open = new JMenuItem("打开(O)");
		open.setMnemonic('O');
		JMenuItem newone = new JMenuItem("新建(N)");
		newone.setMnemonic('N');
		JMenuItem save = new JMenuItem("保存(S)");
		save.setMnemonic('S');
		JMenuItem exit = new JMenuItem("退出(X)");
		exit.setMnemonic('X');

		open.setActionCommand("打开");
		newone.setActionCommand("新建");
		save.setActionCommand("保存");
		exit.setActionCommand("退出");

		JMenu edit = new JMenu("编辑(B)");
		edit.setMnemonic('B');
		JMenuItem rebackItem = new JMenuItem("撤销");
		edit.add(rebackItem);
		rebackItem.setActionCommand("撤销");
		JMenuItem backItem = new JMenuItem("反撤销");
		edit.add(backItem);
		backItem.setActionCommand("反撤销");
		JMenuItem clearItem = new JMenuItem("画布清空");
		edit.add(clearItem);
		clearItem.setActionCommand("画布清空");

		JMenu help = new JMenu("帮助(H)");
		help.setMnemonic('H');
		JMenuItem helpItem = new JMenuItem("帮助文档");
		help.add(helpItem);
		helpItem.setActionCommand("帮助");
		JMenuItem quicklyItem = new JMenuItem("快捷键说明");
		help.add(quicklyItem);
		quicklyItem.setActionCommand("快捷键说明");

		JMenu menu4 = new JMenu("设置前景色(K)");
		menu4.setMnemonic('K');
		JMenuItem item1 = new JMenuItem("红色");
		JMenuItem item2 = new JMenuItem("绿色");
		JMenuItem item3 = new JMenuItem("蓝色");
		JMenuItem item4 = new JMenuItem("黄色");
		JMenuItem item5 = new JMenuItem("黑色");
		JMenuItem item6 = new JMenuItem("白色");
		JMenuItem item7 = new JMenuItem("纯蓝色");
		JMenuItem item8 = new JMenuItem("选择更多前景色...");

		JMenu menu5 = new JMenu("设置背景色(M)");
		menu5.setMnemonic('M');
		JMenuItem item51 = new JMenuItem("红色");
		JMenuItem item52 = new JMenuItem("绿色");
		JMenuItem item53 = new JMenuItem("蓝色");
		JMenuItem item54 = new JMenuItem("黄色");
		JMenuItem item55 = new JMenuItem("黑色");
		JMenuItem item56 = new JMenuItem("白色");
		JMenuItem item57 = new JMenuItem("纯蓝色");
		JMenuItem item58 = new JMenuItem("选择更多背景色...");

		jf.setJMenuBar(mBar);
		mBar.add(file);
		file.add(open);
		file.add(newone);
		file.add(save);
		file.add(exit);

		mBar.add(edit);
		mBar.add(help);

		mBar.add(menu4);
		menu4.add(item1);
		menu4.add(item2);
		menu4.add(item3);
		menu4.add(item4);
		menu4.add(item5);
		menu4.add(item6);
		menu4.add(item7);
		menu4.add(item8);

		mBar.add(menu5);
		menu5.add(item51);
		menu5.add(item52);
		menu5.add(item53);
		menu5.add(item54);
		menu5.add(item55);
		menu5.add(item56);
		menu5.add(item57);
		menu5.add(item58);

		item1.setActionCommand("红色");
		item2.setActionCommand("绿色");
		item3.setActionCommand("蓝色");
		item4.setActionCommand("黄色");
		item5.setActionCommand("黑色");
		item6.setActionCommand("白色");
		item7.setActionCommand("纯蓝色");
		item8.setActionCommand("选择更多前景色...");

		item51.setActionCommand("红色");
		item52.setActionCommand("绿色");
		item53.setActionCommand("蓝色");
		item54.setActionCommand("黄色");
		item55.setActionCommand("黑色");
		item56.setActionCommand("白色");
		item57.setActionCommand("纯蓝色");
		item58.setActionCommand("选择更多背景色...");

		// 定制绘画模块,new一个绘画区域的JPanel
		drawJP = new JPanel();
		// 设置背景颜色为白色，如果不设置背景颜色分别不出来不同Jpanel
		drawJP.setBackground(color);
		// 将绘画模块加到窗体中，边界布局选择中间，如果选择CENTER的话就不要设置大小，默认自动填满
		jf.add(drawJP, BorderLayout.CENTER);

		// 定制工具模块1--toolJP用来存放画画的各种工具
		toolJP = new JPanel();
		// 设置模块背景颜色
		toolJP.setBackground(new Color(28, 227, 192));
		// 除了窗体使用setSize(),其他组件的大小设置都需要使用setPreferredSize()方法
		toolJP.setPreferredSize(new Dimension(100, 0));// 宽度100，高度则自动适应
		// 将工具栏模块加到窗体中，边界布局选择东边
		jf.add(toolJP, BorderLayout.EAST);

		// 定制工具模块2--toolJP2用来存放文本框、粗体、斜体、字体格式等
		toolJP2 = new JPanel();
		// 更改布局为流式布局，从左到右，从上到下布局
		toolJP2.setLayout(new GridLayout(2, 2));
		// 设置背景颜色为绿色
		toolJP2.setBackground(new Color(28, 227, 192));
		// 除了窗体使用setSize(),其他组件的大小设置都需要使用setPreferredSize()方法
		toolJP2.setPreferredSize(new Dimension(0, 60));
		// 将工具栏模块加到窗体中，边界布局选择北边
		jf.add(toolJP2, BorderLayout.NORTH);

		// 添加到toolJP2的第一块面板
		JPanel mis1 = new JPanel();
		// 更改布局为springLayout弹性布局
		mis1.setLayout(springLayout);
		mis1.setBackground(new Color(28, 227, 192));
		JLabel j = new JLabel("输入编辑的文字：");
		// 设置字体，宋体，斜体，加粗，字号为28
		Font fnt3 = new Font("宋体", Font.BOLD, 18);
		// 设置标签中的字体
		j.setFont(fnt3);
		// 这个标签中字体的颜色
		j.setForeground(Color.YELLOW);
		mis1.add(j);
		mis1.add(text);
		springLayout.putConstraint(SpringLayout.WEST, j, 5, SpringLayout.WEST, mis1);
		springLayout.putConstraint(SpringLayout.WEST, text, 5, SpringLayout.EAST, j);
		toolJP2.add(mis1);

		// 添加到toolJP2的第二块面板
		JPanel mis2 = new JPanel();
		// 更改布局为springLayout弹性布局
		mis2.setLayout(springLayout);
		mis2.setBackground(new Color(28, 227, 192));
		// 除了窗体使用setSize(),其他组件的大小设置都需要使用setPreferredSize()方法
		mis2.setPreferredSize(new Dimension(0, 80));
		// 将工具栏模块加到toolJP2中
		toolJP2.add(mis2);

		// 三个复选框设置字体格式
		JCheckBox jcb1 = new JCheckBox("粗体");// Font.BOLD
		JCheckBox jcb2 = new JCheckBox("斜体");// Font.ITALIC
		jcb1.setBackground(new Color(28, 227, 192));
		jcb2.setBackground(new Color(28, 227, 192));
		mis2.add(jcb1);
		mis2.add(jcb2);
		springLayout.putConstraint(SpringLayout.WEST, jcb1, 5, SpringLayout.WEST, mis2);
		springLayout.putConstraint(SpringLayout.WEST, jcb2, 5, SpringLayout.EAST, jcb1);
		// 添加复选框监听器
		jcb1.addItemListener(this);
		jcb2.addItemListener(this);

		// 下拉菜单--字体形式
		Choice C1 = new Choice();
		C1.add("宋体");
		C1.add("华文中宋");
		C1.add("仿宋");
		C1.add("华文仿宋");
		C1.add("华文宋体");
		C1.add("华文彩云");
		C1.add("华文新魏");
		C1.add("华文楷体");
		C1.add("华文琥珀");
		C1.add("华文细黑");
		C1.add("华文行楷");
		C1.add("华文隶书");
		C1.add("幼圆");
		C1.add("微软雅黑");
		C1.add("新宋体");
		C1.add("黑体");
		C1.add("楷体");
		C1.add("方正姚体");
		C1.add("方正舒体");
		C1.add("方正粗黑宋简体");
		JLabel j2 = new JLabel("选择文字格式：");
		// 设置字体，宋体，斜体，加粗，字号为28
		Font fnt2 = new Font("宋体", Font.BOLD, 18);
		// 设置标签中的字体
		j2.setFont(fnt2);
		// 这个标签中字体的颜色
		j2.setForeground(Color.YELLOW);
		mis2.add(j2);
		mis2.add(C1);
		springLayout.putConstraint(SpringLayout.WEST, j2, 20, SpringLayout.EAST, jcb2);
		springLayout.putConstraint(SpringLayout.WEST, C1, 5, SpringLayout.EAST, j2);
		// 给下拉列表增加监视器
		C1.addItemListener(new FontwordType());

		// 下拉菜单--字体大小
		Choice C2 = new Choice();
		C2.add("1");
		C2.add("2");
		C2.add("3");
		C2.add("4");
		C2.add("5");
		C2.add("6");
		C2.add("7");
		C2.add("8");
		C2.add("9");
		C2.add("10");
		C2.add("11");
		C2.add("12");
		C2.add("14");
		C2.add("16");
		C2.add("18");
		C2.add("20");
		C2.add("22");
		C2.add("26");
		mis2.add(C2);
		springLayout.putConstraint(SpringLayout.WEST, C2, 5, SpringLayout.EAST, C1);
		// 给下拉列表增加监视器
		C2.addItemListener(new FontSize());

		JLabel j3 = new JLabel("画笔粗细：");
		j3.setFont(fnt2);
		j3.setForeground(Color.YELLOW);
		// 下拉菜单--画图时画笔的粗细
		Choice C3 = new Choice();
		C3.add("一号粗细");
		C3.add("二号粗细");
		C3.add("三号粗细");
		C3.add("四号粗细");
		mis2.add(j3);
		springLayout.putConstraint(SpringLayout.WEST, j3, 5, SpringLayout.EAST, C2);
		mis2.add(C3);
		springLayout.putConstraint(SpringLayout.WEST, C3, 5, SpringLayout.EAST, j3);
		// 给下拉列表增加监视器
		C3.addItemListener(new LineSize());

		// 在实例化DrawListener类的对象时将获取的画笔对象传递过去
		dl = new DrawListener(drawJP);

		// 定制功能按钮
		// 定义一个tools字符串数组存放你需要的工具
		String[] typeArray = { "直线", "矩形", "圆", "文字", "铅笔", "刷子", "橡皮", "喷枪" };
		for (int i = 0; i < typeArray.length; i++) {
			JButton button = new JButton(typeArray[i]);
			button.setPreferredSize(new Dimension(80, 30));
			toolJP.add(button);
			button.setActionCommand(typeArray[i]);
			button.addActionListener(dl);// 添加动作监听方法
		}

		// 选择分类查看列表
		JLabel label1 = new JLabel("分类查看名称：");
		toolJP.add(label1);
		String[] typeList = {"-请选择-", "ALL", "直线", "矩形", "圆", "文字"};
		JComboBox jcb;
		jcb = new JComboBox(typeList);
		jcb.addItemListener(new SelectedType());
		toolJP.add(jcb);

		// 显示分类结果列表
		jl.setVisibleRowCount(6);
		toolJP.add(jl);

		//显示选中图形位置
		jl.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// 获取所有被选中的选项索引
				int[] indices = jl.getSelectedIndices();
				// 获取选项数据的 ListModel
				ListModel<String> listModel = jl.getModel();
				// 输出选中的选项
				String selectedName = listModel.getElementAt(indices[0]);
				for (int i = 0; i < DrawListener.arrayD.size(); i++) {
					if (DrawListener.arrayD.get(i).getNameStr().equals(selectedName))
						draw(g, DrawListener.arrayD.get(i).getX1(),
								DrawListener.arrayD.get(i).getY1(),
								DrawListener.arrayD.get(i).getX2(),
								DrawListener.arrayD.get(i).getY2()
						);
				}
			}
		});
		//显示选中图形位置
		jl2.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// 获取所有被选中的选项索引
				int[] indices = jl2.getSelectedIndices();
				// 获取选项数据的 ListModel
				ListModel<String> listModel = jl2.getModel();
				// 输出选中的选项
				String selectedName = listModel.getElementAt(indices[0]);
				for (int i = 0; i < DrawListener.arrayD.size(); i++) {
					if (DrawListener.arrayD.get(i).getNameStr().equals(selectedName))
						draw(g, DrawListener.arrayD.get(i).getX1(),
								DrawListener.arrayD.get(i).getY1(),
								DrawListener.arrayD.get(i).getX2(),
								DrawListener.arrayD.get(i).getY2()
						);
				}
			}
		});

		// 对以上列表中选中的图形进行重命名
		JLabel label2 = new JLabel("修改选中名称：");
		toolJP.add(label2);
		JTextField jNewName = new JTextField("输入新名字");
		toolJP.add(jNewName);
		JButton jReName = new JButton("重命名");
		jReName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedName = jl.getSelectedValue().toString();
				for (int i = 0; i < DrawListener.arrayD.size(); i++) {
					if (DrawListener.arrayD.get(i).getNameStr().equals(selectedName))
						DrawListener.arrayD.get(i).setNameStr(jNewName.getText());
				}
				updateListOfType();
			}
		});
		toolJP.add(jReName);

		// 搜索特定图形
		JLabel label3 = new JLabel("搜索特定图形：");
		toolJP.add(label3);
		JTextField jForName = new JTextField("请输入名字");
		toolJP.add(jForName);
		JButton jFind = new JButton("查找图形");
		jFind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> data = new ArrayList<String>();
				String forName = jForName.getText();
				for (int i = 0; i < DrawListener.arrayD.size(); i++) {
					if (DrawListener.arrayD.get(i).getNameStr().equals(forName))
						data.add(DrawListener.arrayD.get(i).getNameStr());
				}
				if(data.isEmpty())
					data.add("无匹配结果");
				jl2.setListData(data.toArray());
			}
		});
		toolJP.add(jFind);
		toolJP.add(jl2);

		// 对搜索到的选中的图形进行重命名
		JLabel label4 = new JLabel("修改选中名称：");
		toolJP.add(label4);
		JTextField jNewName2 = new JTextField("输入新名字");
		toolJP.add(jNewName2);
		JButton jReName2 = new JButton("重命名");
		jReName2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedName = jl2.getSelectedValue().toString();
				for (int i = 0; i < DrawListener.arrayD.size(); i++) {
					if (DrawListener.arrayD.get(i).getNameStr().equals(selectedName))
						DrawListener.arrayD.get(i).setNameStr(jNewName2.getText());
				}
				ArrayList<String> data = new ArrayList<String>();
				data.add("请重新查找");
				jl2.setListData(data.toArray());
			}
		});
		toolJP.add(jReName2);


		jf.setVisible(true);
		// 需要在UI类中添加鼠标监听器以及画笔
		// 注意： 画笔的获取需要在setVisible(true)之后
		g = drawJP.getGraphics();
		// 给窗体添加鼠标事件监听方法，指定事件的处理类的对象dl;
		dl.setG(g);// 设置方法将画笔g传到DrawListener
		drawJP.addMouseListener(dl);
		drawJP.addMouseMotionListener(dl);

		item1.addActionListener(dl);
		item2.addActionListener(dl);
		item3.addActionListener(dl);
		item4.addActionListener(dl);
		item5.addActionListener(dl);
		item6.addActionListener(dl);
		item7.addActionListener(dl);
		item8.addActionListener(dl);

		item51.addActionListener(this);
		item52.addActionListener(this);
		item53.addActionListener(this);
		item54.addActionListener(this);
		item55.addActionListener(this);
		item56.addActionListener(this);
		item57.addActionListener(this);
		item58.addActionListener(this);

		open.addActionListener(dl);
		newone.addActionListener(dl);
		save.addActionListener(dl);
		exit.addActionListener(dl);
		helpItem.addActionListener(dl);
		rebackItem.addActionListener(dl);
		backItem.addActionListener(dl);
		clearItem.addActionListener(dl);
		quicklyItem.addActionListener(dl);
	}

	public void actionPerformed(ActionEvent e) {

		// 设置背景颜色
		String command = e.getActionCommand();
		if (command.equals("红色")) {
			drawJP.setBackground(Color.red);
			dl.setg2dBackground(Color.red);
			return;
		} else if (command.equals("绿色")) {
			drawJP.setBackground(Color.green);
			dl.setg2dBackground(Color.green);
			return;
		} else if (command.equals("蓝色")) {
			drawJP.setBackground(Color.blue);
			dl.setg2dBackground(Color.blue);
			return;
		} else if (command.equals("黄色")) {
			drawJP.setBackground(Color.yellow);
			dl.setg2dBackground(Color.yellow);
			return;
		} else if (command.equals("黑色")) {
			drawJP.setBackground(Color.black);
			dl.setg2dBackground(Color.black);
			return;
		} else if (command.equals("白色")) {
			drawJP.setBackground(Color.white);
			dl.setg2dBackground(Color.white);
			return;
		} else if (command.equals("纯蓝色")) {
			drawJP.setBackground(new Color(0, 255, 255));
			dl.setg2dBackground(new Color(0, 255, 255));
			return;
		} else if (command.equals("选择更多背景色...")) {
			color = JColorChooser.showDialog(drawJP, "选择颜色", Color.red);
			System.out.println(color.getRGB());
			drawJP.setBackground(color);
			dl.setg2dBackground(color);
			return;
		}
	}

	// 实现ItemListener的方法，只有一个方法
	public void itemStateChanged(ItemEvent e) {
		// 得到产生的事件，这里只有复选框所以可以强制类型转换。
		JCheckBox jcb = (JCheckBox) e.getItem(); // 得到产生的事件
		if (jcb.isSelected() && jcb.getText().equals("粗体")) {// t--e.getItem().equals("粗体")
			System.out.println("选中粗体");
			t = 1;
			return;
		} else if (jcb.isSelected() && jcb.getText().equals("斜体")) {// k
			System.out.println("选中斜体");
			k = 1;
			return;
		} else if (jcb.getText().equals("粗体")) {// 下面的都是取消选中复选框的情况
			System.out.println("取消选中粗体");
			t = 0;
			return;
		} else if (jcb.getText().equals("斜体")) {
			System.out.println("取消选中斜体");
			k = 0;
			return;
		}
	}

	public static void setTextFont() {// 根据t、k、h的值设置text的字体--“粗体”、“斜体”
		if (t == 0 && k == 0) {
			Font f = new Font(textFont, 0, textInt);
			UI.setFontType(f);
			return;
		} else if (t == 0 && k == 1) {
			Font f = new Font(textFont, Font.ITALIC, textInt);
			UI.setFontType(f);
			return;
		} else if (t == 1 && k == 0) {
			Font f = new Font(textFont, Font.BOLD, textInt);
			UI.setFontType(f);
			return;
		} else if (t == 1 && k == 1) {
			Font f = new Font(textFont, Font.BOLD + Font.ITALIC, textInt);
			UI.setFontType(f);
			return;
		}
	}
	public static void updateListOfType(){
		ArrayList<String> data = new ArrayList<String>();
		switch (selectedType) {
			case "ALL":
				for (int i = 0; i < DrawListener.arrayD.size(); i++) {
					if (!DrawListener.arrayD.get(i).getNameStr().equals("noShape"))
						data.add(DrawListener.arrayD.get(i).getNameStr());
				}
				break;
			case "直线":
				for (int i = 0; i < DrawListener.arrayD.size(); i++) {
					if (DrawListener.arrayD.get(i).getName().equals("直线"))
						data.add(DrawListener.arrayD.get(i).getNameStr());
				}
				break;
			case "矩形":
				for (int i = 0; i < DrawListener.arrayD.size(); i++) {
					if (DrawListener.arrayD.get(i).getName().equals("矩形"))
						data.add(DrawListener.arrayD.get(i).getNameStr());
				}
				break;
			case "圆":
				for (int i = 0; i < DrawListener.arrayD.size(); i++) {
					if (DrawListener.arrayD.get(i).getName().equals("圆"))
						data.add(DrawListener.arrayD.get(i).getNameStr());
				}
				break;
			case "文字":
				for (int i = 0; i < DrawListener.arrayD.size(); i++) {
					if (DrawListener.arrayD.get(i).getName().equals("文字"))
						data.add(DrawListener.arrayD.get(i).getNameStr());
				}
				break;
		}
		if(data.isEmpty())
			data.add("[无图形]");
		jl.setListData(data.toArray());
	}

	public void draw(Graphics g, int x1, int y1, int x2, int y2) {
		int x11, y11, x22, y22;
		x11 = x1 > x2 ? x1 + 10 : x1 - 10;
		y11 = y1 > y2 ? y1 + 10 : y1 - 10;
		x22 = x1 > x2 ? x2 - 10 : x2 + 10;
		y22 = y1 > y2 ? y2 - 10 : y2 + 10;
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.RED);
		g2d.setStroke(new BasicStroke(6));// 设置线条的粗细为6
		g2d.drawRect(Math.min(x11, x22), Math.min(y11, y22), Math.abs(x11 - x22), Math.abs(y11 - y22));
		Paint k = new Paint(x11, y11, x22, y22, Color.RED, "定时器", 8, null, fnt);// 根据图形的数据实例化Paint对象
		// 将数组对象存入到数组中
		DrawListener.arrayD.add(k);

		TimerTask task = new TimerTask() { // 从arrayD里删除"定时器",然后重绘
			public void run() {
				int x = DrawListener.arrayD.size();
				int t = x - 1;// 获取最后一个对象的下标
				// 删除"定时器"
				DrawListener.arrayD.remove(t);
				dl.paint(g);
			}
		};
		Timer timer = new Timer("Timer");
		long delay = 1000L;
		// 延迟1秒后执行task
		timer.schedule(task, delay);
	}

	public static void main(String[] args) {
		UI ui = new UI();
		ui.showUI(ui);
	}
}
