import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class LeetCode {

   public static void main(String[] args)
   {
       Solution s= new Solution();
       TreeNode a=new TreeNode(5);
       TreeNode b=new TreeNode(4);
       TreeNode c=new TreeNode(8);
       TreeNode d=new TreeNode(11);
       TreeNode e=new TreeNode(13);
       TreeNode f=new TreeNode(4);
       TreeNode g=new TreeNode(7);
       TreeNode h=new TreeNode(2);
       TreeNode i=new TreeNode(1);
       a.left=b;a.right=c;
       b.left=d;
       c.left=e;c.right=f;
       d.left=g;d.right=h;
       f.right=i;
       System.out.println(s.hasPathSum(a,29));

   }
}
