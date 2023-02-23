import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class UserSolution {
    static Map<Integer, Post> posts;
    static boolean[][] following;

    static class Post {
        int pID;
        int uID;
        int timestamp;
        int like;

        Post(int pID, int uID, int timestamp) {
            this.pID = pID;
            this.uID = uID;
            this.timestamp = timestamp;
            this.like = 0;
        }
    }

    public void init(int N) {
        posts = new HashMap<>();
        following = new boolean[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            following[i][i] = true;
        }
    }

    public void follow(int uID1, int uID2, int timestamp) {
        following[uID1][uID2] = true;
    }

    public void makePost(int uID, int pID, int timestamp) {
        posts.put(pID, new Post(pID, uID, timestamp));
    }

    public void like(int pID, int timestamp) {
        posts.get(pID).like++;
    }

    public void getFeed(int uID, int timestamp, int pIDList[]) {
        PriorityQueue<Post> pq1 = new PriorityQueue<>((o1, o2) -> {
            if (o1.like == o2.like) {
                return o2.timestamp - o1.timestamp;
            }
            return o2.like - o1.like;
        });

        PriorityQueue<Post> pq2 = new PriorityQueue<>((o1, o2) -> o2.timestamp - o1.timestamp);

        for (Post post : posts.values()) {
            if (following[uID][post.uID]) {
                if (timestamp - 1000 <= post.timestamp) {
                    pq1.add(post);
                } else {
                    pq2.add(post);
                }
            }
        }

        int listSize = 0;
        while (listSize < 10 && !pq1.isEmpty()) {
            pIDList[listSize++] = pq1.poll().pID;
        }
        while (listSize < 10 && !pq2.isEmpty()) {
            pIDList[listSize++] = pq2.poll().pID;
        }
    }
}
