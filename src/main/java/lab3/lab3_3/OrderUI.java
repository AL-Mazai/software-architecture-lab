package lab3.lab3_3;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class OrderUI extends JFrame {
    private Map<String, Double> noodlePrices;
    private Map<String, Integer> ingredientPrices;

    private JComboBox<String> noodleMenu;
    private Map<String, JSpinner> ingredientSpinners;
    private JTextArea orderSummary;

    // 初始化
    public OrderUI() {
        setTitle("装饰器模式");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GanJiangRiceNoodle ganJiangRiceNoodle = new GanJiangRiceNoodle();
        ShuiRiceNoodle shuiRiceNoodle = new ShuiRiceNoodle();
        SuanJiangRiceNoodle suanJiangRiceNoodle = new SuanJiangRiceNoodle();

        // 初始化米线价格
        noodlePrices = new HashMap<>();
        noodlePrices.put(ganJiangRiceNoodle.description(), ganJiangRiceNoodle.cost());
        noodlePrices.put(suanJiangRiceNoodle.description(), suanJiangRiceNoodle.cost());
        noodlePrices.put(shuiRiceNoodle.description(), shuiRiceNoodle.cost());

        // 初始化配料价格
        ingredientPrices = new HashMap<>();
        ingredientPrices.put("豆腐", 1);
        ingredientPrices.put("鸡蛋", 2);
        ingredientPrices.put("牛肉", 3);

        createPriceTable();  // 创建价格窗口
        createOrderWidgets();  // 创建选项窗口
    }

    // 创建价格窗口
    private void createPriceTable() {
        JPanel pricePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);

        // 价目表字样
        gbc.gridx = 0;
        gbc.gridy = 0;
        pricePanel.add(new JLabel("价目表"), gbc);

        // 米线价格
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        pricePanel.add(new JLabel("米线"), gbc);

        int row = 2;
        for (Map.Entry<String, Double> entry : noodlePrices.entrySet()) {
            gbc.gridx = 0;
            gbc.gridy = row++;
            pricePanel.add(new JLabel(entry.getKey() + "：￥" + entry.getValue()), gbc);
        }

        // 配料价格
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        pricePanel.add(new JLabel("配料"), gbc);

        row = 2;
        for (Map.Entry<String, Integer> entry : ingredientPrices.entrySet()) {
            gbc.gridx = 2;
            gbc.gridy = row++;
            pricePanel.add(new JLabel(entry.getKey() + "：￥" + entry.getValue()), gbc);
        }

        add(pricePanel, BorderLayout.NORTH);
    }

    // 创建选项窗口
    private void createOrderWidgets() {
        JPanel orderPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);

        // 选择米线类型
        gbc.gridx = 0;
        gbc.gridy = 0;
        orderPanel.add(new JLabel("选择米线类型:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        noodleMenu = new JComboBox<>(noodlePrices.keySet().toArray(new String[0]));
        orderPanel.add(noodleMenu, gbc);

        // 配料和配料数量
        gbc.gridx = 0;
        gbc.gridy = 1;

        ingredientSpinners = new HashMap<>();  // 初始化 ingredientSpinners
        for (Map.Entry<String, Integer> entry : ingredientPrices.entrySet()) {
            orderPanel.add(new JLabel(entry.getKey()), gbc);

            gbc.gridx = 1;
            JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
            ingredientSpinners.put(entry.getKey(), spinner);  // 将 spinner 加入 ingredientSpinners
            orderPanel.add(spinner, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
        }

        // 确认按钮
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton confirmButton = new JButton("确认");
        confirmButton.addActionListener(e -> confirmOrder());
        orderPanel.add(confirmButton, gbc);

        // 输出框
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.BOTH;
        orderSummary = new JTextArea();
        orderSummary.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(orderSummary);
        scrollPane.setPreferredSize(new Dimension(400, 50)); // 设置首选大小
        orderPanel.add(scrollPane, gbc);

        add(orderPanel, BorderLayout.CENTER);
    }

    // 提交订单
    private void confirmOrder() {
        double selectedNoodlePrice = noodlePrices.getOrDefault((String) noodleMenu.getSelectedItem(), 0.0);
        double ingredientTotal = 0;

        StringBuilder ingredientDetails = new StringBuilder();  // 使用 StringBuilder 拼接配料详情

        for (Map.Entry<String, Integer> entry : ingredientPrices.entrySet()) {
            String ingredient = entry.getKey();
            int quantity = (int) ingredientSpinners.get(ingredient).getValue();

            // 只有数量大于0的配料才添加到配料详情中
            if (quantity > 0) {
                ingredientTotal += quantity * entry.getValue();
                ingredientDetails.append("+")
                        .append(ingredient)
                        .append(" x ")
                        .append(quantity);
            }
        }

        double totalCost = selectedNoodlePrice + ingredientTotal;
        String orderDescription = "您选择了：" + noodleMenu.getSelectedItem();
        // 如果有选中的配料，将配料详情添加到订单描述中
        if (ingredientDetails.length() > 0) {
            orderDescription += ingredientDetails;
        }
        orderDescription += "\n合计：￥" + totalCost;

        orderSummary.setText(orderDescription);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new OrderUI().setVisible(true);
            }
        });
    }
}
