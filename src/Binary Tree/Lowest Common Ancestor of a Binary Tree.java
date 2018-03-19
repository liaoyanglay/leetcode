 /*开始尝试了把两个子节点的路径记录下来比较，但测试用例中有一例庞大的输入会造成StackOverFlow。 
 还有就是我最开始是用val值来判断节点是否相等，结果测试用例会出现重复的值。
 改为直接判断node的引用是否相同后，Accept */
 /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        Boolean pleft = findPath(root.left,p);
        Boolean qleft = findPath(root.left,q);
        if (pleft && qleft) {
            return lowestCommonAncestor(root.left,p,q);
        } else if (!pleft && !qleft) {
            return lowestCommonAncestor(root.right,p,q);
        } else {
            return root;
        }
    }
    
    private boolean findPath(TreeNode root,TreeNode p) {
        if (root == null) {
            return false;
        }
        if (root == p) {
            return true;
        } else {
            if (root.left != null) {
                if (findPath(root.left,p)) {
                    return true;
                }
            }
            if (root.right != null) {
                if (findPath(root.right,p)) {
                    return true;
                }
            }
        }
        return false;
    }
}
