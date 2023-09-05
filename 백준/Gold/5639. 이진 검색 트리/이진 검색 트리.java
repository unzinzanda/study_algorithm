import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        Node left;
        Node right;
        int data;

        public Node(Node left, Node right, int data) {
            this.left = left;
            this.right = right;
            this.data = data;
        }
    }

    static void numPlace(Node parent, int num) {

        if(parent.data > num) {
            if(parent.left != null) numPlace(parent.left, num);
            else {
                parent.left = new Node(null, null, num);
                return;
            }
        }
        else if(parent.data < num){
            if(parent.right != null) numPlace(parent.right, num);
            else {
                parent.right = new Node(null, null, num);
                return;
            }
        }
    }

    static void postOrder(Node cur) {
        if(cur == null) return;

        postOrder(cur.left);
        postOrder(cur.right);
        System.out.println(cur.data);
    }

    static Node root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean isRoot = true;

        while(true) {
            String str = br.readLine();
            if(str == null || str.equals("")) break;

            int num = Integer.parseInt(str);

            if(isRoot) {
                root = new Node(null, null, num);
                isRoot = false;
            }

            else {
                numPlace(root, num);
            }
        }

        postOrder(root);
    }
}