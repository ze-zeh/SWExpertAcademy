import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

	private static BufferedReader br;
	private static UserSolution usersolution = new UserSolution();
	
	private final static int INIT         = 0;
	private final static int SENDMAIL     = 1;
	private final static int GETCOUNT     = 2;
	private final static int DELETEMAIL   = 3;
	private final static int SEARCHMAIL   = 4;
	
	private final static int MAX_WORD     = 10000;
	
	private static char Word[][] = new char [MAX_WORD][11];
	private static char subjectStr[] = new char [200];
	private static int rids[] = new int [50];

	private static int U, W, minR, maxR, SW;

	private static int mSeed;
	private static int pseudo_rand()
	{
		mSeed = mSeed * 214013 + 2531011;
		return (mSeed >> 16) & 0x7FFF;
	}

	private static void make_string(int seed)
	{
		mSeed = seed;

		for (int i = 0; i < W; ++i) {
			int length = 5 + pseudo_rand() % 6;
			for (int k = 0; k < length; ++k) {
				Word[i][k] = (char)('a' + pseudo_rand() % 26);
			}
			Word[i][length] = '\0';
		}
	}

	private static void send_mail(int seed)
	{
		mSeed = seed;

		int pos = 0;
		int wcnt = 1 + pseudo_rand() % SW;
		for (int i = 0; i < wcnt; ++i) {
			int widx = pseudo_rand() % W;
			for (int k = 0; k < 10; ++k) {
				if (Word[widx][k] == '\0') break;
				subjectStr[pos++] = Word[widx][k];
			}
			subjectStr[pos++] = ' ';
		}
		subjectStr[pos - 1] = '\0';

		int uid = pseudo_rand() % U;
		int rcnt = minR + pseudo_rand() % (maxR - minR + 1);
		for (int i = 0; i < rcnt; ++i) {
			rids[i] = pseudo_rand() % U;
		}

		usersolution.sendMail(subjectStr, uid, rcnt, rids);
	}

	static int delete_mail(int uid, int seed)
	{
		mSeed = seed;
		
		int pos = 0;
		int wcnt = 1 + pseudo_rand() % SW;
		for (int i = 0; i < wcnt; ++i) {
			int widx = pseudo_rand() % W;
			for (int k = 0; k < 10; ++k) {
				if (Word[widx][k] == '\0') break;
				subjectStr[pos++] = Word[widx][k];
			}
			subjectStr[pos++] = ' ';
		}
		subjectStr[pos - 1] = '\0';

		return usersolution.deleteMail(uid, subjectStr);
	}
	
	private static int run(int answer) throws Exception {
		int Q, K, cmd, sample, seed, param1, param2, ret;
		int uid, rcnt;
		char buf[] = new char [30];
		
		String inputStr;		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		Q = Integer.parseInt(st.nextToken());
		sample = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		if (sample == 0) {
			W = Integer.parseInt(st.nextToken());
			minR = Integer.parseInt(st.nextToken());
			maxR = Integer.parseInt(st.nextToken());
			SW = Integer.parseInt(st.nextToken());
			seed = Integer.parseInt(st.nextToken());
			make_string(seed);
		}

		usersolution.init(U, K);

		for (int i = 1; i < Q; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			cmd = Integer.parseInt(st.nextToken());
			switch (cmd) {
			case SENDMAIL:
				if (sample == 1) {
					inputStr = st.nextToken();
					uid = Integer.parseInt(st.nextToken());
					rcnt = Integer.parseInt(st.nextToken());
					for (int k = 0; k < rcnt; ++k) rids[k] = Integer.parseInt(st.nextToken());
					for (int k = 0; k < inputStr.length(); ++k) {
						buf[k] = inputStr.charAt(k);
						if (buf[k] == '_') buf[k] = ' ';
					}
					buf[inputStr.length()] = '\0';
					usersolution.sendMail(buf, uid, rcnt, rids);
				}
				else {
					seed = Integer.parseInt(st.nextToken());
					send_mail(seed);
				}
				break;
			case GETCOUNT:
				param1 = Integer.parseInt(st.nextToken());
				param2 = Integer.parseInt(st.nextToken());
				ret = usersolution.getCount(param1);
				if (ret != param2) answer = 0;
				break;
			case DELETEMAIL:
				if (sample == 1) {
					param1 = Integer.parseInt(st.nextToken());
					inputStr = st.nextToken();
					param2 = Integer.parseInt(st.nextToken());
					for (int k = 0; k < inputStr.length(); ++k) {
						buf[k] = inputStr.charAt(k);
						if (buf[k] == '_') buf[k] = ' ';
					}
					buf[inputStr.length()] = '\0';
					ret = usersolution.deleteMail(param1, buf);
					if (ret != param2) answer = 0;
				}
				else {
					param1 = Integer.parseInt(st.nextToken());
					seed = Integer.parseInt(st.nextToken());
					param2 = Integer.parseInt(st.nextToken());
					ret = delete_mail(param1, seed);
					if (ret != param2) answer = 0;
				}
				break;
			case SEARCHMAIL:
				param1 = Integer.parseInt(st.nextToken());
				inputStr = st.nextToken();
				param2 = Integer.parseInt(st.nextToken());
				for (int k = 0; k < inputStr.length(); ++k) {
					buf[k] = inputStr.charAt(k);
				}
				buf[inputStr.length()] = '\0';
				ret = usersolution.searchMail(param1, buf);
				if (ret != param2) answer = 0;
				break;
			default:
				break;
			}
		}

		return answer;
	}

	public static void main(String[] args) throws Exception {
		int T, Mark;

		//System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stinit = new StringTokenizer(br.readLine(), " ");
		T = Integer.parseInt(stinit.nextToken());
		Mark = Integer.parseInt(stinit.nextToken());
		
		for (int tc = 1; tc <= T; tc++) {
			System.out.println("#" + tc + " " + run(Mark));
		}
		
		br.close();
	}
}
