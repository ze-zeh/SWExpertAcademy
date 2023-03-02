import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
    static int min;

    public static void main(String[] args) throws Exception {
//        System.setIn(new java.io.FileInputStream("/Users/hun/IdeaProjects/algorithm-java/src/sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            min = Integer.MAX_VALUE;

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int c1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            int[] cow = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) cow[i] = Integer.parseInt(st.nextToken());
            Arrays.sort(cow);

            int count = 0;
            int check;
            int val;
            int horse;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                horse = Integer.parseInt(st.nextToken());
                check = min;
                val = binarySearch(cow, horse);
                if (check > min) count = val;
                else if (check == min) count += val;
            }

            int distance = Math.abs(c1 - c2) + min;
            System.out.println("#" + test_case + " " + distance + " " + count);
        }
    }

    static int binarySearch(int[] cow, int horse) {
        int start = 0;
        int end = cow.length;
        int cur;
        int mid;
        int val;
        int tmp = -1;
        int count = 0;

        while (start != end) {
            mid = start + ((end - start) >> 1);
            cur = cow[mid];
            val = Math.abs(cur - horse);

            if (cur < horse) {
                start = mid + 1;
            } else {
                end = mid;
            }

            if (min >= val) {
                min = val;
                tmp = mid;
            }
        }

        if (tmp != -1) {
            count = 1;

            if (tmp > 0) {
                if (Math.abs(cow[tmp - 1] - horse) == min) count++;
            }
            if (tmp < cow.length - 1) {
                if (Math.abs(cow[tmp + 1] - horse) == min) count++;
            }
        }
        return count;
    }
}
