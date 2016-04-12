// ////////////////////////////////////////////////////////
// 
// J_ChatServer.java
// 
// ������: Ӻ����
// ////////////////////////////////////////////////////////
// ���:
//     ����TCP���������̡����������˳��򲿷֡�
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

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class J_ChatServer extends JFrame
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ObjectInputStream m_input;   // ������
    private ObjectOutputStream m_output; // �����
    private JTextField m_enter;  // ��������
    private JTextArea m_display; // ��ʾ����
    private int m_clientNumber = 0; // ���ӵĿͻ���

    public J_ChatServer( ) // ��ͼ�ν�����������
    {
        super("��������������");
        Container c = getContentPane( );
        m_enter = new JTextField( );
        m_enter.setEnabled( false );
        m_enter.addActionListener(new ActionListener( )
            {        
                public void actionPerformed( ActionEvent event )
                { // ��ͻ��˷�������
                    try
                    {
                        String s = event.getActionCommand( );
                        m_output.writeObject( s );
                        m_output.flush( );
                        mb_displayAppend( "��������: " + s );
                        m_enter.setText( "" ); // ������������ԭ������
                    }
                    catch (Exception e)
                    {
                        System.err.println("�����쳣:" + e);
                        e.printStackTrace( );
                    } // try-catch�ṹ����
                } // ����actionPerformed����
            } // ʵ�ֽӿ�ActionListener���ڲ������
        ); // addActionListener�������ý���
        c.add( m_enter, BorderLayout.NORTH );
        m_display = new JTextArea( );
        c.add( new JScrollPane( m_display ), BorderLayout.CENTER );
    } // J_ChatServer���췽������

    public void mb_displayAppend( String s )
    {
        m_display.append( s + "\n" );
        m_display.setCaretPosition( m_display.getText( ).length( ) );
        m_enter.requestFocusInWindow( ); // ת�����뽹�㵽��������
    } // ����mb_displayAppend����

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
        if (m.equalsIgnoreCase("����"))
            return(true);
        return(false);
    } // ����mb_isEndSession����

    public void mb_run( )
    {
        try
        {
            ServerSocket server = new ServerSocket(5000);
            String m; // ���Կͻ��˵���Ϣ
            while (true)
            {
                m_clientNumber++;
                mb_displayAppend("�ȴ�����[" + m_clientNumber + "]");
                Socket s = server.accept( );
                mb_displayAppend("���յ��ͻ�������[" + m_clientNumber + "]");
                m_output = new ObjectOutputStream( s.getOutputStream( ) );
                m_input = new ObjectInputStream( s.getInputStream( ) );
                m_output.writeObject("���ӳɹ�");
                m_output.flush( );
                m_enter.setEnabled( true );
                do
                {
                    m = (String) m_input.readObject( );
                    mb_displayAppend("�ͻ���: " + m);
                } while(!mb_isEndSession( m ));// do-whileѭ������ 
                m_output.writeObject("q"); // ֪ͨ�ͻ����˳�����
                m_output.flush( );
                m_enter.setEnabled( false );
                m_output.close( );
                m_input.close( );
                s.close( );
                mb_displayAppend("����[" + m_clientNumber + "]����");
                server.close();
            } // whileѭ������
        }
        catch (Exception e)
        { 
            System.err.println("�����쳣:" + e);
            e.printStackTrace( );
            mb_displayAppend("����[" + m_clientNumber + "]�����쳣");
        } // try-catch�ṹ���� 
    } // ����mb_run����

    public static void main(String args[ ])
    {
        J_ChatServer app = new J_ChatServer( );

        app.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        app.setSize(350, 150);
        app.setVisible(true);
        app.mb_run( );
    } // ����main����
} // ��J_ChatServer����
