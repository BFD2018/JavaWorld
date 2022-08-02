package com.xjt.javase.algorithm.prim;

import java.util.Arrays;

public class PrimAlgorithm{
    public static final int MAXInt = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] data = {'A','B','C','D','E','F','G'};
        int vertex = data.length;

        //邻接矩阵的关系使用二维数组表示,MAXInt 这个大数，表示两个点不联通
        int[][] weight = {
                {MAXInt,5,7,MAXInt,MAXInt,MAXInt,2},
                {5,MAXInt,MAXInt,9,MAXInt,MAXInt,3},
                {7,MAXInt,MAXInt,MAXInt,8,MAXInt,MAXInt},
                {MAXInt,9,MAXInt,MAXInt,MAXInt,4,MAXInt},
                {MAXInt,MAXInt,8,MAXInt,MAXInt,5,4},
                {MAXInt,MAXInt,MAXInt,4,5,MAXInt,6},
                {2,3,MAXInt,MAXInt,4,6,MAXInt},
        };

        MGraph graph = new MGraph(vertex);
        MinTree minTree = new MinTree();
        minTree.createGraph(graph,vertex,data,weight);
        //输出图的邻接矩阵
        minTree.showGraph(graph);

        //测试普利姆算法
        minTree.prim(graph,0);

    }
}

class MinTree{

    /**
     * //创建图的邻接矩阵
     * @param graph 图对象
     * @param vertex 图对应的节点个数
     * @param data 图节点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph,int vertex,char data[],int[][] weight){
        for (int i = 0; i < vertex; i++) {
            graph.data[i] = data[i];
            for (int j = 0; j < vertex; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //显示图的邻接矩阵
    public void showGraph(MGraph graph){
        for (int[] line : graph.weight) {
            System.out.println(Arrays.toString(line));
        }
    }

    /**
     * //编写prim算法，得到最小生成树
     * @param graph 图
     * @param v 表示从图的第几个顶点开始生成，data中对应的索引 'A'->0 'B'->1...
     */
    public void prim(MGraph graph,int v){
        //visited[] 标记节点是否被访问，0-未访问 1-已访问
        //Java语言默认为{0,0,0,0,0,0,0}
        int visited[] = new int[graph.vertex];

        //从索引v开始访问，故标记v节点为已访问
        visited[v] = 1;

        //h1 h2 记录两个节点的下标
        int h1 = -1;       //假设该节点已访问
        int h2 = -1;    //h1节点的未访问的邻接节点

        //将 minWeight 初始成一个大数，后面在遍历过程中，会被替换
        int minWeight = Integer.MAX_VALUE;
        //因为有graph.vertex 个节点，普利姆算法结束后 有graph.vertex-1 条边
        //第1 2 3 ...graph.vertex-1 条边 依次遍历生成
        for (int k = 1; k < graph.vertex; k++) {
            //从节点 data[] 的每一个节点 遍历出它的相连节点
            for (int i = 0; i < graph.vertex; i++) {
                for (int j = 0; j < graph.vertex; j++) {
                    if(visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight){
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //找到一条边是最小
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值:" + minWeight);

            //将h2这个结点标记为已经访问
            visited[h2] = 1;
            //minWeight 重新设置为最大值
            minWeight = Integer.MAX_VALUE;
        }
    }
}

class MGraph{
    int vertex;     //图的节点个数
    char[] data;    //存放节点的数组
    int[][] weight;     //存放边 --->图对应的邻接矩阵

    public MGraph(int vertex){
        this.vertex = vertex;
        this.data = new char[vertex];
        this.weight = new int[vertex][vertex];
    }

    public MGraph() {

    }
}