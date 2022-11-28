package homework;

import java.awt.Choice;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FontwordType implements ItemListener{
	public FontwordType() {

	}
	// 实现ItemListener的方法，只有一个方法
	public void itemStateChanged(ItemEvent e) {
		Choice ch = (Choice) e.getSource();
		String mid = ch.getSelectedItem();
		if (mid.equals("宋体")) {
			UI.setTextFont(mid);
			System.out.println("字体格式设置为：" + UI.getTextFont());
			return;
		}else if(mid.equals("华文中宋")) {
			UI.setTextFont(mid);
			System.out.println("字体格式设置为：" + UI.getTextFont());
			return;
		}else if(mid.equals("仿宋")) {
			UI.setTextFont(mid);
			System.out.println("字体格式设置为：" + UI.getTextFont());
			return;
		}else if(mid.equals("华文仿宋")) {
			UI.setTextFont(mid);
			System.out.println("字体格式设置为：" + UI.getTextFont());
			return;
		}else if(mid.equals("华文宋体")) {
			UI.setTextFont(mid);
			System.out.println("字体格式设置为：" + UI.getTextFont());
			return;
		}else if(mid.equals("华文彩云")) {
			UI.setTextFont(mid);
			System.out.println("字体格式设置为：" + UI.getTextFont());
			return;
		}else if(mid.equals("华文新魏")) {
			UI.setTextFont(mid);
			System.out.println("字体格式设置为：" + UI.getTextFont());
			return;
		}else if(mid.equals("华文楷体")) {
			UI.setTextFont(mid);
			System.out.println("字体格式设置为：" + UI.getTextFont());
			return;
		}else if(mid.equals("华文琥珀")) {
			UI.setTextFont(mid);
			System.out.println("字体格式设置为：" + UI.getTextFont());
			return;
		}else if(mid.equals("华文细黑")) {
			UI.setTextFont(mid);
			System.out.println("字体格式设置为：" + UI.getTextFont());
			return;
		}else if(mid.equals("华文行楷")) {
			UI.setTextFont(mid);
			System.out.println("字体格式设置为：" + UI.getTextFont());
			return;
		}else if(mid.equals("华文隶书")) {
			UI.setTextFont(mid);
			System.out.println("字体格式设置为：" + UI.getTextFont());
			return;
		}else if(mid.equals("幼圆")) {
			UI.setTextFont(mid);
			System.out.println("字体格式设置为：" + UI.getTextFont());
			return;
		}else if(mid.equals("微软雅黑")) {
			UI.setTextFont(mid);
			System.out.println("字体格式设置为：" + UI.getTextFont());
			return;
		}else if(mid.equals("新宋体")) {
			UI.setTextFont(mid);
			System.out.println("字体格式设置为：" + UI.getTextFont());
			return;
		}else if(mid.equals("黑体")) {
			UI.setTextFont(mid);
			System.out.println("字体格式设置为：" + UI.getTextFont());
			return;
		}else if(mid.equals("楷体")) {
			UI.setTextFont(mid);
			System.out.println("字体格式设置为：" + UI.getTextFont());
			return;
		}else if(mid.equals("方正姚体")) {
			UI.setTextFont(mid);
			System.out.println("字体格式设置为：" + UI.getTextFont());
			return;
		}else if(mid.equals("方正舒体")) {
			UI.setTextFont(mid);
			System.out.println("字体格式设置为：" + UI.getTextFont());
			return;
		}else if(mid.equals("方正粗黑宋简体")) {
			UI.setTextFont(mid);
			System.out.println("字体格式设置为：" + UI.getTextFont());
			return;
		}
	}
}
