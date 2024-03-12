package top.xiaolikey.udb;

import top.xiaolikey.udb.store.PageOffset;

/**
 *
 *
 * @author xiaolikey
 * @date 2024/3/10
 * @since 0.0.1
 */
public interface IndexSystem {
    PageOffset getById(long id);

    PageOffset getByKey(String key);
}
