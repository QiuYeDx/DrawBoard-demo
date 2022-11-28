package homework;

import java.awt.Choice;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class LineSize implements ItemListener {

    public LineSize() {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub
        Choice ch = (Choice) e.getSource();
        String mid = ch.getSelectedItem();
        if (mid.equals("一号粗细")) {
            DrawListener.setStraightLineSize(1);
            DrawListener.setCircleSize(1);
            DrawListener.setPencilSize(1);
            DrawListener.setRectangleSize(1);
            DrawListener.setBrushSize(10);
            DrawListener.setPengunSize(10);
            return;
        } else if (mid.equals("二号粗细")) {
            DrawListener.setStraightLineSize(3);
            DrawListener.setCircleSize(2);
            DrawListener.setPencilSize(3);
            DrawListener.setRectangleSize(3);
            DrawListener.setBrushSize(15);
            DrawListener.setPengunSize(20);
            return;
        }else if (mid.equals("三号粗细")) {
            DrawListener.setStraightLineSize(6);
            DrawListener.setCircleSize(4);
            DrawListener.setPencilSize(5);
            DrawListener.setRectangleSize(5);
            DrawListener.setBrushSize(20);
            DrawListener.setPengunSize(30);
            return;
        }else if (mid.equals("四号粗细")) {
            DrawListener.setStraightLineSize(9);
            DrawListener.setCircleSize(8);
            DrawListener.setPencilSize(7);
            DrawListener.setRectangleSize(7);
            DrawListener.setBrushSize(25);
            DrawListener.setPengunSize(40);
            return;
        }
    }

}
