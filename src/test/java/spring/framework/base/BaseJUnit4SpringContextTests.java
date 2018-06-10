package spring.framework.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Lenovo on 2015/7/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public abstract class BaseJUnit4SpringContextTests extends AbstractJUnit4SpringContextTests {
    /**
     * 打印list<Map>
     *
     * @param list
     */
    protected void print(List<Map> list) {
        for (int i = 0; i < list.size(); i++) {
            Map map = list.get(i);
            Set keys = map.keySet();
            System.out.print("第" + i + "行 ");
            for (Object key : keys) {
                if (key instanceof String) {
                    System.out.print(key + ":" + map.get(key) + ";");
                }
            }
            System.out.println();
        }
    }

    /**
     * 打印map
     *
     * @param map
     */
    protected void print(Map map) {
        Set keys = map.keySet();
        for (Object key : keys) {
            if (key instanceof String) {
                System.out.print(key + ":" + map.get(key) + ";");
            }
        }
        System.out.println();
    }
}
