package lab3;

import lab3.cpu.CPU;
import lab3.disk.Disk;
import lab3.gpu.GPU;
import lab3.mainboard.Mainboard;
import lab3.memory.Memory;

public class ConcreteComputerFactory extends ComputerFactory{
    @Override
    public CPU setCPU(CPU cpu) {return cpu;}
    @Override
    public Mainboard setMainboard(Mainboard mainboard) {return mainboard;}
    @Override
    public GPU setGPU(GPU gpu) {return gpu;}
    @Override
    public Memory setMemory(Memory memory) {return memory;}
    @Override
    public Disk setDisk(Disk disk) {return disk;}
}
