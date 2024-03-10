package top.xiaolikey.udb;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 操作
 *
 * @author xiaolikey
 * @date 2024/3/11
 * @since 0.0.1
 */
public class Operator {
    String command;
    OperatorType operatorType;
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
                break;
            case SELECT_BY_ID:
                pattern = Pattern.compile(constants.PAT_SELECT_BY_ID);
                matcher = pattern.matcher(command);
                matcher.find();
                row = new Row(Long.parseLong(matcher.group(1)), null, null);
                break;
            case SELECT_BY_KEY:
                pattern = Pattern.compile(constants.PAT_SELECT_BY_KEY);
                matcher = pattern.matcher(command);
                matcher.find();
                row = new Row(-1, matcher.group(1), null);
                break;
            case TRUNCATE:
            case EXIT:
                break;
            default:
                throw new IllegalArgumentException("未知操作");
        }
    }

    public void execute(){
        switch (operatorType) {
            case INSERT:
                row.id = database.nextId();
                System.out.println(row);
                database.insertSuccess();
                break;
            case SELECT_BY_ID:
            case SELECT_BY_KEY:
                System.out.println(row);
                database.insertSuccess();
                break;
            case TRUNCATE:
            case EXIT:
                System.out.println("1");
                break;
            default:
                break;
        }
    }

}
