package homework;

import java.awt.Choice;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FontSize implements ItemListener {
	public FontSize() {

	}
	// 实现ItemListener的方法，只有一个方法
	public void itemStateChanged(ItemEvent e) {
		Choice ch = (Choice) e.getSource();
		String mid = ch.getSelectedItem();
		if (mid.equals("1")) {
			int a = Integer.parseInt(mid);
			UI.setTextInt(a);
			System.out.println("字体大小设置为：" + UI.getTextInt() + "号");
			return;
		} else if (mid.equals("2")) {
			int a = Integer.parseInt(mid);
			UI.setTextInt(a);
			System.out.println("字体大小设置为：" + UI.getTextInt() + "号");
			return;
		} else if (mid.equals("3")) {
			int a = Integer.parseInt(mid);
			UI.setTextInt(a);
			System.out.println("字体大小设置为：" + UI.getTextInt() + "号");
			return;
		}else if (mid.equals("4")) {
			int a = Integer.parseInt(mid);
			UI.setTextInt(a);
			System.out.println("字体大小设置为：" + UI.getTextInt() + "号");
			return;
		}else if (mid.equals("5")) {
			int a = Integer.parseInt(mid);
			UI.setTextInt(a);
			System.out.println("字体大小设置为：" + UI.getTextInt() + "号");
			return;
		}else if (mid.equals("6")) {
			int a = Integer.parseInt(mid);
			UI.setTextInt(a);
			System.out.println("字体大小设置为：" + UI.getTextInt() + "号");
			return;
		}else if (mid.equals("7")) {
			int a = Integer.parseInt(mid);
			UI.setTextInt(a);
			System.out.println("字体大小设置为：" + UI.getTextInt() + "号");
			return;
		}else if (mid.equals("8")) {
			int a = Integer.parseInt(mid);
			UI.setTextInt(a);
			System.out.println("字体大小设置为：" + UI.getTextInt() + "号");
			return;
		}else if (mid.equals("9")) {
			int a = Integer.parseInt(mid);
			UI.setTextInt(a);
			System.out.println("字体大小设置为：" + UI.getTextInt() + "号");
			return;
		}else if (mid.equals("10")) {
			int a = Integer.parseInt(mid);
			UI.setTextInt(a);
			System.out.println("字体大小设置为：" + UI.getTextInt() + "号");
			return;
		}else if (mid.equals("11")) {
			int a = Integer.parseInt(mid);
			UI.setTextInt(a);
			System.out.println("字体大小设置为：" + UI.getTextInt() + "号");
			return;
		}else if (mid.equals("12")) {
			int a = Integer.parseInt(mid);
			UI.setTextInt(a);
			System.out.println("字体大小设置为：" + UI.getTextInt() + "号");
			return;
		}else if (mid.equals("14")) {
			int a = Integer.parseInt(mid);
			UI.setTextInt(a);
			System.out.println("字体大小设置为：" + UI.getTextInt() + "号");
			return;
		}else if (mid.equals("16")) {
			int a = Integer.parseInt(mid);
			UI.setTextInt(a);
			System.out.println("字体大小设置为：" + UI.getTextInt() + "号");
			return;
		}else if (mid.equals("18")) {
			int a = Integer.parseInt(mid);
			UI.setTextInt(a);
			System.out.println("字体大小设置为：" + UI.getTextInt() + "号");
			return;
		}
		else if (mid.equals("20")) {
			int a = Integer.parseInt(mid);
			UI.setTextInt(a);
			System.out.println("字体大小设置为：" + UI.getTextInt() + "号");
			return;
		}else if (mid.equals("22")) {
			int a = Integer.parseInt(mid);
			UI.setTextInt(a);
			System.out.println("字体大小设置为：" + UI.getTextInt() + "号");
			return;
		}else if (mid.equals("26")) {
			int a = Integer.parseInt(mid);
			UI.setTextInt(a);
			System.out.println("字体大小设置为：" + UI.getTextInt() + "号");
			return;
		}
	}
}
