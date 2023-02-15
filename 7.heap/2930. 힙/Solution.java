import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution {
    final static int MAX_SIZE = 100_000;
    static int N;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws Exception {
//        System.setIn(new java.io.FileInputStream("/Users/hun/IdeaProjects/algorithm-java/src/sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb;

        int T = Integer.parseInt(br.readLine());
        for (int testcase = 1; testcase <= T; ++testcase) {
            N = Integer.parseInt(br.readLine());
            pq = new PriorityQueue<>(MAX_SIZE, Collections.reverseOrder());
            sb = new StringBuilder();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                int command = Integer.parseInt(st.nextToken());
                if (command == 1) {
                    pq.add(Integer.parseInt(st.nextToken()));
                } else {
                    sb.append(" ");
                    if (pq.isEmpty()) {
                        sb.append(-1);
                    } else {
                        sb.append(pq.poll());
                    }
                }
            }

            System.out.println("#" + testcase + sb);
        }

        br.close();
    }
}
