import java.util.LinkedList;
import java.util.Queue;

public class Tree {

    public int findCircleNum2(int[][] isConnected) {
        int visited[] = new int[isConnected.length];
        int count = 0;
        Queue<Integer> integers = new LinkedList<>();
        for (int i = 0; i < isConnected.length; i++) {
            if (visited[i] == 0){
                ((LinkedList<Integer>) integers).add(i);
                while(!integers.isEmpty()){
                    int s = integers.remove();
                    visited[s] = 1;
                    for (int j = 0; j < isConnected.length; j++) {
                        if(isConnected[i][j] == 1 && visited[j] == 0)
                            ((LinkedList<Integer>) integers).add(j);
                    }
                }
                count++;
            }
        }
        return count;
    }

    public int findCircleNum(int[][] isConnected) {
        int[] visited = new int[isConnected.length];
        int count = 0;
        for (int i = 0; i < isConnected.length; i++) {
            if (visited[i] == 0){
                dfs(isConnected, visited, i);
                count++;
            }
        }
        return count;
    }

    private void dfs(int[][] data, int[] visited, int pos) {
        for (int i = 0; i < data.length; i++) {
            if (data[pos][i] == 1 && visited[i] == 0){
                visited[i] = 1;
                dfs(data, visited, i);
            }
        }
    }

}
