package ngx_test;

import java.util.Scanner;

public class ngx_1 {
		public static int StrtoInt(String str){
			char[]ch=new char[100];
			boolean i,fh=true;
			int toInt=0;
			int max=0;
			i=str.equals("");
			//System.out.println(i);
			ch=str.toCharArray();
			if(!i){
				for(int len=0;len<ch.length;len++){
					if(ch[0]=='+')
						fh=true;
					else if(ch[0]=='-')
						fh=false;
					if(ch[len]>='0'&&ch[len]<='9'){
						max=len;
						toInt=(toInt+((int)ch[len])-48)*10;
						//System.out.println(toInt);
						if(len==(ch.length-1))
							toInt=toInt/10;
					}				
				}
				if(max<ch.length-2)
					if(ch[max+1]<='0'||ch[max+1]>='9')
						toInt=toInt/10;
			}
			if(fh)
				return toInt;
			else
				return (-1)*toInt;
		}
		public static void main(String[] args) {
			Scanner in=new Scanner(System.in);
			String str=in.nextLine();
			
			
			System.out.println(StrtoInt(str));
			in.close();
		}
}
