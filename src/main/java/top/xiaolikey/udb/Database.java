package top.xiaolikey.udb;

/**
 * 数据库
 *
 * @author xiaolikey
 * @date 2024/3/11
 * @since 0.0.1
 */
public class Database {

    private volatile long SIZE = 0;

    public long nextId() {
        return SIZE + 1;
    }

    public long insertSuccess(){
        return ++SIZE;
    }
}
