package top.xiaolikey.udb;

/**
 *
 *
 * @author xiaolikey
 * @date 2024/3/11
 * @since 0.0.1
 */
public interface IndexSystem {
    PageOffset getById(long id);

    PageOffset getByKey(String key);
}
