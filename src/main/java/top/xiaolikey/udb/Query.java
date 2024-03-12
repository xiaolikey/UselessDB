package top.xiaolikey.udb;

import java.util.List;

/**
 * 查询方案
 *
 * @author xiaolikey
 * @date 2024/3/11
 * @since 0.0.1
 */
public interface Query {
    Row getById(Long id);

    Row getByKey(String key);

    Row getValueContainsWord(String value);
}
