package leecode.other;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class 钥匙和房间_841 {
    //孤岛问题
    //dfs解决
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[]visit=new boolean[rooms.size()];
//        visit[0]=true;
        dfs(rooms,visit,0);
        for (int i = 0; i <visit.length ; i++) {
            if(!visit[i]){
                return false;
            }
        }
        return true;
    }
    private void dfs(List<List<Integer>> rooms,boolean[]visit,int index){
        if(visit[index]){//这里先判断就不能让 visit[0]=true;
            return;
        }
        visit[index]=true;
        for(Integer room:rooms.get(index)){
            //如果在这里加了判断，就可以,去掉上面那个，就可以 visit[0]=true
            dfs(rooms,visit,room);
        }
    }
    //bfs解决
    public boolean canVisitAllRoomsbfs(List<List<Integer>> rooms) {
        Queue<Integer>queue=new ArrayDeque<>();
        boolean[]visit=new boolean[rooms.size()];
        visit[0]=true;
        queue.add(0);
        while (!queue.isEmpty()){
            int cur=queue.poll();
            visit[cur]=true;
            for(Integer room:rooms.get(cur)){
                if(!visit[room]){
                    queue.add(room);
                }
            }
        }
        for (int i = 0; i <visit.length ; i++) {
            if(!visit[i]){
                return false;
            }
        }
        return true;
    }

}
