package com.xjt.javase.algorithm.dijikstar;

import java.util.Arrays;

public class DijikstarAlgorithm {
    public static void main(String[] args) {

    }
}

class Graph{
    // 顶点数组
    private char[] vertex;
    // 邻接矩阵
    private int[][] matrix;
    //已经访问的顶点的集合
    private VisitedVertex vv;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    // 显示图
    public void showGraph(){
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * //迪杰斯特拉算法实现
     * @param index 表示出发顶点对应的下标
     */
    public void dsj(int index){
       // vv = new VisitedVertex();
    }
}

//已访问顶点集合
class VisitedVertex{
    // 记录各个顶点是否访问过 1表示访问过,0未访问,会动态更新
    public int[] already_arr;
    // 每个下标对应的值为前一个顶点下标, 会动态更新
    public int[] pre_visited;
    // 记录出发顶点到其他所有顶点的距离,比如G为出发顶点，就会记录G到其它顶点的距离，会动态更新，求的最短距离就会存放到dis
    public int[] dis;

    /**
     *
     * @param length 表示顶点的个数
     * @param index 出发顶点对应的下标, 比如G顶点，下标就是6
     */
    public VisitedVertex(int length,int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        //初始化dis[]
        Arrays.fill(dis,65535);
        this.already_arr[index] = 1;
        this.dis[index] = 0;
    }

    /**
     * 功能: 判断index顶点是否被访问过
     * @param index
     * @return
     */
    public boolean in(int index){
        return already_arr[index] == 1;
    }

    /**
     * 功能: 更新出发顶点到index顶点的距离
     * @param index 终点
     * @param len 出发顶点到index顶点的距离
     */
    public void updateDis(int index,int len){
        dis[index] = len;
    }

    /**
     * 功能: 更新pre这个顶点的前驱顶点为index顶点
     * @param pre 当前节点索引
     * @param index 当前节点的前驱节点索引
     */
    public void updatePre(int pre,int index){
        pre_visited[pre] = index;
    }


}
