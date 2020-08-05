package leecode.other;

import org.omg.PortableInterceptor.INACTIVE;
import zuoshen.other.并查集_labuladong;

import java.util.Deque;
import java.util.LinkedList;

public class 被围绕的区域_130 {
    public static void solve(char[][] board) {
        if(board==null||board.length==0){
            return;
        }
        int m=board.length;
        int n=board[0].length;
        boolean[][] visit=new boolean[m][n];
        for (int i = 1; i <m-1 ; i++) {
            for (int j = 1; j <n-1 ; j++) {
                if(board[i][j]=='O'&&!dfs(board,i,j,visit)){//并且四边不是 O
                    board[i][j]='X';
                }
            }
        }
    }
    public static boolean dfs(char[][]board,int i,int j,boolean[][]visit){
        if(board[i][j]=='X'){
            return false;
        }
        if(board[i][j]=='O'&&bound(board,i,j)){
            return true;
        }
        visit[i][j]=true;
        int[][]dir=new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        for (int k = 0; k <4 ; k++) {
            int x=i+dir[k][0];
            int y=j+dir[k][1];
            if(bound1(board,x,y)&&!visit[x][y]&&board[x][y]=='O'){
                if(dfs(board,x,y,visit)){
                    /*visit[i][j]标志要不要还原为默认值的问题，这个题目也是对每个点都要进行dfs，不是说只对一个点dfs
                    在这个点结束dfs之后，如果visit标志没有还原为原来的false，则下一个点进行dfs的时候就不能走这个点了
                    会产生错误的结果，所以在结果返回之前要还原visit，注意：在这个visit标志设置之后所有的return之前都要
                    还原visit，不要漏掉循环里面这个return，上面的return时，visit标志还没有设置，所以不需要返回
                     */
                    visit[i][j]=false;
                    return true;
                }
            }
        }
        visit[i][j]=false;
        return false;
    }
    public static boolean bound(char[][]board,int i,int j){
        if(i==0||i==board.length-1||j==0||j==board[0].length-1){
            return true;
        }
        return false;
    }
    public static boolean bound1(char[][]board,int i,int j){
        if(i<0||i>board.length-1||j<0||j>board[0].length-1){
            return false;
        }
        return true;
    }


    //这个题目关键是边界上O相通的O不能够变成X，所以要先把这些O找出来变成b，再把符合条件的o变成x，最后b变成o
    int[][]dirs={{1,0},{-1,0},{0,1},{0,-1}};
    public void solvewithdfs(char[][]boards){
        int row=boards.length;
        int col=boards[0].length;
        for (int i = 0; i <col ; i++) {
            //第一行 找边界o连通的o
            if(boards[0][i]=='O'){
                dfsmethod(0,i,boards,row,col);//或者调用dfsmethod
            }
            //最后一行
            if(boards[i][0]=='O'){
                dfsmethod(row-1,i,boards,row,col);//或者调用dfsmethod
            }
        }
        for (int i = 0; i <row ; i++) {
            //第一列
            if(boards[i][0]=='O'){
                dfsmethod(i,0,boards,row,col);
            }
            //最后一列
            if(boards[i][col-1]=='O'){
                dfsmethod(i,col-1,boards,row,col);
            }
        }
        for (int i = 0; i <row ; i++) {
            for (int j = 0; j <col ; j++) {
                if(boards[i][j]=='O') {
                    boards[i][j]='X';
                }
                if(boards[i][j]=='B'){
                    boards[i][j]='O';
                }
            }
        }
    }
    public void dfsmethod(int i,int j,char[][]board,int row,int col){
        board[i][j]='B';
        for (int[] dir:dirs) {
            int next_x = i+dir[0];
            int next_y = j+dir[1];
            if(next_x<0||next_x>=row||next_y<0||next_y>=col||board[next_x][next_y]!='O'){
                continue;
            }
            dfsmethod(next_x,next_y,board,row,col);
        }
    }

    class point{
        int x;
        int y;
        point(int x,int y){
            this.x=x;
            this.y=y;
        }
    }

    public void bfsmethod(int i,int j,char[][]board,int row,int col){
        Deque<point>deque=new LinkedList<>();
        deque.offer(new point(i,j));
        while (!deque.isEmpty()){
            point cur=deque.poll();
            if(cur.x>=0&&cur.x<row&&cur.y>=0&&cur.y<col&&board[cur.x][cur.y]=='O'){
                board[cur.x][cur.y]='B';
                for (int[]dir:dirs) {
                    deque.offer(new point(cur.x+dir[0],cur.y+dir[1]));
                }
            }
        }
    }


    //使用并查集，见labuladong的pdf
    public void solvewithunion(char[][]boards){
        int m=boards.length;
        int n=boards[0].length;
        并查集_labuladong  union = new 并查集_labuladong(m*n+1);//多一个dummy节点
        int dummy = m*n;//[0,m*n]
        for (int i = 0; i <m ; i++) {
            if (boards[i][0] == 'O') {//首列 0
                union.union(dummy, i * n);
            }
            if (boards[i][n - 1] == 'O') {//末列 n-1
                union.union(dummy,i*n+n-1);
            }
        }
        for (int i = 0; i <n ; i++) {
            if(boards[0][i]=='0'){//首行
                union.union(i,dummy);
            }
            if(boards[m-1][i]=='0'){
                union.union(n*(m-1)+i,dummy);
            }
        }
        for (int i = 1; i <m-1 ; i++) {
            for (int j = 1; j <n-1 ; j++) {
                if(boards[i][j]=='0'){
                    for (int k = 0; k <4 ; k++) {//这里还要dfs
                        int x=i+dirs[k][0];
                        int y=j+dirs[k][1];
                        if(boards[x][y]=='O'){
                            union.union(x*n+y,i*n+j);//这里注意,i，j不需要减1，是乘以列，不是行
                        }
                    }
                }
            }
        }
        //从1开始
        for (int i = 1; i <m-1 ; i++) {
            for (int j = 1; j <n-1 ; j++) {
                if(!union.connected(dummy,i*n+j)){//需不需要判断是不是 'O' ？？？
                    boards[i][j]='X';
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][]board=new char[][]{
                {'O','O','O','O','X','X'},
                {'O','O','O','O','O','O'},
                {'O','X','O','X','O','O'},
                {'O','X','O','O','X','O'},
                {'O','X','O','X','O','O'},
                {'O','X','O','O','O','O'}
        };
        solve(board);
        for (int i = 0; i <board.length ; i++) {
            for (int j = 0; j <board[0].length ; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }
}
