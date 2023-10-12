import java.util.*;

class Solution {
    public int solution(int N, int number) {
        if (N == number) return 1;
        List<Set<Integer>> dict = new ArrayList<>();
        int tmp = 0;
        for (int i = 0; i <= 8; i++) {
            dict.add(new HashSet<>());
            if (i >= 1) {
                tmp += N;
                if (tmp == number) return i;
                dict.get(i).add(tmp);
                tmp *= 10;
            }
        }
        int eval = 0;
        for (int i = 2; i <= 8; i++) {
            for (int j = 1; j < i/2 + 1; j++) {
                for (int val1 : dict.get(i - j)) {
                    for (int val2 : dict.get(j)) {
                        for (int k = 0; k < 6; k++) {
                            if (k == 0) {
                                eval = val1 + val2;
                            }
                            else if (k == 1) {
                                eval = val1 - val2;
                            }
                            else if (k == 2) {
                                eval = val2 - val1;
                            }
                            else if (k == 3) {
                                eval = val1 / val2;
                            }
                            else if (k == 4) {
                                eval = val2 / val1;
                            }
                            else if (k == 5) {
                                eval = val1 * val2;
                            }
                            if (eval > 0) {
                                if (eval == number) return i;
                                dict.get(i).add(eval);
                            }
                        }
                    }
                }
            }
        }
        
        return -1;
    }
}