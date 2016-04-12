import java.util.Scanner;  
  
  
public class tB33 {  
    public static void main(String[] args) {  
    	tB33 mke=new tB33();  
    	Scanner in=new Scanner(System.in);
    	String str=new String(in.nextLine());
		String[] arr=str.split(",");
		int ArrLen=arr.length;
		int k=in.nextInt();
		int []a=new int[ArrLen];
		for(int i=0;i<ArrLen;i++){
			a[i]=Integer.parseInt(arr[i]);
		}  
        mke.findKMin(a,k);  
        in.close();
    }    
    public void findKMin(int[] a,int k){  
        int[] heap=a; 
        int rootIndex=k/2-1;  
        while(rootIndex>=0){  
            reheap(heap,rootIndex,k-1);  
            rootIndex--;  
        }  
        for(int i=k,len=heap.length;i<len;i++){  
            if(heap[i]<heap[0]){  
                heap[0]=heap[i];  
                reheap(heap,0,k-1);    
            }  
        }  
        System.out.print("K个最小数为：");  
        for(int i=0;i<k;i++){  
            System.out.print(heap[i]+",");  
        }  
    }  

    public void reheap(int[] heap,int rootIndex,int lastIndex){  
        int orphan=heap[rootIndex];  
        boolean done=false;  
        int leftIndex=rootIndex*2+1;  
        while(!done&&leftIndex<=lastIndex){  
            int largerIndex=leftIndex;  
            if(leftIndex+1<=lastIndex){  
                int rightIndex=leftIndex+1;  
                if(heap[rightIndex]>heap[leftIndex]){  
                    largerIndex=rightIndex;  
                }  
            }  
            if(orphan<heap[largerIndex]){  
                heap[rootIndex]=heap[largerIndex];  
                rootIndex=largerIndex;  
                leftIndex=rootIndex*2+1;  
            }else{  
                done=true;  
            }  
        }  
        heap[rootIndex]=orphan;  
          
    }  
    public void swap(int[] a,int i,int j){  
        int temp=a[i];  
        a[i]=a[j];  
        a[j]=temp;  
    }  
}  
