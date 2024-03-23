import java.util.*;

public class Main {
    static boolean[] visited;
    static int[] distance;
    static ArrayList<int[]>[] Tree;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Tree = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            Tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
           int s = sc.nextInt();
            while (true) {
                int node = sc.nextInt();
                if (node == -1) break;
                int weight = sc.nextInt();
                Tree[s].add(new int[]{node, weight});
            }
        }
        
        distance = new int[N + 1];
        visited = new boolean[N + 1];
        bfs(1);
        int farthestNode = 1;
        for (int i = 2; i <= N; i++) {
            if (distance[farthestNode] < distance[i]) farthestNode = i;
        }
        distance = new int[N + 1];
        visited = new boolean[N + 1];
        bfs(farthestNode);
        int maxDistance = 0;
        for (int i = 1; i <= N; i++) {
            if (maxDistance < distance[i]) maxDistance = distance[i];
        }
        
        System.out.println(maxDistance);
        return;
    }
    
    private static void bfs(int index) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(index);
        visited[index] = true;
        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            for (int[] edge : Tree[curNode]) {
                int node = edge[0];
                int weight = edge[1];
                if (!visited[node]) {
                    visited[node] = true;
                    queue.add(node);
                    distance[node] = distance[curNode] + weight;
                }
            }
        }
    } 
}