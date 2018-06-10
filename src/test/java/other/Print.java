package other;

import javax.servlet.http.HttpServletRequest;

/**
 * *******************************************************************
 * &lt;p&gt;文件名：${file_name} &lt;/p&gt;
 * &lt;p&gt;文件描述：${todo}(描述该文件做什么)
 *
 * @author ${user}
 * @project_name：${project_name}
 * @date ${date} ${time}
 * @history
 * @department：政务事业部 Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 * All Rights Reserved.
 */
public class Print {
    /**
     * @param ch
     * @return
     */
    public static boolean out(char ch) {
        System.out.print(ch);
        return true;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
//        int i = 0;
//        for (out('A'); out('B') && (i < 2); out('C')) {
//            i++;
//            Print.out('D');
//        }
        //ABDCBDCB
        HttpServletRequest request = null;
        //request.getParameterMap();
        System.out.println();
        double num = 660;
//        for(int i=1;Math.pow(1.08,i)<1000/num||num*Math.pow(1.08,i)<1100;i++){
//            System.out.println("第"+i+"年("+(2016+i)+"),GDP="+Math.round(660 * Math.pow(1.08, i)));
//        }
        Node head = new Node(0, null);
        Node tail, first;
        tail = first = head;
        for (int i = 1; i < 10; i++) {
            Node node = new Node(i, null);
            tail.setNode(node);
            tail = node;
        }
        print(first);
        first=reverse(head);
        print(first);
    }

    private static Node reverse(Node head) {
        //a->b->c->d->e
        Node node = head.getNode();
        if (node == null) {
            return head;
        }
        Node first=reverse(node);
        //a->b->c->d->e
        head.getNode().setNode(node);
        return first;
    }

    static void print(Node head) {
        Node node = head;
        System.out.println();
        while (node.getNode() != null) {
            System.out.print(node.getVal());
            node = node.getNode();
            System.out.print(",");
        }
    }
}

class Node {
    private int val;
    private Node node;

    public Node(int val, Node node) {
        this.val = val;
        this.node = node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Node getNode() {
        return node;
    }

    public int getVal() {
        return val;
    }
}






