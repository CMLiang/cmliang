import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class J_UdpClient
{
    public static void main(String args[ ])
    {
    	@SuppressWarnings("resource")
		Scanner in=new Scanner(System.in);
        DatagramPacket inPacket;
        InetAddress    sAddr;
        byte[ ]         inBuffer= new byte[100];
        String   input,output;
        int cPort;

        try
        {
            @SuppressWarnings("resource")
			DatagramSocket dSocket = new DatagramSocket( );
            if ( args.length == 0 )
                sAddr = InetAddress.getByName("127.0.0.1");
            else 
            	sAddr = InetAddress.getByName(args[0]);
            cPort=8000;
            String s = "��������";
            byte[ ] outBuffer= s.getBytes( );
            DatagramPacket outPacket= new DatagramPacket(
                outBuffer, outBuffer.length, sAddr, 8000);
            dSocket.send(outPacket); // �������ݱ�

            do
            {
            	output=in.next();
                outBuffer=output.getBytes();
                outPacket = new DatagramPacket(outBuffer, outBuffer.length,
                		sAddr, cPort);
                dSocket.send(outPacket); // �������ݱ�
            	
                inPacket = new DatagramPacket(inBuffer, inBuffer.length);
            	dSocket.receive(inPacket); // �������ݱ�
            	input=new String(inPacket.getData( ), 0, inPacket.getLength( ));
            	System.out.println("������: " + input);
            }while(true);
        }
        catch (Exception e)
        { 
            System.err.println("�����쳣:" + e);
            e.printStackTrace( );
        } // try-catch�ṹ����
    } // ����main����
} // ��J_UdpClient����
