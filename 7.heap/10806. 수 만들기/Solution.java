import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution {
    static int N;
    static int K;
    static int[] A;
    static PriorityQueue<Pair> heap;
    static int result;

    static class Pair implements Comparable<Pair> {
        int left;
        int cnt;

        public Pair(int left, int cnt) {
            this.left = left;
            this.cnt = cnt;
        }

        public int compareTo(Pair n) {
            return Integer.compare(this.cnt, n.cnt);
        }
    }

    public static void main(String[] args) throws Exception {
//        System.setIn(new java.io.FileInputStream("/Users/hun/IdeaProjects/algorithm-java/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testcase = 1; testcase <= T; ++testcase) {
            N = Integer.parseInt(br.readLine());
            heap = new PriorityQueue<>(N);
            A = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            K = Integer.parseInt(br.readLine());
            heap.add(new Pair(K, 0));
            result = K;

            while (!heap.isEmpty()) {
                Pair cur = heap.poll();

                if (cur.left == 0) {
                    result = cur.cnt;
                    break;
                }

                heap.add(new Pair(0, cur.left + cur.cnt));

                for (int i = 0; i < N; i++) {
                    heap.add(new Pair(cur.left / A[i], cur.cnt + cur.left % A[i]));
                }
            }

            System.out.println("#" + testcase + " " + result);
        }

        br.close();
    }
}
