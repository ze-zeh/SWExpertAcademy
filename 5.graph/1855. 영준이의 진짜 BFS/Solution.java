import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    static class Node {
        int data;
        List<Node> children;

        Node(int data) {
            this.data = data;
            children = new ArrayList<>();
        }
    }

    static int N;
    static Node[] tree;
    static long result;
    static int[] depths;
    static int[][] parents;

    public static void main(String[] args) throws Exception {
        System.setIn(new java.io.FileInputStream("/Users/hun/IdeaProjects/algorithm-java/src/sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int testcase = 1; testcase <= T; ++testcase) {
            N = Integer.parseInt(br.readLine());
            tree = new Node[N + 1];
            tree[1] = new Node(1);
            depths = new int[N + 1];
            depths[1] = 0;
            result = 0L;
            parents = new int[N + 1][20];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 2; i <= N; i++) {
                int parent = Integer.parseInt(st.nextToken());
                depths[i] = depths[parent] + 1;
                tree[i] = new Node(i);
                tree[parent].children.add(tree[i]);
                parents[i][0] = parent;
            }

            for (int i = 1; i < 20; i++) {
                for (int j = 1; j <= N; j++) {
                    parents[j][i] = parents[parents[j][i - 1]][i - 1];
                }
            }

            bfs();

            System.out.println("#" + testcase + " " + result);
        }

        br.close();
    }

    static void bfs() {
        boolean[] visited = new boolean[N + 1];
        Queue<Node> queue = new LinkedList<>();
        queue.add(tree[1]);
        Node prev;
        Node cur = null;

        while (!queue.isEmpty()) {
            prev = cur;
            cur = queue.poll();
            visited[cur.data] = true;

            if (prev != null)
                result += getDistance(prev.data, cur.data);

            for (Node child : cur.children) {
                if (visited[child.data]) continue;

                queue.add(child);
            }
        }
    }

    private static long getDistance(int a, int b) {
        int commonParent = findLCA(a, b);
        return depths[a] + depths[b] - depths[commonParent] * 2L;
    }

    private static int findLCA(int a, int b) {
        if (a == 1 || b == 1) return 1;

        if (depths[a] < depths[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        for (int i = 19; i >= 0; i--) {
            if (depths[a] - depths[b] >= (1 << i)) {
                a = parents[a][i];
            }
        }

        if (a == b) return a;

        for (int i = 19; i >= 0; i--) {
            if (parents[a][i] != parents[b][i]) {
                a = parents[a][i];
                b = parents[b][i];
            }
        }

        return parents[a][0];
    }
}
