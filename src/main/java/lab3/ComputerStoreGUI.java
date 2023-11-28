package lab3;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lab3.cpu.CPU;
import lab3.disk.Disk;
import lab3.gpu.GPU;
import lab3.mainboard.Mainboard;
import lab3.memory.Memory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class ComputerStoreGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("抽象反射工厂模式");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel instructionLabel = new JLabel("选择电脑配置:");

        //创建CPU下拉框并添加组件选项
        String[] cpuOptions = {"i3", "i5", "i7", "i9"};
        JComboBox<String> cpuComboBox = new JComboBox<>(cpuOptions);
        //创建GPU下拉框并添加组件选项
        String[] gpuOptions = {"3090Ti", "3080Ti", "3080", "3060", "2080Ti", "2080"};
        JComboBox<String> gpuComboBox = new JComboBox<>(gpuOptions);
        //创建主板下拉框并添加组件选项
        String[] mainboardOptions = {"ATX", "M_ATX", "MINI_ITX"};
        JComboBox<String> mainboardComboBox = new JComboBox<>(mainboardOptions);
        //创建内存下拉框并添加组件选项
        String[] memoryOptions = {"64G", "32G", "16G", "8G"};
        JComboBox<String> memoryComboBox = new JComboBox<>(memoryOptions);
        //创建硬盘下拉框并添加组件选项
        String[] diskOptions = {"1T", "2T", "4T", "512G"};
        JComboBox<String> diskComboBox = new JComboBox<>(diskOptions);
        //创建电脑
        JButton createComputerButton = new JButton("创建电脑");
        JTextArea resultTextArea = new JTextArea(10, 100);

        //事件监听器，监听"创建电脑"按钮的动作
        createComputerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //从下拉框中获取选择的组件
                String selectedCPU = (String) cpuComboBox.getSelectedItem();
                String selectedGPU = (String) gpuComboBox.getSelectedItem();
                String selectedMainboard = (String) mainboardComboBox.getSelectedItem();
                String selectedMemory = (String) memoryComboBox.getSelectedItem();
                String selectedDisk = (String) diskComboBox.getSelectedItem();

                // 构建JSON字符串
                String configuration = "{\"CPU\":\"" + selectedCPU +
                        "\",\"Mainboard\":\"" + selectedMainboard +
                        "\",\"GPU\":\"" + selectedGPU +
                        "\",\"Memory\":\"" + selectedMemory +
                        "\",\"Disk\":\"" + selectedDisk + "\"}";
                // 将JSON字符串解析为JSONObject
                JSONObject jsonObject = JSON.parseObject(configuration);
                // 将JSONObject转换为Map
                Map<String, Object> map = jsonObject.getInnerMap();

                String cpuName = "lab3.cpu.CPU_" + map.get("CPU");
                String gpuName = "lab3.gpu.GPU_" + map.get("GPU");
                String memoryName = "lab3.memory.Memory_" + map.get("Memory");
                String mainboardName = "lab3.mainboard.Mainboard_" + map.get("Mainboard");
                String diskName = "lab3.disk.Disk_" + map.get("Disk");
                //创建Class对象，用于后面反射获取配件实例
                Class<?> cpuClass = null;
                Class<?> gpuClass = null;
                Class<?> memoryClass = null;
                Class<?> mainboardClass = null;
                Class<?> diskClass = null;
                //通过反射创建配件实例传给电脑工厂，作为computer的配件
                Object cpuInstance = null;
                Object gpuInstance = null;
                Object memoryInstance = null;
                Object mainboardInstance = null;
                Object diskInstance = null;
                try {
                    //通过反射获取
                    cpuClass = Class.forName(cpuName);
                    gpuClass = Class.forName(gpuName);
                    memoryClass = Class.forName(memoryName);
                    mainboardClass = Class.forName(mainboardName);
                    diskClass = Class.forName(diskName);
                    // 创建配件实例
                    cpuInstance = cpuClass.getDeclaredConstructor().newInstance();
                    gpuInstance = gpuClass.getDeclaredConstructor().newInstance();
                    memoryInstance = memoryClass.getDeclaredConstructor().newInstance();
                    mainboardInstance = mainboardClass.getDeclaredConstructor().newInstance();
                    diskInstance = diskClass.getDeclaredConstructor().newInstance();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

                //创建电脑工厂
                ComputerFactory computerFactory = new ConcreteComputerFactory();
                Computer computer = new Computer(
                        computerFactory.setCPU((CPU) cpuInstance),
                        computerFactory.setMainboard((Mainboard) mainboardInstance),
                        computerFactory.setGPU((GPU) gpuInstance),
                        computerFactory.setMemory((Memory) memoryInstance),
                        computerFactory.setDisk((Disk) diskInstance)
                );

                int computerPrice = computer.calculatePrice();
                //输出到界面的配置信息
                String result = "电脑配置信息如下：\n" +
                        "配件" + "\t\t" + "型号" + "\t\t" + "描述" + "\t\t\t" + "价格" + "\n" +
                        "CPU" + "\t\t" + map.get("CPU") + "\t\t" + computer.getCpu().getDescription() + "\t\t\t" + computer.getCpu().getPrice() + "\n" +
                        "GPU" + "\t\t" + map.get("GPU") + "\t\t" + computer.getGpu().getDescription() + "\t\t\t" + computer.getGpu().getPrice() + "\n" +
                        "主板" + "\t\t" + map.get("Mainboard") + "\t\t" + computer.getMainboard().getDescription() +"\t\t\t" + computer.getMainboard().getPrice() + "\n" +
                        "内存" + "\t\t" + map.get("Memory") + "\t\t" + computer.getMemory().getDescription() + "\t\t\t" + computer.getMemory().getPrice() + "\n" +
                        "硬盘" + "\t\t" + map.get("Disk") + "\t\t" + computer.getDisk().getDescription() + "\t\t\t"+ computer.getDisk().getPrice() + "\n" +
                        "\n该电脑价格为：" + computerPrice;
                resultTextArea.setText(result);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(instructionLabel);

        // 将下拉框添加到面板
        panel.add(new JLabel("CPU:"));
        panel.add(cpuComboBox);
        panel.add(new JLabel("GPU:"));
        panel.add(gpuComboBox);
        panel.add(new JLabel("主板:"));
        panel.add(mainboardComboBox);
        panel.add(new JLabel("内存:"));
        panel.add(memoryComboBox);
        panel.add(new JLabel("硬盘:"));
        panel.add(diskComboBox);

        //添加到界面
        panel.add(createComputerButton);
        panel.add(resultTextArea);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(1400, 400);
        frame.setVisible(true);
    }
}
