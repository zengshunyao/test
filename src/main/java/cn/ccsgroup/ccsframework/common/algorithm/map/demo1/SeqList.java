package cn.ccsgroup.ccsframework.common.algorithm.map.demo1;

import java.util.Arrays;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/6/8 12:32
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class SeqList<T> {
    /**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     * MUST be a power of two <= 1<<30.
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * The load factor used when none specified in constructor.
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    //存储元素
    private Object[] element;               //对象数组，设置成私有成员
    //长度
    private int len;                        //顺序表的长度，元素的个数

    public SeqList(int size) {               //构造方法，创建容量为size的空表
        this.element = new Object[size];
        this.len = 0;
    }

    public SeqList() {
        this(64);
    }             //seqList构造方法，创建默认的空表

    public boolean isEmpty() {
        return this.len == 0;
    }         //判断顺序表是否为空

    public int length() {                    //返回顺序表的长度
        return this.len;
    }

    public T get(int i) {                    //返回顺序表第i个位置的值
        if (i >= 0 && i < this.len) {
            return (T) this.element[i];
        }
        return null;
    }

    public void set(int i, T x) {             //设置顺序表第i个位置的值
        if (x == null) {
            return;
        }
        if (i >= 0 && i < this.len) {
            this.element[i] = x;
        } else {
            throw new IndexOutOfBoundsException(i + "");          //抛出序号越界异常
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.len; i++) {
            str += this.element[i].toString() + " ";
        }
        return str;
    }

    public void insert(int i, T x) {              //插入x作为第i个元素
        if (x == null) {
            return;
        }
        if (this.len == element.length) {         //若数组满，则扩充顺序表容量
            Object[] oldElement = this.element;
//            this.element = new Object[oldEement.length * 2];
//            for (int j = 0; j < oldEement.length; j++) {
//                this.element[j] = oldEement[j];
//            }
            this.element = Arrays.copyOf(oldElement, oldElement.length * 2);
        }
        if (i < 0) {
            i = 0;
        }
        if (i > 0) {
            i = this.len;
        }
        for (int j = this.len - 1; j >= i; j--) {
            this.element[j + 1] = this.element[j];
        }
        this.element[i] = x;
        this.len++;
    }

    public T remove(int i) {
        //删除第i个元素，返回删除的值
        if (this.len == 0 || i < 0 || i >= this.len) {
            return null;
        }
        T old = (T) this.element[i];
        for (int j = i; j < this.len - 1; j++) {
            this.element[j] = this.element[j + 1];
        }
        this.len--;
        return old;
    }

    public void append(T x) {
        this.insert(this.len, x);
    }

    /**
     * Returns a power of two size for the given target capacity.
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static void main(String[] args) {
        SeqList<String> seq = new SeqList();
        seq.insert(0, "1");
        seq.insert(1, "2");
        seq.insert(2, "3");
        System.out.print(seq.toString());
    }
}
