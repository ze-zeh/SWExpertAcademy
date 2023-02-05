import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    private static Node[] arr;

    static class Node {
        String data;
        int left;
        int right;

        Node(String data, int left, int right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new java.io.FileInputStream("/Users/hun/IdeaProjects/algorithm-java/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int testcase = 1; testcase <= 10; ++testcase) {
            arr = new Node[101];
            int n = Integer.parseInt(br.readLine());

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");

                int idx = Integer.parseInt(st.nextToken());
                int left = -1;
                int right = -1;
                String data = st.nextToken();

                if (st.hasMoreTokens()) {
                    left = Integer.parseInt(st.nextToken());
                }

                if (st.hasMoreTokens()) {
                    right = Integer.parseInt(st.nextToken());
                }

                arr[idx] = new Node(data, left, right);
            }

            System.out.println("#" + testcase + " " + inorderTraverse(arr[1]));
        }

        br.close();
    }

    static String inorderTraverse(Node node) {
        String result = "";

        if (node.left != -1)
            result += inorderTraverse(arr[node.left]);

        result += node.data;

        if (node.right != -1)
            result += inorderTraverse(arr[node.right]);

        return result;
    }
}
