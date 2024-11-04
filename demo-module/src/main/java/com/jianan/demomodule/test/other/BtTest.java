package com.jianan.demomodule.test.other;

import org.junit.Test;

/**
 * @Author: jn
 * @Date: 2024/10/31
 * @description
 **/
public class BtTest {
    class TreeNode {
        char val;
        TreeNode left;
        TreeNode right;

        TreeNode(char val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    public class BinaryTree {
        public int countNodesGreaterThanX(TreeNode root, char x) {
            if (root == null) {
                return 0;
            }

            int count = 0;
            if (root.val > x) {
                count++;
            }

            count += countNodesGreaterThanX(root.left, x);
            count += countNodesGreaterThanX(root.right, x);

            return count;
        }
    }

    @Test
    public void test() {
        // 构建二叉树
        TreeNode root = new TreeNode('D');
        root.left = new TreeNode('B');
        root.right = new TreeNode('F');
        root.left.left = new TreeNode('A');
        root.left.right = new TreeNode('C');
        root.right.left = new TreeNode('E');
        root.right.right = new TreeNode('G');

        // 创建 BinaryTree 实例
        BinaryTree binaryTree = new BinaryTree();

        // 给定值 x
        char x = 'C';

        // 计算大于 x 的结点个数
        int count = binaryTree.countNodesGreaterThanX(root, x);

        // 输出结果
        System.out.println("大于 " + x + " 的结点个数: " + count);
    }
}
