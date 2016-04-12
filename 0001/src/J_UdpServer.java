import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class J_UdpServer
{
    public static void main(String args[ ])
    {
    	@SuppressWarnings("resource")
		Scanner in=new Scanner(System.in);
        DatagramSocket dSocket;
        DatagramPacket inPacket;
        DatagramPacket outPacket;
        InetAddress    cAddr;
        int            cPort;
        byte[ ]         inBuffer= new byte[100];
        byte[ ]         outBuffer;
        String         s,input,output;

        try
        {
            dSocket = new DatagramSocket(8000);
            inPacket = new DatagramPacket(inBuffer, inBuffer.length);
            dSocket.receive(inPacket); // �������ݱ�
            cAddr = inPacket.getAddress( );
            cPort = inPacket.getPort( );
            s= new String(inPacket.getData( ), 0, inPacket.getLength( ));
            System.out.println("���յ��ͻ�����Ϣ: " + s);
            System.out.println("�ͻ���������Ϊ: " + cAddr.getHostName( ));
            System.out.println("�ͻ��˶˿�Ϊ: " + cPort);
            do
            {
            	inPacket = new DatagramPacket(inBuffer, inBuffer.length);
            	dSocket.receive(inPacket); // �������ݱ�
            	input=new String(inPacket.getData( ), 0, inPacket.getLength( ));
            	System.out.println("�ͻ���: " + input);
                	
                output=in.next();
                outBuffer=output.getBytes();
                outPacket = new DatagramPacket(outBuffer, outBuffer.length,
                		cAddr, cPort);
                dSocket.send(outPacket); // �������ݱ�
            } while(true);// do-whileѭ������
        }
        catch (Exception e)
        { 
            System.err.println("�����쳣:" + e);
            e.printStackTrace( );
        } // try-catch�ṹ���� 
    } // ����main����
} // ��J_UdpServer����
