package 函数表达式;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.BinaryOperator;

public class Lambda表达式 {
    public static void main(String[] args) {
        //Lambda 表达式一共有五种基本形式
        // 1. 匿名内部类
        Runnable noArguments1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        };
        // 1.函数表达式
        /*
         Lambda 表达式不包含参数,使用空括号 () 表示没有参数。
         该 Lambda 表达式 实现了 Runnable 接口,该接口也只有一个 run 方法,没有参数,
         且返回类型为 void。
         */
        Runnable noArguments = () -> System.out.println("Hello World");

        // 2. 匿名内部类
        ActionListener oneArgument1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
        // 2.函数表达式  Lambda 表达式包含且只包含一个参数,可省略参数的括号,
        ActionListener oneArgument = event -> System.out.println("button clicked");

        // 3.Lambda 表达式的主体不仅可以是一个表达式,而且也可以是一段代码块,
        // 使用大括号 ({})将代码块括起来，用以明确 Lambda表达式从何处开始、到哪里结束。
        Runnable multiStatement = () -> {
            System.out.print("Hello");
            System.out.println(" World");
        };

        //4.Lambda 表达式也可以表示包含多个参数的方法,
        //这行代码并不是将两个数字相加,而是创建了一个函数function,用来计算 两个数字相加的结果。
        // 变量 add 的类型是 BinaryOperator,它不是两个数字的和, 而是将两个数字相加的那行代码。
        BinaryOperator<Long> add = (x, y) -> x + y;

        //5.Lambda 表达式中的参数类型都是由编译器推断得出的。这当然不错, 但有时最好也可以
        // 显式声明参数类型,此时就需要使用小括号将参数括起来,多个参数的 情况也是如此。
        BinaryOperator<Long> addExplicit = (Long x, Long y) -> x + y;

        //什么是lamada
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello lambda");
            }
        });

        new Thread(
                () -> System.out.println("hello lambda")
        );

        //自定义函数式接口MyFunction   System.out::print是Lambda表达式。作为实参
        String text = "试试自定义函数好使不";
        printString(text, System.out::print);
    }

    //MyFunction是接口类型 用函数式接口作为参数，调用时传递Lambda表达式。
    private static void printString(String text, MyFunction myFunction) {
        myFunction.print(text);
    }
}
