package com.louis.resume.util;

import java.util.Collection;

/**
 * @author liuh
 * @since 2023/3/26 12:20
 */
public class CollectionUtils {
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}
