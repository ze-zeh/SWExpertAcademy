import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution {
    static int N;
    static int M;
    static int[] parents;
    static PriorityQueue<Edge> pq;
    static List<Edge> path;

    static class Edge implements Comparable<Edge> {
        int from, to;
        int cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws Exception {
//        System.setIn(new java.io.FileInputStream("/Users/hun/IdeaProjects/algorithm-java/src/sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testcase = 1; testcase <= T; ++testcase) {
            long result = 0;
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            pq = new PriorityQueue<>();
            parents = new int[N + 1];
            path = new ArrayList<>();

            for (int i = 1; i <= N; i++) {
                parents[i] = i;
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                pq.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            kruskal();

            for (Edge edge : path) {
                result += edge.cost;
            }

            System.out.println("#" + testcase + " " + result);
        }

        br.close();
    }

    private static void kruskal() {
        for (int i = 0; i < M; i++) {
            Edge edge = pq.poll();
            int a = find(edge.from);
            int b = find(edge.to);

            if (a == b) continue;
            union(a, b);
            path.add(edge);
        }
    }

    static int find(int a) {
        if (parents[a] == a) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }

    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);
        if (parentA != parentB) {
            parents[parentB] = parentA;
        } else {
            parents[parentA] = parentB;
        }
    }
}
