import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Solution {
    private static boolean[] check;
    private static int p;

    public static void main(String[] args) throws Exception {
//        System.setIn(new java.io.FileInputStream("/Users/hun/IdeaProjects/algorithm-java/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            List<Integer> list = new ArrayList<>();
            int n = Integer.parseInt(st.nextToken());
            int max = Integer.MIN_VALUE;
            p = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int num = Integer.parseInt(st.nextToken());
                max = Math.max(max, num);
                list.add(num);
            }

            check = new boolean[max + 1];

            for (int i : list) check[i] = true;

            System.out.println("#" + test_case + " " + solution());
        }
    }

    private static int solution() {
        int left = 0;
        int right = 0;
        int count = 0;
        int length = 0;
        int maxLen = p + 1;

        while (right < check.length) {
            if (check[right]) {
                length++;
                right++;
            } else {
                if (count == p) {
                    if (!check[left++]) count--;
                    length--;
                } else {
                    count++;
                    length++;
                    right++;
                }
            }

            maxLen = Math.max(maxLen, length);
        }

        return maxLen;
    }
}
