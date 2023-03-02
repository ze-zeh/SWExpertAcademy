import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public static void main(String[] args) throws Exception {
//        System.setIn(new java.io.FileInputStream("/Users/hun/IdeaProjects/algorithm-java/src/s_input.txt"));
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            String[] arr = new String[N];

            for (int i = 0; i < N; i++) arr[i] = sc.next();

            System.out.println("#" + test_case);

            Arrays.stream(arr)
                    .distinct()
                    .sorted((o1, o2) -> {
                        if (o1.length() == o2.length()) {
                            return o1.compareTo(o2);
                        } else {
                            return o1.length() - o2.length();
                        }
                    })
                    .forEach(System.out::println);
        }
    }
}
