import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    private static Node[] arr;
    private static boolean[] parents;

    static class Node {
        int subSize;
        int left = -1;
        int right = -1;
        int parent = -1;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new java.io.FileInputStream("/Users/hun/IdeaProjects/algorithm-java/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int testcase = 1; testcase <= T; ++testcase) {
            st = new StringTokenizer(br.readLine(), " ");
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr = new Node[v + 1];
            parents = new boolean[v + 1];

            st = new StringTokenizer(br.readLine(), " ");

            for (int i = 0; i <= v; i++) {
                arr[i] = new Node();
            }

            for (int i = 0; i < e; i++) {
                int parentNode = Integer.parseInt(st.nextToken());
                int childNode = Integer.parseInt(st.nextToken());

                if (arr[parentNode].left == -1) {
                    arr[parentNode].left = childNode;
                } else {
                    arr[parentNode].right = childNode;
                }

                arr[childNode].parent = parentNode;
            }

            postorderTraverse(arr[1]);
            int commonParent = findCommonParent(a, b);

            System.out.println("#" + testcase + " " + commonParent + " " + arr[commonParent].subSize);
        }

        br.close();
    }

    static int postorderTraverse(Node node) {
        int left = 0;
        int right = 0;

        if (node.left != -1) {
            left = postorderTraverse(arr[node.left]);
        }
        if (node.right != -1) {
            right = postorderTraverse(arr[node.right]);
        }
        node.subSize = left + right + 1;

        return node.subSize;
    }

    static int findCommonParent(int a, int b) {
        int commonParent;

        while (true) {
            if (a != 1) {
                int parent = arr[a].parent;
                if (parents[parent]) {
                    commonParent = parent;
                    break;
                }

                parents[parent] = true;
                a = parent;
            }

            if (b != 1) {
                int parent = arr[b].parent;
                if (parents[parent]) {
                    commonParent = parent;
                    break;
                }

                parents[parent] = true;
                b = parent;
            }
        }

        return commonParent;
    }
}
