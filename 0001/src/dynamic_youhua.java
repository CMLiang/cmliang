
public class dynamic_youhua {

	public static int memoized_cut_rod(int p[],int n){
		int []r=new int[n+1];
		for(int i=1;i<=n;i++){
			r[i]=-1;
		}
		return memoized_cut_rod_aux(p,n,r);
	}
	public static int memoized_cut_rod_aux(int p[],int n,int r[]){
		int q=-1;
		if(r[n]>=0)
			return r[n];
		if((n)==0){
			q=0;
		}else {
			for(int i=1;i<=n;i++){
				q=Math.max(q, p[i]+memoized_cut_rod_aux(p,n-i,r));
			}
		}
		r[n]=q;
		return q;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startTime=System.nanoTime();   //获取开始时间
		int [] a={
				0,1,5,8,9,10,17,17,20,24,30,
				34,36,37,39,41,45,48,51,53,54,
				55,57,59,60,63,64,67,69,73,75
		};
		System.out.println(memoized_cut_rod(a, 30));
		long endTime=System.nanoTime(); //获取结束时间
		System.out.println("程序运行时间： "+(endTime-startTime)+"ns");
	}

}
