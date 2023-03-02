import java.util.HashMap;
import java.util.HashSet;

class UserSolution {
    static final int MAX_SIZE = 1_000;

    static HashMap<Integer, HashMap<Integer, Integer>> arr;
    static HashMap<Integer, HashMap<Integer, Integer>> reverse;
    static HashMap<Integer, HashMap<Integer, Integer>> dist;
    static int[] node;
    static int s;
    static int cost;

    public int init(int N, int[] sCity, int[] eCity, int[] mCost) {
        arr = new HashMap<>();
        reverse = new HashMap<>();
        dist = new HashMap<>();
        node = new int[MAX_SIZE];
        s = 0;

        for (int i = 0; i < N; i++) {
            if (arr.containsKey(sCity[i])) {
                arr.get(sCity[i]).put(eCity[i], mCost[i]);
            } else {
                arr.put(sCity[i], new HashMap<>());
                arr.get(sCity[i]).put(eCity[i], mCost[i]);
                node[s++] = sCity[i];
            }

            if (reverse.containsKey(eCity[i])) {
                reverse.get(eCity[i]).put(sCity[i], mCost[i]);
            } else {
                reverse.put(eCity[i], new HashMap<>());
                reverse.get(eCity[i]).put(sCity[i], mCost[i]);
            }
        }

        for (int i = 0; i < s; i++) dist.put(node[i], new HashMap<>());

        cost = s * 100 + 1;

        return s;
    }

    public void add(int sCity, int eCity, int mCost) {
        arr.get(sCity).put(eCity, mCost);
        reverse.get(eCity).put(sCity, mCost);
    }

    public int cost(int mHub) {
        int sum = 0;

        dijkstra(mHub, arr);
        for (int i = 0; i < s; i++) sum += dist.get(mHub).get(node[i]);

        dijkstra(mHub, reverse);
        for (int i = 0; i < s; i++) sum += dist.get(mHub).get(node[i]);

        return sum;
    }

    void dijkstra(int mHub, HashMap<Integer, HashMap<Integer, Integer>> arr) {
        HashSet<Integer> set = new HashSet<>();
        set.add(mHub);

        for (int i = 0; i < s; i++) {
            if (mHub == node[i]) {
                dist.get(mHub).put(node[i], 0);
            } else {
                dist.get(mHub).put(node[i], arr.get(mHub).getOrDefault(node[i], cost));
            }
        }

        for (int i = 0; i < s - 1; i++) {
            int cur = getMin(set, mHub);
            set.add(cur);

            for (int j = 0; j < s; j++) {
                if (set.contains(node[j])) continue;
                if (!arr.get(cur).containsKey(node[j])) continue;

                if (dist.get(mHub).get(node[j]) > dist.get(mHub).get(cur) + arr.get(cur).get(node[j])) {
                    dist.get(mHub).replace(node[j], dist.get(mHub).get(cur) + arr.get(cur).get(node[j]));
                }
            }
        }
    }

    int getMin(HashSet<Integer> set, int mHub) {
        int min = cost;
        int index = -1;

        for (int i = 0; i < s; i++) {
            if (set.contains(node[i])) continue;

            if (index < 0 || dist.get(mHub).get(node[i]) < min) {
                min = dist.get(mHub).get(node[i]);
                index = i;
            }
        }

        return node[index];
    }
}
