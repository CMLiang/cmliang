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
            String s = "请求连接";
            byte[ ] outBuffer= s.getBytes( );
            DatagramPacket outPacket= new DatagramPacket(
                outBuffer, outBuffer.length, sAddr, 8000);
            dSocket.send(outPacket); // 发送数据报

            do
            {
            	output=in.next();
                outBuffer=output.getBytes();
                outPacket = new DatagramPacket(outBuffer, outBuffer.length,
                		sAddr, cPort);
                dSocket.send(outPacket); // 发送数据报
            	
                inPacket = new DatagramPacket(inBuffer, inBuffer.length);
            	dSocket.receive(inPacket); // 接收数据报
            	input=new String(inPacket.getData( ), 0, inPacket.getLength( ));
            	System.out.println("服务器: " + input);
            }while(true);
        }
        catch (Exception e)
        { 
            System.err.println("发生异常:" + e);
            e.printStackTrace( );
        } // try-catch结构结束
    } // 方法main结束
} // 类J_UdpClient结束
