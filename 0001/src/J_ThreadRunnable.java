// ////////////////////////////////////////////////////////
// 
// J_ThreadRunnable.java
// 
// ������: Ӻ����
// ////////////////////////////////////////////////////////
// ���:
//     ͨ���ӿ�Runnable�����̵߳����̡�
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
// ��Ȩ:
// ʹ�ñ����ӣ���ע������:
//     Ӻ����. Java ������ƽ̳�. ����: �廪��ѧ������, 2007.
// ��Ϸ�ʹ�����̣�����;Ӧ���Ϸ�������Ҳ�Ӧ���κ�������κ�
// �˺�����ʧ��ͬʱ��ע��̲����߼�������û�ж����������κγ�
// ŵ�뱣֤��
// �������õķ�������������:
// ������������̲ĵ�һ������(�򱾳����������̲ĵ������޸�)
//     Ӻ����. Java ������ƽ̳�. ����: �廪��ѧ������, 2007.
//
// Ӻ��������д�����µĽ̲ĺͽ̲�:
//      [1] Ӻ����. Java �������. ����: �廪��ѧ������, 2004.
//      [2] Ӻ����. Java�������ϰ�⼯(���ο���). 
//          ����: �廪��ѧ������, 2006.
//
// ////////////////////////////////////////////////////////


public class J_ThreadRunnable implements Runnable
{
    private int m_threadID;

    public J_ThreadRunnable(int i)
    {
        m_threadID=i;
        System.out.println("�����߳�: " + i );
    } // J_ThreadRunnable���췽������
    
    public void run( )
    {
        for(int i=0; i<3; i++)
        {
            System.out.println("�����߳�: " + m_threadID);
            try
            {
                Thread.sleep((int)(Math.random( ) * 1000));
            }
            catch ( InterruptedException e )
            {
                System.err.println("�쳣InterruptedException: " + e);
                e.printStackTrace( );
            } // try-catch�ṹ����
        } // forѭ������
    } // ����run����

    public static void main( String args[ ] )
    {
        Thread t1= new Thread(new J_ThreadRunnable(1));
        t1.start( );
        Thread t2= new Thread(new J_ThreadRunnable(2));
        t2.start( );
        System.out.println("����main����");
    } // ����main����
} // ��J_ThreadRunnable����
