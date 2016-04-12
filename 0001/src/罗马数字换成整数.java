import java.util.Scanner; 
public class 罗马数字换成整数 {
	  public static void main(String []args){
			Scanner in=new Scanner(System.in);       
	        String a1=in.next();
	        char []a=a1.toCharArray();//字符串转换成字符数组
	        int len=a.length;
	        //int str=strlen(a);
	        int n=0;
	        for(int i=0;i<len;i++){
	        	switch(a[i]){
	        	case'M':n+=1000;break;
	        	case'D':n+=500;break;  
	        	case'C':if(a[i+1]=='D'||a[i+1]=='M')
	        		n-=100;
	        	else
	        		n+=100;
	        		break;
	        	case'L':n+=50;break;
	        	case'X':if(a[i+1]=='L'||a[i+1]=='C')
	        		n-=10;
	        	else
	        		n+=10;
	        		break;
	        	case'V':n+=5;break;
	        	case'I':if(a[i+1]=='V'||a[i+1]=='X')
	        				n--;
	        			else
	        				n++;
	        		break;
	        		
	        	}	        	
	        }
	        System.out.println(n);
	        in.close();
}

}
