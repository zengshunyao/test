package com.funi;

import java.util.HashMap;

public class MapDemo {

    public static void main(String[] args) throws Exception {
        //map内的数组容量大于等于64 且 链表数量大于8才会进行红黑树转换
        HashMap map = new HashMap(64);
        for (int i = 0; i <= 8; i++) {
            map.put(new HashCodeOneObj(i), i);
        }
        System.out.println(map);
    }

    private static class HashCodeOneObj implements Comparable<HashCodeOneObj> {
        private int val;

        public HashCodeOneObj(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        @Override
        public int hashCode() {
            //让所有数据都存入一个桶
            return 1;
        }

        @Override
        public int compareTo(HashCodeOneObj o) {
            if (null == o) {
                return -1;
            }
            return Integer.compare(this.getVal(), o.getVal());
        }
    }
}
