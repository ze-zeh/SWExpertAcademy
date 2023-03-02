import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    static long ans;
    static int[] buf;

    public static void main(String[] args) throws Exception {
//        System.setIn(new java.io.FileInputStream("/Users/hun/IdeaProjects/algorithm-java/src/sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            buf = new int[N];
            ans = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

            mergeSort(arr, 0, N);

            System.out.println("#" + test_case + " " + ans);
        }
    }

    static void mergeSort(int[] arr, int begin, int end) {
        if (end - begin <= 1) return;

        int mid = begin + (end - begin) / 2;
        int i = begin;
        int j = mid;
        int k = begin;

        mergeSort(arr, begin, mid);
        mergeSort(arr, mid, end);

        while (i != mid && j != end) {
            if (arr[i] > arr[j]) {
                ans += mid - i;
                buf[k++] = arr[j++];
            } else {
                buf[k++] = arr[i++];
            }
        }

        while (j != end) buf[k++] = arr[j++];
        while (i != mid) buf[k++] = arr[i++];

        for (i = begin; i < end; i++) arr[i] = buf[i];
    }
}
