//package ��������;

import java.util.LinkedList;  
/* 

�������� �������� 
����������������һ�����У����������Ԫ��������ģ��ǵ������ߵݼ� 

��Ŀ������һ������ΪN����������a(i),i=0,1,...,N-1�ʹ�����k. 

Ҫ��f(i) = max{a(i-k+1),a(i-k+2),..., a(i)},i = 0,1,...,N-1 

�������һ������������һ������Ϊk�Ĵ��������������ƶ������������������������ֵ 

�����  
@������ http://weibo.com/lirenchen 
http://weibo.com/1915548291/z3T4HbRRr  
����΢�������˻ظ�˵�� 
@����RickyYang��ά��һ����k��Ԫ�ش󶥶ѣ����ÿ���Ƴ��ѵ��ǶѶ�����ô�������滻�Ѷ������Զ��������� 
����Ƴ����ǶѶ�����ֻ�轫�����ͶѶ��Ƚϣ�������ڶѶ���С���滻�Ƴ���������������ѡ���Ļ�ʱ�临�Ӷ�nlogk,���n-k+logk (11��6�� 08:22) 

��Ӧ��Ҳ�ǿ��еġ��ҵĸտ�ʼ�뵽��Ҳ�������ѡ����������鷳���ǣ�������Ƴ����ǶѶ�����ֻ�轫�����ͶѶ��Ƚϣ�������ڶѶ���С���滻�Ƴ������� 
�����Ļ���Ҫ��¼�����ƶ����Ƴ����ڵ��Ǹ�����ͬʱ���滻�Ƴ���������Ҫ������ִ������ 

�������еĲο�˼·�� 
http://xuyemin520.is-programmer.com/posts/25964 

1.���ȿ�����Ԫ�أ�Ϊ�˱�֤���еĵݼ��ԣ������ڲ���Ԫ��v��ʱ��Ҫ����β��Ԫ�غ�v�Ƚϣ� 
�����β��Ԫ�ز�����v,��ɾ����β��Ԫ�أ�Ȼ��������µĶ�β��Ԫ����v�Ƚϣ�ֱ����β��Ԫ�ش���v,���ʱ�����ǲŽ�v���뵽��β 

2.��β��ɾ���ո��Ѿ�˵�ˣ���ô���׵�Ԫ��ʲôʱ��ɾ���أ� 
��������ֻ��Ҫ����i��ǰk-1��Ԫ���е����ֵ�����Ե����׵�Ԫ�ص��������±�С�� i-k+1��ʱ�� 
��˵�����׵�Ԫ�ض�����f(i)�Ѿ�û�������ˣ���Ϊ���Ѿ����ڴ������ˡ����Ե�index[����Ԫ��]<i-k+1ʱ�������� Ԫ��ɾ�� 

������Ĵ������棬ʵ�ֶ���ʱ����ֱ������LinkedList����Ϊ�ֹ�������ά��һ����������Ҫ����ͦ��ġ����ڰ��ص�����㷨��˼·��^_^ 

*/  
public class Main {  
 
   public static void main(String[] args) {  
       /* 
       int[] array = { 8, 7, 12, 5, 16, 9, 17, 2, 4, 6 }; 
       int k = 3; 
        */  
         
       int[] array = { 8 , 4, 515, 7, 3, 5, 2, 9 };  
       int k = 3;  
         
       int[] max = maxInWindow(array, k);  
       for (int i : max) {  
           System.out.println(i);  
       }  
   }  
 
   public static int[] maxInWindow(int[] array, int k) {  
       if (k <= 0 || array == null || array.length == 0) {  
           System.out.println("invalid input");  
           return new int[0];  
       }  
       int len = array.length;  
       int[] max = new int[len];  
       if (k == 1) {  
           System.arraycopy(array, 0, max, 0, len);    //����һ�ݣ���Ӱ��ԭ����  
       } else {  
           LinkedList<Item> queue = new LinkedList<Item>();  
           for (int i = 0; i < len; i++) {  
               Item curItem = new Item(array[i], i);  
               if (queue.isEmpty()) {  
                   queue.addLast(curItem);  
               } else {  
                   Item head = queue.getFirst();  
                   int headIndex = head.getIndex();  
                     
                   //�������Ԫ���Ѳ��ڴ����ڣ���ɾ��  
                   if (headIndex < (i - k + 1)) {     
                       queue.removeFirst();  
                   }  
                     
                   //����Ԫ��  
                   while (!queue.isEmpty()) {  
                       Item tail = queue.getLast();  
                       int tailValue = tail.getValue();  
                       if (tailValue < array[i]) {  
                           queue.removeLast();  
                           if (queue.isEmpty()) {  
                               queue.addLast(curItem);  
                               break;  
                           }  
                       } else if (tailValue > array[i]) {  
                           queue.addLast(curItem);  
                       } else {  
                           break;  
                       }  
                   }  
               }  
                 
               //ÿ�β����󣬶���Ԫ��Ϊ��ǰ���ڵ����ֵ  
               if (!queue.isEmpty()) {  
                   max[i] = queue.getFirst().getValue();  
               }  
           }  
       }  
       return max;  
   }  
 
}  
 
class Item {  
     
   //����Ԫ��ֵ  
   private int value;  
     
   //����Ԫ�ض�Ӧ���±�  
   private int index;  
 
   public Item(int value, int index) {  
       this.value = value;  
       this.index = index;  
   }  
 
   public int getValue() {  
       return value;  
   }  
 
   public int getIndex() {  
       return index;  
   }  
 
}