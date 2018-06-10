package cn.ccsgroup.ccsframework.common.algorithm.map;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/6/8 12:17
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class Edge implements Comparable<Edge> {

    public int start, dest, weight;

    public Edge(int start, int dest, int weight) {
        this.start = start;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "(" + start + "," + dest + "," + weight + ")";
    }

    @Override
    public int compareTo(Edge e) {
        // TODO Auto-generated method stub
        if (this.start != e.start) {
            return this.start - e.start;
        }
        return this.dest - e.dest;
    }

}
