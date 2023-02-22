import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution {
    static int N;
    static long[] X;
    static long[] Y;
    static double E;
    static int[] parents;

    static class Edge implements Comparable<Edge> {
        int from, to;
        long cost;

        Edge(int from, int to, long cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws Exception {
//        System.setIn(new java.io.FileInputStream("/Users/hun/IdeaProjects/algorithm-java/src/re_sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testcase = 1; testcase <= T; ++testcase) {
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            long result = 0;
            int count = 0;
            N = Integer.parseInt(br.readLine());
            X = new long[N];
            Y = new long[N];
            parents = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                X[i] = Long.parseLong(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                Y[i] = Long.parseLong(st.nextToken());
            }

            E = Double.parseDouble(br.readLine());

            for (int i = 0; i < N; i++) {
                parents[i] = i;
            }

            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    long L = (long) (Math.pow(X[i] - X[j], 2) + Math.pow(Y[i] - Y[j], 2));
                    pq.add(new Edge(i, j, L));
                }
            }

            while (!pq.isEmpty()) {
                Edge e = pq.poll();

                if (union(e.from, e.to)) continue;
                result += e.cost;
                if (count == N - 1) break;
                count++;
            }

            System.out.println("#" + testcase + " " + Math.round(result * E));
        }

        br.close();
    }

    static int find(int a) {
        if (parents[a] == a) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);
        if (parentA != parentB) {
            parents[parentB] = parentA;
            return false;
        }
        return true;
    }
}
