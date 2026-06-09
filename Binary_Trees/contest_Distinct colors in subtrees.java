/*
Given an undirected connected tree with N coloured nodes (colours denoted with integers 1 to N), numbered from 1 to N, and rooted at node 1. Your task is to determine, for each node, 
the number of distinct colours present in its subtree.

For example, a subtree rooted at a specific node might contain several nodes with duplicate colors. You must count only the unique colors present in that 
entire subtree (including the root of that subtree).

Parameters
N: An integer representing the total number of nodes in the tree.
C: A list/array of integers of length N, where C[i] represents the color of the (i+1)th node (assuming 0-indexed arrays for colors and 1-indexed nodes).
edges: A 2D list/array of size (N-1)x2 representing the undirected edges of the tree, where each pair (u, v) indicates an edge between node u and node v.

Return Value
Returns a list/array of integers of length N: The ith element in the returned array should represent the total number of distinct colors in the subtree rooted at node i+1.

Constraints:
1 <= T <= 10 (Number of test cases)
1 <= N <= 10^5
1 <= C[i] <= N for each 0 <= i < N
1 <= u, v <= N
The given edges are guaranteed to form a valid connected tree.
The sum of N over all test cases won't exceed 5*10^5.

Note: The input and output formats provided below are only for testing with custom inputs. You only need to return the value. Printing is handled automatically.

Input Format
The first line of the input contains a single integer N — the number of nodes.
The second line contains N space-separated integers Ci - the colour of ith node.
The next N-1 lines describe the edges. The i-th of these N-1 lines contains two space-separated integers ui and vi, denoting a bidirectional edge between ui and vi.

Output Format
Output on a single line, N space-separated integers, the number of distinct coloured nodes in the subtree of node i (1 <= i <= N).

Sample 1:
Input
7
1 2 3 4 3 4 5
1 2
1 7
1 5
2 4
2 3
5 6

Output
5 3 1 1 2 1 1

*/

public static int[] distinctColorsInSubtrees(int N, int[] C, int[][] edges) {
    
    ArrayList<Integer>[] graph = new ArrayList[N+1];
    
    for(int i=1; i<=N; i++){
        graph[i] = new ArrayList<>();
    }
    
    for(int e[] : edges){
        graph[e[0]].add(e[1]);
        graph[e[1]].add(e[0]);
    }
    
    int ans[] = new int[N];
    
    fun(1, -1, C, graph, ans);
    
    return ans;
}

public static Set<Integer> fun(int node, int par, int c[], ArrayList<Integer>[] graph, int ans[]){
    Set<Integer> set = new HashSet<>();
    set.add(c[node-1]);

    
    for(int child : graph[node]){
        if(child != par){
            Set<Integer> childSet = fun(child, node, c, graph, ans);
            
            if(set.size() < childSet.size()){
                Set<Integer> temp = set;
                set = childSet;
                childSet = temp;
            }
            
            set.addAll(childSet);
        }
    }
    
    ans[node-1] = set.size();
    // System.out.println(Arrays.toString(ans));
    return set;
}
