import java.util.HashMap;
 
class Addshudu {
    public int what_jb(double wnf) {// 根据WNF值求级别
        if (wnf <= 0.19) {
            return 0;
        } else if (wnf <= 0.23) {// 0.19-0.23简单
            return 1;
        } else if (wnf <= 0.35) {// 0.25-0.35中等
            return 2;
        } else if (wnf <= 0.48) {// 0.35-0.48难
            return 3;
        } else {
            return 4;
        }
    }
 
    public int[][] updateshudu() {// 重一个内存里已存在的数独进行更新
        int seedArray[][] = {// 原始数独
        		{ 9, 7, 8, 3, 1, 2, 6, 4, 5 }, { 3, 1, 2, 6, 4, 5, 9, 7, 8 },{ 6, 4, 5, 9, 7, 8, 3, 1, 2 }, 
        		{ 7, 8, 9, 1, 2, 3, 4, 5, 6 },{ 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 4, 5, 6, 7, 8, 9, 1, 2, 3 },
                { 8, 9, 7, 2, 3, 1, 5, 6, 4 }, { 2, 3, 1, 5, 6, 4, 8, 9, 7 },{ 5, 6, 4, 8, 9, 7, 2, 3, 1 } };
        int[] a = new int[9];
        int t;
        boolean w;
        // 生成不重复长度为9一维数组
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
        // 在数独中替换数字
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
        // 循环三次，把栈内和带内的数据交换
        int start_;
        for (int i = 0; i < 3; i++) {
            start_ = i * 3;
            int temp;
            // 行内交换
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
            // 栈内交换
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
 
    public int[][] add(int jibie) {// 传入级别参数，生成该级别的数独
        Slove sl = null;
        int answer=0;
        int[][] te;
        do {
            te = updateshudu();// 更新数独终盘
            int ax, ay;
            double wnf;
            do {
                do {// 在数独内随机挖洞
                    ax = (int) (Math.random() * 9);
                    ay = (int) (Math.random() * 9);
                } while (te[ax][ay] == 0);
                te[ax][ay] = 0;
                wnf = Wnf_(te);
            } while (what_jb(wnf) != jibie);// 不属于该级别继续挖洞
            sl = new Slove(te);
            sl.resolve();
            answer = sl.answers;
        } while (answer !=1);//确定其解是否唯一
        return te;// 返回特定级别的数独初盘
    }
 
    public double Wnf_(int[][] sz) {// 求wnf值（难易程度值）
 
        int C[] = new int[10];// 候选数在全部单元格中所占个数[1:2个 2:5个....]
        double W[] = new double[10];// 加权 (候选数)/单元格数
        int empty_sum = 0;// 空格数量
 
        // 找候选数
        for (int x = 0; x < sz.length; x++) {
            for (int y = 0; y < sz[x].length; y++) {
                if (sz[x][y] == 0) {// 空格
                    empty_sum++;// 空格数加1
                    int startx, starty;
                    int tem[] = new int[10];
                    int a;
                    // 取中单元格(3*3)区域
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
                    for (int x2 = 0; x2 < 9; x2++) {// 横向
                        a = sz[x2][y];
                        tem[a] = a;
                    }
                    for (int y2 = 0; y2 < 9; y2++) {// 纵向
                        a = sz[x][y2];
                        tem[a] = a;
                    }
                    a = 0;// 最多9个候选数 最少0个候选数
                    for (int i = 1; i < tem.length; i++) {// 统计当前单元格的候选数
                        if (tem[i] == 0) {
                            a++;
                        }
                    }
                    C[a]++;// 候选数列表 候选数：候选数个数
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
    public int answers = 0;// 答案个数
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
     
    public void resolve(char[] A) {// 回溯法解数独
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
         
        answers +=1;//答案增加了一个
         
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
