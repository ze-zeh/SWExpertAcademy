Fail Code

// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.util.StringTokenizer;

// class Solution {
//     private static final int DEFAULT = 0;
//     static int n;
//     static int[] a;

//     static class SegmentTree {
//         private final int size;
//         private final int[] tree;

//         public SegmentTree() {
//             this.size = n;
//             this.tree = new int[size * 4];

//             buildRec(1, 0, n - 1);
//         }

//         private int buildRec(int node, int nodeLeft, int nodeRight) {
//             if (nodeLeft == nodeRight) return tree[node] = a[nodeLeft];

//             int mid = (nodeLeft + nodeRight) / 2;
//             int leftValue = buildRec(node * 2, nodeLeft, mid);
//             int rightValue = buildRec(node * 2 + 1, mid + 1, nodeRight);

//             return tree[node] = merge(leftValue, rightValue);
//         }

//         private int merge(int left, int right) {
//             return left + right;
//         }

//         public int query(int left, int right) {
//             return queryRec(left, right, 1, 0, size - 1);
//         }

//         private int queryRec(int left, int right, int node, int nodeLeft, int nodeRight) {
//             if (right < nodeLeft || nodeRight < left) return DEFAULT;
//             if (left <= nodeLeft && nodeRight <= right) return tree[node];

//             int mid = (nodeLeft + nodeRight) / 2;

//             return merge(queryRec(left, right, node * 2, nodeLeft, mid),
//                     queryRec(left, right, node * 2 + 1, mid + 1, nodeRight));
//         }

//         public void update(int index, int value) {
//             updateRec(index, value, 1, 0, size - 1);
//         }

//         private int updateRec(int index, int value, int node, int nodeLeft, int nodeRight) {
//             if (index < nodeLeft || nodeRight < index) return tree[node];
//             if (nodeLeft == nodeRight) return tree[node] = value;

//             int mid = (nodeLeft + nodeRight) / 2;
//             int leftValue = updateRec(index, value, node * 2, nodeLeft, mid);
//             int rightValue = updateRec(index, value, node * 2 + 1, mid + 1, nodeRight);

//             return tree[node] = merge(leftValue, rightValue);
//         }
//     }

//     public static void main(String[] args) throws Exception {
// //        System.setIn(new java.io.FileInputStream("/Users/hun/IdeaProjects/algorithm-java/src/sample_in.txt"));
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st;
//         StringBuilder sb;
//         int T = Integer.parseInt(br.readLine());

//         for (int test_case = 1; test_case <= T; test_case++) {
//             sb = new StringBuilder("#" + test_case);
//             st = new StringTokenizer(br.readLine());
//             n = Integer.parseInt(st.nextToken());
//             a = new int[n];
//             int q = Integer.parseInt(st.nextToken());

//             st = new StringTokenizer(br.readLine());
//             for (int i = 0; i < n; i++) {
//                 if (i % 2 == 0) {
//                     a[i] = Integer.parseInt(st.nextToken());
//                 } else {
//                     a[i] = -Integer.parseInt(st.nextToken());
//                 }
//             }

//             SegmentTree segmentTree = new SegmentTree();

//             for (int i = 0; i < q; i++) {
//                 st = new StringTokenizer(br.readLine());
//                 int command = Integer.parseInt(st.nextToken());
//                 int x = Integer.parseInt(st.nextToken());
//                 int y = Integer.parseInt(st.nextToken());

//                 if (command == 0) {
//                     if (x % 2 == 1) y = -y;
//                     segmentTree.update(x, y);
//                 } else if (command == 1) {
//                     int sum = segmentTree.query(x, y - 1);
//                     if (x % 2 == 1) sum = -sum;
//                     sb.append(" ").append(sum);
//                 }
//             }

//             System.out.println(sb);
//         }
//     }
// }
