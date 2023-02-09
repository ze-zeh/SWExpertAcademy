import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    final static int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};
    final static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int result;

    public static void main(String[] args) throws Exception {
//        System.setIn(new java.io.FileInputStream("/Users/hun/IdeaProjects/algorithm-java/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int testcase = 1; testcase <= T; ++testcase) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new boolean[N][N];
            result = 0;

            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                for (int j = 0; j < N; j++) {
                    if (input.charAt(j) == '*') {
                        map[i][j] = -1;
                    }
                }
            }

            countMine();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 0 && !visited[i][j]) {
                        bfs(i, j);
                    }
                }
            }

            System.out.println("#" + testcase + " " + result);
        }

        br.close();
    }

    private static void countMine() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != -1) {
                    int count = 0;

                    for (int k = 0; k < 8; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                        if (map[nx][ny] == -1) count++;
                    }

                    map[i][j] = count;

                    if (count > 0) result++;
                }
            }
        }
    }

    static void bfs(int x, int y) {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(x, y));
        visited[x][y] = true;
        result++;

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();

            for (int i = 0; i < 8; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (visited[nx][ny]) continue;

                if (map[nx][ny] < 0) {
                    continue;
                } else if (map[nx][ny] == 0) {
                    queue.add(new Pair(nx, ny));
                } else {
                    result--;
                }

                visited[nx][ny] = true;
            }
        }
    }
}
