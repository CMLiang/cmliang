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
            dSocket.receive(inPacket); // 接收数据报
            cAddr = inPacket.getAddress( );
            cPort = inPacket.getPort( );
            s= new String(inPacket.getData( ), 0, inPacket.getLength( ));
            System.out.println("接收到客户端信息: " + s);
            System.out.println("客户端主机名为: " + cAddr.getHostName( ));
            System.out.println("客户端端口为: " + cPort);
            do
            {
            	inPacket = new DatagramPacket(inBuffer, inBuffer.length);
            	dSocket.receive(inPacket); // 接收数据报
            	input=new String(inPacket.getData( ), 0, inPacket.getLength( ));
            	System.out.println("客户端: " + input);
                	
                output=in.next();
                outBuffer=output.getBytes();
                outPacket = new DatagramPacket(outBuffer, outBuffer.length,
                		cAddr, cPort);
                dSocket.send(outPacket); // 发送数据报
            } while(true);// do-while循环结束
        }
        catch (Exception e)
        { 
            System.err.println("发生异常:" + e);
            e.printStackTrace( );
        } // try-catch结构结束 
    } // 方法main结束
} // 类J_UdpServer结束
