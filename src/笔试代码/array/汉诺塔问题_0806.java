package 笔试代码.array;

import java.util.List;

public class 汉诺塔问题_0806 {
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        hanotadigui(A.size(),A,B,C);
    }
    public void hanotadigui(int n,List<Integer> A, List<Integer> B, List<Integer> C){
        if(n==1){
            move(A,C);
        }else {
            hanotadigui(n-1,A,C,B);
            move(A,C);
            hanotadigui(n-1,B,A,C);
        }
    }
    public void move(List<Integer>from,List<Integer>to){
        int pop=from.remove(from.size()-1);
        to.add(pop);
        return;
    }
}
