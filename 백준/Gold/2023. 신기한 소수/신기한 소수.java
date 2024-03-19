import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		
		dfs(0, 0, n);
	}
	
	public static boolean isPrime(int n) {
	    if (n == 1) return false;
	    for (int i = 2; i * i <= n; i++) {
	        if (n % i == 0) return false;
	    }
	    return true;
	}
	
	public static void dfs(int num, int pos, int n) {
	    if (!isPrime(num)) return;
	    if (pos == n) {
	        System.out.println(num);
	        return;
	    }
	    
	    num *= 10;
	    for (int i = 1; i <= 9; i++) {
	        num += i;
	        dfs(num, pos + 1, n);
	        num -= i;
	    }
	}
}