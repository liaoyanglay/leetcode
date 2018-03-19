/* 这题错在我开始用了inorder traversal，同一深度的节点next域没有全部初始化。
调整遍历顺序后AC */
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        root.next = null;
        TreeLinkNode first;
        for (first = root; first != null; ) {
            switch (checkChildNode(first)) {
                case 0:
                    first = first.next;
                    break;
                case 1:
                case 3:
                    myConnect(first);
                    first = first.left;
                    break;
                case 2:
                    myConnect(first);
                    first = first.right;
                    break;
            }
        }
    }
    
    private void myConnect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        TreeLinkNode next = root.next;
        switch (checkChildNode(root)) {
            case 0:
                break;
            case 1:
                while (true) {
                    if (next == null) {
                        root.left.next = null;
                        break;
                    }
                    if (checkChildNode(next) == 1 || checkChildNode(next) == 3) {
                        root.left.next = next.left;
                        break;
                    }
                    if (checkChildNode(next) == 2) {
                        root.left.next = next.right;
                        break;
                    }
                    next = next.next;
                }
                break;
            case 3:
                root.left.next = root.right;
            case 2:
                while (true) {
                    if (next == null) {
                        root.right.next = null;
                        break;
                    }
                    if (checkChildNode(next) == 1 || checkChildNode(next) == 3) {
                        root.right.next = next.left;
                        break;
                    }
                    if (checkChildNode(next) == 2) {
                        root.right.next = next.right;
                        break;
                    }
                    next = next.next;
                }
                break;
        }
        myConnect(root.next);
    }
    
    private int checkChildNode(TreeLinkNode root) {
        int i = 0;
        if (root.left != null)
            i=i+1;
        if (root.right != null) 
            i=i+2;
        return i;
    }
} 
