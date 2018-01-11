/*
2017 校招合并在线编程题
 */

import javax.script.ScriptContext;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
        while (in.hasNext())
        {
            String a=in.nextLine();
            String b=in.nextLine();
            int len1=a.length();
            int len2=b.length();
            int[][] dp=new int[len1+1][len2+1];  //dp[i][j] 表示len1前i个字符 与len2前j个字符的最长公共子序列
            int i,j;
            for(i=0;i<len1;i++)
                dp[i][0]=0;
            for(j=0;j<len2;++j)
                dp[0][j]=0;
            for(i=1;i<=len1;i++)
                for(j=1;j<=len2;j++)
                    if(a.charAt(i-1)==b.charAt(j-1))
                        dp[i][j]=dp[i-1][j-1]+1;
                    else
                        dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
            if(dp[len1][len2]==len2)
                System.out.println("Yes");
            else
                System.out.println("No");
        }
    }

    /*集合 */
    public static void UnionSet()
    {
        Scanner in=new Scanner(System.in);
        while (in.hasNext())
        {
            int n,m;
            n=in.nextInt();
            m=in.nextInt();
            if(m==0&&n==0)
                continue;
            List a=new ArrayList();
            int i,j;
            for(i=0;i<m;i++)
            {
                int t=in.nextInt();
                if(a.indexOf(t)==-1)
                    a.add(t);
            }
            for(j=0;j<n;j++)
            {
                int t=in.nextInt();
                if(a.indexOf(t)==-1)
                    a.add(t);
            }

            Collections.sort(a);
            for(i=0;i<a.size()-1;i++)
                System.out.print(a.get(i)+" ");
            System.out.print(a.get(a.size()-1));
        }
    }
    /* 回文序列 */

    /*  解析文件名后缀*/

    /*数字和为sum的方法数  思路 递归 但会超时 */
    public static int SolutionofSum(int[] array,int start,int sum)
    {
       //用递归来做

        if(sum==0)
        {
            return 1;}
        if(sum<0)
            return 0;
        if(start>=array.length)
            return 0;
       return SolutionofSum(array,start+1,sum-array[start])+SolutionofSum(array,start+1,sum);
    }

   public static long dpSum(int[] array,int sum)
   {
       int len=array.length-1;
       long [][] dp=new long[len+1][sum+1];
       int i,j;
       for(i=0;i<=len;i++)
           dp[i][0]=1;
       for(j=1;j<=sum;j++)
           dp[0][j]=0;

       for(i=1;i<=len;i++)
           for(j=0;j<=sum;j++)
               if(j>=array[i])
                   dp[i][j]=dp[i-1][j]+dp[i-1][j-array[i]];
         else
           dp[i][j]=dp[i-1][j];
       return dp[len][sum];
   }
   /* 提取字符串中的数字 正则表达式 "\D"  匹配任意非数字的字符  String[] a=src.split("[\\D]+");*/
   public static void SpiltMaxNumofString()
   {
       Scanner in=new Scanner(System.in);
       while (in.hasNext())
       {
           String src=in.nextLine();
           String[] a=src.split("[\\D]+");
           int max=0;
           String r=" ";
           for(int i=0;i<a.length;i++)
               if(a[i].length()>max)
               {
                   max=a[i].length();
                   r=a[i];
               }
           System.out.println(r);
       }
   }
   /* 统计n个数出现次数大于n/2的数 哈希表统计次数 但数字如果没有上下限就会有BUG 超过半数
   可以排序 中位数即是过半的结果 */
   public static void CalMaxOccur()
   { Scanner in=new Scanner(System.in);
       while(in.hasNext())
       {
           String src=in.nextLine();
           String[] array=src.split(" ");
           int[] arr=new int[array.length];
           for(int i=0;i<arr.length;i++)
               arr[i]=Integer.valueOf(array[i]);
           Arrays.sort(arr);
           if(arr[arr.length/2]==arr[arr.length-1])
               System.out.println(arr[arr.length/2]);
           else
               System.out.println(arr[arr.length/2-1]);
       }}

    /* 求和  既然要列出组合 那就是搜索算法*/
    public static void dfs(int[] array,int start,int m,int[] result)
    {

        if(m==0)
        {
            StringBuffer sb=new StringBuffer();
            for(int i=0;i<array.length;i++)
                if(result[i]==1)
                {
                    sb.append(array[i]);
                    sb.append(' ');
                }
            System.out.println(sb.toString().substring(0,sb.length()-1));  //去掉末尾空格 （0,sb.length)最后一个元素是空格 (0,length-1)去掉最后一个空格
            return;
        }
        if(start>=array.length)
        {
            return;
        }
        if(m<0)
        {
            return;
        }
        result[start]=1;
        dfs(array,start+1,m-array[start],result);
        result[start]=0;
        dfs(array,start+1,m,result);
    }

    /* 头条校招 需要补多少题  先排序 在处理 */
    public static int leastQuestion(int[] arr)
    {
        int result=0;
        Arrays.sort(arr);
        int round=1;
        int i,len=arr.length;
        for(i=1;i<len;i++)
        {
            if(round==3)
                round=1;
            else if(arr[i]-arr[i-1]<=10)
            {
                round+=1;
            }
           //补位的情况
            else if(round==1&&arr[i]-arr[i-1]<=20)
            {
                result+=1;
                round=3;
            }
            else if(round==2&&arr[i]-arr[i-1]<=20)
            {
                result+=1;
                round=1;
            }
            else if(round==1&&arr[i]-arr[i-1]>20)
            {
                result+=2;
                round=1;
            }
            else if(round==2&&arr[i]-arr[i-1]>20)
            {
                result+=1;
                round=1;
            }
        }
        result+=3-round;
        return result;
    }
   /* 连续最大和  动态规划*/
    public static void maxSub()
    {
        Scanner in=new Scanner(System.in);
        while (in.hasNext())
        {
            int n=in.nextInt();
            int[] arr=new int[n];
            for(int i=0;i<n;++i)
                arr[i]=in.nextInt();
            int max=arr[0];
            int cursum=arr[0];
            for(int i=1;i<arr.length;i++)
            {
                if(cursum>0)
                    cursum+=arr[i];
                else if(cursum<=0)
                    cursum=arr[i];
                if(cursum>max)
                    max=cursum;
            }
            System.out.println(max);
        }
    }

    /* 幸运数  */
    public static int LuckyNumber(int target)
    {
        int result=0;
        for(int i=1;i<=target;i++)
            if(Ten(i)==Two(i))
                result++;

        return result;
    }

    public static int Ten(int target)
    {
        int sum=0;
        while (target!=0)
        {
            sum+=target%10;
            target/=10;
        }
        return sum;
    }
    //求二进制中1的个数 n&(n-1)的次数  或者与1求与运算 再除以10
    public static int Two(int target)
    {
        int sum=0;
         while (target!=0)
         {
             sum++;
             target=target&(target-1);
         }
         return sum;
    }
    /* 拼凑面额 动态规划  */
    public static long MoneyCombine(int sum)
    {
        int[] money={1,5,10,20,50,100};
        long[] dp=new long[sum+1];
        dp[0]=1;
        int i,j;
        for(i=0;i<6;i++)
            for(j=1;j<=sum;j++)
                if(j>=money[i])
                dp[j]=dp[j]+dp[j-money[i]];
        return dp[sum];
    }
    /* 走格点 动态规划*/
    public static int TotalRoute(int x,int y)
    {
        int[][] dp=new int[x+1][y+1];
        int i,j;
        for(i=0;i<=x;i++)
            dp[i][0]=1;
        for(j=0;j<=y;++j)
            dp[0][j]=1;
        for(i=1;i<=x;i++)
            for(j=1;j<=y;j++)
                dp[i][j]=dp[i-1][j]+dp[i][j-1];
        return dp[x][y];
    }
    /* 去掉部分数字 每次从高位遍历 去掉递增序列的第一个值 */
    public static void RomanMax(int src,int m)
    {
        List<Integer> a=new ArrayList<Integer>();
        while (src!=0)
        {
            int t=src%10;
            a.add(t);
            src/=10;
        }
        Collections.sort(a);

        for(int i=a.size()-1;i>=m;i--)
            System.out.print(a.get(i));

    }

    /* 下厨房  集合的使用*/
    public static void hashSet()
    {
        Scanner in=new Scanner(System.in);
        HashSet hs=new HashSet<String>();
        while (in.hasNext())
        {
            String src=in.nextLine();
            String[] a=src.split(" ");
            for(int i=0;i<a.length;i++)
                hs.add(a[i]);

        }
        System.out.println(hs.size());
        in.close();
    }

    /* Fibonacci 数列 */
    public static int closestFibonacci(int target,int start,int end)
    {

        if(target>=start&&target<=end)
            return Math.min(Math.abs(target-start),Math.abs(target-end));
        else {
            return closestFibonacci(target,end,start+end);
        }
    }

    /* 树的高度  两个哈希表 层次遍历 往上递推 */
    public static void MaxLength()
    {
        Scanner in=new Scanner(System.in);
        while (in.hasNext())
        {
            int n=in.nextInt();
            int max=1;
            HashMap<Integer,Integer> node=new HashMap<Integer, Integer>(); //表示当前节点 与当前节点的层数
            HashMap<Integer,Integer> child=new HashMap<Integer, Integer>();  //表示当前节点 与当前节点孩子的个数
            node.put(0,1);  //
            child.put(0,0);
            int i=0;
            int a,b;
            for(i=0;i<n-1;i++)
            {
                a=in.nextInt();  //父节点编号
                b=in.nextInt();  //子节点编号
                if(!node.containsKey(a)||child.get(a)>=2)
                    continue;
                node.put(b,node.get(a)+1); //子节点的层数 等于父节点层数+1
                child.put(a,child.get(a)+1);  //父节点当前子节点个数+1
                child.put(b,0);
                if(node.get(b)>=max)
                    max=node.get(b);
            }
            System.out.println(max);
        }
    }

    /* 数串  比较(x+y) 与(y+x)的大小 */
   public static void CompareStringSquence()
   {
       Scanner in=new Scanner(System.in);

       while (in.hasNext())
       {
           int n;
           n=in.nextInt();
           in.nextLine();
           String src=in.nextLine();
           String[] a=src.split(" ");
           Arrays.sort(a, new Comparator<String>() {
               @Override
               public int compare(String o1, String o2) {
                   return Integer.valueOf(o1+o2)-Integer.valueOf(o2+o1);
               }
           });
           for(int i=a.length-1;i>=0;i--)
               System.out.print(a[i]);
       }
   }
   /* 单词倒序 spilt sb  substring去掉最后一个空格*/
   public static void reverseWord()
   {
       Scanner in=new Scanner(System.in);

       while (in.hasNext())
       {
           String src=in.nextLine();
           String[] a=src.split(" ");
           StringBuilder sb=new StringBuilder();
           for(int i=a.length-1;i>=0;i--)
               sb.append(a[i]+" ");
           System.out.println(sb.substring(0,sb.length()-1));
       }
   }

   /* 数字游戏  先排序  排序之后追踪前n项和  这是前n项的最大值 如果与n+1项不是连续的  则存在不能表示 因为前n项最大值 与当前不连续
   *  如果<=1表示 前n项与当前的连续 又因为前n项可以表示为[0,Sum(n)] 那么对应每项+an，则最大范围增至[0,Sum(n)+ai]
   * */
   public static void MinCanNotCombine()
   {
       Scanner in=new Scanner(System.in);

       while (in.hasNext())
       {
           int n=in.nextInt();
           int[] arr=new int[n];
           for(int i=0;i<n;i++)
               arr[i]=in.nextInt();
           Arrays.sort(arr);
           int miss=0;
           for(int i=0;i<arr.length;i++)
           {
               if(arr[i]>miss+1)
                   break;
               else
                   miss+=arr[i];
           }

           System.out.println(miss+1);
       }
   }
  /*  地牢逃脱  广度优先搜索 遍历所有的可通行点  找到最长路径 or不可达  BFS经典 统计次数 */
   public static void bfsMaze()
   {
       Scanner in=new Scanner(System.in);
       while (in.hasNext()) {
           int n, m,max=0;
           n = in.nextInt();
           m = in.nextInt();
           int[][] steps = new int[n][m];
           char[][] points=new char[n][m];
           boolean[][] visited=new boolean[n][m]; //标记数组
           int i, j;
           for(i=0;i<n;++i)
               for(j=0;j<m;++j)
               {
                   visited[i][j]=false;
                   steps[i][j]=-1;
               }
           for (i = 0; i < n; ++i)
           {
               String t=in.next();
               points[i]=t.toCharArray();
           }
           int startX,startY,k;
           startX=in.nextInt();  //起始x
           startY=in.nextInt();  //起始y
           k=in.nextInt();
           int[] dx=new int[k];
           int[] dy=new int[k];
           for(j=0;j<k;++j)
           {
               dx[j]=in.nextInt();
               dy[j]=in.nextInt();
           }

           Queue<PointM> q=new ArrayDeque<PointM>();
           q.add(new PointM(startX,startY));
           visited[startX][startY]=true;
           steps[startX][startY]=0;
           while (!q.isEmpty())
           {
               PointM temp=q.poll();
               int tempx,tempy;
               tempx=temp.x;
               tempy=temp.y;
               for(i=0;i<dx.length;++i)
               {
                   tempx=temp.x+dx[i];
                   tempy=temp.y+dy[i];
                   if(tempx>=0&&tempx<n&&tempy>=0&&tempy<m)
                   {
                       if(points[tempx][tempy]=='.'&&visited[tempx][tempy]==false)
                       {
                           steps[tempx][tempy]=steps[temp.x][temp.y]+1;  //可以走步数加1
                           visited[tempx][tempy]=true;
                           q.add(new PointM(tempx,tempy));
                           if(steps[tempx][tempy]>=max)
                               max=steps[tempx][tempy];
                       }
                   }
               }
           }

           //
           for(i=0;i<n;++i)
               for(j=0;j<m;++j)
                   if(points[i][j]=='.'&&steps[i][j]==-1)
                   {
                       max=-1;break;
                   }
           System.out.println(max);
       }
   }


   /* 子序列  不要求连续 但要求相对顺序 可用动态规划求最长公共子序列  要是判断的话 可以顺序的求子串每个字符是否在父串中按序出现
     对每次找出后 重新构造父串 调用indexof判断是否出现
     */

   /* 幸运的袋子 排序 */
  static class PointM{
      int x,y;
      PointM(int x1,int y1)
      {
          this.x=x1;
          this.y=y1;
      }
  }
}
