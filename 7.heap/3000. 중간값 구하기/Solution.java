import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution {
    final static int MAX_SIZE = 200_000;
    final static int LIMIT = 20171109;
    static int N;
    static int A;
    static int result;
    static PriorityQueue<Integer> minHeap;
    static PriorityQueue<Integer> maxHeap;

    public static void main(String[] args) throws Exception {
//        System.setIn(new java.io.FileInputStream("/Users/hun/IdeaProjects/algorithm-java/src/sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testcase = 1; testcase <= T; ++testcase) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            result = 0;
            minHeap = new PriorityQueue<>(MAX_SIZE);
            maxHeap = new PriorityQueue<>(MAX_SIZE, Collections.reverseOrder());

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                int X = Integer.parseInt(st.nextToken());
                if (X > A) {
                    minHeap.add(X);
                } else {
                    maxHeap.add(X);
                }

                int Y = Integer.parseInt(st.nextToken());
                if (minHeap.size() > maxHeap.size()) {
                    if (Y > A) {
                        maxHeap.add(A);
                        minHeap.add(Y);
                        A = minHeap.poll();
                    } else {
                        maxHeap.add(Y);
                    }
                } else {
                    if (Y > A) {
                        minHeap.add(Y);
                    } else {
                        minHeap.add(A);
                        maxHeap.add(Y);
                        A = maxHeap.poll();
                    }
                }

                result = (result + A) % LIMIT;
            }

            System.out.println("#" + testcase + " " + result);
        }

        br.close();
    }
}
