package top.xiaolikey.udb;

import top.xiaolikey.udb.store.DiskData;

/**
 * 行记录
 *
 * @author xiaolikey
 * @date 2024/3/11
 * @since 0.0.1
 */
public class Row implements DiskData {
    public long id;
    public String key;
    public String value;
    public Row(long id, String key, String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }
    @Override
    public String toString() {
        return id+","+key+","+value;
    }

    @Override
    public int getLength(){
        return 8 + key.length() + value.length();
    }


}
