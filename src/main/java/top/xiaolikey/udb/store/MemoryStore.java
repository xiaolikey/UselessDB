package top.xiaolikey.udb.store;

import top.xiaolikey.udb.Row;

import java.util.LinkedHashMap;

/**
 * 存储
 *
 * @author xiaolikey
 * @date 2024/3/10
 * @since 0.0.1
 */
public class MemoryStore implements Store {
    LinkedHashMap<String, Row> data = new LinkedHashMap<>();
    LinkedHashMap<Long, Row> idIndex = new LinkedHashMap<>();

    @Override
    public int saveOrUpdate(Row row) {
        int ans = 1;
        if (data.containsKey(row.key)) {
            Row oldValue = data.get(row.key);
            row.id = oldValue.id;
            oldValue.value = row.value;
            ans = 2;
        } else {
            data.put(row.key, row);
            idIndex.put(row.id, row);
        }
        return ans;
    }

    @Override
    public void truncate() {
        data.clear();
        idIndex.clear();
    }

    @Override
    public Row getById(Long id) {
        return idIndex.get(id);
    }

    @Override
    public Row getByKey(String key) {
        return data.get(key);
    }

    @Override
    public Row getValueContainsWord(String value) {
        for (Row row : data.values()) {
            if (row.value.contains(value)) {
                return row;
            }
        }
        return null;
    }
}
