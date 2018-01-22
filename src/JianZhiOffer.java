import java.util.ArrayList;
import java.util.*;
public class JianZhiOffer {


    public class Solution {
        public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
            ArrayList<Integer> result=new ArrayList<Integer>();
            Queue<TreeNode> q=new ArrayDeque<TreeNode>();
            if(root==null)
                return result;
            q.add(root);
            while (q.isEmpty()==false)
            {
                TreeNode t=q.poll();
                result.add(t.val);
                if(t.left!=null)
                    q.add(t.left);
                if(t.right!=null)
                    q.add(t.right);
            }
            return result;

        }
    }
}
