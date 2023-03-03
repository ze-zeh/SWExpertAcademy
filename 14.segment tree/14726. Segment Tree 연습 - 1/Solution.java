import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    public static final int MAX = 0;
    public static final int MIN = 1;

    static int n;
    static int[] a;

    static class SegmentTree {
        private final int DEFAULT_VALUE;
        private final int type, size;
        private final int[] tree;

        public SegmentTree(int type) {
            this.size = n;
            this.type = type;
            this.tree = new int[size * 4];

            if (type == MAX) {
                DEFAULT_VALUE = Integer.MIN_VALUE;
            } else {
                DEFAULT_VALUE = Integer.MAX_VALUE;
            }

            buildRec(1, 0, size - 1);
        }

        private int buildRec(int node, int nodeLeft, int nodeRight) {
            if (nodeLeft == nodeRight) return tree[node] = a[nodeLeft];

            int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
            int leftValue = buildRec(node * 2, nodeLeft, mid);
            int rightValue = buildRec(node * 2 + 1, mid + 1, nodeRight);

            return tree[node] = merge(leftValue, rightValue);
        }

        private int merge(int left, int right) {
            if (type == MAX) {
                return Math.max(left, right);
            } else {
                return Math.min(left, right);
            }
        }

        public int query(int left, int right) {
            return queryRec(left, right, 1, 0, size - 1);
        }

        public void update(int index, int newValue) {
            updateRec(index, newValue, 1, 0, size - 1);
        }

        private int updateRec(int index, int value, int node, int nodeLeft, int nodeRight) {
            if (index < nodeLeft || nodeRight < index) return tree[node];
            if (nodeLeft == nodeRight) return tree[node] = value;

            int mid = (nodeLeft + nodeRight) / 2;
            int left = updateRec(index, value, node * 2, nodeLeft, mid);
            int right = updateRec(index, value, node * 2 + 1, mid + 1, nodeRight);

            return tree[node] = merge(left, right);
        }

        private int queryRec(int left, int right, int node, int nodeLeft, int nodeRight) {
            if (right < nodeLeft || nodeRight < left) return DEFAULT_VALUE;
            if (left <= nodeLeft && nodeRight <= right) return tree[node];

            int mid = (nodeLeft + nodeRight) / 2;

            return merge(queryRec(left, right, node * 2, nodeLeft, mid),
                    queryRec(left, right, node * 2 + 1, mid + 1, nodeRight));
        }
    }

    public static void main(String[] args) throws Exception {
//        System.setIn(new java.io.FileInputStream("/Users/hun/IdeaProjects/algorithm-java/src/sample_in.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb;
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            sb = new StringBuilder("#" + test_case);
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            a = new int[n];
            int q = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) a[i] = Integer.parseInt(st.nextToken());

            SegmentTree maxST = new SegmentTree(MAX);
            SegmentTree minST = new SegmentTree(MIN);

            for (int i = 0; i < q; i++) {
                st = new StringTokenizer(br.readLine());
                int command = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                if (command == 0) {
                    maxST.update(x, y);
                    minST.update(x, y);
                } else if (command == 1) {
                    int max = maxST.query(x, y - 1);
                    int min = minST.query(x, y - 1);
                    sb.append(" ").append(max - min);
                }
            }

            System.out.println(sb);
        }
    }
}
