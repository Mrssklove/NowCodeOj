import java.util.*;
/*

链表题目活用快慢指针
 */

public class Solution {

    //树的最小深度 当一边为0时 不是深度
    public int run(TreeNode root)
    {
        if(root==null)
            return 0;
        int left=run(root.left);
        int right=run(root.right);
        if(left==0||right==0)
            return 1+left+right;
        return left>right?(1+right):(1+left);
    }

    //逆波兰表达式的计算 判断相等使用.equals()
    public  int evalRPN(String[] tokens)
    {
        Stack<Integer> stack=new Stack<>();
        for(int i=0;i<tokens.length;i++)
        {
            if(tokens[i].equals("+")||tokens[i].equals("-")||tokens[i].equals("*")||tokens[i].equals("/"))
            {
                int y=stack.pop();
                int x=stack.pop();
                int temp=0;
                switch (tokens[i])
                {
                    case "+":
                        temp=x+y;break;
                    case "-":
                        temp=x-y;break;
                    case "*":
                        temp=x*y;break;
                    case "/":
                        temp=x/y;break;
                        default:
                            temp=0;
                }
                stack.add(temp);
            }
            else
                stack.add(Integer.valueOf(tokens[i]));
        }
        return stack.pop();
    }

    //一条直线上做多的点 max-Points-on-a-line
    public int maxPoints(Point[] points)
    {
     return 0;

    }

    //单链表的排序  o(nlgn) 归并排序 快慢指针找中点 合并采用辅助空间
    public ListNode sortList(ListNode head)
    {
        if(head==null||head.next==null)
            return head;
        ListNode middle=FindMiddle(head); //找到中点
        ListNode right=sortList(middle.next);
        middle.next=null;
        ListNode left=sortList(head);
       return mergeList(left,right);
    }

    public static ListNode FindMiddle(ListNode root)
    {
        if(root==null||root.next==null)
            return root;

        ListNode slow=root;
        ListNode quick=root.next;
        while (quick!=null&&quick.next!=null)
        {
            slow=slow.next;
            quick=quick.next.next;
        }
        return slow;
    }

    public static ListNode mergeList(ListNode left,ListNode right)
    {
          ListNode dummy=new ListNode(0); //辅助头结点
          ListNode head=dummy;
          while (left!=null&&right!=null)
          {
              if(left.val<right.val)
              {
                  dummy.next=left;
                  left=left.next;
              }
              else
              {
                  dummy.next=right;
                  right=right.next;
              }
              dummy=dummy.next;
          }
          if(left==null)
              dummy.next=right;
          if(right==null)
              dummy.next=left;
          return head.next;
    }

    //单链表的插入排序 插入一个已经排好序的链表中
    public ListNode insertionSortList(ListNode head)
    {
        if(head==null||head.next==null)
            return head;
        ListNode dummy=new ListNode(0);
        ListNode root=dummy;

        dummy.next=new ListNode(head.val);
        head=head.next;

        ListNode prev,p;
        while (head!=null)
        {
            ListNode temp=new ListNode(head.val);
            temp.next=null;
            prev=root;
            p=root.next;
           while (p!=null&&p.val<head.val)
           {
               prev=p;
               p=p.next;
           }
           prev.next=temp;
           temp.next=p;

           head=head.next;
        }
        return root.next;
    }
    //单链表插入顺序 参考别人的 逻辑清晰


    //双栈后序遍历 先根结点入栈s1 弹出s1栈顶元素 入栈s2 如果s1左右字数不为空压入栈s1
    public ArrayList<Integer> postorderTraversal(TreeNode root)
    {
        ArrayList<Integer> result=new ArrayList<Integer>();
        if(root==null)
            return result;
        Stack<TreeNode> s1=new Stack<>();
        Stack<TreeNode> s2=new Stack<>();
        s1.push(root);
        while (!s1.isEmpty())
        {
            TreeNode temp=s1.pop();
            s2.push(temp);
            if(temp.left!=null)
                s1.push(temp.right);
            if(temp.right!=null)
                s1.push(temp.left);
        }
        while (!s2.isEmpty())
        {
            result.add(s2.pop().val);
        }
        return result;
    }

    //前序遍历非递归 栈  右子树入栈
    public ArrayList<Integer> preorderTraversal(TreeNode root)
    {
        Stack<TreeNode> s1=new Stack<TreeNode>();
        ArrayList<Integer> result=new ArrayList<Integer>();
        if(root==null)
            return result;
        s1.push(root);
        while (!s1.isEmpty())
        {
            TreeNode temp=s1.pop();
            while (temp!=null)
            {
                result.add(temp.val);
                if(temp.right!=null)
                    s1.push(temp.right);
                temp=temp.left;
            }
        }
        return result;
    }

    //单链表检测是否有环 用快慢指针
    public boolean hasCycle(ListNode head)
    {
        if(head==null||head.next==null)
            return false;
        boolean isCycle=false;
        ListNode slow=head;
        ListNode quick=head.next;
        while (slow!=null&&quick!=null&&quick.next!=null)
        {
            if(slow==quick)
            {
                isCycle=true;
                break;
            }
            slow=slow.next;
            quick=quick.next.next;
        }
        return isCycle;

    }

    //同时出发 对后面找出环的起始结点证明有作用
    //检测是否有环 同时出发 速度为2倍 检测是否相等 需要数学证明 2*(a+b)=a+b+n*(b+c) 下次从开始结点与相遇结点各走一步 相遇点即为环的起始结点
    public ListNode detectCycle(ListNode head)
    {
        boolean isCycle=false;
        if(head==null||head.next==null)
            return null;
        ListNode slow=head;
        ListNode fast=head;
        ListNode meet=new ListNode(0);
        while (slow!=null&&fast!=null&&fast.next!=null) //注意判断条件
        {
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast)
            {
                isCycle=true;
                meet=slow;
                break;
            }
        }
        if(isCycle==false)
            return null;
        slow=head;
        while (slow!=meet)
        {
            slow=slow.next;
            meet=meet.next;
        }
        return slow;
    }
    //reorder-list 用了辅助栈  不用辅助栈的话 先找中点 在逆序后半段 在合并 指针的合并不需要额外空间
    public void reorderList(ListNode head)
    {
        if(head==null||head.next==null||head.next.next==null)
            return;
        //先找中点 并把中点以前的结点压入栈
        ListNode slow=head;
        ListNode quick=head.next;
        while (quick!=null&&quick.next!=null)
        {
            slow=slow.next;
            quick=quick.next.next;
        }
        //此时slow为中点 把中点后面的结点压入栈中
        Stack<ListNode> s=new Stack<>();
        while (slow.next!=null)
        {
            s.add(slow.next);
            slow=slow.next;
        }
        //在从头开始扫描 把栈中元素插入原始链表
        ListNode pre=head,next=pre.next;
        while (!s.isEmpty())
        {
            ListNode temp=s.pop();
            pre.next=temp;
            temp.next=next;
            pre=next;
            next=pre.next;
        }
        pre.next=null;

    }

    //字符串分词  动态规划  。。。

    //分糖果 dp
    public int candy(int[] ratings)
    {
       if(ratings.length==0)
           return 0;
       int[] dp=new int[ratings.length];
       dp[0]=1;
       int sum=0;
      for(int i=1;i<ratings.length;i++)
          if(ratings[i]>ratings[i-1])
              dp[i]=dp[i-1]+1;
          else
          {
              dp[i]=1;
          }

       for(int j=ratings.length-2;j>=0;j--)
           if(ratings[j]>ratings[j+1])
               dp[j]=Math.max(dp[j],dp[j+1]+1);
      for(int i=0;i<dp.length;i++)
          sum+=dp[i];
       return sum;


    }

    //暴力贪心..
    public int canCompleteCircuit(int[] gas,int[] cost)
    {
        int len=gas.length;
        int[] ramain=new int[len];
        for(int i=0;i<len;i++)
            ramain[i]=gas[i]-cost[i];
        int i,j,sum=0,resultindex=-1;
        for(i=0;i<len;i++)
        {
            sum=0;
            for(j=0;j<len;j++)
            {
                sum+=ramain[(i+j)%len];
                if(sum<0)
                    break;
            }
            if(j==len)
            {
                resultindex=i;
                break;
            }
        }
        return resultindex;
    }
    //最小切割次数 求次数用dp 回文串  aab 切割一次 dp[i]=Math.min(dp[i],dp[j-1]+1) substring(i,j)为回文子串的话
    public int minCut(String s)
    {
        int[] dp=new int[s.length()];
        int i,j,len=s.length();
        for(i=0;i<len;i++) dp[i]=Integer.MAX_VALUE;
        dp[0]=0;
        for(i=1;i<len;i++)
        {
            for(j=0;j<=i;j++) {

                if (ispalindrome(s.substring(j, i + 1)) && j != 0&&dp[j]!=Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[j-1] + 1);
                }
                else if (ispalindrome(s.substring(j, i + 1)) && j == 0) {
                    {
                        dp[i] = 0;
                    }
                }
                else
                    dp[i]=Math.min(dp[i-1]+1,dp[i]);
            }
        }
        return dp[s.length()-1];
    }

    public boolean ispalindrome(String s)
    {
        if(s.length()==0)
            return false;
        boolean ispalidnd=true;
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)!=s.charAt(s.length()-1-i))
            {
                ispalidnd=false;
                break;
            }
        }
        return ispalidnd;
    }
    //切割子串串 返回所有可能的回文子串 因为是返回结果 所有是搜索
    public ArrayList<ArrayList<String>> partion(String s)
    {
        ArrayList<ArrayList<String>> result=new ArrayList<>();
        ArrayList<String> temp=new ArrayList<>();
        partiondfs(s,0,s.length(),result,temp);
        return result;
    }

    //递归注意模拟 注意循环的次数 二次循环n*n
    public void partiondfs(String s,int start,int end,ArrayList<ArrayList<String>> result,ArrayList<String> temp)
    {
        if(start>=end) {
            result.add(new ArrayList<String>(temp));
            return;
        }

            for(int j=start;j<end;j++)
            {
                if(ispalindrome(s.substring(start,j+1))&&s.substring(start,j+1)!=null)
                {
                    temp.add(s.substring(start,j+1));
                    partiondfs(s,j+1,end,result,temp);
                    temp.remove(temp.size()-1);
                }
            }
    }

    public void solve(char[][] board)
    {
        int m=board.length;
        int n=board[0].length;
    }

    public int singleNumber(int[] A)
    {
        int result=A[0];
        for(int i=1;i<A.length;i++)
        {
            result=result^A[i];

        }
        return  result;
    }


    public int sumNumbers(TreeNode root)
    {
      return helpsumNumbers(root,0);

    }

    //先序遍历 叶节点的判断 左右子树均为空 立刻返回
    public int helpsumNumbers(TreeNode root,int currentsum)
    {
           if(root==null)
           {
               return 0;
           }
           int sum=0;
           currentsum=currentsum*10+root.val;
           if(root.left==null&&root.right==null)
               return currentsum;
           if(root.left!=null)
           sum+= helpsumNumbers(root.left,currentsum);
           if(root.right!=null)
           sum+= helpsumNumbers(root.right,currentsum);
           return sum;

    }

    //O(n)找到最长的连续序列的长度  java哈希表  HashMap put自动更新值 containsKey查询是否存 HashSet也行 remove
    public int longestConsecutive(int[] num)
    {
        HashMap<Integer,Boolean> hashMap=new HashMap<>();
        for(int i=0;i<num.length;++i)
            hashMap.put(num[i],false);
        int count=Integer.MIN_VALUE;
        int temp=1;
        for(int i=0;i<num.length;++i)
        {
            if(hashMap.get(num[i])) continue;
            if(hashMap.containsKey(num[i]))
            {
                temp=1;
                hashMap.put(num[i],true);
            }
            int k=num[i];
            int prev=k-1;
            int post=k+1;
            while (hashMap.containsKey(prev)) {
                hashMap.put(prev,true);  //
                temp++;
                prev--;

            }
            while (hashMap.containsKey(post))
            {
                hashMap.put(post,true);
                temp++;
                post++;
            }
            if(temp>count)
                count=temp;
        }
        return count;
    }

    //最短变换次数 变换的word应该在字典中  采取BFS深搜
    public int ladderLength(String start, String end, HashSet<String> dict)
    {
        Queue<String> q=new ArrayDeque<>();
        HashMap<String,Integer> hashMap=new HashMap<>();
        HashMap<String,Boolean> visited=new HashMap<>();
        dict.add(end);
        for(String s:dict)
        {
            visited.put(s,false);
        }
        hashMap.put(start,1);
        q.add(start);
        int count=-1;
        while (!q.isEmpty())
        {
            String temp=q.poll();
            visited.put(temp,true);
            if(temp.equals(end))
            {
                count=hashMap.get(end);
                break;
            }
            for(int i=0;i<temp.length();i++)
            for(int j=0;j<26;j++)
            {
                StringBuilder sb1=new StringBuilder(temp);
                if(sb1.charAt(i)!=(char)('a'+j))
                  sb1.setCharAt(i,(char)('a'+j));
                if(dict.contains(sb1.toString())&&visited.get(sb1.toString())==false)
                {
                    q.add(sb1.toString());
                    visited.put(sb1.toString(),true);
                    hashMap.put(sb1.toString(),hashMap.get(temp)+1);
                }
            }
        }
        return count;
    }
    int maxSumvalue=Integer.MIN_VALUE;
    public int maxSum(TreeNode root)  //递归求出root左右子树的最大值
    {
        if(root==null)
            return 0;

        int left=Math.max(maxSum(root.left),0);
        int right=Math.max(maxSum(root.right),0);
        maxSumvalue=Math.max(maxSumvalue,left+right+root.val);  //合并最大值 相加  利用成员变量 处理左右子树的最大和
        return root.val+Math.max(left,right);
    }

    public int maxPathSum(TreeNode root)
    {
        maxSum(root);
        return maxSumvalue;
    }
    //叶节点的问题   if(root.right==null&&root.left==null)
    public boolean hasPathSum(TreeNode root, int sum) {
       if(root==null)
           return false;
       helpHaspathSum(root,sum);
       return iscan;
    }

    boolean iscan=false;
    public void helpHaspathSum(TreeNode root,int sum)
    {
        if(root==null)
            return;
        if(root.right==null&&root.left==null)
        {
            if(root.val==sum)
            {
                iscan=true;
                return;
            }
            return;
        }

        helpHaspathSum(root.left,sum-root.val);
        helpHaspathSum(root.right,sum-root.val);
    }
    //下面这种递归很简洁
    /*

* 递归求解 
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        if (sum == root.val && root.left == null && root.right == null)
            return true;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
     */


}
