package homework;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class SelectedType implements ItemListener {
    public SelectedType (){}
//    public void itemStateChanged(ItemEvent e) {
//        Choice ch = (Choice) e.getSource();
//        String mid = ch.getSelectedItem();
//        UI.setSelectedType(mid);
//        UI.updateListOfType();
//        System.out.println("分类查看设置为：" + UI.getSelectedType());
//    }
    public void itemStateChanged(ItemEvent event)
    {
        switch (event.getStateChange())
        {
            case ItemEvent.SELECTED:
                UI.setSelectedType(event.getItem().toString());
                UI.updateListOfType();
                System.out.println("选中" + event.getItem());
                break;
            case ItemEvent.DESELECTED:
//                System.out.println("取消选中"+event.getItem());
                break;
        }
    }
}
