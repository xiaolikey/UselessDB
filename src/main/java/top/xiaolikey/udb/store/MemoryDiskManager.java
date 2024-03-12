package top.xiaolikey.udb.store;

import java.util.ArrayList;
import java.util.List;

/**
 * 虚拟的磁盘管理
 *
 * @author xiaolikey
 * @date 2024/3/10
 * @since 0.0.1
 */
@SuppressWarnings("ALL")
public class MemoryDiskManager {

    int totalRead;
    int totalWirte;

    List<MemoryPage> pages;

    FixedSizePageBufferManger bufferManager;


    public MemoryDiskManager() {
        this.bufferManager = new FixedSizePageBufferManger();
        pages = new ArrayList<>();
    }

    public int getPageSize() {
        return pages.size();
    }

    public MemoryPage read(int pageNum) {
        MemoryPage page = bufferManager.get(pageNum);
        if(page == null) {
            page = _read(pageNum);
        }
        return page;
    }


    public MemoryPage write(int pageNum, DiskData data) {
        if(pageNum < 0){
            _write(data);
        }
        MemoryPage page = bufferManager.get(pageNum);
        if(page == null) {
            page = _read(pageNum);
        }
        totalWirte++;
        return page;
    }

    public MemoryPage _write(DiskData data) {
        if(pages.size() == 0) {
            pages.add(new MemoryPage(0));
        }
        MemoryPage page = pages.get(pages.size() - 1);
        if(!page.canAdd(data)) {
            page = new MemoryPage(pages.size());
            pages.add(page);
        }
        page.addData(data);
        bufferManager.addPage(page);
        return page;
    }

    public MemoryPage _update(MemoryPage page, DiskData data) {
        page.addData(data);
        bufferManager.addPage(page);
        return page;
    }

    private MemoryPage _read(int pageNum) {
        totalRead++;
        return pages.get(pageNum);
    }

    public int getTotalRead() {
        return totalRead;
    }

    public int getTotalWirte() {
        return totalWirte;
    }

    public void restart() {
        totalRead = 0;
        totalWirte = 0;
    }

}
