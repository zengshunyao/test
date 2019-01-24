package cn.ccsgroup.ccsframework.common.cache;

import java.util.HashMap;
import java.util.Iterator;

/**
 * LFU缓存实现
 * <p>
 * Created by shunyao.zeng on 9/25/14.
 */
public class LFUCache<K, V> extends AbstractCacheMap<K, V> {

    /**
     * 构造方法
     *
     * @param cacheSize
     * @param defaultExpire
     */
    public LFUCache(int cacheSize, long defaultExpire) {
        super(cacheSize, defaultExpire);
        cacheMap = new HashMap<K, CacheObject<K, V>>(cacheSize + 1);
    }

    /**
     * 实现删除过期对象 和 删除访问次数最少的对象
     */
    @Override
    protected int eliminateCache() {
        final Iterator<CacheObject<K, V>> iterator = cacheMap.values().iterator();
        //删除多少对象
        int removedCount = 0;
        long minAccessCount = Long.MAX_VALUE;
        while (iterator.hasNext()) {
            final CacheObject<K, V> cacheObject = iterator.next();

            if (cacheObject.isExpired()) {
                iterator.remove();
                removedCount++;
                continue;
            } else {
                //记录下最少的访问次数
                minAccessCount = Math.min(cacheObject.accessCount, minAccessCount);
            }
        }

        if (removedCount > 0) {
            return removedCount;
        }

        if (minAccessCount != Long.MAX_VALUE) {
            final Iterator<CacheObject<K, V>> iterator1 = cacheMap.values().iterator();

            while (iterator1.hasNext()) {
                final CacheObject<K, V> cacheObject = iterator1.next();
                cacheObject.accessCount -= minAccessCount;
                if (cacheObject.accessCount <= 0) {
                    iterator1.remove();
                    removedCount++;
                }
            }
        }
        return removedCount;
    }
}
