package lab1;

import java.awt.event.*;
import javax.swing.*;

public class MyGUI extends JFrame {
    private JButton buttonA, buttonB, buttonC; // 定义三个按钮对象
    private ButtonController controller = new ButtonController();

    public MyGUI() {
        setTitle("Button Controller"); // 设置窗口标题
        setSize(300, 200); // 设置窗口大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗口关闭操作

        buttonA = new JButton("A"); // 创建按钮A
        buttonB = new JButton("B"); // 创建按钮B
        buttonC = new JButton("C"); // 创建按钮C

        // 给按钮A添加点击事件监听器
        buttonA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.executeA();
            }
        });
        // 给按钮B添加点击事件监听器
        buttonB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.executeB();
            }
        });
        // 给按钮C添加点击事件监听器
        buttonC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.executeC();
            }
        });

        setLayout(null); // 设置布局为空
        // 设置三个按钮的位置和大小
        buttonA.setBounds(60, 50, 60, 30);
        buttonB.setBounds(130, 50, 60, 30);
        buttonC.setBounds(200, 50, 60, 30);
        add(buttonA); // 添加按钮A到窗口
        add(buttonB); // 添加按钮B到窗口
        add(buttonC); // 添加按钮C到窗口
    }

    public static void main(String[] args) {
        MyGUI myGUI = new MyGUI(); // 创建窗口对象
        myGUI.setVisible(true); // 显示窗口
    }
}
