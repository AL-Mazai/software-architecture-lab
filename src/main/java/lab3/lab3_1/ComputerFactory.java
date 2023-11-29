package lab3.lab3_1;

import lab3.lab3_1.cpu.CPU;
import lab3.lab3_1.disk.Disk;
import lab3.lab3_1.gpu.GPU;
import lab3.lab3_1.mainboard.Mainboard;
import lab3.lab3_1.memory.Memory;

public abstract class ComputerFactory {
    public abstract CPU setCPU(CPU cpu);
    public abstract Mainboard setMainboard(Mainboard mainboard);
    public abstract GPU setGPU(GPU gpu);
    public abstract Memory setMemory(Memory memory);
    public abstract Disk setDisk(Disk disk);
}
