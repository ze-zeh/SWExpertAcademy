import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Solution {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int result;

    static class Cell implements Comparable<Cell> {
        int x, y;
        int time;

        Cell(int from, int to, int time) {
            this.x = from;
            this.y = to;
            this.time = time;
        }

        @Override
        public int compareTo(Cell o) {
            return this.time - o.time;
        }
    }

    public static void main(String[] args) throws Exception {
//        System.setIn(new java.io.FileInputStream("/Users/hun/IdeaProjects/algorithm-java/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int testcase = 1; testcase <= T; ++testcase) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new boolean[N][N];
            Solution.result = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = str.charAt(j) - '0';
                }
            }

            bfs();

            System.out.println("#" + testcase + " " + result);
        }

        br.close();
    }

    private static void bfs() {
        PriorityQueue<Cell> queue = new PriorityQueue<>();

        queue.add(new Cell(0, 0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Cell cur = queue.poll();

            if (cur.x == N - 1 && cur.y == N - 1) {
                result = Math.min(result, cur.time);
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (visited[nx][ny]) continue;

                queue.add(new Cell(nx, ny, cur.time + map[nx][ny]));
                visited[nx][ny] = true;
            }
        }
    }
}
