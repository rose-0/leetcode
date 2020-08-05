package 笔试代码.实战;
//实现bitmap
//https://zhuanlan.zhihu.com/p/94818952
public class Bitmap {
    private byte[] bitmap;
    private int length;
    public boolean get(int number){

        int site=number>>>3;//相当于除以8
        byte temp=bitmap[site];

        int i=number&7;//相当于%8

        //byte占一个字节，8位
        if(((temp>>>(7-i))&1)==0){//右移 7-i 位
            return false;
        }
        return true;

        /*
        //获取位置
        int site=number>>>3;//等价于 site=number/8
        //获取该字节
        byte temp=bitmap[site];

        //获取该字节的第几个
        int i=number&7;//等价于 i=number%8

        //将这个字节数右移(7-i)位（也就是把要查找的位移动到最右侧），然后与 0000 0001相与
        if(((temp>>>(7-i))&1)==0){
            return false;
        }
        return true;

         */
    }

    public void set(int number,boolean bool){

        int site=number>>>3;//相当于除以8
        System.out.print("number="+number+" site="+site+"  ");
        byte temp=bitmap[site];
        System.out.println("temp:"+temp+ "  ");

        int i=number&7;//相当于%8
        System.out.print("number="+number+"i="+i+"   ");
        //将0000 0001 左移(7-i)
        byte comp= (byte) (1<<(7-i));//1左移3位
        System.out.print("comp"+ comp+"   ");

        if(bool){
//            bitmap[site]=(byte)(comp|temp);
            bitmap[site]= (byte) (comp|temp);
            System.out.println(comp|temp);
        }else {
            comp=(byte)~comp;
            bitmap[site]=(byte)(comp&temp);
        }

        /*
        //获取位置
        int site=number>>>3;//等价于 site=number/8
        //获取该字节
        byte temp=bitmap[site];

        //获取该字节的第几个
        int i=number&7;//等价于 i=number%8
        //将0000 0001 左移(7-i)
        byte comp= (byte) (1<<(7-i));

        if(bool){//设置为1
            bitmap[site]= (byte) (comp|temp);//取或(0.. 1 0..)
        }else {//设置为0
            comp= (byte) ~comp;//取反
            bitmap[site]= (byte) (comp&temp);//相与(1.. 0 1..)
        }

         */
    }

    public void add(int index){
        set(index,true);
    }
    public void delete(int index){
        set(index,false);
    }

    public Bitmap (int length){
        this.length=length;
        bitmap=new byte[length>>>3];//有移三位，看int需要几个字节
    }

    public int getLength() {
        return length;
    }

    public static void main(String[] args){
        Bitmap bitmap=new Bitmap(100000);
        bitmap.add(100);
        System.out.println(bitmap.get(100));
        bitmap.delete(100);
        System.out.println(bitmap.get(100));
    }
}
