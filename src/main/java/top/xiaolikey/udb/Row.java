package top.xiaolikey.udb;

/**
 * 行记录
 *
 * @author xiaolikey
 * @date 2024/3/11
 * @since 0.0.1
 */
public class Row {
    long id;
    String key;
    String value;
    public Row(long id, String key, String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }
    @Override
    public String toString() {
        return id+","+key+","+value;
    }
}
