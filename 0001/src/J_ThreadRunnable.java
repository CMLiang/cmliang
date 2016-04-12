// ////////////////////////////////////////////////////////
// 
// J_ThreadRunnable.java
// 
// 开发者: 雍俊海
// ////////////////////////////////////////////////////////
// 简介:
//     通过接口Runnable构造线程的例程。
// ////////////////////////////////////////////////////////
// Copyright:
//
// Using this example, please explicitly refer to the book:
//     Jun-Hai Yong. Textbook for Programming in Java. 
//     Beijing: Tsinghua University Press, 2007.
// The example should be used legally, beneficially and
// without any harm to anybody. Please note that the
// author and the publisher make no warranty of any kind
// on the examples provided.
// Citation examples:
// The program is an example in (or based on, or modified from)
//     Jun-Hai Yong. Textbook for Programming in Java. 
//     Beijing: Tsinghua University Press, 2007.
//
// Some other books by Jun-Hai Yong are:
//      [1] Jun-Hai Yong. Programming in Java. 
//          Beijing: Tsinghua University Press, 2004.
//      [2] Jun-Hai Yong. Exercises for Programming in Java.
//          Beijing: Tsinghua University Press, 2006.
//
// 版权:
// 使用本例子，请注明引用:
//     雍俊海. Java 程序设计教程. 北京: 清华大学出版社, 2007.
// 请合法使用例程，其用途应当合法有益而且不应对任何人造成任何
// 伤害或损失。同时请注意教材作者及出版社没有对例程做出任何承
// 诺与保证。
// 具体引用的方法及例子如下:
// 本程序是下面教材的一个例程(或本程序基于下面教材的例程修改)
//     雍俊海. Java 程序设计教程. 北京: 清华大学出版社, 2007.
//
// 雍俊海还编写过如下的教材和教参:
//      [1] 雍俊海. Java 程序设计. 北京: 清华大学出版社, 2004.
//      [2] 雍俊海. Java程序设计习题集(含参考答案). 
//          北京: 清华大学出版社, 2006.
//
// ////////////////////////////////////////////////////////


public class J_ThreadRunnable implements Runnable
{
    private int m_threadID;

    public J_ThreadRunnable(int i)
    {
        m_threadID=i;
        System.out.println("创建线程: " + i );
    } // J_ThreadRunnable构造方法结束
    
    public void run( )
    {
        for(int i=0; i<3; i++)
        {
            System.out.println("运行线程: " + m_threadID);
            try
            {
                Thread.sleep((int)(Math.random( ) * 1000));
            }
            catch ( InterruptedException e )
            {
                System.err.println("异常InterruptedException: " + e);
                e.printStackTrace( );
            } // try-catch结构结束
        } // for循环结束
    } // 方法run结束

    public static void main( String args[ ] )
    {
        Thread t1= new Thread(new J_ThreadRunnable(1));
        t1.start( );
        Thread t2= new Thread(new J_ThreadRunnable(2));
        t2.start( );
        System.out.println("方法main结束");
    } // 方法main结束
} // 类J_ThreadRunnable结束
