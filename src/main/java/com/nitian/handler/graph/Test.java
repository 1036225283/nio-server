package com.nitian.handler.graph;

import com.alibaba.fastjson.JSON;
import com.nitian.util.column.graph.Graph;
import com.nitian.util.sql.DBHelper;
import com.nitian.util.sql.UtilSql;

import java.util.List;
import java.util.Map;

/**
 * graph test
 * Created by xws on 8/14/17.
 */
public class Test {

    public static void main(String[] args) throws Exception {
        String[][] aa = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                aa[i][j] = i * j + "";
            }
        }
        ;
        System.out.println(JSON.toJSON(aa).toString());


        test();
    }


    public static void test() throws Exception {
        DBHelper dbHelper = new DBHelper();

        List<Map<String, Object>> vertex = UtilSql.getList("SELECT * FROM vertex;", dbHelper);

        List<Map<String, Object>> edge = UtilSql.getList("SELECT * FROM edge;", dbHelper);

        Graph graph = new Graph();
        for (Map<String, Object> map : vertex) {
            graph.addVertex(map.get("strName").toString());
        }

        for (Map<String, Object> map : edge) {
            graph.addEdge(map.get("strFrom").toString(), map.get("strTo").toString(), (int) map.get("nWeight"));
        }

        graph.showEdge();
        graph.showVertex();
    }

}
