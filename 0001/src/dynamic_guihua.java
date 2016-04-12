
public class dynamic_guihua {

	public static int cut_rod(int p[],int n){
		if(n==0)
			return 0;
		int q=-1;
		for(int i=0;i<n;i++){
			q=Math.max(q, p[i]+cut_rod(p,n-i-1));
		}
		return q;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startTime=System.currentTimeMillis();   //获取开始时间
		int [] a={
				1,5,8,9,10,17,17,20,24,30,
				34,36,37,39,41,45,48,51,53,54,
				55,57,59,60,63,64,67,69,73,75
		};
		System.out.println(cut_rod(a, 30));
		long endTime=System.currentTimeMillis(); //获取结束时间
		System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
	}

}
