package com.example.demo.function;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Tan Ke
 * @date 2021/9/30
 */
public class SqlParseTests {
    String sql = "SELECT T.* , (SELECT SUM(CONVERT(WORKING_TIME_PLAN, DECIMAL(10,2))) FROM TASK_PLAN_WORKING_TIME WHERE TASK_ID = T.ID) AS planWorkingTime, (SELECT SUM(CONVERT(WORKING_TIME, DECIMAL(10,2))) FROM TASK_WORKINGTIME_FILL_RECORD WHERE TASK_ID = T.ID) AS realWorkingTime, A.USERNAME AS A_USERNAME, M.USERNAME AS M_USERNAME, (CASE WHEN T.STATUS = 'finished' THEN 1 ELSE 0 END ) FINISHED FROM TASK T LEFT JOIN eteams.EMPLOYEE A ON T.CREATOR = A.ID LEFT JOIN eteams.EMPLOYEE M ON T.MANAGER = M.ID WHERE T.TENANT_KEY = ? AND EXISTS (SELECT ENTITY_ID FROM SHARE_ENTRY WHERE TENANT_KEY = ? AND SID = ? AND ENTRY_TYPE = 'user' AND MODULE = 'task' AND MASK > 2 AND MASK < 31 AND ENTITY_ID=T.ID) AND (T.IS_DELETE = 0) AND (T.STATUS = 'todo') ORDER BY T.ORDER_TIME DESC limit 0 ,50000";
    String paramsStr = "trw7zok2ze(String),  trw7zok2ze(String), 5819167358832084035(Long)";

    @Test
    public void test() throws Exception {
        String[] params = paramsStr.split(", ");
        List<String> splitSql = Arrays.asList(sql.split("\\?"));

        List<Pair<String, String>> paramList = new ArrayList<>();
        for (String param : params) {
            String[] split = param.split("\\(|\\)");
            Pair<String, String> pair = Pair.of(split[0], split[1]);
            paramList.add(pair);
        }
        if (splitSql.size()-1 != paramList.size()) {
            System.err.printf("参数与sql中个数不匹配，sql：%d, param: %d \n", splitSql.size(), paramList.size());
            return;
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < splitSql.size(); i++) {
            if (i >= paramList.size()) {
                sb.append(splitSql.get(i));
                continue;
            }
            sb.append(splitSql.get(i));
            sb.append(" ");
            Pair<String, String> pair = paramList.get(i);
            String paramType = pair.getSecond();
            if (Objects.equals("String", paramType) || Objects.equals("Timestamp", paramType)) {
                sb.append("'");
                sb.append(pair.getFirst());
                sb.append("'");
            } else {
                sb.append(pair.getFirst());
            }
        }
        System.out.println(sb);
    }

    public static class Pair<K, V> {
        private K first;
        private V second;

        public Pair(K first, V second) {
            this.first = first;
            this.second = second;
        }

        public static <K, V> Pair<K, V> of(K first, V second) {
            return new Pair<>(first, second);
        }

        public K getFirst() {
            return first;
        }

        public void setFirst(K first) {
            this.first = first;
        }

        public V getSecond() {
            return second;
        }

        public void setSecond(V second) {
            this.second = second;
        }
    }
}
