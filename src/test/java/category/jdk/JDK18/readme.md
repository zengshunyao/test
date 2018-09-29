##JDK1.8新特性
<p>总的来说，JDK在以下方面具有新特性：</p>
 
1. 速度更快 – 红黑树 
2. 代码更少 – Lambda 
3. 强大的Stream API – Stream 
4. 便于并行 – Parallel 
5. 最大化减少空指针异常 – Optional

###1、HashMap中的红黑树

HashMap中链长度大于8时采取红黑树的结构存储。

红黑树，除了添加，效率高于链表结构。

###2、ConcurrentHashMap

Jdk1.7时隔壁级别CocnurrentLevel（锁分段机制）默认为16。

JDK1.8采取了CAS算法

Jdk1.8没有永久区，取而代之的是MetaSpace元空间，用的是物理内存。

###3、Lambda表达式

1、Lambda表达式的基础语法：Java8引入了一个新的操作符“->”，该操作符成为箭头操作符或者Lambda操作符，箭头操作符将Lambda表达式拆分成两部分

左侧：Lambda表达式的参数列表 
右侧：Lambda表达式中所需执行的功能，即Lambda体。


###4、Java8中的四大核心函数式接口

-------------------------------
/**
Consumer<T> :消费型接口
    void accept(T t);

Supplier<T> :供给型接口
    T get();

Function<T,R> :函数型接口
    R apply(T t);

Predicate<T> :断言型接口
    boolean test(T t);
*/

---------------------

本文来自 op134972 的CSDN 博客 ，全文地址请点击：https://blog.csdn.net/op134972/article/details/76408237?utm_source=copy 