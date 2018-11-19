package com.liyafeng.video;

public class CPU {

    /**
     * ===========指令集 ISA====================
     * 指令集架构（英语：Instruction Set Architecture，缩写为ISA），
     * 指令集 = 指令集体系  = 指令集架构 = 指令集体系架构 = 微处理器指令集架构
     * CPU架构就是指令集架构
     *
     * 是给写汇编软件（编译器）的同事看的，让他们知道如何输出正确功能的机器码
     *
     * ISA规则中包括（定义了）执行模型，处理器寄存器，地址和数据格式
     *
     * ---------指令集的种类-------------------
     * CISC指令集，复杂指令集 英文名是CISC，Complex Instruction Set Computer的缩写
     * RISC指令集 精简指令集 Reduced Instruction Set Computing  的缩写
     *
     * -------------------------
     * 复杂指令集的特点是指令数目多而复杂，每条指令字长并不相等，
     * 计算机必须加以判读，并为此付出了性能的代价。
     * -----------生产cpu的公司------------------
     * intel
     * amd
     * --------------架构与指令集-------------
     * 不同的架构可以实现同一种指令集
     * x86架构就代表实现了x86指令集的cpu
     *
     * -------------------------
     * intel 发明了x86指令集和 x86 架构的处理器
     * amd 拿到了可以使用x86指令集的授权，生产了自己的x86架构的cpu
     * （当然内部实现原理intel是不会给你的，要不两家都可以生产一模一样的cpu了，
     * 所以amd有自己的物理实现）
     *
     * 后来intel搞了 ia64=x64=intel architecture 64 指令集和cpu
     * 但是和x86指令集不兼容，就是用x86指令集不能运行在新的ia64架构的cpu上
     * 所以放弃了
     * 后来amd搞了兼容 x86指令集的 新指令集，叫x86-64=amd64 和cpu
     * 这个很好，所以intel后来也被授权用了这种指令集
     *
     * 所以我们基于x86指令集写的汇编编译的机器码，也能在x86-64的cpu上运行
     *
     * x86目前泛指x86和x86-64架构（因为x86-64架构的cpu完全兼容x86指令集）
     *
     * x86（x86或x86-64架构）的主要产品有Intel的至强，酷睿，奔腾，赛扬和凌动
     *
     * x64指令集和x64架构已经被放弃，安腾处理器用的这个架构
     *
     * -----------------------
     * arm公司
     * arm是risc的典型代表，但是外加了一部分cisc
     * 便宜低功耗
     * ARM架构的cpu的比X86架构的cpu耗电少
     * ------------------
     * mips公司
     * 也是risc的代表，授权门槛极低，所以我们拿到他们公司的指令集授权就可以
     * 生产自己的CPU了， mips架构cpu主要用在嵌入式领域，比如路由器。
     * 中国的龙芯是mips架构
     *
     *  纯计算能力很强
     * ------------------------------
     *
     *
     * x86指令集是一种CISC指令集
     *
     * x86架构是代表 x86指令集 的一种物理实现方式
     * 于1978年推出的Intel 8086（型号名称）中央处理器中首度出现
     *
     *
     *
     *
     *
     * ===========微架构（英语：microarchitecture）===============
     * 微架构 = 微体系架构 = CPU电路结构 = CPU架构，是一种指令集的实现方式
     * 比如指令集说110000010001 代表 add 1 1
     * 那么微架构就要当输入这个机器码的时候实现1加1的功能并输出
     *
     *
     *
     * ===============汇编语言====================
     * 就是我们能看懂的低级语言，一种汇编语言对应一种架构的
     *
     * ===========应用二进制接口（英语：application binary interface，缩写为 ABI）=========
     * 其实就是指的某种特定的指令集，比如 x86指令集 ，armabi-v7a指令集、mips指令集
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}