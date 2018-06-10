package cn.ccsgroup.ccsframework.common.algorithm.map.demo2;

import cn.ccsgroup.ccsframework.common.algorithm.map.Edge;

import java.util.ArrayList;
import java.util.List;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/6/8 13:57
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class AdjMatrixGraph<E> {

    // 顺序表存储图的顶点集合
    protected MySeqList<E> vertexlist;

    // 图的邻接矩阵 二维图 存储的是每个顶点的名称（A,B,C,D....）
    protected int[][] adjmatrix;

    private final int MAX_WEIGHT = Integer.MAX_VALUE / 2;

    // -------一，构造图：增删改查-------------------------//
    public AdjMatrixGraph(int n) {// n为顶点的数目
        this.vertexlist = new MySeqList<E>(n);
        this.adjmatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 对角线上为0，其他的都为无穷大。
                this.adjmatrix[i][j] = (i == j) ? 0 : MAX_WEIGHT;
            }
        }
    }

    // 构造函数内一个是字符串数组，一个是edge的set集合
    public AdjMatrixGraph(E[] vertices, Edge[] edges) {
        this(vertices.length);
        // 添加顶点
        for (int i = 0; i < vertices.length; i++) {
            insertVertex(vertices[i]);
        }
        // 添加边
        for (int j = 0; j < edges.length; j++) {
            insertEdge(edges[j]);
        }
    }


    // 构造函数内一个是数组集合，一个是edge的set集合
    public AdjMatrixGraph(MySeqList<E> list, Edge[] edges) {
        this(list.size());
        this.vertexlist = list;
        for (int j = 0; j < edges.length; j++) {
            insertEdge(edges[j]);
        }
    }

    // 显示出一共顶点的数目
    public int vertexCount() {
        return this.vertexlist.size();
    }

    // 根据编号得到该顶点
    public E get(int i) {
        return this.vertexlist.get(i);
    }

    public boolean insertVertex(E vertex) { // 插入一个顶点，若插入成功，返回true
        return this.vertexlist.add(vertex);
    }

    // 插入一条权值为weight的边<vi,vj>，若该边已有，则不插入
    public boolean insertEdge(int i, int j, int weight) {
        // 先判断该边两个顶点的编号是否在范围，该边的值是否为最大值，来确定所添加边的值是否存在；
        if (i >= 0 && i < this.vertexCount() && j >= 0 && j < this.vertexCount()
                && i != j && this.adjmatrix[i][j] == this.MAX_WEIGHT) {
            // 添加权值
            this.adjmatrix[i][j] = weight;
            return true;
        }
        return false;
    }

    /**
     * 插入边
     *
     * @param edge
     * @return
     */
    public boolean insertEdge(Edge edge) {
        if (edge == null) {
            return false;
        }
        return insertEdge(edge.start, edge.dest, edge.weight);
    }

    @Override
    public String toString() {
        String str = "顶点集合： " + this.vertexlist.toString() + "\n";
        str += "邻近矩阵：    \n";
        int n = vertexCount();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.adjmatrix[i][j] == MAX_WEIGHT) {
                    str += " ∞";// 最大值（不存在）的时候的显示方式；
                } else {
                    str += " " + adjmatrix[i][j];// 每一个顶点到其他顶点的权值
                }
            }
            str += "\n";
        }
        return str;
    }

    // 删除边〈vi,vj〉，若成功，返回T
    public boolean removeEdge(int i, int j) {
        if (i >= 0 && i < vertexCount() && j >= 0 && j < vertexCount()
                && i != j && this.adjmatrix[i][j] != this.MAX_WEIGHT) {
// 判断该边的两个顶点是否存在，以及改边的值是否为最大值来判断改边是否存在；
            this.adjmatrix[i][j] = this.MAX_WEIGHT; // 设置该边的权值为无穷大，说明已不存在；
            return true;
        }
        return false;
    }

    // 删除序号为v的顶点及其关联的边
    public boolean removeVertex(int v) {
        int n = vertexCount(); // 删除之前的顶点数
        if (v >= 0 && v < n) {// V的要求范围
            this.vertexlist.remove(v); // 删除顺序表的第i个元素，顶点数已减一
            for (int i = v; i < n - 1; i++) {
                for (int j = 0; j < n; j++) {
                    this.adjmatrix[i][j] = this.adjmatrix[i + 1][j]; // 邻接矩阵：删除点以下往上移动一位
                }
            }
            for (int j = v; j < n - 1; j++) {
                for (int i = 0; i < n - 1; i++) {
                    this.adjmatrix[i][j] = this.adjmatrix[i][j + 1]; // 邻接矩阵：删除点以右往左移动一位
                }
            }
            return true;
        }
        return false;
    }

    // 返回顶点v的第一个邻接顶点的序号
    // 若不存在第一个邻接顶点，则返回-1
    public int getFirstNeighbor(int v) {
        return getNextNeighbor(v, -1);
    }

    // 返回v在w后的下一个邻接顶点
    public int getNextNeighbor(int v, int w) {
        // 对v
        // w的范围限定
        if (v >= 0 && v < this.vertexCount()
                && w >= -1
                && w < this.vertexCount()
                && v != w) {
            for (int j = w + 1; j < this.vertexCount(); j++) {
                // w=-1时，j从0开始寻找下一个邻接顶点
                if (this.adjmatrix[v][j] > 0 && this.adjmatrix[v][j] < this.MAX_WEIGHT) {
                    // 遍历和v相关的点，得到下一个点
                    return j;
                }
            }
        }
        return -1;
    }

// -------二，最小生成树-------------------------//

    /**
     * 普里姆算法的基本思想: 取图中任意一个顶点 v 作为生成树的根，之后往生成树上添加新的顶点 w。 在添加的顶点 w
     * <p>
     * 和已经在生成树上的顶点v之间必定存在一条边， 并且该边的权值在所有连通顶点 v 和 w 之间的边中取值最小。
     * <p>
     * 之后继续往生成树上添加顶点，直至生成树上含有 n-1 个顶点为止。
     */
    public AdjMatrixGraph minSpanTree_prim() {
        Edge[] mst = new Edge[this.vertexCount() - 1]; // n个顶点最小生成树有n-1条边
        int un;
        List<Integer> u = new ArrayList<Integer>();// 存放所有已访问过的顶点集合
        u.add(0);// 起始点默认为标识为0的顶点
        for (int i = 0; i < this.vertexCount() - 1; i++) {
            int minweight = MAX_WEIGHT;// 最小边的时候，权值
            int minstart = MAX_WEIGHT;// 最小边的时候，起点
            int mindest = MAX_WEIGHT;// 最小边的时候，终点
            for (int j = 0; j < u.size(); j++) {
                un = u.get(j);
                for (int k = 0; k < this.vertexCount(); k++) {
// 获取最小值的条件：1.该边比当前情况下的最小值小；2.该边还未访问过；
                    if ((minweight > adjmatrix[un][k]) && (!u.contains(k))) {
                        minweight = adjmatrix[un][k];
                        minstart = un;
                        mindest = k;
                    }
                }
            }
            System.out.println("一次遍历所添加的最小边：他的权值，起点，终点分别为：weight:" + minweight
                    + "start:" + minstart + "dest:" + mindest);
            u.add(mindest);
            Edge e = new Edge(minstart, mindest, adjmatrix[minstart][mindest]);
            mst[i] = e;
        }
        return new AdjMatrixGraph(this.vertexlist.toArray(), mst); // 构造最小生成树相应的图对象
    }

    /**
     * public AdjMatrixGraph minSpanTree_kruskal() { }
     */
    // -------三，图的遍历（广度遍历，深度遍历）-------------------------//

    /**
     * 深度遍历
     */
    public void DFStraverse() {
        int n = this.vertexCount();
        boolean[] visited = new boolean[n];
        for (int i = 1; i < n; i++) {
            visited[i] = false;
        }
        // 编号0为起始点，进行一次深度优先遍历（一次得到一个连通分量）
        for (int j = 0; j < n; j++) {
            if (!visited[j]) {
                System.out.println("以该顶点为" + j + "起始点的遍历：");
                this.DFS(j, visited);
            }
        }
    }

    // 参数1：遍历起始点的编号，
    // 参数2：记录各个顶点是否被访问过
    public void DFS(int v, boolean[] visited2) {
        boolean[] visited = visited2;
        visited[v] = true;
        System.out.println("遍历顶点" + v);
        for (int w = this.getFirstNeighbor(v); w >= 0; w = this.getNextNeighbor(v, w)) {
            if (!visited[w]) {
                visited[w] = true;
                this.DFS(w, visited);
            }
        }
    }

    /**
     * 广度遍历
     */
    public void BFStraverse() {
        int n = this.vertexCount();
        boolean[] visited = new boolean[n];
        MyQueue myqueue = new MyQueue<Integer>();
        for (int i = 1; i < n; i++) {
            visited[i] = false;
        }

        for (int j = 0; j < n; j++) {
            if (!visited[j]) {
                visited[j] = true;
                System.out.println("遍历起点：" + j);
                myqueue.offer(j);
                while (!myqueue.isEmpty()) {
                    int v = (Integer) myqueue.poll();
                    System.out.println("遍历点：" + v);
                    for (int w = this.getFirstNeighbor(v); w >= 0; w = this.getNextNeighbor(v, w)) {
                        if (!visited[w]) {
                            visited[w] = true;
                            myqueue.offer(w);
                        }
                    }
                }
            }
        }
    }

    // -------四，图的最短路径Dijkstra算法-------------------------//
    public void Dijkstra() {
        int n = this.vertexCount();
        int minweight = MAX_WEIGHT;
        int minUn = 0;
        int[] minmatrix = new int[n];// 存放当前起始点到其余各个顶点的距离；
        boolean[] isS = new boolean[n];// 判断各个是否被访问过
        String[] route = new String[n];// 每个字符串是显示对应顶点最短距离的路径；
        for (int i = 1; i < n; i++) {// 初始化
            minmatrix[i] = adjmatrix[0][i];
            isS[i] = false;
            route[i] = "起点->" + i;
        }

        for (int i = 1; i < n; i++) {
// 选择 当前 和起点 连通的，且值最小的顶点；
            for (int k = 1; k < n; k++) {
                if (!isS[k]) {
                    if (minmatrix[k] < minweight) {
                        minweight = minmatrix[k];
                        minUn = k;
                    }
                }
            }

            isS[minUn] = true;// 将该点设置为已访问；
            for (int j = 1; j < n; j++) {
                if (!isS[j]) {// 判断：该顶点还没加入到S中/属于U-S；
                    if (minweight + adjmatrix[minUn][j] < minmatrix[j]) {
// 通过当下最小值 访问到得其他顶点的距离小于原先的最小值 则进行交换值
                        minmatrix[j] = minweight + adjmatrix[minUn][j];
                        route[j] = route[minUn] + "->" + j;
                    }
                }
            }
            minweight = MAX_WEIGHT;// 因为要放到下一个循环中，所以一定要重设置一下，回到最大值
        }

        for (int m = 1; m < n; m++) {
            System.out.println("从V0出发到达" + m + "点");
            if (minmatrix[m] == MAX_WEIGHT) {
                System.out.println("没有到达该点的路径");
            } else {
                System.out.println("当前从V0出发到达该点的最短距离：" + minmatrix[m]);
                System.out.println("当前从V0出发到达该点的最短距离：" + route[m]);
            }
        }
    }

    // -------五，图的连通性-------------------------//
    public boolean isConnect() {
        int n = this.vertexCount();
        boolean[] visited = new boolean[n];
        // 记录不能一次深度优先遍历通过的数目
        // 全部顶点作为出发点开始遍历，如果全部都不能一次遍历通过（notConnectNum == n），说明该图不连通。
        int notConnectNum = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                visited[i] = false;
            }
            this.DFS(j, visited);
            // 一旦有没有被遍历到的顶点（说明该顶点不属于该连通分量），跳出循环
            for (int k = 0; k < n; k++) {
                System.out.println(visited[k]);
                if (visited[k] == false) {
                    notConnectNum++;
                    break;
                }
            }
        }

        if (notConnectNum == n) {
            System.out.println("此图是不连通的");
            return false;
        } else {
            System.out.println("此图是连通的");
            return true;
        }
    }

    // -------六，图的拓扑排序-------------------------//
    public void topoLogicalSort() {
        int n = this.vertexCount();
        int[] inDegree = new int[n];
        MyStack mystack = new MyStack();
        String route = "拓扑排序出发：";
        int count = 0;
        for (int i = 0; i < n; i++) {
            inDegree[i] = 0;
            //获取每一个顶点的入度
            for (int j = 0; j < n; j++) {
                if (adjmatrix[j][i] != 0
                        && adjmatrix[j][i] != MAX_WEIGHT) {
                    inDegree[i] += 1;
                }
            }
            //先将入度为0的顶点加入到栈中
            if (inDegree[i] == 0) {
                mystack.push(i);
            }
        }
        while (!mystack.empty()) {
            //从栈中删除该顶点
            int v = (Integer) mystack.pop();
            route += "->" + v;
            ++count;
            for (int w = this.getFirstNeighbor(v);
                 w >= 0;
                 w = this.getNextNeighbor(v, w)) {
                //因为该顶点被“删除”，所有以该顶点为弧尾的边的弧头的入度减一
                inDegree[w] -= 1;
                //先将入度为0的顶点加入到栈中
                if (inDegree[w] == 0) {
                    mystack.push(w);
                }
            }
        }
        //当经历拓扑排序遍历后，所有顶点都被“删除”时（count=n），此时实现拓扑排序
        if (count < n) {
            System.out.println("存在回路，不满足拓扑排序的条件");
        } else {
            System.out.println("实现拓扑排序" + route);
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

        //图的遍历
        //深度遍历
        graph.DFStraverse();
        //广度遍历
        graph.BFStraverse();


        System.out.println(graph.toString());
    }
}
