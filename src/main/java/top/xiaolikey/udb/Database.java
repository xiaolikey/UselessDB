package top.xiaolikey.udb;

import top.xiaolikey.udb.store.MemoryStore;
import top.xiaolikey.udb.store.Store;

/**
 * 数据库
 *
 * @author xiaolikey
 * @date 2024/3/10
 * @since 0.0.1
 */
public class Database {

    private long size = 0;
    private Store store;


    public Database() {
        store = new MemoryStore();
    }


    public long nextId() {
        return size + 1;
    }

    public long executeInsert(Row row) {
        //预分配
        row.id = nextId();
        if (store.saveOrUpdate(row) == 1) {
            ++size;
        }
        return row.id;
    }

    public void executeTruncate() {
        store.truncate();
        size = 0;
    }

    public Row executeQuery(Operator operator) {
        switch (operator.operatorType) {
            case SELECT_BY_ID:
                return store.getById(operator.row.id);
            case SELECT_BY_KEY:
                return store.getByKey(operator.row.key);
            case SELECT_VALUE_CONTAINS_WORD:
                return store.getValueContainsWord(operator.row.value);
            default:
                return null;
        }
    }
}
