public class UserSolution {
    private final static int MAX_INPUT = 100000;
    private final static int UID = 0;
    private final static int INCOME = 1;

    int[][] users;
    int[][] backup;
    int userCount;

    public void init() {
        users = new int[MAX_INPUT + 1][2];
        userCount = 0;
    }

    public void addUser(int uID, int income) {
        int i = ++userCount;
        users[userCount][UID] = uID;
        users[userCount][INCOME] = income;

        while (i > 1) {
            if (users[i][INCOME] <= users[i / 2][INCOME] &&
                    !(users[i][INCOME] == users[i / 2][INCOME] && users[i][UID] < users[i / 2][UID])) {
                break;
            }

            int[] tmp = users[i].clone();
            users[i] = users[i / 2].clone();
            users[i / 2] = tmp.clone();
            i /= 2;
        }
    }

    int getTop10(int[] result) {
        int count = userCount;
        int ans = 10;
        backup = new int[10][2];

        for (int i = 1; i <= 10; i++) {
            if (count < i) {
                ans = count;
                break;
            }

            int j = 1;
            result[i - 1] = users[1][UID];
            backup[i - 1] = users[1];
            users[1] = users[userCount].clone();
            userCount--;

            while (j * 2 <= userCount) {
                int idx;

                if (j * 2 + 1 > userCount) {
                    if (users[j][INCOME] > users[j * 2][INCOME]
                            || (users[j][INCOME] == users[j * 2][INCOME] && users[j][UID] < users[j * 2][UID])) {
                        break;
                    }

                    idx = j * 2;
                } else {
                    if (users[j][INCOME] > users[j * 2][INCOME] && users[j][INCOME] > users[j * 2 + 1][INCOME]) {
                        break;
                    }

                    if (users[j * 2][INCOME] > users[j * 2 + 1][INCOME]) {
                        idx = j * 2;
                    } else if (users[j * 2][INCOME] < users[j * 2 + 1][INCOME]) {
                        idx = j * 2 + 1;
                    } else {
                        if (users[j * 2][UID] < users[j * 2 + 1][UID]) {
                            idx = j * 2;
                        } else {
                            idx = j * 2 + 1;
                        }
                    }
                }

                int[] tmp = users[j];
                users[j] = users[idx];
                users[idx] = tmp;

                j = idx;
            }
        }

        for (int i = 0; i < ans; i++) {
            addUser(backup[i][UID], backup[i][INCOME]);
        }

        return ans;
    }
}
