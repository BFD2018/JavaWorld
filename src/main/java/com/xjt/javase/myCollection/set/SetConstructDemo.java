package com.xjt.javase.myCollection.set;

import java.util.HashMap;
import java.util.HashSet;

public class SetConstructDemo {
    public static void main(String[] args) {
        HashSet hashSet = new HashSet();
        hashSet.add("java");//到此位置，第 1 次 add 分析完毕.
        hashSet.add("php");//到此位置，第 2 次 add 分析完毕
        hashSet.add("java");
        System.out.println("set=" + hashSet);

        /*
        private transient HashMap<E,Object> map;

        private static final Object PRESENT = new Object();
        //1. 实例化
        public HashSet() {
            map = new HashMap<>();
        }

        //2. 执行 add()
        public boolean add(E e) {
            return map.put(e, PRESENT)==null;
        }

        //3. set的add方法 实际执行的是map的put 方法   //HashMap.java
        public V put(K key, V value) {
            return putVal(hash(key), key, value, false, true);
        }
            //对key 进行 hash运算
            static final int hash(Object key) {
                int h;
                return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
            }

            //执行 puVal （重点！！！）
            * Implements Map.put and related methods.
            *
            * @param hash hash for key
            * @param key the key
            * @param value the value to put
            * @param onlyIfAbsent if true, don't change existing value
            * @param evict if false, the table is in creation mode.
            * @return previous value, or null if none

            final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
            boolean evict) {
                HashMap.Node<K,V>[] tab; HashMap.Node<K,V> p; int n, i;
                if ((tab = table) == null || (n = tab.length) == 0)
                    n = (tab = resize()).length;
                if ((p = tab[i = (n - 1) & hash]) == null)
                    tab[i] = newNode(hash, key, value, null);
                else {
                    HashMap.Node<K,V> e; K k;
                    if (p.hash == hash &&
                            ((k = p.key) == key || (key != null && key.equals(k))))
                        e = p;
                    else if (p instanceof HashMap.TreeNode)
                        e = ((HashMap.TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
                    else {
                        for (int binCount = 0; ; ++binCount) {
                            if ((e = p.next) == null) {
                                p.next = newNode(hash, key, value, null);
                                if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                                    treeifyBin(tab, hash);
                                break;
                            }
                            if (e.hash == hash &&
                                    ((k = e.key) == key || (key != null && key.equals(k))))
                                break;
                            p = e;
                        }
                    }
                    if (e != null) { // existing mapping for key
                        V oldValue = e.value;
                        if (!onlyIfAbsent || oldValue == null)
                            e.value = value;
                        afterNodeAccess(e);
                        return oldValue;
                    }
                }
                ++modCount;
                if (++size > threshold)
                    resize();
                afterNodeInsertion(evict);
                return null;
            }
        * */
    }
}
