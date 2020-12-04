package com.hdzl.util;

/**
 * 项目名称:     zz-client-two
 * 类名称:       HashMapUtil
 * 类描述:       合理初始化hashMap初始容量
 * 创建人:       张喆 zhangzhe@biconjs.com
 * 创建时间:     2020/12/2 14:48
 * 版本:         1.0
 */
public class HashMapUtil {
/*
    public static <K, V> HashMap<K, V> getHashMap(int expectedSize) {
        return new HashMap(capacity(expectedSize));
    }

    static int capacity(int expectedSize) {
        if (expectedSize < 3) {
            CollectPreconditions.checkNonnegative(expectedSize, "expectedSize");
            return expectedSize + 1;
        } else {
            return expectedSize < 1073741824 ? (int)((float)expectedSize / 0.75F
                    + 1.0F) : 2147483647;
        }
    }*/
}
