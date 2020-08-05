package GC;
//https://www.infoq.cn/article/3WyReTKqrHIvtw4frmr3
//例子来源这个文章
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class OOMTest {
    public static void main(String[] args) {
        OOMTest test = new OOMTest();
//      heap 区 OOM 测试
//      D:\Demo\src> 目录下执行 java -Xmx10M -XX:+UseG1GC -XX:+HeapDumpBeforeFullGC  GC.OOMTest 注意文件不要带任何后缀
        test.heapOOM();

        // 虚拟机栈和本地方法栈溢出
        //test.stackOverflow();

        //metaspace OOM 测试
        //test.metaspaceOOM();

        // 堆外内存 OOM 测试
        //test.directOOM();
    }
    /**
     * heap OOM 测试
     */
    public void heapOOM() {
        List<OOMTest> list = new ArrayList<>();
        while (true) {
            list.add(new OOMTest());
        }
    }

    /**
     * VM Stack / Native method Stack 溢出测试
     */
    public void stackOverflow() {
        OOMTest test = new OOMTest();
        try {
            test.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + test.stackLength);
            throw e;
        }
    }
    private int stackLength = 1;

    public void stackLeak() {
        stackLength += 1;
        stackLeak();
    }

    /**
     * metaspace/ 常量池 OOM 测试
     */
    public void metaspaceOOM() {
        OOMTest test = new OOMTest();
        test.metaspaceOOM();
    }

    /**
     * 堆外内存 OOM 测试
     */
    public void directOOM() {
        OOMTest test = new OOMTest();
        test.allocDirectMemory();
    }
    public void allocDirectMemory() {
        final int _1MB = 1024 * 1024;

        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = null;
        try {
            unsafe = (Unsafe) unsafeField.get(null);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
