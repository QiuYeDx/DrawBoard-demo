package homework;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;

public class Paint implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int num = 0;

    private int x1, y1, x2, y2;
    private Color color;
    private String name;
    private String nameStr;
    private int width = 1;// 线条粗细默认为1
    private String text = null;// 用来保存文字内容
    private Font fnt = new Font(null);// 用来保存文字的格式信息

    public Paint() {

    }

    public Paint(int x1, int y1, int x2, int y2, Color color, String name, int width, String text, Font fnt) {
        super();
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.name = name;
        if(name == "直线" || name == "矩形" || name == "圆" || name == "文字"){
            this.nameStr = "图形" + String.valueOf(num);
            num++;
        }else{
            this.nameStr = "noShape";
        }
        this.width = width;
        this.text = text;
        this.fnt = fnt;
        System.out.println(this.nameStr);   // for test
    }

    public Paint(int x1, int y1, int x2, int y2, Color color, String name, String nameStr, int width, String text, Font fnt) {
        super();
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.name = name;
        this.nameStr = nameStr;
        this.width = width;
        this.text = text;
        this.fnt = fnt;
    }

    // 可以把方法写在这里，在Draw类和DrawListener类中调用
    // 获取和设置X1
    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    // 获取和设置Y1
    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    // 获取和设置X2
    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    // 获取和设置Y2
    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    // 获取和设置color
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    // 获取和设置操作名称name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //获取和设置图形名称
    public String getNameStr() {
        return nameStr;
    }

    public void setNameStr(String nameStr) {
        this.nameStr = nameStr;
    }

    // 获取和设置字的大小
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    // 获取和设置写在画板上的文字内容
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    // 获取和设置写在画板上的文字格式
    public Font getTextFont() {
        return fnt;
    }

    public void setTextFont(Font font) {
        this.fnt = font;
    }
}
