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
        		//���������
        		System.out.println(address1.getHostName( ));
        		num--;
        	}while(num>0);
        }
        catch (UnknownHostException e)
        {
            //��ʾ������Ϣ
            System.err.println(e);
        }
    }
}
