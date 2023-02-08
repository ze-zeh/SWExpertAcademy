import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N;
    static boolean[][] processor;
    static boolean[][] visited;
    static Pair[] core;
    static int coreSize;
    static int maxCoreCount;
    static int minWireCount;
    final static int[] dx = {1, 0, -1, 0};
    final static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        System.setIn(new java.io.FileInputStream("/Users/hun/IdeaProjects/algorithm-java/src/sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int testcase = 1; testcase <= T; ++testcase) {
            N = Integer.parseInt(br.readLine());
            processor = new boolean[N][N];
            visited = new boolean[N][N];
            core = new Pair[12];
            coreSize = 0;
            maxCoreCount = Integer.MIN_VALUE;
            minWireCount = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    if (Integer.parseInt(st.nextToken()) == 1) {
                        processor[i][j] = true;
                        if (i != 0 && i != N - 1 && j != 0 && j != N - 1) {
                            core[coreSize++] = new Pair(i, j);
                        }
                    }
                }
            }

            dfs(0, 0, 0);
            System.out.println("#" + testcase + " " + minWireCount);
        }

        br.close();
    }

    static void dfs(int idx, int wireCount, int connectedCoreCount) {
        if (idx == coreSize) {
            if (maxCoreCount < connectedCoreCount) {
                maxCoreCount = connectedCoreCount;
                minWireCount = wireCount;
            } else if (maxCoreCount == connectedCoreCount) {
                minWireCount = Math.min(minWireCount, wireCount);
            }
            return;
        }

        Pair cur = core[idx];

        for (int i = 0; i < 4; i++) {
            if (!checkConnectable(cur, new Pair(dx[i], dy[i]))) continue;

            int count = visit(cur, new Pair(dx[i], dy[i]), true);
            dfs(idx + 1, wireCount + count, connectedCoreCount + 1);
            visit(cur, new Pair(dx[i], dy[i]), false);
        }

        dfs(idx + 1, wireCount, connectedCoreCount);
    }

    static boolean checkConnectable(Pair xy, Pair d) {
        int nx = xy.x;
        int ny = xy.y;

        while (true) {
            nx += d.x;
            ny += d.y;

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) return true;
            if (visited[nx][ny] || processor[nx][ny]) return false;
        }
    }

    static int visit(Pair xy, Pair d, boolean isVisited) {
        int nx = xy.x;
        int ny = xy.y;
        int count = 0;

        while (true) {
            nx += d.x;
            ny += d.y;

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) return count;

            count++;
            visited[nx][ny] = isVisited;
        }
    }
}
