import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

	private static int mSeed;
	private static int pseudo_rand()
	{
		mSeed = mSeed * 431345 + 2531999;
		return mSeed & 0x7FFFFFFF;
	}

	private static int follow_status[][] = new int[1005][1005];
	private static int answer_score;
	private static int n; // n >= 5 && n <= 1000
	private static int end_timestamp;
	private static int follow_ratio; // follow_ratio >= 1 && follow_ratio <= 10000
	private static int make_ratio; // make_ratio >= 1 && make_ratio <= 10000
	private static int like_ratio; // like_ratio >= 1 && like_ratio <= 10000
	private static int get_feed_ratio; // get_feed_ratio >= 1 && get_feed_ratio <= 10000
	private static int post_arr[] = new int[200000];
	private static int total_post_cnt;
	private static int min_post_cnt;
	
	private static BufferedReader br;
	private static UserSolution user = new UserSolution();

	private static boolean run() throws Exception
	{
		int uId1;
		int uId2;
		int pId;
		int pIdList[] = new int[10];
		int ans_pIdList[] = new int[10];
		int rand_val;
		boolean ret = true;

		StringTokenizer stdin = new StringTokenizer(br.readLine(), " ");
		mSeed = Integer.parseInt(stdin.nextToken());
		n = Integer.parseInt(stdin.nextToken());
		end_timestamp = Integer.parseInt(stdin.nextToken());
		follow_ratio = Integer.parseInt(stdin.nextToken());
		make_ratio = Integer.parseInt(stdin.nextToken());
		like_ratio = Integer.parseInt(stdin.nextToken());
		get_feed_ratio = Integer.parseInt(stdin.nextToken());
		
		user.init(n);

		for (uId1 = 1; uId1 <= n; uId1++)
		{
			follow_status[uId1][uId1] = 1;
			int m = n / 10 + 1;
			if (m > 10)
				m = 10;
			for (int i = 0; i < m; i++)
			{
				uId2 = uId1;
				while (follow_status[uId1][uId2] == 1)
				{
					uId2 = pseudo_rand() % n + 1;
				}
				user.follow(uId1, uId2, 1);
				follow_status[uId1][uId2] = 1;
			}
		}
		min_post_cnt = total_post_cnt = 1;

		for (int timestamp = 1; timestamp <= end_timestamp; timestamp++)
		{
			rand_val = pseudo_rand() % 10000;
			if (rand_val < follow_ratio)
			{
				uId1 = pseudo_rand() % n + 1;
				uId2 = pseudo_rand() % n + 1;
				int lim = 0;
				while (follow_status[uId1][uId2] == 1 || uId1 == uId2)
				{
					uId2 = pseudo_rand() % n + 1;
					lim++;
					if (lim >= 5)
						break;
				}
				if (follow_status[uId1][uId2] == 0)
				{
					user.follow(uId1, uId2, timestamp);
					follow_status[uId1][uId2] = 1;
				}
			}
			rand_val = pseudo_rand() % 10000;

			if (rand_val < make_ratio)
			{
				uId1 = pseudo_rand() % n + 1;
				post_arr[total_post_cnt] = timestamp;

				user.makePost(uId1, total_post_cnt, timestamp);
				total_post_cnt += 1;
			}

			rand_val = pseudo_rand() % 10000;

			if (rand_val < like_ratio && total_post_cnt - min_post_cnt > 0)
			{
				while (post_arr[min_post_cnt] < timestamp - 1000 && min_post_cnt < total_post_cnt)
					min_post_cnt++;

				if (total_post_cnt != min_post_cnt)
				{
					pId = pseudo_rand() % (total_post_cnt - min_post_cnt) + min_post_cnt;
					user.like(pId, timestamp);
				}
			}

			rand_val = pseudo_rand() % 10000;
			if (rand_val < get_feed_ratio && total_post_cnt > 0)
			{
				uId1 = pseudo_rand() % n + 1;
				user.getFeed(uId1, timestamp, pIdList);
				stdin = new StringTokenizer(br.readLine(), " ");

				for (int i = 0; i < 10; i++)
				{
					ans_pIdList[i] = Integer.parseInt(stdin.nextToken()); 
				}

				for (int i = 0; i < 10; i++)
				{
					if (ans_pIdList[i] == 0)
						break;

					if (ans_pIdList[i] != pIdList[i])
					{
						ret = false;
					}
				}
			}
		}

		return ret;
	}
	

	public static void main(String[] args) throws Exception {
//		System.setIn(new java.io.FileInputStream("/Users/hun/IdeaProjects/algorithm-java/src/sample_sample_input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc;
		StringTokenizer stdin = new StringTokenizer(br.readLine(), " ");
		tc = Integer.parseInt(stdin.nextToken());
		answer_score = Integer.parseInt(stdin.nextToken());

		for (int t = 1; t <= tc; t++)
		{
			int score;
			for (int i = 0; i < 1005; i++)
				for (int j = 0; j < 1005; j++)
					follow_status[i][j] = 0;

			if (run())
				score = answer_score;
			else
				score = 0;

			System.out.println("#" + t + " " + score);
		}
	}
}
