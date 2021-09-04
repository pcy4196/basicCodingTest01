package codingTest.basic01;

import java.util.LinkedList;
import java.util.Queue;

public class B01NumberOfIslandBfs {

    // Problem
    // m x n binary grid가 주어집니다. 각 cell의 값 중에 ‘1’은 땅이고 ‘0’은 물입니다.
    //섬은 물에 의해 둘러싸입니다.
    //섬은 수직 또는 수평으로 인접하는 땅(1)을연결함으로써 형성됩니다.
    //섬은 grid의 네 모서리가 모두 물로 둘러싸여 있다고 가정 할 수 있습니다.
    //섬의 개수를 리턴하세요

    public static void main(String[] args) throws Exception {

        char[][] grid  = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','0','1','1'}
        };

        B01NumberOfIslandBfs bfs = new B01NumberOfIslandBfs();
        System.out.println(bfs.solve(grid));
    }

    int[][] dirs = { {-1,0}, {1,0}, {0,-1}, {0,1} };
    int m;
    int n;

    public int solve(char[][] grid) throws Exception {
        // 에러 처리문
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        m = grid.length;        // 행의 수
        n = grid[0].length;     // 열의 수
        int cnt = 0;            // 섬의 개수를 담는 변수
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    cnt++;
                    bfs(grid, i, j);
                }
            }
        }

        return cnt;
    }

    // BFS (너비우선검색을 하기위한 메서드)
    private void bfs(char[][] grid, int i, int j) {
        grid[i][j] = 'X'; // visited
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {i, j});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int[] dir : dirs) {
                int x1 = cur[0] + dir[0]; // 0+x;
                int y1 = cur[1] + dir[1]; // 0+y;

                if (x1 >= 0 && y1 >= 0
                  && x1 < m && y1 < n
                  && grid[x1][y1] == '1') {
                    grid[x1][y1] = 'X';                 // visted(탐색 완료)
                    queue.offer(new int[] {x1, y1});    // 해당값을 기준으로 또 탐색하기 위해 입력
                }
            }
        }
    }

}
