package lab3;

import lab3.cpu.CPU;
import lab3.disk.Disk;
import lab3.gpu.GPU;
import lab3.mainboard.Mainboard;
import lab3.memory.Memory;

public abstract class ComputerFactory {
    public abstract CPU setCPU(CPU cpu);
    public abstract Mainboard setMainboard(Mainboard mainboard);
    public abstract GPU setGPU(GPU gpu);
    public abstract Memory setMemory(Memory memory);
    public abstract Disk setDisk(Disk disk);
}
