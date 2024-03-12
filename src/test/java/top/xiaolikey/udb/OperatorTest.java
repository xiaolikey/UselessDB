package top.xiaolikey.udb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO
 *
 * @author xiaolikey
 * @date 2024/3/11
 * @since 0.0.1
 */
class OperatorTest {
    Database database;

    @BeforeEach
    void setUp() {
        database = new Database();
        Operator operator = Operator.ofOperator(database, "insert into ('abc', 'abc 123 456 word best')");
        operator.execute();
    }

    @Test
    void execute() {
        assertQuery( "select from where id=1");
        assertQuery( "select from where key='abc'");
        assertQuery( "select from where value like 'abc'");
        assertQuery( "select from where value like '123'");
        assertQuery( "select from where value like 'word'");
        assertQuery( "select from where value like '456'");
    }

    private void assertQuery(String cmd) {
        Operator operator = Operator.ofOperator(database, cmd);
        operator.execute();
        Row row = operator.row;
        assertEquals(1, row.id);
        assertEquals("abc", row.key);
        assertEquals("abc 123 456 word best", row.value);
    }
}