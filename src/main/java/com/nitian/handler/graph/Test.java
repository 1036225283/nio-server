package com.nitian.handler.graph;

import com._1036225283.util.self.column.graph.Edge;
import com._1036225283.util.self.column.graph.Graph;
import com._1036225283.util.self.sql.DBHelper;
import com._1036225283.util.self.sql.UtilSql;
import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.Map;

/**
 * graph test
 * Created by xws on 8/14/17.
 */
public class Test {

    public static void main(String[] args) throws Exception {

        Graph graph = getGraph();
//        List<Integer> out =  graph.dfsSearch("上海");
//        List<Integer> out = graph.bfsSearch("上海");
//        System.out.println(out);

        graph.showEdge();
        graph.showVertex();
        prim(graph);
//        createSql();
        System.out.println("this is end");
    }

    //测试findSearchIndex
    public static void test1() {

    }

    //测试新增节点
    public static Graph getGraph() throws Exception {
        DBHelper dbHelper = new DBHelper();

        List<Map<String, Object>> vertex = UtilSql.getList("SELECT * FROM vertex WHERE strType = 'test';", dbHelper);

        List<Map<String, Object>> edge = UtilSql.getList("SELECT * FROM edge WHERE strType = 'test';", dbHelper);

        Graph graph = new Graph();
        for (Map<String, Object> map : vertex) {
            graph.addVertex(map.get("strName").toString());
        }

        for (Map<String, Object> map : edge) {
            graph.addEdge(map.get("strFrom").toString(), map.get("strTo").toString(), (int) map.get("nWeight"));
        }


        return graph;
    }


    public static void arrayToJson() {
        String[][] aa = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                aa[i][j] = i * j + "";
            }
        }
        System.out.println(JSON.toJSON(aa).toString());

    }

    //根据顶点创建sql,插入数据到数据库
    public static void createSql() {
        String[] vertex = new String[]{
                "v0 v1 10",
                "v1 v2 18",
                "v2 v3 22",
                "v3 v4 20",
                "v4 v5 26",
                "v5 v0 11",
                "v5 v6 17",
                "v6 v1 16",
                "v6 v7 19",
                "v6 v3 24",
                "v7 v4 7",
                "v7 v3 16",
                "v8 v1 12",
                "v8 v2 8",
                "v8 v3 21"};
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO edge (strFrom, strTo, nWeight, strType) VALUES ");
        for (String string : vertex) {
            String[] values = string.split(" ");
            sb.append("('").append(values[0]).append("','")
                    .append(values[1]).append("',").append(values[2]).append(",'test'")
                    .append("),");
            sb.append("('").append(values[1]).append("','")
                    .append(values[0]).append("',").append(values[2]).append(",'test'")
                    .append("),");

        }
        System.out.println(sb.toString());
    }

    public static void prim(Graph graph) {
        List<Edge> edges = graph.prim();

        int total = 0;
        for (Edge edge : edges) {
            System.out.println(edge.getFromIndex() + "->" + edge.getToIndex() + ":" + edge.getWeight());
            total = total + edge.getWeight();
        }

        System.out.println("最小生成树的大小:" + total);

    }
}
