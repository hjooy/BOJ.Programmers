import sys
from collections import deque

def function():
    N = int(input())
    tree = [[] for _ in range (N + 1)]
    
    distance = [0] * (N + 1)
    visited = [0] * (N + 1)

    def bfs(idx):
        q = deque([])
        q.append(idx)
        visited[idx] = 1
        while q:
            cur = q.popleft()
            for e in tree[cur]:
                n = e[0]
                w = e[1]
                if visited[n] == 0:
                    visited[n] = 1
                    q.append(n)
                    distance[n] = distance[cur] + w
                
    # initialize tree
    for _ in range(N):
        l = list(map(int, sys.stdin.readline().split()))
        node = l[0]
        for i in range(1, len(l), 2):
            if l[i] == -1: break
            tree[node].append((l[i], l[i + 1]))

    bfs(1)
    max_node = distance.index(max(distance))
    distance = [0] * (N + 1)
    visited = [0] * (N + 1)
    bfs(max_node)
        
    return max(distance)

if __name__ == "__main__":
    print(function())