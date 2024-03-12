package top.xiaolikey.udb.store;

import top.xiaolikey.udb.Query;
import top.xiaolikey.udb.Row;

import java.util.List;
import java.util.Objects;

/**
 * 存储
 *
 * @author xiaolikey
 * @date 2024/3/10
 * @since 0.0.1
 */
public interface Store extends Query {
    int saveOrUpdate(Row row);

    void truncate();

    default int[] batchSaveOrUpdate(List<Row> rows) {
        Objects.requireNonNull(rows);
        int[] ans = new int[rows.size()];
        for (int i = 0; i < rows.size(); i++) {
            ans[i] = saveOrUpdate(rows.get(i));
        }
        return ans;
    }
}
