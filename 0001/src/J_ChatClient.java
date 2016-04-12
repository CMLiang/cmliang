// ////////////////////////////////////////////////////////
// 
// J_ChatClient.java
// 
// 开发者: 雍俊海
// ////////////////////////////////////////////////////////
// 简介:
//     基于TCP的聊天例程——客户端程序部分。
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

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class J_ChatClient extends JFrame
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ObjectInputStream m_input;   // 输入流
    private ObjectOutputStream m_output; // 输出流
    private JTextField m_enter;  // 输入区域
    private JTextArea m_display; // 显示区域

    public J_ChatClient( ) // 在图形界面中添加组件
    {
        super("聊天程序客户端");
        Container c = getContentPane( );
        m_enter = new JTextField( );
        m_enter.setEnabled( false );
        m_enter.addActionListener(new ActionListener( )
            {        
                public void actionPerformed( ActionEvent event )
                { // 向服务器端发送数据
                    try
                    {
                        String s = event.getActionCommand( );
                        m_output.writeObject( s );
                        m_output.flush( );
                        mb_displayAppend( "客户端: " + s );
                        m_enter.setText( "" ); // 清除输入区域的原有内容
                    }
                    catch (Exception e)
                    {
                        System.err.println("发生异常:" + e);
                        e.printStackTrace( );
                    } // try-catch结构结束
                } // 方法actionPerformed结束
            } // 实现接口ActionListener的内部类结束
        ); // addActionListener方法调用结束
        c.add( m_enter, BorderLayout.NORTH );
        m_display = new JTextArea( );
        c.add( new JScrollPane( m_display ), BorderLayout.CENTER );
    } // J_ChatClient构造方法结束

    public void mb_displayAppend( String s )
    {
        m_display.append( s + "\n" );
        m_display.setCaretPosition( m_display.getText( ).length( ) );
        m_enter.requestFocusInWindow( ); // 转移输入焦点到输入区域
    } // 方法mb_displayAppend结束

    public boolean mb_isEndSession( String m )
    {
        if (m.equalsIgnoreCase("q"))
            return(true);
        if (m.equalsIgnoreCase("quit"))
            return(true);
        if (m.equalsIgnoreCase("exit"))
            return(true);
        if (m.equalsIgnoreCase("end"))
            return(true);
        if (m.equalsIgnoreCase("结束"))
            return(true);
        return(false);
    } // 方法mb_isEndSession结束

    public void mb_run( String host, int port)
    {
        try
        {
            mb_displayAppend("尝试连接");
            Socket s = new Socket(host, port);
            String m; // 来自服务器端的消息
            m_output = new ObjectOutputStream( s.getOutputStream( ) );
            m_input = new ObjectInputStream( s.getInputStream( ) );
            m_enter.setEnabled( true );
            do
            {
                m = (String) m_input.readObject( );
                mb_displayAppend("服务器端: " + m);
            } while(!mb_isEndSession( m ));// do-while循环结束
            m_output.writeObject("q"); // 通知服务器端退出程序
            m_output.flush( );
            m_output.close( );
            m_input.close( );
            s.close( );
            System.exit( 0 );
        }
        catch (Exception e)
        { 
            System.err.println("发生异常:" + e);
            e.printStackTrace( );
            mb_displayAppend("发生异常");
        } // try-catch结构结束 
    } // 方法mb_run结束

    public static void main(String args[ ])
    {
        J_ChatClient app = new J_ChatClient( );
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(350, 150);
        app.setVisible(true);
        if ( args.length == 0 )
            app.mb_run(null, 5000);
        else app.mb_run(args[0], 5000);
    } // 方法main结束
} // 类J_ChatClient结束
