package cn.ccsgroup.ccsframework.common.algorithm.map.demo1;

import cn.ccsgroup.ccsframework.common.algorithm.map.Edge;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(带权无向图)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/6/8 11:18
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class AdjMatrixGraph<T> {
    /**
     * 设置最大权值，设置成常量
     */
    public static final int MAX_WEIGHT = Integer.MAX_VALUE / 2;

    /**
     * 图的邻接矩阵，用二维数组表示
     */
    private int[][] adjmatrix;

    /**
     * 顺序表存储的顶底集合
     */
    private SeqList<T> vertexlist;


    public AdjMatrixGraph(int size) {
        size = size < 10 ? 10 : size;
        //构造容量为size的空顺序表
        this.vertexlist = new SeqList<T>(size);
        this.adjmatrix = new int[size][size];

        //初始化邻接矩阵
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.adjmatrix[i][j] = (i == j) ? 0 : MAX_WEIGHT;
            }
        }
    }

    public AdjMatrixGraph(T[] vertices, Edge[] edges) {
        //初始化顶点列表和邻接矩阵
        this(vertices.length);
        if (vertices == null) {
            return;
        }
        //插入顶点
        for (int i = 0; i < vertices.length; i++) {
            insertVertex(vertices[i]);
        }
        //插入边
        if (edges != null) {
            for (int j = 0; j < edges.length; j++) {
                insertEdge(edges[j]);
            }
        }
    }

    /**
     * 顶点个数
     *
     * @return
     */
    public int vertexCount() {
        return this.vertexlist.length();
    }      //返回定点顺序表的元素个数

    public T get(int i) {
        return this.vertexlist.get(i);
    }             //返回第i个定点的元素

    /**
     * i到j的权重/花费
     *
     * @param i
     * @param j
     * @return
     */
    public int getWeight(int i, int j) {
        return this.adjmatrix[i][j];
    } //返<vi,vj>边的权值

    @Override
    public String toString() {
        String str = "顶点集合:" + this.vertexlist.toString() + "\n邻接矩阵：\n";
        int n = this.vertexCount();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                str += this.adjmatrix[i][j] == MAX_WEIGHT ? " $" : " " + this.adjmatrix[i][j];
            }
            str += "\n";
        }
        return str;
    }

    public int insertVertex(T x) {
        //顺序表追加元素，自动扩充
        this.vertexlist.append(x);
        //若二维数组不足,则扩充
        if (this.vertexCount() > this.adjmatrix.length) {
            //定义了局部变量i,j;
            int temp[][] = adjmatrix, i, j;
            //二维数组扩充2倍
            this.adjmatrix = new int[temp.length * 2][temp.length * 2];
            // A  B1
            // B2 B3
            for (i = 0; i < temp.length; i++) {
                for (j = 0; j < temp.length; j++) {
                    this.adjmatrix[i][j] = temp[i][j];
                }
                //填充数据到B1部分
                for (j = temp.length; j < temp.length * 2; j++) {
                    this.adjmatrix[i][j] = MAX_WEIGHT;
                }
            }
            //填充数据到B1.B3部分
            for (i = temp.length; i < temp.length * 2; i++) {
                for (j = 0; j < temp.length * 2; j++) {
                    this.adjmatrix[i][j] = (i == j) ? 0 : MAX_WEIGHT;
                }
            }
        }
        //返回插入顶点的序号
        return this.vertexlist.length() - 1;
    }

    /**
     * 插入一条边
     */
    public void insertEdge(int i, int j, int weight) {
        int n = this.vertexCount();
        if (i >= 0 && i < n && j >= 0 && j < n && this.adjmatrix[i][j] == MAX_WEIGHT && i != j) {
            this.adjmatrix[i][j] = weight;
        }
    }

    /**
     * 插入一条边
     */
    public void insertEdge(Edge edge) {
        this.insertEdge(edge.start, edge.dest, edge.weight);
    }

    /**
     * 删除一条边
     *
     * @param i
     * @param j
     */
    public void removeEdge(int i, int j) {
        if (i >= 0 && i < vertexCount() && j >= 0 && j < vertexCount() && i != j) {
            this.adjmatrix[i][j] = MAX_WEIGHT;
        }
    }

    /**
     * 删除顶点以及和顶点有关系的边
     */

    public void removeVertex(int i) {
        int n = this.vertexCount();
        if (i < 0 || i > n) {
            return;
        }
        this.vertexlist.remove(i);
        for (int j = 0; j < i; j++) {
            for (int k = i + 1; k < n; k++) {
                this.adjmatrix[j][k - 1] = this.adjmatrix[j][k];      //元素向左移一行
            }
        }
        for (int j = i + 1; j < n; j++) {
            for (int k = 0; k < i; k++) {
                this.adjmatrix[j - 1][k] = this.adjmatrix[j][k];      //元素向上移一行
            }
        }
        for (int j = i + 1; j < n; j++) {
            for (int k = i + 1; k < n; k++) {
                this.adjmatrix[j - 1][k - 1] = this.adjmatrix[j][k];
            }
        }
    }

    public static void main(String[] args) {
        String[] verices = {"A", "B", "C", "D", "E"};
        Edge edges[] = {
                new Edge(0, 1, 5),
                new Edge(0, 3, 2),
                new Edge(1, 0, 5),
                new Edge(1, 2, 7),
                new Edge(1, 3, 6),
                new Edge(2, 1, 7),
                new Edge(2, 3, 8),
                new Edge(2, 4, 3),
                new Edge(3, 0, 2),
                new Edge(3, 1, 6),
                new Edge(3, 2, 8),
                new Edge(3, 4, 9),
                new Edge(4, 2, 3),
                new Edge(4, 3, 9)
        };
        AdjMatrixGraph<String> graph = new AdjMatrixGraph<String>(verices, edges);
        System.out.println("带权无向图" + graph.toString());
        System.out.println("插入顶点F,插入边(A,F,9),删除顶点C,删除边(D,E)");
        int i = graph.insertVertex("F");
        graph.insertEdge(0, i, 9);
        graph.insertEdge(i, 0, 9);
        graph.removeVertex(2);
        graph.removeEdge(2, 3);
        graph.removeEdge(3, 2);
        System.out.println(graph.toString());
    }
}