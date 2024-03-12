package top.xiaolikey.udb.store;

import com.sun.jmx.remote.internal.ArrayQueue;
import sun.jvm.hotspot.debugger.Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * TODO
 *
 * @author xiaolikey
 * @date 2024/3/10
 * @since 0.0.1
 */
public class FixedSizePageBufferManger {
    int fixedSize = 10;

    ArrayBlockingQueue<MemoryPage> buffer;

    public FixedSizePageBufferManger() {
        buffer = new ArrayBlockingQueue<>(fixedSize);
    }

    public void addPage(MemoryPage page) {
        if(buffer.size() >= fixedSize) {
            buffer.poll();
        }
        buffer.offer(page);
    }

    public MemoryPage get(int pageNum) {
        for (MemoryPage memoryPage : buffer) {
            if(memoryPage.pageNum == pageNum) {
                return memoryPage;
            }
        }
        return null;
    }
}
