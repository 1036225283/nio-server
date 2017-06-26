package com.nitian.test;

import com.nitian.util.column.graph.Graph;
import com.nitian.util.sql.DBHelper;
import com.nitian.util.sql.UtilSql;

import java.util.List;
import java.util.Map;

/**
 * graph test
 * Created by xws on 6/18/17.
 */
public class GraphTest {

    public static void main(String[] args) throws Exception {

        test1();
    }


    //简单测试
    public static void test0() {
        Graph graph = new Graph();
        int len = graph.addVertex("北京");
        System.out.println("len:" + len);


        len = graph.addVertex("武汉");
        System.out.println("len:" + len);

        len = graph.addVertex("天津");
        System.out.println("len:" + len);


        len = graph.addVertex("上海");
        System.out.println("len:" + len);


        len = graph.addVertex("建始");
        System.out.println("len:" + len);


        len = graph.addVertex("红岩");
        System.out.println("len:" + len);


        graph.showEdge();
        graph.showVertex();
    }

    public static void test1() throws Exception {
        DBHelper dbHelper = new DBHelper();
        List<Map<String, Object>> vertexs = UtilSql.getList("SELECT * FROM vertex;", dbHelper);
        List<Map<String, Object>> edges = UtilSql.getList("SELECT * FROM edge;", dbHelper);

        Graph graph = new Graph();
        for (Map<String, Object> map : vertexs) {
            graph.addVertex(map.get("strName").toString());
        }

        for (Map<String, Object> map : edges) {
            String strFrom = map.get("strFrom").toString();
            String strTo = map.get("strTo").toString();
            int nWeight = (int) map.get("nWeight");
            graph.addEdge(strFrom, strTo, nWeight);
        }

        graph.showVertex();
        graph.showEdge();

    }
}
