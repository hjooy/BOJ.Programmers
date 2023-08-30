import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int r1, c1, r2, c2;
    static int maxVal;
    static int maxNumOfDigits;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        r1 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());
        maxVal = getMaxValue(r1, c1, r2, c2);
        maxNumOfDigits = getNumOfDigits(maxVal);
        printResult();
        return;
    }

    public static void printResult() {
        Queue<Integer> queue;
        int curr;
        int numOfDigits;
        int numOfBlanks;
        for (int i = r1; i <= r2; i++) {
            queue = new LinkedList<Integer>();
            for (int j = c1; j <= c2; j++) {
                queue.add(getValueAt(i, j));
            }
            for (int j = c2 - c1 + 1; j > 0; j--) {
                curr = queue.poll();
                numOfDigits = getNumOfDigits(curr);
                numOfBlanks = maxNumOfDigits - numOfDigits;
                for (int k = 0 ; k < numOfBlanks; k++) {
                    System.out.print(' ');
                }
                System.out.print(curr);
                if (j != 1) System.out.print(' ');
            }
            if (i != r2)  System.out.print('\n');
        }
    }

    public static int getValueAt(int r, int c) {
        int val = 0;
        if (r < 0) {
            if (r == -1) {
                if (c == 0) val = formulaE(r, c);
                else if (c < 0) val = formulaB(r, c);
                else {
                    if (c == 1) val = formulaA(r, c);
                    else val = formulaD(r, c);
                }
            }
            else { // r < -1
                if (c < 0) {
                    if (r >= c) val = formulaB(r, c);
                    else if (r == c - 1) val = formulaE(r, c);
                    else val = formulaA(r, c); // r < c - 1
                }
                else { // c >= 0
                    if (r <= -c) val = formulaA(r, c);
                    else val = formulaD(r, c); // r > -c
                }
            }
        }
        else { // r >= 0
            if (c < 0) {
                if (r < -c) val = formulaB(r, c);
                else val = formulaC(r, c);
            }
            else {
                if (r > c) val = formulaC(r, c);
                else if (r == c) val = formulaF(r, c);
                else val = formulaD(r, c); // r < c
            }
        }
        return val;
    }
    public static int getMaxValue(int r1, int c1, int r2, int c2) {
        int maxVal = 0;
        int val;
        int[] rows = new int[2];
        int[] cols = new int[2];
        rows[0] = r1;
        rows[1] = r2;
        cols[0] = c1;
        cols[1] = c2;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                val = getValueAt(rows[i], cols[j]);
                if (maxVal <= val) maxVal = val;
            }
        }
        return maxVal;
    }
    public static int getNumOfDigits(int val) {
        int count = 0;
        while (val > 0) {
            count++;
            val /= 10;
        }
        return count;
    }

    public static int formulaA(int r, int c) {
        return (int) (Math.pow((Math.abs(r) * 2), 2)) - Math.abs(c - (r + 1));
    }

    public static int formulaB(int r, int c) {
        return (int) (Math.pow((Math.abs(c) * 2), 2)) + Math.abs(r - c) + 1;
    }

    public static int formulaC(int r, int c) {
        return (int) Math.pow((r * 2 + 1), 2) - Math.abs(c - r);
    }

    public static int formulaD(int r, int c) {
        return (int) Math.pow(((c - 1) * 2 + 1), 2) + Math.abs(r - (c - 1)) + 1;
    }

    public static int formulaE(int r, int c) {
        return (int) Math.pow((Math.abs(r) * 2), 2);
    }

    public static int formulaF(int r, int c) {
        return (int) Math.pow((r * 2 + 1), 2);
    }
}