class UserSolution {
    public static class Queue {
        int MAX = 100;
        int front;
        int rear;
        Pair[] queue;

        Queue() {
            front = rear = 0;
            queue = new Pair[MAX];
        }

        boolean isQueueEmpty() {
            return front == rear;
        }

        void push(Pair pair) {
            queue[rear] = new Pair();
            queue[rear].key = pair.key;
            queue[rear].value = pair.value;
            rear++;
        }

        Pair pop() {
            return queue[front++];
        }
    }

    static class Pair {
        int key;
        int value;

        Pair() {
            this.key = -1;
            this.value = -1;
        }

        Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    int map_size;
    int[][] map;
    int[][] vis;
    final int[] dx = {1, 0, -1, 0};
    final int[] dy = {0, -1, 0, 1};

    void bfs_init(int map_size, int[][] map) {
        this.map_size = map_size;
        this.map = map;
    }

    int bfs(int x1, int y1, int x2, int y2) {
        Queue queue = new Queue();
        queue.push(new Pair(y1 - 1, x1 - 1));
        int result = -1;
        vis = new int[map_size][map_size];

        while (!queue.isQueueEmpty() && result == -1) {
            Pair cur = queue.pop();

            for (int i = 0; i < 4; i++) {
                int nx = cur.key + dx[i];
                int ny = cur.value + dy[i];

                if (nx < 0 || nx >= map_size || ny < 0 || ny >= map_size) continue;
                if (map[nx][ny] == 1) continue;
                if (vis[nx][ny] != 0) continue;

                vis[nx][ny] = vis[cur.key][cur.value] + 1;
                if (nx == y2 - 1 && ny == x2 - 1) {
                    result = vis[nx][ny];
                    break;
                }
                queue.push(new Pair(nx, ny));
            }
        }

        return result;
    }
}
