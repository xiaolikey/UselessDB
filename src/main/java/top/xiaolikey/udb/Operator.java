package top.xiaolikey.udb;

import top.xiaolikey.udb.sql.SQLParser;
import top.xiaolikey.udb.sql.SQLType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 操作
 *
 * @author xiaolikey
 * @date 2024/3/10
 * @since 0.0.1
 */
public class Operator {
    String command;
    OperatorType operatorType;
    SQLType sqlType;
    Row row;
    Database database;

    private Operator(Database database, String command, OperatorType operatorType) {
        this.database = database;
        this.command = command;
        this.operatorType = operatorType;
        initRow();
    }

    public static Operator ofOperator(Database database, String command) {
        OperatorType operatorType = SQLParser.match(command);
        if (operatorType == null) {
            return null;
        }
        return new Operator(database, command, operatorType);
    }

    private void initRow() {
        Pattern pattern;
        Matcher matcher;
        switch (operatorType) {
            case INSERT:
                pattern = Pattern.compile(constants.PAT_INSERT);
                matcher = pattern.matcher(command);
                matcher.find();
                String key = matcher.group(1);
                String val = matcher.group(2);
                row = new Row(database.nextId(), key, val);
                sqlType = SQLType.DML;
                break;
            case SELECT_BY_ID:
                pattern = Pattern.compile(constants.PAT_SELECT_BY_ID);
                matcher = pattern.matcher(command);
                matcher.find();
                row = new Row(Long.parseLong(matcher.group(1)), null, null);
                sqlType = SQLType.DQL;
                break;
            case SELECT_BY_KEY:
                pattern = Pattern.compile(constants.PAT_SELECT_BY_KEY);
                matcher = pattern.matcher(command);
                matcher.find();
                row = new Row(-1, matcher.group(1), null);
                sqlType = SQLType.DQL;
                break;
            case SELECT_VALUE_CONTAINS_WORD:
                pattern = Pattern.compile(constants.PAT_SELECT_VALUE_CONTAINS_WORD);
                matcher = pattern.matcher(command);
                matcher.find();
                row = new Row(-1, null, matcher.group(1));
                sqlType = SQLType.DQL;
                break;
            case TRUNCATE:
                sqlType = SQLType.DML;
            case EXIT:
                break;
            default:
                throw new IllegalArgumentException("未知操作");
        }
    }

    public void execute() {
        switch (operatorType) {
            case INSERT:
                database.executeInsert(row);
                break;
            case SELECT_BY_ID:
            case SELECT_BY_KEY:
            case SELECT_VALUE_CONTAINS_WORD:
                row = database.executeQuery(this);
                break;
            case TRUNCATE:
                database.executeTruncate();
            case EXIT:
                System.out.println("1");
                break;
            default:
                break;
        }
    }

}
