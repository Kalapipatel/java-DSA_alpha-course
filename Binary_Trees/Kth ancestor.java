/*
Kth Ancestor of the Node

Given an undirected connected tree with n nodes, numbered from 1 to n, and rooted at node 1, and two positive integers k and v, 
find the kth ancestor (Ancestor at k level up. Parent of a node is also an ancestor 1 level up) of the node v.

For example, in the following tree, the 2nd ancestor of node 7 is node 1.

Parameters
n: An integer representing the number of nodes in the tree.
edges: A list of n-1 edges where each edge [u, v] denotes an undirected edge between nodes u and v.
v: The node whose kth ancestor needs to be found.
k: The ancestor level to be queried.

Return Value
Returns an integer — the kth ancestor of node v.
Returns -1 if the kth ancestor does not exist.

Constraints:
1 <= n <= 100000
1 <= ui, vi <= n
1 <= v <= n
1 <= k <= depth(v)

Note: The input and output formats provided below are only for testing with custom inputs. You only need to return the value. Printing is handled automatically.

Input Format
The first line of the input contains three space-separated integers n, k and v — the number of nodes, level up from node v where we need to find the ancestor and the node v.
The next n-1 lines describe the edges. The i-th of these n-1 lines contains two space-separated integers ui and vi, denoting a bidirectional edge between ui and vi.

Output Format
Output on a single line, the Kth ancestor of node v.

Sample 1:
Input
7 2 7
1 2
1 4
2 5
2 3
2 6
4 7

Output
1
*/

//import java.util.*;

public int kthAncestor(int n, int[][] edges, int v, int k) {
    ArrayList<Integer>[] graph = new ArrayList[n+1];
    
    for(int i=1; i<=n; i++){
        graph[i] = new ArrayList<>();
    }
    
    for(int e[] : edges){
        graph[e[0]].add(e[1]);
        graph[e[1]].add(e[0]);
    }
    
    int parent[] = new int[n+1];
    Queue<Integer> q = new LinkedList<>();
    
    q.add(1);
    parent[1] = -1;
    
    while(!q.isEmpty()){
        int curr = q.poll();
        
        for(int next : graph[curr]){
            if(next != parent[curr]){
                parent[next] = curr;
                q.add(next);
            }
        }
    }
    
    while(k > 0 && v != -1){
        v = parent[v];
        k--;
    }
    
    return v;
}
    
