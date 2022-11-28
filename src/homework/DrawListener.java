package homework;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

//定义DrawListener事件处理类，该类继承自MouseListener鼠标事件接口，重写接口中的抽象方法。
public class DrawListener extends JPanel implements MouseListener, MouseMotionListener, ActionListener {

	private static final long serialVersionUID = 1L;

	private int x1, x2, y1, y2;
	// 定义Graphics画笔类的对象属性名
	private Graphics2D g;
	public String name = "直线";
	private Color color = Color.red;
	private JPanel jp;
	public static ArrayList<Paint> arrayD = new ArrayList<Paint>();// 定义存储图形的数组
	public ArrayList<Paint> arrayR = new ArrayList<Paint>();// 存储被撤销的图形
	private static Font fnt = new Font(null);// 用来保存文字的格式信息，设置为空---重绘图像时有用
	private Color backgroundColor = Color.WHITE;
	int[][] img = null;// 存储打开图片时，图片的信息，方便在保存图片时再次重绘在缓冲区里
	private static int straightlinesize = 1;// 直线粗细
	private static int circlesize = 1;// 椭圆线条粗细
	private static int rectanglesize = 1;// 矩形线条粗细
	private static int pencilsize = 1;// 铅笔线条粗细
	private static int brushsize = 10;// 刷子线条粗细
	private static int pengunsize = 10;// 喷枪宽度

	public DrawListener() {

	}

	public DrawListener(JPanel jp) {
		this.jp = jp;
	}

	// 设置各种线条粗细
	public static void setStraightLineSize(int size) {
		straightlinesize = size;
	}

	public static void setCircleSize(int size) {
		circlesize = size;
	}

	public static void setRectangleSize(int size) {
		rectanglesize = size;
	}

	public static void setPencilSize(int size) {
		pencilsize = size;
	}

	public static void setBrushSize(int size) {
		brushsize = size;
	}

	public static void setPengunSize(int size) {
		pengunsize = size;
	}

	// 设置保存图片时的背景颜色
	public void setg2dBackground(Color color) {
		this.backgroundColor = color;
	}

	// 定义一个带Graphics参数的构造方法
	public void setG(Graphics g) {
		this.g = (Graphics2D) g;
		this.g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);// 设置画笔开启抗锯齿
		this.g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
	}

	// 重写父类的重绘方法
	@Override
	public void paint(Graphics g) {
		// 一定要先调用父类的paint方法，用来绘制窗体
		super.paint(g);

		Dimension imagesize = this.jp.getSize();
		BufferedImage bufferedImage = new BufferedImage(imagesize.width, imagesize.height, BufferedImage.TYPE_INT_RGB);
		Graphics2D image = (Graphics2D) bufferedImage.createGraphics();
		// 设置背景颜色
		image.setColor(backgroundColor);
		// 填充整张图片(其实就是设置背景颜色)
		image.fillRect(0, 0, imagesize.width, imagesize.height);
		// 检测一下是否有从外部打开的文件，如果有就先把该文件画在缓冲区里，然后在画在此图片上的修改
		if (img != null) {
			drawImage(image, img);
		}
		// 绘制图像
		initPaint(image);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);// 设置画笔抗锯齿
		g2d.drawImage(bufferedImage, 0, 0, null);
	}

	private void initPaint(Graphics2D g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);// 设置画笔抗锯齿

		// 如果array==0,则清屏
		Color color = jp.getBackground();
		if (arrayD.size() == 0) {
			jp.setBackground(Color.GRAY);
			jp.setBackground(color);
			return;
		}

		// 把存储在数组中的图形数据取出来，重新画一次
		for (Paint p : arrayD) { // 获取数组中指定下标位置的图形对象
			if (p != null) {
				x1 = p.getX1();
				x2 = p.getX2();
				y1 = p.getY1();
				y2 = p.getY2();
				g2d.setColor(p.getColor());
				if (p.getName().equals("直线")) {
					g2d.setStroke(new BasicStroke(p.getWidth()));
					g2d.drawLine(x1, y1, x2, y2);
				} else if (p.getName().equals("矩形")) {
					g2d.setStroke(new BasicStroke(p.getWidth()));
					g2d.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
				} else if (p.getName().equals("圆")) {
					g2d.setStroke(new BasicStroke(p.getWidth()));
					g2d.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
				} else if (p.getName().equals("文字")) {
					g2d.setStroke(new BasicStroke(p.getWidth()));
					g2d.setFont(p.getTextFont());
					g2d.drawString(p.getText(), x1, y1);
				} else if (p.getName().equals("铅笔")) {
					g2d.setStroke(new BasicStroke(p.getWidth()));
					g2d.drawLine(x1, y1, x2, y2);
				} else if (p.getName().equals("刷子")) {
					g2d.setStroke(new BasicStroke(p.getWidth()));
					g2d.drawLine(x1, y1, x2, y2);
				} else if (p.getName().equals("橡皮")) {
					g2d.setStroke(new BasicStroke(p.getWidth()));
					g2d.drawLine(x1, y1, x2, y2);
				} else if (p.getName().equals("喷枪")) {
					g2d.setStroke(new BasicStroke(p.getWidth()));
					g2d.drawLine(x1, y1, x2, y2);
				} else
					break;
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("点击的按钮是：" + e.getActionCommand());
		// 判断是哪个菜单项被按下
		String command = e.getActionCommand();
		if (command.equals("红色")) {
			color = Color.red;
			return;
		} else if (command.equals("绿色")) {
			color = Color.green;
			return;
		} else if (command.equals("蓝色")) {
			color = Color.blue;
			return;
		} else if (command.equals("黄色")) {
			color = Color.yellow;
			return;
		} else if (command.equals("黑色")) {
			color = Color.black;
			return;
		} else if (command.equals("白色")) {
			color = Color.white;
			return;
		} else if (command.equals("纯蓝色")) {
			color = new Color(0, 255, 255);
			return;
		} else if (command.equals("选择更多前景色...")) {
			color = JColorChooser.showDialog(jp, "选择颜色", Color.red);
			System.out.println(color.getRGB());
			return;
		} else if (command.equals("打开")) {
			// 打开逻辑实现： 当点击打开菜单项时，
			// 首先应该清空容器里面的东西，然后面板重绘
			// 然后再把打开的文件利用对象输入流读入
			// 将读入的信息取出来，转换成相应的图形对象
			// 将取出来的图形对象添加到容器里面 调用中间画板，将取出来的图形进行绘制
			int value = JOptionPane.showConfirmDialog(null, "是否需要保存当前文件？", "提示信息", 0);
			if (value == 0) {
				saveFile();
			}
			if (value == 1) {
				// 在再次打开一个图片时，需要把上一次打开的图片数组先清零
				img = null;
				// 清空容器里面的东西
				String fileName = null;
				arrayD.clear();
				paint(g);
				try {
					JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
					chooser.setFileFilter(filter);
					int returnVal = chooser.showOpenDialog(null);
					if (returnVal == JFileChooser.APPROVE_OPTION) { // JFileChooser.APPROVE_OPTION 批准选项
						System.out.println("You chose to open this file: " + chooser.getSelectedFile().getPath());
						fileName = chooser.getSelectedFile().getPath(); // 获取文件的本地路径
					}
					Dimension imagesize = this.jp.getSize();
					BufferedImage bufferedImage = new BufferedImage(imagesize.width, imagesize.height,
							BufferedImage.TYPE_INT_RGB);
					Graphics buffg = bufferedImage.getGraphics();
					img = getImagePixel(fileName);
					// drawImage将图形画在缓冲区里
					drawImage(buffg, img);
					ImagePaint pa = new ImagePaint();
					pa.setBufferedImage(bufferedImage);
					// g.drawImage将缓冲区里的图形画在画图板上
					g.drawImage(bufferedImage, 0, 0, null);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			return;
		} else if (command.equals("保存")) {
			saveFile();
			return;
		} else if (command.equals("新建")) {

			int value = JOptionPane.showConfirmDialog(null, "是否需要保存当前文件？", "提示信息", 0);
			if (value == 0) {
				saveFile();
			}
			if (value == 1) {
				arrayD.clear();
				paint(g);
			}
			return;
		} else if (command.equals("撤销")) {// 撤销

			// 先检查画板上是否有图像
			if (arrayD.size() == 0) {
				JOptionPane.showMessageDialog(null, "没有进行画图,撤销操作失败");
				return;
			}

			int x = (int) arrayD.size();
			Paint pa = (Paint) arrayD.get(x - 1);// 获取数组的最后一个对象
			int t = x - 1;// 获取最后一个对象的下标
			String name = pa.getName();// 获取最后一个对象的操作是什么

			// 加上撤销的最后一个操作，然后重画
			switch (name) {
				case "铅笔":
					while (arrayD.get(t).getName().equals(name) && arrayD.size() != 0) {
						// 先把要删除的最后一个Paint对象存储到arrayR里，便于反撤销操作
						arrayR.add(arrayD.get(t));
						// 再删除最后一个元素
						arrayD.remove(t);
						if (t != 0) {
							--t;// 减一
						} else {
							break;// 数组里已经没有对象了，再减一就数组越界了
						}
					}
					System.out.println("arrayR的长度:" + arrayR.size());
					paint(g);
					break;
				case "刷子":
					while (arrayD.get(t).getName().equals(name) && arrayD.size() != 0) {
						// 先把要删除的最后一个Paint对象存储到arrayR里，便于反撤销操作
						arrayR.add(arrayD.get(t));
						// 再删除最后一个元素
						arrayD.remove(t);
						if (t != 0) {
							--t;// 减一
						} else {
							break;// 数组里已经没有对象了，再减一就数组越界了
						}
					}
					System.out.println("arrayR的长度:" + arrayR.size());
					paint(g);
					break;
				case "橡皮":
					while (arrayD.get(t).getName().equals(name) && arrayD.size() != 0) {
						// 先把要删除的最后一个Paint对象存储到arrayR里，便于反撤销操作
						arrayR.add(arrayD.get(t));
						// 再删除最后一个元素
						arrayD.remove(t);
						if (t != 0) {
							--t;// 减一
						} else {
							break;// 数组里已经没有对象了，再减一就数组越界了
						}
					}
					System.out.println("arrayR的长度:" + arrayR.size());
					paint(g);
					break;
				case "喷枪":
					while (arrayD.get(t).getName().equals(name) && arrayD.size() != 0) {
						// 先把要删除的最后一个Paint对象存储到arrayR里，便于反撤销操作
						arrayR.add(arrayD.get(t));
						// 再删除最后一个元素
						arrayD.remove(t);
						if (t != 0) {
							--t;// 减一
						} else {
							break;// 数组里已经没有对象了，再减一就数组越界了
						}
					}
					System.out.println("arrayR的长度:" + arrayR.size());
					paint(g);
					break;
				case "直线":
					// 先把要删除的最后一个Paint对象存储到arrayR里，便于反撤销操作
					arrayR.add(arrayD.get(t));
					// 再删除最后一个元素
					arrayD.remove(t);
					System.out.println("arrayR的长度:" + arrayR.size());
					paint(g);
					break;
				case "矩形":
					// 先把要删除的最后一个Paint对象存储到arrayR里，便于反撤销操作
					arrayR.add(arrayD.get(t));
					// 再删除最后一个元素
					arrayD.remove(t);
					System.out.println("arrayR的长度:" + arrayR.size());
					paint(g);
					break;
				case "圆":
					// 先把要删除的最后一个Paint对象存储到arrayR里，便于反撤销操作
					arrayR.add(arrayD.get(t));
					// 再删除最后一个元素
					arrayD.remove(t);
					System.out.println("arrayR的长度:" + arrayR.size());
					paint(g);
					break;
				case "文字":
					// 先把要删除的最后一个Paint对象存储到arrayR里，便于反撤销操作
					arrayR.add(arrayD.get(t));
					// 再删除最后一个元素
					arrayD.remove(t);
					System.out.println("arrayR的长度:" + arrayR.size());
					paint(g);
					break;
				default:
					break;
			}
			return;
		} else if (command.equals("反撤销")) {// 反撤销
			// 先检查arrayR里面是否有存储的被撤销的对象
			System.out.println("先检查arrayR的长度:" + arrayR.size());
			if (arrayR.size() == 0) {
				JOptionPane.showMessageDialog(null, "反撤销操作失败,没有可以进行反撤销的图像了");
				return;
			}

			int x = (int) arrayR.size();
			Paint pa = (Paint) arrayR.get(x - 1);// 获取数组的最后一个对象
			int t = x - 1;// 获取最后一个对象的下标
			String name = pa.getName();// 获取最后一个对象的操作是什么
			// 删除最后一个操作，然后重画
			switch (name) {
				case "铅笔":
					while (arrayR.get(t).getName().equals(name) && arrayR.size() != 0) {
						arrayD.add(arrayR.get(t));// 先把要删除的Paint对象存储到arrayD里
						arrayR.remove(t);// 再删除arrayR最后一个元素
						if (t != 0) {
							--t;// 减一
						} else {
							break;// 数组里已经没有对象了，再减一就数组越界了
						}
					}
					paint(g);
					break;
				case "刷子":
					while (arrayR.get(t).getName().equals(name) && arrayR.size() != 0) {
						arrayD.add(arrayR.get(t));// 先把要删除的Paint对象存储到arrayD里
						arrayR.remove(t);// 再删除arrayR最后一个元素
						if (t != 0) {
							--t;// 减一
						} else {
							break;// 数组里已经没有对象了，再减一就数组越界了
						}
					}
					paint(g);
					break;
				case "橡皮":
					while (arrayR.get(t).getName().equals(name) && arrayR.size() != 0) {
						arrayD.add(arrayR.get(t));// 先把要删除的Paint对象存储到arrayD里
						arrayR.remove(t);// 再删除arrayR最后一个元素
						if (t != 0) {
							--t;// 减一
						} else {
							break;// 数组里已经没有对象了，再减一就数组越界了
						}
					}
					paint(g);
					break;
				case "喷枪":
					while (arrayR.get(t).getName().equals(name) && arrayR.size() != 0) {
						arrayD.add(arrayR.get(t));// 先把要删除的Paint对象存储到arrayD里
						arrayR.remove(t);// 再删除arrayR最后一个元素
						if (t != 0) {
							--t;// 减一
						} else {
							break;// 数组里已经没有对象了，再减一就数组越界了
						}
					}
					paint(g);
					break;
				case "直线":
					arrayD.add(arrayR.get(t));// 先把要删除的Paint对象存储到arrayD里
					arrayR.remove(t);// 再删除arrayR最后一个元素
					paint(g);
					break;
				case "矩形":
					arrayD.add(arrayR.get(t));// 先把要删除的Paint对象存储到arrayD里
					arrayR.remove(t);// 再删除arrayR最后一个元素
					paint(g);
					break;
				case "圆":
					arrayD.add(arrayR.get(t));// 先把要删除的Paint对象存储到arrayD里
					arrayR.remove(t);// 再删除arrayR最后一个元素
					paint(g);
					break;
				case "文字":
					arrayD.add(arrayR.get(t));// 先把要删除的Paint对象存储到arrayD里
					arrayR.remove(t);// 再删除arrayR最后一个元素
					paint(g);
					break;
				default:
					break;
			}
			return;

		} else if (command.equals("画布清空")) {// 画布清空
			arrayD.clear();
			arrayR.clear();
			paint(g);
			return;
		} else if (command.equals("快捷键说明")) {// 快捷键说明
			try {
				File f = new File("./src/photo/快捷键说明.txt");
				String path = f.getAbsolutePath();
				Desktop.getDesktop().open(new File(path));
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
			return;
		} else if (command.equals("退出")) {
			System.exit(1);
		} else if (command.equals("帮助")) {
			try {
				File f = new File("./src/photo/帮助文档.txt");
				String path = f.getAbsolutePath();
				Desktop.getDesktop().open(new File(path));
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
			return;
		} else {
			name = e.getActionCommand();// 获取按钮信息
			return;
		}
	}

	// drawImage将图形画在缓冲区里
	public void drawImage(Graphics g, int[][] img) {

		for (int i = 0; i < img.length; i++) {
			for (int j = 0; j < img[i].length; j++) {
				Color c = new Color(img[i][j]);
				g.setColor(c);
				g.drawOval(i, j, 1, 1);
			}
		}
	}

	// getImagePixel 返回图片的二维数组
	public static int[][] getImagePixel(String filePath) {

		File file = new File(filePath); // filePath为文件路径
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int w = bi.getWidth();
		int h = bi.getHeight();
		int[][] imIndex = new int[w][h];
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				int pixel = bi.getRGB(i, j);
				imIndex[i][j] = pixel;
			}
		}
		return imIndex;
	}

	public void saveFile() {
		// 选择要保存的位置以及文件名字和信息
		JFileChooser chooser = new JFileChooser();
		chooser.showSaveDialog(null);
		File file = chooser.getSelectedFile();

		if (file == null) {
			JOptionPane.showMessageDialog(null, "没有选择文件");
		} else {

			try {
				Dimension imagesize = this.jp.getSize();
				BufferedImage image = new BufferedImage(imagesize.width, imagesize.height, BufferedImage.TYPE_INT_RGB);
				Graphics2D g2d = image.createGraphics();
				// 设置背景颜色
				g2d.setColor(backgroundColor);
				// 填充整张图片(其实就是设置背景颜色)
				g2d.fillRect(0, 0, imagesize.width, imagesize.height);
				// 画图
				paint(g2d);
				// 释放对象
				g2d.dispose();
				ImageIO.write(image, "jpg", file);
				JOptionPane.showMessageDialog(null, "保存成功！");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public void mouseDragged(MouseEvent e) {
		// System.out.println("拖动");
		x2 = e.getX();
		y2 = e.getY();
		switch (name) {
			case "铅笔":
				g.setStroke(new BasicStroke(pencilsize));// 设置线条的粗细
				g.drawLine(x1, y1, x2, y2);// 画曲线
				Paint paint = new Paint(x1, y1, x2, y2, color, name, pencilsize, null, fnt);// 根据图形的数据实例化Paint对象
				// 将数组对象存入到数组中
				arrayD.add(paint);
				x1 = x2;
				y1 = y2;
				break;
			case "刷子":
				g.setStroke(new BasicStroke(brushsize));// 设置线条的粗细
				g.drawLine(x1, y1, x2, y2);// 画曲线
				Paint pain = new Paint(x1, y1, x2, y2, color, name, brushsize, null, fnt);// 根据图形的数据实例化Paint对象
				// 将数组对象存入到数组中
				arrayD.add(pain);
				x1 = x2;
				y1 = y2;
				break;
			case "橡皮":
				Color color2 = jp.getBackground();
				g.setColor(color2);
				g.setStroke(new BasicStroke(50));
				g.drawLine(x1, y1, x2, y2);// 画曲线
				Paint pai = new Paint(x1, y1, x2, y2, color2, name, 50, null, fnt);// 根据图形的数据实例化Paint对象
				// 将数组对象存入到数组中
				arrayD.add(pai);
				x1 = x2;
				y1 = y2;
				g.setColor(color);
				break;
			case "喷枪":
				g.setStroke(new BasicStroke(1));
				Random rand = new Random();
				for (int i = 0; i < pengunsize; i++) {// pengunsize喷枪宽度
					int p = rand.nextInt(pengunsize);
					int q = rand.nextInt(pengunsize);
					g.drawLine(x2 + p, y2 + q, x2 + p, y2 + q);

					Paint pa = new Paint(x2 + p, y2 + q, x2 + p, y2 + q, color, name, 1, null, fnt);// 根据图形的数据实例化Paint对象
					// 将数组对象存入到数组中
					arrayD.add(pa);
				}
				x1 = x2;
				y1 = y2;
				break;
		}
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
		// System.out.println("点击");
	}

	public void mousePressed(MouseEvent e) {
		// System.out.println("按下");
		// 在按下和释放的事件处理方法中获取按下和释放的坐标值
		x1 = e.getX();
		y1 = e.getY();
		g.setColor(color);
	}

	public void mouseReleased(MouseEvent e) {
		// System.out.println("释放");
		x2 = e.getX();
		y2 = e.getY();
		// 根据按下和释放的坐标值，使用Graphics对象进行画图
		g.setStroke(new BasicStroke(1));// 设置线条的粗细
		switch (name) {
			case "直线":
				g.setStroke(new BasicStroke(straightlinesize));// 设置线条的粗细
				g.drawLine(x1, y1, x2, y2);
				Paint paint = new Paint(x1, y1, x2, y2, color, name, straightlinesize, null, fnt);// 根据图形的数据实例化Paint对象
				// 将数组对象存入到数组中
				arrayD.add(paint);
				break;

			case "矩形":
				g.setStroke(new BasicStroke(rectanglesize));// 设置线条的粗细
				g.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
				Paint k = new Paint(x1, y1, x2, y2, color, name, rectanglesize, null, fnt);// 根据图形的数据实例化Paint对象
				// 将数组对象存入到数组中
				arrayD.add(k);
				break;

			case "圆":
				g.setStroke(new BasicStroke(circlesize));// 设置线条的粗细
				g.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
				Paint f = new Paint(x1, y1, x2, y2, color, name, circlesize, null, fnt);// 根据图形的数据实例化Paint对象
				// 将数组对象存入到数组中
				arrayD.add(f);
				break;

			case "文字":
				UI.setTextFont();
				Font font = UI.getF();
				g.setFont(font);
				g.drawString(UI.getText(), x1, y1);
				Paint j = new Paint(x1, y1, x2, y2, color, name, 1, UI.getText(), font);// 根据图形的数据实例化Paint对象
				// 将数组对象存入到数组中
				arrayD.add(j);
				break;
		}
	}

	public void mouseEntered(MouseEvent e) {
		// System.out.println("进入");
	}

	public void mouseExited(MouseEvent e) {
		// System.out.println("离开");
	}

}
