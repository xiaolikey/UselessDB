package top.xiaolikey.udb.store;

import top.xiaolikey.udb.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * 虚拟的磁盘管理
 *
 * @author xiaolikey
 * @date 2024/3/10
 * @since 0.0.1
 */
public class MemoryPage<T extends DiskData> {
    /**
     * 这里页大小实际应该是4096字节，只是为了模型简化，这里直接用4096字段的字符
     */
    public static final int PAGE_LENGTH = 4096;
    public int pageNum;
    public int size = 0;
    public int length = 0;
    public List<T> data;

    public MemoryPage(int pageNum) {
        this.pageNum = pageNum;
        new ArrayList<>();
    }

    public boolean canAdd(T row) {
        return length + row.getLength() <= PAGE_LENGTH;
    }

    /**
     * 添加一行记录   返回记录的位置
     * @param row
     * @return
     */
    public int addData(T row) {
        int ans = data.size();
        length += row.getLength();
        data.add(row);
        return ans;
    }

    public T get(int index) {
        return data.get(index);
    }
}
