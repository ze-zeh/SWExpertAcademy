import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
    private static Node[] arr;
    private static final String[] operation = {"+", "-", "*", "/"};
    private static boolean isValid;

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
            arr = new Node[1001];
            int n = Integer.parseInt(br.readLine());
            isValid = true;

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
            inorderTraverse(arr[1]);
            if (isValid) {
                System.out.println("#" + testcase + " 1");
            } else {
                System.out.println("#" + testcase + " 0");
            }
        }

        br.close();
    }

    static String inorderTraverse(Node node) {
        if (node.left == -1 && node.right == -1) {
            if (Arrays.asList(operation).contains(node.data)) {
                isValid = false;
                return "0";
            }
        }

        if (!isValid) {
            return "0";
        }

        if (node.left != -1 && node.right != -1) {
            String left = inorderTraverse(arr[node.left]);
            String right = inorderTraverse(arr[node.right]);

            if (Arrays.asList(operation).contains(new String[]{left, right})) {
                return "0";
            }
            if (!Arrays.asList(operation).contains(node.data)) {
                return "0";
            }
        }

        return "1";
    }
}
