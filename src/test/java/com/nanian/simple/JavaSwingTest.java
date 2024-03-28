package com.nanian.simple;

import javax.swing.*;
import javax.swing.plaf.LayerUI;
import javax.swing.text.JTextComponent;
import java.awt.*;

/**
 * @Author Caoheng
 * @Date 2024/3/21 16:26
 * @Description
 */
public class JavaSwingTest extends JFrame {

     private JTextField textField;

    public JavaSwingTest() {
        setTitle("带有提示语的文本框");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);

        // 创建文本框
        textField = new JTextField(20);

        // 使用JLayer添加提示文本
        HintLayerUI hintLayerUI = new HintLayerUI("在这里输入内容");
        JLayer<JTextField> layer = new JLayer<>(textField, hintLayerUI);

        // 添加文本框到面板
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(layer, BorderLayout.CENTER);

        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JavaSwingTest());
    }

    // 定义JLayer的UI类来显示提示文本
    private static class HintLayerUI extends LayerUI<JTextField> {
        private String hint;

        public HintLayerUI(String hint) {
            this.hint = hint;
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            super.paint(g, c);
            if (((JTextComponent) c).getText().isEmpty() && !((JTextComponent) c).hasFocus()) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.GRAY);
                g2.setFont(c.getFont().deriveFont(Font.ITALIC));
                int padding = (c.getHeight() - c.getFontMetrics(c.getFont()).getHeight()) / 2;
                g2.drawString(hint, 5, c.getHeight() - padding - 2);
                g2.dispose();
            }
        }
    }
}
