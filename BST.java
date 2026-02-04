import java.io.*;
import java.util.*;

public class BST
{
    /**
     *  Problem: Perform rotations on tree1 to make it equivalent to tree2.
     */
    public static void problem(BST tree1, BST tree2)
    {
        Queue<Node> queue = new LinkedList<>();

        // We will add tree two nodes since we want tree one to look like tree two
        queue.add(tree2.root);

        
        while (!queue.isEmpty()) {


            // Remove node from queue
            Node nodeToProcess = queue.poll();

            // This is guaranteed to be found since both trees have the same nodes
            Node nodeToFind = tree1.find(nodeToProcess.key);
            while (true) {

                // Since we want the structure to be the same, one way to ensure this is if the nodes have the same parents.
                Node p1 = nodeToFind.parent; 
                Node p2 = nodeToProcess.parent; 

                // If they have no parents, that means they are the root nodes. We break
                if (p1 == null && p2 == null) {
                    break;
                }

                
                // If they are not the root and the parents are the same, we know they are in the same place
                if (p1 != null && p2 != null && p1.key == p2.key) {
                    break;
                }


                // We will rotate the parent node in tree one to the right if it is greater than the parent node in tree two. 
                if (p1.key > nodeToFind.key) {
                    tree1.rotateR(p1);
                }

                // Other wise, we will left rotate the parent
                else {
                    tree1.rotateL(p1);
                }


            }

            
            // Add right and left children of current node being processed in tree two
            if (nodeToProcess.left != null) {
                queue.add(nodeToProcess.left);
            }

            if (nodeToProcess.right != null) {
                queue.add(nodeToProcess.right);
            }


        }


        
    }

    // ---------------------------------------------------------------------
    // Do not change any of the code below!

    private class Node
    {
        public Node left = null;
        public Node right = null;
        public Node parent = null;

        public int key;

        public Node(int key)
        {
            this.key = key;
        }
    }

    private Node root = null;

    public int getRootKey()
    {
        return root.key;
    }

    private Node find(int key)
    {
        for (Node cur = root; cur != null;)
        {
            if (key < cur.key)
            {
                cur = cur.left;
            }
            else if (key == cur.key)
            {
                return cur;
            }
            else // key > cur.key
            {
                cur = cur.right;
            }
        }

        return null;
    }

    //     x            y
    //    / \          / \
    //   a   y   =>   x   c
    //      / \      / \
    //     b   c    a   b
    private void rotateL(Node xNode)
    {
        Node xPar = xNode.parent;
        boolean isRoot = xPar == null;
        boolean isLChild = !isRoot && xPar.left == xNode;

        Node yNode = xNode.right;
        Node beta = yNode.left;

        if (isRoot) root = yNode;
        else if (isLChild) xPar.left = yNode;
        else xPar.right = yNode;

        yNode.parent = xPar;
        yNode.left = xNode;

        xNode.parent = yNode;
        xNode.right = beta;

        if (beta != null) beta.parent = xNode;
    }

    //     y        x
    //    / \      / \
    //   x   c => a   y
    //  / \          / \
    // a   b        b   c
    private void rotateR(Node yNode)
    {
        Node yPar = yNode.parent;
        boolean isRoot = yPar == null;
        boolean isLChild = !isRoot && yPar.left == yNode;

        Node xNode = yNode.left;
        Node beta = xNode.right;

        if (isRoot) root = xNode;
        else if (isLChild) yPar.left = xNode;
        else yPar.right = xNode;

        xNode.parent = yPar;
        xNode.right = yNode;

        yNode.parent = xNode;
        yNode.left = beta;

        if (beta != null) beta.parent = yNode;
    }
    public void rotateLeftAtRoot() {
        if (root != null) rotateL(root);
    }

    public void rotateRightAtRoot() {
        if (root != null) rotateR(root);
    }

    public void printPreOrder() {
        printPreOrder(root);
        System.out.println();
    }

    private void printPreOrder(Node n) {
        if (n == null) {
            System.out.print("null ");
            return;
        }
        System.out.print(n.key + " ");
        printPreOrder(n.left);
        printPreOrder(n.right);
    }

    public void insert(int key)
    {
        if (root == null)
        {
            root = new Node(key);
            return;
        }

        Node par = null;

        for (Node node = root; node != null;)
        {
            par = node;

            if (key < node.key)
            {
                node = node.left;
            }
            else if (key > node.key)
            {
                node = node.right;
            }
            else // key == node.key
            {
                // Nothing to do, because no value to update.
                return;
            }
        }

        // Create node and set pointers.
        Node newNode = new Node(key);
        newNode.parent = par;

        if (key < par.key) par.left = newNode;
        else par.right = newNode;
    }

    public int[] getInOrder()
    {
        if (root == null) return new int[] { };

        Stack<Node> stack = new Stack<Node>();
        ArrayList<Integer> orderList = new ArrayList<Integer>();

        for (Node node = root;;)
        {
            if (node == null)
            {
                if (stack.empty()) break;

                node = stack.pop();
                orderList.add(node.key);
                node = node.right;
            }
            else
            {
                stack.push(node);
                node = node.left;
            }
        }

        int[] order = new int[orderList.size()];
        for (int i = 0; i < order.length; i++)
        {
            order[i] = orderList.get(i);
        }

        return order;
    }

    public int[] getPreOrder()
    {
        if (root == null) return new int[] { };

        Stack<Node> stack = new Stack<Node>();
        ArrayList<Integer> orderList = new ArrayList<Integer>();

        for (Node node = root;;)
        {
            if (node == null)
            {
                if (stack.empty()) break;

                node = stack.pop();
                node = node.right;
            }
            else
            {
                orderList.add(node.key);
                stack.push(node);
                node = node.left;
            }
        }

        int[] order = new int[orderList.size()];
        for (int i = 0; i < order.length; i++)
        {
            order[i] = orderList.get(i);
        }

        return order;
    }
}
