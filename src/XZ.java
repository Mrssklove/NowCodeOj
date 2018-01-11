import java.math.BigInteger;
import java.util.*;

public class XZ {
    public static void main(String[] args)
    {
         Scanner in=new Scanner(System.in);
         while (in.hasNext())
         {
            int row,column,energy;
            int i,j;
            row=in.nextInt();
            column=in.nextInt();
            energy=in.nextInt();
            int[][] maze=new int[row][column];
            boolean[][] visited=new boolean[row][column];
            for(i=0;i<row;i++)
                for(j=0;j<column;j++)
                {
                    maze[i][j]=in.nextInt();
                    visited[i][j]=false;
                }
            int[] dx={1,-1,0,0};  //k=0 1 水平移动 消耗1体力 2 向上 消耗3体力 3不消耗体力
            int[] dy={0,0,1,-1};
            Queue<QingWa> q=new ArrayDeque<QingWa>();
            q.add(new QingWa(0,0,energy));
            while (!q.isEmpty())
            {
                QingWa temp=q.poll();
                int k,tempx,tempy,tempenergy;
                tempx=temp.x;
                tempy=temp.y;
                if(tempx==0&&tempy==column-1)
                {
                    System.out.println("xx");

                }
                visited[tempx][tempy]=true;
                tempenergy=temp.energy;
                for(k=0;k<4;k++)
                {
                    tempx=temp.x+dx[k];
                    tempy=temp.y+dy[k];
                    if(  tempx>=0&&tempx<=row-1&&tempy>=0&&tempy<=column-1&& maze[tempx][tempy]!=0&&visited[tempx][tempy]==false)
                    {
                       if(k==0||k==1)
                           q.add(new QingWa(tempx,tempy,temp.energy-1));
                       if(k==2)
                           q.add(new QingWa(tempx,tempy,temp.energy-3));
                       if(k==3)
                            q.add(new QingWa(tempx,tempy,temp.energy));
                    }
                }
            }






         }
    }
    //全组合
    public static void AllCombine(int[] a, int start, int end, List result)
    {
        if(start>=end)
        {
            if(result.size()==0)
            {
                System.out.println("xx");
                return;
            }
            for(int i=0;i<result.size();i++)
                System.out.print(result.get(i));
            System.out.println();
            return;
        }
        result.add(a[start]);
        AllCombine(a,start+1,end,result);
     //   if(result.size()>=1)
        result.remove(result.get(result.size()-1));
        while (start+1<end&&a[start+1]==a[start]) start++;
        AllCombine(a,start+1,end,result);
    }
    /*  幸运的袋子 带重复元素的全组合+DFS+剪枝  递归返回值要理解*/
    public static int LuckyBalls(int[] balls,int start,int sum,int mul)
    {
        System.out.println(sum+" "+mul);
        int count=0;
        for(int i=start;i<balls.length;i++)
        {
            sum+=balls[i];
            mul*=balls[i];
            if(sum>mul)
            count+=1+LuckyBalls(balls,i+1,sum,mul);
            else if(balls[i]==1)
            count+= LuckyBalls(balls,i+1,sum,mul);
            else
                break;
            //回溯
            sum-=balls[i];
            mul/=balls[i];
            while (i+1<balls.length&&balls[i+1]==balls[i]) i++;  //去重
        }
        return count;
    }
    /* 不要二*/


    /*  阶乘 末尾0的个数   10 由5跟 一个偶数构成  5区间内 必有偶数 所有只需知道5的个数即可*/
   public static void LastZero()
   {
       Scanner in=new Scanner(System.in);
       while (in.hasNext())
       {
           int n=in.nextInt();
           int temp=0;
           while (n!=0)
           {
               temp+=n/5;
               n/=5;
           }
           System.out.println(temp);
       }
   }

   /* 删除公共字符串 哈希统计出现 StringBuilder构成新的字符串*/
   public static void RemoveSameString()
   {
       Scanner in=new Scanner(System.in);
       while (in.hasNext())
       {
           String a=in.nextLine();
           String b=in.nextLine();
           StringBuilder sb=new StringBuilder();
           int i,j;
           int[] hashchar=new int[256];
           for(i=0;i<b.length();i++)
               hashchar[b.charAt(i)]=1;
           for(i=0;i<a.length();i++)
               if(hashchar[a.charAt(i)]!=1)
                   sb.append(a.charAt(i));
           System.out.println(sb.toString());

       }
   }

   /*  丢失的三个数  需要注意StringBuilder 不能直接转Int  需要先转String 再转数组 */
   public static void Findlost()
   {
       Scanner in=new Scanner(System.in);
       while (in.hasNext())
       {
           int[] hashint=new int[10001];
           String src=in.nextLine();
           String[] arr=src.split(" ");
           int i,j=0;
           int[] lose=new int[3];
           for(i=0;i<arr.length;i++)
               hashint[Integer.valueOf(arr[i])]=1;
           for(i=1;i<=10000;i++)
               if(hashint[i]==0)
                   lose[j++]=i;
           Arrays.sort(lose);
           StringBuilder sb=new StringBuilder();
           for(i=0;i<3;i++)
               sb.append(String.valueOf(lose[i]));
           String ta=sb.toString();
           long newNum=Long.parseLong(ta);
           System.out.println(newNum%7);

       }
   }

   /* 电话号码分身  哈希统计次数 在找规律 含有Z的只有zero一个  含有X只有SIX 含有U的只有Four等等 依次减小哈希规模*/
   public static void NumberFenShen()
   {
       Scanner in=new Scanner(System.in);
       while (in.hasNext()) {
           int n = in.nextInt();

           int i = 0;
           for(i=0;i<n;i++) {
               int[] hashchar=new int[256];
               String src=in.next();
               for(int j=0;j<src.length();++j)
                   hashchar[src.charAt(j)]+=1;
               List<Integer> re=new ArrayList<Integer>();
               while (hashchar['T'] >= 1 && hashchar['W'] >= 1 && hashchar['O'] >= 1) {
                   hashchar['T'] -= 1;
                   hashchar['W'] -= 1;
                   hashchar['O'] -= 1;
                   re.add(4);
               }
               while (hashchar['Z'] >= 1 && hashchar['E'] >= 1 && hashchar['R'] >= 1 && hashchar['O'] >= 1) {
                   hashchar['Z'] -= 1;
                   hashchar['E'] -= 1;
                   hashchar['R'] -= 1;
                   hashchar['O'] -= 1;
                   re.add(2);
               }
               while (hashchar['S'] >= 1 && hashchar['I'] >= 1 && hashchar['X'] >= 1) {
                   hashchar['S'] -= 1;
                   hashchar['I'] -= 1;
                   hashchar['X'] -= 1;
                   re.add(8);
               }
               while (hashchar['E'] >= 1 && hashchar['I'] >= 1 && hashchar['G'] >= 1 && hashchar['H'] >= 1 && hashchar['T'] >= 1) {
                   hashchar['E'] -= 1;
                   hashchar['I'] -= 1;
                   hashchar['G'] -= 1;
                   hashchar['H'] -= 1;
                   hashchar['T'] -= 1;
                   re.add(0);
               }
               while (hashchar['T'] >= 1 && hashchar['H'] >= 1 && hashchar['R'] >= 1 && hashchar['E'] >= 2) {
                   hashchar['T'] -= 1;
                   hashchar['H'] -= 1;
                   hashchar['R'] -= 1;
                   hashchar['E'] -= 2;
                   re.add(5);
               }
               while (hashchar['F'] >= 1 && hashchar['O'] >= 1 && hashchar['U'] >= 1 && hashchar['R'] >= 1) {
                   hashchar['F'] -= 1;
                   hashchar['O'] -= 1;
                   hashchar['U'] -= 1;
                   hashchar['R'] -= 1;
                   re.add(6);
               }
               while (hashchar['F'] >= 1 && hashchar['I'] >= 1 && hashchar['V'] >= 1 && hashchar['E'] >= 1) {
                   hashchar['F'] -= 1;
                   hashchar['I'] -= 1;
                   hashchar['V'] -= 1;
                   hashchar['E'] -= 1;
                   re.add(7);
               }

               while (hashchar['O'] >= 1 && hashchar['N'] >= 1 && hashchar['E'] >= 1) {
                   hashchar['O'] -= 1;
                   hashchar['N'] -= 1;
                   hashchar['E'] -= 1;
                   re.add(3);
               }


               while (hashchar['S'] >= 1 && hashchar['E'] >= 2 && hashchar['V'] >= 1 && hashchar['N'] >= 1) {
                   hashchar['S'] -= 1;
                   hashchar['E'] -= 2;
                   hashchar['V'] -= 1;
                   hashchar['N'] -= 1;
                   re.add(9);
               }

               while (hashchar['N'] >= 2 && hashchar['I'] >= 1 && hashchar['E'] >= 1) {
                   hashchar['N'] -= 2;
                   hashchar['I'] -= 1;
                   hashchar['E'] -= 1;
                   re.add(1);
               }


               Collections.sort(re);
               StringBuilder sb = new StringBuilder();
               for (int s = 0; s < re.size(); ++s)
                   sb.append(re.get(s));
               System.out.println(sb.toString());
           }
       }

   }
   /* 水仙花数 */
   public static boolean isShuiXian(int target)
   {
       boolean flag=false;
       int temp=target;
       int sum=0;
       while (temp!=0)
       {
           int last=temp%10;
           sum+=last*last*last;
           temp=temp/10;
       }
       if(sum==target)
           flag=true;
       return flag;
   }

   /* 袋鼠过河  BFS  会内存溢出 动态规划试试*/
   public static int BFS(int[] dx,int end)
   {
       Queue<PointM> q=new LinkedList<PointM>();
       q.add(new PointM(0,0));  //起点是0  初始步数是1
       int max=-1;
       boolean isCan=false;
       while (!q.isEmpty())
       {
           PointM temp=q.poll();
           int x=temp.x;
           int i=0,tempx;
           for(i=1;i<=dx[x];++i)
           {
               tempx=temp.x+i;
               if(tempx>end)
               {
                   isCan=true;
                   return temp.step+1;
               }
               if(dx[tempx]!=0)
               {
                   q.add(new PointM(tempx,temp.step+1));
               }
           }
       }
       if(isCan==false)
           max=-1;
       return max;
   }
   public static int DynamicProgram(int[] dx)
   {
       int i,j,len=dx.length;
       int[] dp=new int[len+1];
       for(i=0;i<=len;++i)
           dp[i]=Integer.MAX_VALUE;
       dp[0]=0;
       if(dx[0]==0) return -1;
       for(i=0;i<len;++i) {
           if(dx[i]!=0)
           for (j = 1; j <= dx[i]; ++j)
           {
               if((i+j)<len&&dx[i+j]!=0&&dp[i]!=Integer.MAX_VALUE)
                   dp[i+j] = Math.min(dp[i+j],dp[i]+1);
               else if(i+j>=len&&dp[i]!=Integer.MAX_VALUE)
                   dp[len]=Math.min(dp[len],dp[i]+1);
           }

       }

       if(dp[len]==Integer.MAX_VALUE)
           return -1;
       else
           return dp[len];

   }

   /* 彩色宝石项链 首尾字符串合并 暴力法 */
    public static int MostDimaon(String src)
    {
        if(src.length()<5) return 0;
        int len=src.length();
        src+=src;
        int i,j,r=0;
        String temp=" ";
        for(i=0;i<len;i++)
            for(j=i+5;j<=i+len;j++)
            {
                temp=src.substring(i,j);
                if(temp.indexOf('A')!=-1&&temp.indexOf('B')!=-1&&temp.indexOf('C')!=-1&&temp.indexOf('D')!=-1&&temp.indexOf('E')!=-1)
                   r=Math.max(r,len-temp.length());
            }
        return r;
    }
    /* 保留最大的数 从高位开始扫描 */
    public static String RomanMax(String src)
    {
        String temp=src;
        if(temp.length()<=1)
            return "0";
        List<Integer> arr=new ArrayList<Integer>();
        for(int s=0;s<src.length();s++)
            arr.add(Integer.valueOf(temp.charAt(s)-'0'));
        int min=arr.size()-1;  //下标默认为个位
        for(int j=0;j<arr.size()-1;j++)
            if(arr.get(j)<arr.get(j+1))
            {
                min=j;
                break;
            }
        StringBuilder sb=new StringBuilder();
        for(int k=0;k<arr.size();++k)
        {
            if(k!=min)
                sb.append(arr.get(k));
        }
       return sb.toString();

    }

    /*  判断一个数是否为素数 */
    public static boolean isPrime(int n)
    {
        if(n==1)
            return false;
        if(n==2)
            return true;
        int i=1;
        boolean flag=true;
        for(i=2;i<=Math.sqrt(n);i++)
        {
            if(n%i==0)
            {
                flag=false;
                break;
            }
        }
        return flag;
    }

    /* 匹配父串中是否有子串  正则匹配 */
    public static void isPipei()
    {
        Scanner in=new Scanner(System.in);
        while (in.hasNext())
        {
            String src=in.nextLine();
            String a=in.nextLine();
            String b=in.nextLine();
            StringBuilder sb=new StringBuilder(src);
            String reversrc=sb.reverse().toString();
            boolean forword=src.matches(".*"+a+".*"+b+".*");
            boolean backward=reversrc.matches(".*"+a+".*"+b+".*");
            if(forword&&backward)
                System.out.println("both");
            else if(forword&&!backward)
                System.out.println("forward");
            else if(!forword&&backward)
                System.out.println("backward");
            else if(!forword&&!backward)
                System.out.println("invalid");



        }
    }

    /* 回文序列 首尾对应的指针一定要相等 若首小 加相邻的  若尾小加相邻的  双端队列Deque */
    public static void MinOpration()
    {
        Scanner in=new Scanner(System.in);
        while (in.hasNext())
        {
            int n=in.nextInt();
            in.nextLine();
            String src=in.nextLine();
            String[] arr=src.split(" ");
            Deque<Integer> q=new ArrayDeque<Integer>();  //创建一个双端队列 首尾操作

            for(int i=0;i<n;i++)
                q.add(Integer.valueOf(arr[i]));

            int count=0;
            while (!q.isEmpty())
            {
                if(q.size()<=1)
                    break;
                int first=q.peekFirst();
                int last=q.peekLast();
                if(first==last)
                {
                    q.pollFirst();
                    q.pollLast();
                }
                else if(first<last)
                {
                    int x1=q.pollFirst();
                    int x2=q.pollFirst();
                    q.addFirst(x1+x2);

                    count+=1;
                }
                else if(first>last)
                {
                    int x1=q.pollLast();
                    int x2=q.pollLast();
                    q.addLast(x1+x2);

                    count+=1;
                }
            }
            System.out.println(count);

        }
    }

    /*  跳石板 动态规划 从前往后推     dp[i+j]=Math.min(dp[i+j],dp[i]+1); 主要求约数的时候 j<=sqrt(i)即可 */
    public static void Minstep(){
        return;
    }

    /*  暗黑的字符串 */

    /* 最大奇约数   */
    public static void MaxOddSum()
    {
        Scanner in=new Scanner(System.in);
        while (in.hasNext())
        {
            int n=in.nextInt();
            int sum=0;
            while (n!=0)
            {
                if(n%2!=0)
                {
                    sum+=n;
                    n--;
                }
                else if(n%2==0)
                {
                    sum+=(n*n)/4;
                    n/=2;
                }
            }
            System.out.println(sum);
        }
    }
   /*  分苹果 动态规划 dp[i]表示i袋分最少的次数 */
   public static void FenApple()
   {

       Scanner in=new Scanner(System.in);
       while (in.hasNext())
       {
           int src=in.nextInt();
           int[] values={6,8};
           int[] dp=new int[101];
           Arrays.fill(dp,Integer.MAX_VALUE);
           dp[0]=0;
           int i,j;
           for(i=0;i<2;i++)
               for(j=1;j<=src;j++) {
                   if (j >= values[i] && dp[j - values[i]] != Integer.MAX_VALUE)
                       dp[j] = Math.min(dp[j], dp[j - values[i]] + 1);
               }
           if(dp[src]!=Integer.MAX_VALUE)
               System.out.println(dp[src]);
           else
               System.out.println(-1);
       }
   }

   /*  地下迷宫 */

    {
        Scanner in=new Scanner(System.in);
        while (in.hasNext())
        {
            int start,end;
            start=in.nextInt();
            end=in.nextInt();
            int[] dp=new int[end+1];
            int i,j;
            for(i=start;i<=end;i++)
                dp[i]=Integer.MAX_VALUE;
            dp[start]=0;
            for(i=start;i<=end;i++)
                for(j=2;j<=Math.sqrt(i);j++)
                {
                    if(i%j==0&&(i+j)<=end&&dp[i]!=Integer.MAX_VALUE)
                        dp[i+j]=Math.min(dp[i+j],dp[i]+1);
                    if((i+i/j)<=end&&dp[i]!=Integer.MAX_VALUE&&i%j==0)
                        dp[i+(i/j)]=Math.min(dp[i+(i/j)],dp[i]+1);
                }
            if(dp[end]==Integer.MAX_VALUE)
                System.out.println(-1);
            else
                System.out.println(dp[end]);
        }
    }
   static class PointM{
           int x;  //下标
           int step;
           PointM(int x1,int step1)
           {
               this.x=x1;
               this.step=step1;
           }
}
   static class QingWa{
        int x,y,energy;
        QingWa(int x1,int y1,int p)
        {
            this.x=x1;
            this.y=y1;
            this.energy=p;
        }
   }
}
