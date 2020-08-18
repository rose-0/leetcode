package 笔试代码.array;

public class 交换数字_1601 {
    public int[] swapNumbers(int[] numbers) {
        //使用加法
        numbers[0]=numbers[0]+numbers[1];
        numbers[1]=numbers[0]-numbers[1];
        numbers[0]=numbers[0]-numbers[1];
        return numbers;
    }
    public int[] swapNumbers2(int[] numbers) {
        //使用减法
        numbers[0]=numbers[0]-numbers[1];
        numbers[1]=numbers[0]+numbers[1];
        numbers[0]=numbers[1]-numbers[0];
        return numbers;
    }
    public int[] swapNumbers3(int[] numbers) {
        //异或
        numbers[0]=numbers[0]^numbers[1];
        numbers[1]=numbers[1]^numbers[0];
        numbers[0]=numbers[0]^numbers[1];
        return numbers;
    }
}
