import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

    public static void main(String[] args) throws Exception {
//        System.setIn(new java.io.FileInputStream("/Users/hun/IdeaProjects/algorithm-java/src/sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            long M = Integer.parseInt(st.nextToken());
            long[] A = new long[N];
            long left = 1L;
            long right = 0L;
            long mid;
            long sum;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                A[i] = Long.parseLong(st.nextToken());
                right = Math.max(right, A[i]);
            }

            while (left <= right) {
                sum = 0;
                mid = (left + right) / 2;

                for (int i = 0; i < N; i++) sum += (A[i] / mid);

                if (sum < M) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            System.out.println("#" + test_case + " " + right);
        }
    }
}
