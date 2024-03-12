package top.xiaolikey.udb;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常量
 *
 * @author xiaolikey
 * @date 2024/3/10
 * @since 0.0.1
 */
public class constants {
    public static final String PAT_INSERT = "insert\\s+into\\s?\\(\\s?[\"']([0-9a-zA-Z]+)[\"']\\s?,\\s?[\"']([0-9a-zA-Z ]+)[\"']\\)";
    public static final String PAT_SELECT_BY_ID = "select\\s+from\\s+where\\s+id\\s?=\\s?([0-9]+)";
    public static final String PAT_SELECT_BY_KEY = "select\\s+from\\s+where\\s+key\\s?=\\s?[\"']([0-9a-zA-Z]+)[\"']";
    public static final String PAT_SELECT_VALUE_CONTAINS_WORD = "select\\s+from\\s+where\\s+value\\s?like\\s?[\"']([0-9a-zA-Z]+)[\"']";

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(PAT_INSERT);
        Matcher matcher = pattern.matcher("insert into('key', 'value')");
        while (matcher.find()){
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
        }
    }
}
