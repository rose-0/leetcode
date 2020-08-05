package 异常;

public class 执行顺序 {
    boolean testEx() throws Exception{
        boolean ret=true;
        try{
            ret=testEx1();
        }catch (Exception e){
            System.out.println("testEx,catch exception");
            ret=false;
            throw e;
        }finally {
            System.out.println("testEx finally; return true"+ret);
            return ret;
        }
    }
    boolean testEx1() throws Exception{
        boolean ret=true;
        try {
            ret=testEx2();
            if(!ret){//return前，先执行finally，finally里面有return。结果这个return也没有执行
                return false;
            }
            System.out.println("testEx1, at the end of try");
            return ret;
        }catch (Exception e){
            System.out.println("testEx1, catch exception");
            ret=false;
            throw e;
        }finally {
            System.out.println("testEx1, finally; return value="+ret);
            return ret;
        }
    }
    boolean testEx2()throws Exception{
        boolean ret=true;
        try {
            int b=12;
            int c;
            for (int i = 2; i >=-2 ; i--) {
                c=b/i;
                System.out.println("i="+i);
            }
            return true;
        }catch (Exception e){
            System.out.println("testEx2, catch exception");
            ret=false;
            System.out.println("---");
            throw e;
        }finally {
            System.out.println("testEx2,finally return value="+ret);
            return ret;
        }
    }

    public static void main(String[] args) {
        执行顺序 test=new 执行顺序();
        try {
            test.testEx();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
