import java.util.HashMap;
 
class Addshudu {
    public int what_jb(double wnf) {// ����WNFֵ�󼶱�
        if (wnf <= 0.19) {
            return 0;
        } else if (wnf <= 0.23) {// 0.19-0.23��
            return 1;
        } else if (wnf <= 0.35) {// 0.25-0.35�е�
            return 2;
        } else if (wnf <= 0.48) {// 0.35-0.48��
            return 3;
        } else {
            return 4;
        }
    }
 
    public int[][] updateshudu() {// ��һ���ڴ����Ѵ��ڵ��������и���
        int seedArray[][] = {// ԭʼ����
        		{ 9, 7, 8, 3, 1, 2, 6, 4, 5 }, { 3, 1, 2, 6, 4, 5, 9, 7, 8 },{ 6, 4, 5, 9, 7, 8, 3, 1, 2 }, 
        		{ 7, 8, 9, 1, 2, 3, 4, 5, 6 },{ 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 4, 5, 6, 7, 8, 9, 1, 2, 3 },
                { 8, 9, 7, 2, 3, 1, 5, 6, 4 }, { 2, 3, 1, 5, 6, 4, 8, 9, 7 },{ 5, 6, 4, 8, 9, 7, 2, 3, 1 } };
        int[] a = new int[9];
        int t;
        boolean w;
        // ���ɲ��ظ�����Ϊ9һά����
        for (int i = 0; i < a.length; i++) {
            while (a[i] == 0) {
                t = (int) (Math.random() * 9 + 1);
                w = true;
                for (int j = 0; j < a.length; j++) {
                    if (a[j] == t) {
                        w = false;
                        break;
                    }
                }
                if (w) {
                    a[i] = t;
                }
            }
        }
        // ���������滻����
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    if (seedArray[i][j] == a[k]) {
                        seedArray[i][j] = a[(k + 1) % 9];
                        break;
                    }
                }
            }
        }
        // ѭ�����Σ���ջ�ںʹ��ڵ����ݽ���
        int start_;
        for (int i = 0; i < 3; i++) {
            start_ = i * 3;
            int temp;
            // ���ڽ���
            int h1, h2;
            h1 = start_ + (int) (Math.random() * 3);
            h2 = start_ + (int) (Math.random() * 3);
 
            if (h1 != h2) {
                for (int j = 0; j < 9; j++) {
                    temp = seedArray[h1][j];
                    seedArray[h1][j] = seedArray[h2][j];
                    seedArray[h2][j] = temp;
                }
            }
            // ջ�ڽ���
            int l1, l2;
            l1 = start_ + (int) (Math.random() * 3);
            l2 = start_ + (int) (Math.random() * 3);
            if (l1 != l2) {
                for (int j = 0; j < 9; j++) {
                    temp = seedArray[j][l1];
                    seedArray[j][l1] = seedArray[j][l2];
                    seedArray[j][l2] = temp;
                }
            }
        }
        return seedArray;
    }
 
    public int[][] add(int jibie) {// ���뼶����������ɸü��������
        Slove sl = null;
        int answer=0;
        int[][] te;
        do {
            te = updateshudu();// ������������
            int ax, ay;
            double wnf;
            do {
                do {// ������������ڶ�
                    ax = (int) (Math.random() * 9);
                    ay = (int) (Math.random() * 9);
                } while (te[ax][ay] == 0);
                te[ax][ay] = 0;
                wnf = Wnf_(te);
            } while (what_jb(wnf) != jibie);// �����ڸü�������ڶ�
            sl = new Slove(te);
            sl.resolve();
            answer = sl.answers;
        } while (answer !=1);//ȷ������Ƿ�Ψһ
        return te;// �����ض��������������
    }
 
    public double Wnf_(int[][] sz) {// ��wnfֵ�����׳̶�ֵ��
 
        int C[] = new int[10];// ��ѡ����ȫ����Ԫ������ռ����[1:2�� 2:5��....]
        double W[] = new double[10];// ��Ȩ (��ѡ��)/��Ԫ����
        int empty_sum = 0;// �ո�����
 
        // �Һ�ѡ��
        for (int x = 0; x < sz.length; x++) {
            for (int y = 0; y < sz[x].length; y++) {
                if (sz[x][y] == 0) {// �ո�
                    empty_sum++;// �ո�����1
                    int startx, starty;
                    int tem[] = new int[10];
                    int a;
                    // ȡ�е�Ԫ��(3*3)����
                    if (x < 3) {
                        startx = 0;
                    } else if (x < 6) {
                        startx = 3;
                    } else {
                        startx = 6;
                    }
                    if (y < 3) {
                        starty = 0;
                    } else if (y < 6) {
                        starty = 3;
                    } else {
                        starty = 6;
                    }
                    for (int x2 = startx; x2 < startx + 3; x2++) {
                        for (int y2 = starty; y2 < startx + 3; y2++) {
                            a = sz[x2][y2];
                            tem[a] = a;
                        }
                    }
                    for (int x2 = 0; x2 < 9; x2++) {// ����
                        a = sz[x2][y];
                        tem[a] = a;
                    }
                    for (int y2 = 0; y2 < 9; y2++) {// ����
                        a = sz[x][y2];
                        tem[a] = a;
                    }
                    a = 0;// ���9����ѡ�� ����0����ѡ��
                    for (int i = 1; i < tem.length; i++) {// ͳ�Ƶ�ǰ��Ԫ��ĺ�ѡ��
                        if (tem[i] == 0) {
                            a++;
                        }
                    }
                    C[a]++;// ��ѡ���б� ��ѡ������ѡ������
                }
 
            }
        }
        int sum_ = 0;
        double avg = 0;
        for (int i = 1; i < C.length; i++) {
            sum_ += C[i];
            avg += (double) (C[i]) * i;
        }
        avg = avg / sum_;
        for (int i = 1; i < W.length; i++) {
            W[i] = i * avg;
        }
        double wnf = 0;
        for (int n = 1; n < W.length; n++) {
            wnf += (W[n] / W[9]) * C[n];
        }
        wnf = wnf / empty_sum;
        return wnf;
    }
 
}
 
class Slove {
    public int answers = 0;// �𰸸���
    private char[] te_ = null;
    public String sd_answer = ""; 
     
    public Slove(int[][] sz) {
        this.te_ = new char[81];
        int cc=0;
        for (int i = 0; i < sz.length; i++) {
            for (int j = 0; j < sz[i].length; j++) {
                te_[cc]= (sz[i][j]+"").charAt(0);
                cc++;
            }
        }
    }
     
    public void resolve() {
        resolve(te_);
    }
     
    public void resolve(char[] A) {// ���ݷ�������
        int i, j;
        for (i = 0; i < 81; i++) {
            if (A[i] != '0') {
                continue;
            }
            HashMap<String, String> h = new HashMap<String, String>();
            for (j = 0; j < 81; j++) {
                h.put(j / 9 == i / 9 || j % 9 == i % 9 || (j / 27 == i / 27)
                        && ((j % 9 / 3) == (i % 9 / 3)) ? "" + A[j] : "0", "1");
            }
            for (j = 1; j <= 9; j++) {
                if (h.get("" + j) == null) {
                    A[i] = (char) ('0' + j);
                    resolve(A);
                }
            }
            A[i] = '0';
            return;
        }
         
        answers +=1;//��������һ��
         
        for (i = 0; i < 81; i++) {
            sd_answer += A[i];
        }
        return;
    }
     
    public void Show_answer() {
        int a=0,b=0,c=0;
        for (int i = 0; i < this.answers; i++) {
            for (int j = i*81; j < i*81+81; j++) {
                a++;b++;c++;
                System.out.print(sd_answer.charAt(j));
                if(c==3){
                	System.out.print("|");
                	c=0;
                }
                if (a==9) {
                    System.out.println();
                    if(b==27){
                    	System.out.println("------------");
                    	b=0;
                    }
                    a=0;
                }
            }
            System.out.println();
        }
    }
     
}
public class ShuDu {
	public static void main(String[] args) {
		Addshudu a1=new Addshudu();
		Slove s1=new Slove(a1.add(a1.what_jb(a1.Wnf_(a1.updateshudu()))));
		s1.resolve();
		s1.Show_answer();
	}
}
