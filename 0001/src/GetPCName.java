import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;



public class GetPCName
{
    public static void main (String[] args)
    {
    	Scanner in = new Scanner(System.in);
    	int num=255;
        try
        {
        	do{
        		String Ip=in.next();
        		InetAddress address1 = InetAddress.getByName(Ip);
        		//输出主机名
        		System.out.println(address1.getHostName( ));
        		num--;
        	}while(num>0);
        }
        catch (UnknownHostException e)
        {
            //显示错误信息
            System.err.println(e);
        }
    }
}
