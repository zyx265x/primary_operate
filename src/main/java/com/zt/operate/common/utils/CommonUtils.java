package com.zt.operate.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonUtils {
    /**
     * 从list中随机抽取元素
     *
     * @param list
     * @param n
     * @return void
     * @throws
     * @Title: createRandomList
     * @Description: TODO
     */
    public static List getRandomList(List list, int n) {
        // TODO Auto-generated method stub
        Map map = new HashMap();
        List listNew = new ArrayList();
        if(list.size()<n){
            n = list.size();
        }
        while (map.size() < n) {
            int random = (int) (Math.random() * list.size());
            if (!map.containsKey(random)) {
                map.put(random, "");
                listNew.add(list.get(random));
            }
        }
        return listNew;
    }
}
