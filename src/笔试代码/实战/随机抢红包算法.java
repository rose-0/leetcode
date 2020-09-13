package 笔试代码.实战;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

// 解释 https://cloud.tencent.com/developer/article/1587563
// 代码 https://www.cnblogs.com/alimayun/p/12795698.html
public class 随机抢红包算法 {
    public static List<Integer> hongbao(int totalAmount,int totalNumber){
        List<Integer>list=new ArrayList<>();
        Set<Integer>set=new HashSet<>();
        while (set.size()<totalNumber-1){
            //随机的范围是 1到totalamount
            int random= ThreadLocalRandom.current().nextInt(1,totalAmount);
            set.add(random);
        }
        //使用set.toArray(new Integer[0])是为了保证转成数组后不用转型。因为不带Integer[0]的话，转过后是Object[]
        Integer[]amounts=set.toArray(new Integer[0]);
        Arrays.sort(amounts);
        list.add(amounts[0]);
        for (int i = 1; i <amounts.length ; i++) {
            list.add(amounts[i]-amounts[i-1]);
        }
        //不要忘记这个
        list.add(totalAmount-amounts[amounts.length-1]);
        return list;
    }
}
