package top.xiaolikey.udb;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SQL parser
 *
 * @author xiaolikey
 * @date 2024/3/11
 * @since 0.0.1
 */
public class SQLParser {

    public static OperatorType match(String line) {
        if (line == null || line.trim().length() == 0) {
            return null;
        }
        OperatorType ans = null;
        line = line.trim().toLowerCase();
        if(mathInsert(line)) {
            ans = OperatorType.INSERT;
        } else if(mathSelectById(line)) {
            ans = OperatorType.SELECT_BY_ID;
        } else if(mathSelectByKey(line)) {
            ans = OperatorType.SELECT_BY_KEY;
        } else if(mathTruncate(line)) {
            ans = OperatorType.TRUNCATE;
        } else if(mathExit(line)) {
            ans = OperatorType.EXIT;
        }
        return ans;
    }

    private static boolean mathInsert(String line) {
        return Pattern.matches(constants.PAT_INSERT, line);
    }

    private static boolean mathSelectById(String line) {
        return Pattern.matches(constants.PAT_SELECT_BY_ID, line);
    }

    private static boolean mathSelectByKey(String line) {
        return Pattern.matches(constants.PAT_SELECT_BY_KEY, line);
    }

    private static boolean mathTruncate(String line) {
        String patternString = "truncate";
        return Pattern.matches(patternString, line);
    }

    private static boolean mathExit(String line) {
        String patternString = "exit";
        return Pattern.matches(patternString, line);
    }

}
