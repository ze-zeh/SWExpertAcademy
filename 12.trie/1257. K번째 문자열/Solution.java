import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

class Solution {
    public static void main(String[] args) throws Exception {
//        System.setIn(new java.io.FileInputStream("/Users/hun/IdeaProjects/algorithm-java/src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int K = Integer.parseInt(br.readLine());
            String str = br.readLine();
            String[] arr = new String[str.length()];

            for (int i = 0; i < str.length(); i++) arr[i] = str.substring(i);
            Arrays.sort(arr);

            System.out.println("#" + test_case + " " + find(K, arr));
        }
    }

    static String find(int n, String[] arr) {
        HashSet<String> check = new HashSet<>();

        for (String str : arr) {
            for (int j = 0; j < str.length(); j++) {
                if (check.add(str.substring(0, j + 1))) n--;
                if (n == 0) return str.substring(0, j + 1);
            }
        }

        return "none";
    }
}
