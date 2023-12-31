package lab3.lab3_1;

import lab3.lab3_1.cpu.CPU;
import lab3.lab3_1.disk.Disk;
import lab3.lab3_1.gpu.GPU;
import lab3.lab3_1.mainboard.Mainboard;
import lab3.lab3_1.memory.Memory;
import lombok.Data;

@Data
public class Computer {
    private CPU cpu;
    private Mainboard mainboard;
    private GPU gpu;
    private Memory memory;
    private Disk disk;

    public Computer(CPU cpu, Mainboard mainboard, GPU gpu, Memory memory, Disk disk) {
        this.cpu = cpu;
        this.mainboard = mainboard;
        this.gpu = gpu;
        this.memory = memory;
        this.disk = disk;
    }

    //电脑价格
    public int calculatePrice() {
        return cpu.getPrice() + mainboard.getPrice() + gpu.getPrice() + memory.getPrice() + disk.getPrice();
    }
}
