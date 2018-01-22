import com.sun.javafx.collections.MappingChange;

import java.lang.ref.SoftReference;
import java.math.BigInteger;
import java.util.*;
public class XZ2017 {
    static java.lang.String path="";
    static boolean isCan=false;
    static int maxenergy=-10;
    static int count=0;
    static int k;
    public static void main(String[] args)
    {
        final long MOD=1000000007;
        Scanner in=new Scanner(System.in);
      while (in.hasNext()) {
         int start,result=-1;
         start=in.nextInt();
         Queue<Node> q=new ArrayDeque<Node>();
         Map<Long,Integer> visited=new HashMap<Long, Integer>();
         visited.put(Long.valueOf(start),1);
         q.add(new Node(start,0));
         while (!q.isEmpty())
         {
             long one,two;
             Node t=q.poll();
             if(t.x==0&&t.y<=100000)
             {
                 result=t.y;
                 break;
             }
             if(t.y>100000)
                 break;
             one=t.x*4+3;
             two=t.x*8+7;
             one=one%MOD;   //每次只需要把余数加入队列 避免数值过大
             two=two%MOD;   //但会有重复 会陷入循环 加哈希表
             if(visited.containsKey(one)==false)
             {
                 visited.put(one,1);
                 q.add(new Node(one,t.y+1));
             }
             if(visited.containsKey(two)==false)
             {
                 visited.put(two,1);
                 q.add(new Node(two,t.y+1));
             }
         }
         System.out.println(result);
      }

    }

    /*  地下迷宫 DFS 搜索所有路径 选择体力最好的 类中static全局变量 */
    public static void dfs(int[][] maze,boolean[][] visited,int[]dx,int[] dy,int energy,int row,int column,int currentx,int currenty,
    List<Node> result)
    {
         if(energy<0) return;
         int tempx=currentx;
         int tempy=currenty;
         visited[tempx][tempy]=true;
         result.add(new Node(tempx,tempy));

         if(currentx==0&&currenty==column-1&&energy>=0)
         {
             isCan=true;
             if(energy>=maxenergy)
             {
                 maxenergy=energy;
                 StringBuilder sb=new StringBuilder();
                 Iterator<Node> iter=result.iterator();
                 while (iter.hasNext())
                 {
                     Node r=iter.next();
                     sb.append("["+r.x+","+r.y+"]"+",");
                 }
                 path=sb.toString().substring(0,sb.length()-1);



             }
             visited[tempx][tempy]=false;
             result.remove(result.size()-1);
             return;
         }

         for(int i=0;i<4;i++)
         {
             currentx=tempx+dx[i];
             currenty=tempy+dy[i];

             if(currentx>=0&&currentx<=row-1&&currenty>=0&&currenty<=column-1&&maze[currentx][currenty]==1&&visited[currentx][currenty]==false&&i==0&&energy>=1)
             {  //向右
                 dfs(maze,visited,dx,dy,energy-1,row,column,currentx,currenty,result);
             }
             if(currentx>=0&&currentx<=row-1&&currenty>=0&&currenty<=column-1&&maze[currentx][currenty]==1&&visited[currentx][currenty]==false&&i==1&&energy>=1)
             {  //向左
                 dfs(maze,visited,dx,dy,energy-1,row,column,currentx,currenty,result);
             }
             if(currentx>=0&&currentx<=row-1&&currenty>=0&&currenty<=column-1&&maze[currentx][currenty]==1&&visited[currentx][currenty]==false&&i==2&&energy>=0)
             {  //向下
                 dfs(maze,visited,dx,dy,energy,row,column,currentx,currenty,result);
             }
             if(currentx>=0&&currentx<=row-1&&currenty>=0&&currenty<=column-1&&maze[currentx][currenty]==1&&visited[currentx][currenty]==false&&i==3&&energy>=3)
             {  //向上
                 dfs(maze,visited,dx,dy,energy-3,row,column,currentx,currenty,result);
             }
         }
        visited[tempx][tempy]=false;
        result.remove(result.size()-1);

    }
    /*  能否整除 能否被2整除 首尾指针移动 */
    public static void FenApple()
    {
        Scanner in=new Scanner(System.in);
        while (in.hasNext())
        {
            int n=in.nextInt();
            int[] apples=new int[n];
            in.nextLine();   //整数之后接一行有空格的输入
            String src=in.nextLine();
            String[] arr=src.split(" ");
            int i,sum=0,count=0,start=0,end=n-1;
            boolean isCan=true;
            for(i=0;i<n;i++) {
                apples[i] = Integer.valueOf(arr[i]);
                sum+=apples[i];
            }
            if(sum%n!=0)
                System.out.println(-1);
            else
            {
                int average=sum/n;  //平均数
                for(i=0;i<n;i++)
                {
                    apples[i]-=average;
                    if(apples[i]%2!=0)
                    {
                        isCan=false;
                        break;
                    }
                }
                if(isCan==false)
                    System.out.println(-1);
                else
                {
                    Arrays.sort(apples);
                    int p,q;
                    p=start;
                    q=end;
                    while (p<=q)
                    {
                        while (apples[p]<0&&apples[q]>0)
                        {
                            count+=1;
                            apples[p]+=2;
                            apples[q]-=2;
                        }
                        while (p<=end&&apples[p]==0) p++;
                        while (q>=start&&apples[q]==0) q--;
                    }
                    System.out.println(count);

                }

            }
        }
    }

    /* 优雅的点 水*/
    public static void ElegmentPoint()
    {
        Scanner in=new Scanner(System.in);
        while (in.hasNext())
        {
            int m=in.nextInt();
            int i,j;
            double radius;
            radius=Math.sqrt(m);
            int count=0;
            for(i=0;i<=radius;i++)
            {
                int temp=m-i*i;
                int s=(int)Math.sqrt(temp);
                if((s*s+i*i)==m)
                {
                    if(s!=0&&i!=0)
                        count+=4;
                    else if(s==0||i==0)
                        count+=2;
                }
            }
            System.out.println(count);
        }
    }
    /* 解救小易 BFS会 java heap space 利用动态规划 因为是最少进 有最优解*/
    /* 不用动态规划 因为最小路径可以直接计算出来*/
    public static void savexiaoyi()
    {
     /*   Scanner in=new Scanner(System.in);
        while (in.hasNext())
        {
            int[][] steps=new int[1001][1001];
            int[][] maze=new int[1001][1001];
            boolean[][] visited=new boolean[1001][1001];
            int n,i,j,minstep=10000;
            for(i=1;i<=1000;i++)
                for(j=1;j<=1000;j++)
                {
                    steps[i][j]=0;
                    maze[i][j]=0;
                    visited[i][j]=false;
                }
            n=in.nextInt();
            in.nextLine();
            String x=in.nextLine();
            String y=in.nextLine();
            String[] xi=x.split(" ");
            String[] yi=y.split(" ");
            int[] dx={0,0,1,-1};
            int[] dy={1,-1,0,0};
            for(i=0;i<n;i++)
            {
                maze[Integer.valueOf(xi[i])][Integer.valueOf(yi[i])]=1;
            }
            Queue<Node> q=new ArrayDeque<Node>();
            q.add(new Node(1,1));
            steps[1][1]=0;
            visited[1][1]=true;
            while (!q.isEmpty())
            {
                Node temp=q.poll();
                int tx=temp.x;
                int ty=temp.y;
                if(maze[tx][ty]==1)
                {
                    minstep=steps[tx][ty];
                    break;
                }
                visited[tx][ty]=true;
                for(j=0;j<4;j++)
                {
                    tx=temp.x+dx[j];
                    ty=temp.y+dy[j];
                    if(tx>=1&&tx<=1000&&ty>=1&&ty<=1000&&visited[tx][ty]==false)
                    {
                        steps[tx][ty]=steps[temp.x][temp.y]+1;
                        q.add(new Node(tx,ty));
                    }
                }
            }
            System.out.println(minstep);
        }*/
    }
    public static void saveDpxiaoyi()
    {
        Scanner in=new Scanner(System.in);
        while (in.hasNext())
        {
            int[][] dp=new int[1001][1001];
            int i,j,n;
            for(i=1;i<=1000;i++)
                dp[i][1]=i-1;
            for(j=1;j<=1000;j++)
                dp[1][j]=j-1;
          /* 滚动数组更新 */
            for(i=1;i<=1000;i++)
                for(j=1;j<=1000;j++)
                    if(i-1>=1&&j-1>=1)
                        dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+1;
            n=in.nextInt();
            in.nextLine();
            String x=in.nextLine();
            String y=in.nextLine();
            String[] xi=x.split(" ");
            String[] yi=y.split(" ");
            int[] results=new int[n];
            int k=0;
            for(i=0;i<n;i++)
            {
                results[k++]=dp[Integer.valueOf(xi[i])][Integer.valueOf(yi[i])];
            }
            Arrays.sort(results);
            System.out.println(results[0]);


        }
    }

    /* 合唱团 动态规划 选择变量 n k 画二维表格 得出状态转移方程 因为有正负 所有需要维护2个数组 */
    public static void HeChangtuan()
    {
        Scanner in=new Scanner(System.in);
        while (in.hasNext())
        {
            int n=in.nextInt();
            long[][]  dpmax=new long[n+1][n+1];
            long[][]  dpmin=new long[n+1][n+1];
            int[] values=new int[n];
            int i,j,k,d,temp;
            long maxtemp=1,mintemp;
            for(i=0;i<n;i++)
                values[i]=in.nextInt();
            k=in.nextInt();
            d=in.nextInt();
            for(i=1;i<=n;i++)  //初始化
            {
                dpmax[i][1]=values[i-1];
                dpmin[i][1]=values[i-1];
            }
            for(j=0;j<=n;j++)
                dpmin[0][j]=dpmax[0][j]=1;
            for(i=2;i<=n;i++)
                for(j=2;j<=i;j++)
                {
                    //因为有正负 更新最大最小数组
                    mintemp= maxtemp=values[i-2];
                    for(temp=i-2;temp>=i-d-1;temp--)
                    {
                        if(temp>=0)
                        {
                            maxtemp=Math.max(maxtemp,dpmax[temp+1][j-1]);
                            mintemp=Math.min(mintemp,dpmin[temp+1][j-1]);
                        }
                    }
                    if(values[i-1]>0)
                    {
                        dpmax[i][j]=maxtemp*values[i-1];
                        dpmin[i][j]=mintemp*values[i-1];
                    }
                    else
                    {
                        dpmax[i][j]=mintemp*values[i-1];
                        dpmin[i][j]=maxtemp*values[i-1];
                    }
                }

            maxtemp=1;
            for(i=1;i<=n;i++) {
                maxtemp=Math.max(dpmax[i][k],maxtemp);
            }
            System.out.println(maxtemp);
        }
    }
    /* 酒店价格 区间 利用哈希 数组 依次遍历 */
    public static void hotelprice()
    {
        Scanner in=new Scanner(System.in);
        int  min=10000,max=0;
        int[] hasharr=new int[10001];
        while (in.hasNext())
        {

            int start,end,price;
            start=in.nextInt();
            end=in.nextInt();
            price=in.nextInt();
            int i;
            for(i=start;i<=end;++i)
                hasharr[i]=price;
            if(start<=min)
                min=start;
            if(end>=max)
                max=end;
        }

        StringBuilder sb=new StringBuilder();
        sb.append("["+min+", ");
        for(int i=min+1;i<=max;i++)
        {
            if(hasharr[i]!=hasharr[i-1])
            {
                if(hasharr[i-1]!=0)
                    sb.append(i-1+", "+hasharr[i-1]+"]");
                if(hasharr[i]!=0)
                    sb.append(","+"["+i+", ");
            }
        }
        sb.append(max+", "+hasharr[max]+"]");
        System.out.println(sb.toString());

    }

    /* 分田地 动态规划*/

    /* 数列还原 全排列 试试 */
    public static void AllPermute(int[] src,int start,int end,int[] srcarrs)
    {
        if(start==end)
        {
           count+=IsOk(src,srcarrs);
            return;
        }
        int i;
        for(i=start;i<end;i++)
        {
            swap(src,start,i);
            AllPermute(src,start+1,end,srcarrs);
            swap(src,i,start);
        }
    }

    public static void swap(int[] src,int i,int j)
    {
        int temp=src[i];
        src[i]=src[j];
        src[j]=temp;
    }

    public static int IsOk(int[] lose,int[] arr)
    {
        int result=0,j=0,s=0,xudui=0;
        int[] temp=new int[arr.length];
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]!=0)
                temp[s]=arr[i];
            else
                temp[s]=lose[j++];
            s++;
        }
        int i;
        for(i=0;i<arr.length;i++)
            for(j=i+1;j<arr.length;j++)
                if(temp[j]>temp[i])
                    xudui++;
        if(xudui==k)
            result=1;
        return result;
    }

    /* 判断是否是回文串 substring利用*/
    public static int HuiWen(String src)
    {
        int result=1;
        int i;
        for(i=0;i<=src.length()/2;i++)
        {
            if(src.charAt(i)!=src.charAt(src.length()-1-i))
                return 0;
        }
        return result;
    }

    /* 洗牌 集合的使用 引用类型的浅拷贝  */
    public static void xipai()
    {
        Scanner in=new Scanner(System.in);
        while (in.hasNext())
        {
            int t=in.nextInt(); //表示数据组数
            while (t-->0)
            {
                int n,k,i,j;
                n=in.nextInt();
                k=in.nextInt(); //表示洗牌次数

                List<Integer> left=new Stack();
                List<Integer> right=new Stack();
                List<Integer> result=new Stack<>();
                for(i=0;i<2*n;i++)
                {

                    result.add(in.nextInt());
                }
                while (k-->0)
                {
                    left.clear();
                    right.clear();
                    left=new ArrayList<>(result.subList(0,n)); //副本赋值
                    right=new ArrayList<>(result.subList(n,2*n));
                    result.clear();
                    while (!left.isEmpty())
                    {

                        int leftvalue=left.get(left.size()-1);
                        int rightvalue=right.get(right.size()-1);
                        result.add(rightvalue);
                        result.add(leftvalue);
                        left.remove(left.size()-1);
                        right.remove(right.size()-1);
                    }
                    Collections.reverse(result);

                }
                StringBuilder sb=new StringBuilder();
                for(j=0;j<result.size();j++)
                    sb.append(result.get(j)+" ");
                System.out.println(sb.toString().substring(0,sb.length()-1));
            }
        }

    }

      /*  字符串的排序 CompareTo*/
    public static void SortStrings()
    {
        Scanner in=new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            boolean issortbyLength = true;
            boolean issortbyDict = true;
            List<String> src = new ArrayList<String>();
            for (int i = 0; i < n; i++) {
                src.add(in.next());
                if (i >= 1) {
                    if (src.get(i).length() <= src.get(i - 1).length())
                        issortbyLength = false;
                    if (src.get(i).compareTo(src.get(i - 1)) < 0)
                        issortbyDict = false;
                }
            }
            if (issortbyDict && issortbyLength)
                System.out.println("both");
            else if (issortbyDict && !issortbyLength)
                System.out.println("lexicographically");
            else if (!issortbyDict && issortbyLength)
                System.out.println("lengths");
            else if (!issortbyDict && !issortbyLength)
                System.out.println("none");
        }
        }

     /* 小易喜欢的单词  正则匹配*/
     public static void XiaoLikes()
     {
         Scanner in=new Scanner(System.in);
         while (in.hasNext()) {
             String src = in.next();
             boolean isCan = true;
             int i,j;
             for ( i = 0; i < src.length(); i++)
                 if (  i >= 1&&src.charAt(i) == src.charAt(i - 1)) {
                     isCan = false;
                     break;
                 }

             StringBuilder sb=new StringBuilder();
             String temp="";
             for(i=0;i<src.length()-1;i++)
                 for(j=i+1;j<src.length();j++)
                 {
                     if(src.substring(j+1,src.length()).matches(".*"+src.charAt(i)+".*"+src.charAt(j)))
                     {
                         isCan=false;
                         break;
                     }
                 }
             if (isCan == false)
                 System.out.println("Dislikes");
             else
                 System.out.println("Likes");
         }
     }
    static class Node
    {
        long x;
        int y;
        Node(long x1,int y1){
            this.x=x1;
            this.y=y1;
        }
    }
}
