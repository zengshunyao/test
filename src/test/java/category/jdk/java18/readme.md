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

####Lambda表达式的 

一、方法引用： 
若Lambda体中的内容有方法已经实现了，我们可以使用“方法引用”（可以理解为方法引用是Lambda表达式的另外一种表现形式）

主要语法：

对象：：实例方法名

类：：静态方法名

类：：实例方法名

注意： 
①Lambda体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型一致。 
②若Lambda参数列表中的第一参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用ClassName：：method进行调用。

####二、构造器引用

格式：

ClassName：：new

注意：需要引用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致。

三、数组引用

格式：

Type[]：：new

####4、Stream流

一系列流水线式的中间操作。(见 category.jdk.JDK18.LambdaTest.test4)

流是数据渠道，用于操作数据源（集合、数组等）所生成的元素序列。

注意： 
①Stream自己不会存储元素。 
②Stream不会改变源对象。相反，会返回持有新结果的新Stream。 
③Stream操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。



操作的三个步骤： 
1、创建一个流Stream 
2、中间操作 
3、终止操作

代码见category.jdk.JDK18.LambdaTest.test4

中间操作：API

多个中间操作可以连接起来形成一个流水线，除非流水想上出发终止操作。否则中间操作不会执行任何的处理，而在终止操作时一次性全部处理，成为“惰性求值”。

筛选和切片

代码见category.jdk.JDK18.LambdaTest.test5

映射 

接受Lambda，将元素转换成其他形式或提取信息，接受一个函数作为参数， 该函数会被应用到每个元素上，并将其映射成一个新的元素。

代码见:
    category.jdk.JDK18.StreamTest3.test2,
    category.jdk.JDK18.StreamTest3.test3,
    category.jdk.JDK18.StreamTest4.test1


Stream的终止操作 
- allMatch – 检查是否匹配所有元素 
- anyMatch – 检查是否至少匹配一个元素 
- noneMatch – 检查是否没有匹配所有元素 
- findFirst – 返回第一个元素 
- count – 返回流中元素的总个数 
- max – 返回流中最大值 
- min – 返回流中最小值

代码见:category.jdk.JDK18.StreamTest5.test1

终止操作：归约reduce

map和reduce的连接通常称为map-reduce模式，因google用它进行网络搜索而出名

代码见:category.jdk.JDK18.StreamTest6.test1。

终止操作：收集Collect（很强大） 
将流转换成其他格式，接受一个Collector接口的实现，用于给Stream中元素做汇总的操作。

Collector接口中方法的实现决定了如何对流进行收集操作（如收集到List、Set、Map）中，Collectors实用类提供了很多静态方法，可以方便的创建常用收集器实例，具体方法见API

代码见:
      category.jdk.JDK18.StreamTest8.test1,
      category.jdk.JDK18.StreamTest8.test2,
      category.jdk.JDK18.StreamTest8.test3,
      category.jdk.JDK18.StreamTest8.test4。

###5、并行流
    
Fork/Join框架： 
在必要的情况下，将一个大任务进行必要的拆分Fork成若干个小任务，再将小任务的运算结果进行Join汇总。

Fork/Join框架和传统线程池的区别： 
采用“工作窃取”模式（Working-stealing），即当执行新的任务时它可以将其拆分分成更小的任务执行，并将小任务加到线程队列中，然后再从一个随机线程的队列中偷一个并把它放在自己的队列中。

相对于一般的线程池实现，fork/join框架的优势体现在对其中包含的任务的处理方式上，如果一个线程正在执行的任务由于某些原因无法继续运行，那么该线程会处于等待状态，而在fork/join框架实现中，如果某个子问题由于等待另外一个子问题的完成而无法继续运行，那么处理该子问题的线程会主动寻找其他尚未运行的子问题来执行，这种方式减少了线程等待的时间，提高了性能。

并行流就是把一个内容分成多个数据块，并用不同的线程分别处理每个数据块的流。 
Java 8 中将并行进行了优化，我们可以很容易的对数据进行并行操作。Stream API 可以声明性地通过parallel() 与sequential() 在并行流与顺序流之间进行切换。
    
代码见:
      category.jdk.JDK18.StreamTest9.test1,
      category.jdk.JDK18.StreamTest9.test2,
      category.jdk.JDK18.StreamTest9.test3.    

###6、Optional类
   
Optional<T> 类(java.util.Optional) 是一个容器类，代表一个值存在或不存在，原来用null 表示一个值不存在，现在Optional 可以更好的表达这个概念。并且可以避免空指针异常。

常用方法： 
- Optional.of(T t) : 创建一个Optional 实例 
- Optional.empty() : 创建一个空的Optional 实例 
- Optional.ofNullable(T t):若t 不为null,创建Optional 实例,否则创建空实例 
- isPresent() : 判断是否包含值 
- orElse(T t) : 如果调用对象包含值，返回该值，否则返回t 
- orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回s 获取的值 
- map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty() 
- flatMap(Function mapper):与map 类似，要求返回值必须是Optional

代码见:
      category.jdk.JDK18.OptinalTest1.test1;   
   
---------------------
本文来自 op134972 的CSDN 博客 ，全文地址请点击：
StreamTest3