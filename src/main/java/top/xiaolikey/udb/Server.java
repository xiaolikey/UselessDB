package top.xiaolikey.udb;

import java.util.Scanner;

/**
 * Server start
 *
 * @author xiaolikey
 * @date 2024/3/10
 * @since 0.0.1
 */
public class Server {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Database database = new Database();
        while (true) {
            // 读取命令
            String command = scanner.nextLine();
            Operator operator = Operator.ofOperator(database, command);
            if (operator == null) {
                System.out.println("未知操作");
                continue;
            }
            if(operator.operatorType == OperatorType.EXIT){
                break;
            }
            //执行命令行
            operator.execute();
        }
    }
}
