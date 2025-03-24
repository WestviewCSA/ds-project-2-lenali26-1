import java.util.LinkedList;
import java.util.Stack;
import java.util.*;

public class PathFinder {
	//instance variable for direction/rows
	private static final int[] dRow = {-1, 1, 0, 0}; // North, South, East, West
    private static final int[] dCol = {0, 0, -1, 1};
    private static int newRow;
    private static int newCol;
    
    public static boolean Stack(String[][] map, int startX, int startY, int endX, int endY) {
        Stack<int[]> stack = new Stack<>(); //create a stack to store data/position/coordinate
        boolean[][] visited = new boolean[map.length][map[0].length];//track visited

        stack.push(new int[]{startX, startY}); //Start from the beginning
        visited[startX][startY] = true; //marked that this position has already visited

        while (!stack.isEmpty()) { //if stack isn't empty 
            //pop the last position
            int[] current = stack.pop();
            //current row and col
            int r = current[0];
            int c = current[1];

            if (r == endX && c == endY) { //check have we reached the coin or not
                return true; //YAY we reached the coin!!! 
            }
            
            //Check all direction - North, South, East, West
            for (int i = 0; i < 4; i++) {
                //new row and new col
                int newR = r + dRow[i]; 
                int newC = c + dCol[i];
                
                //check is the new row and new col is able to move
                if (isValidMove(map, newR, newC, visited)) {
                    //add to stack
                    stack.push(new int[]{newR, newC});
                    //mark that it is already visited, so we will not revisit it
                    visited[newR][newC] = true;
                    //mark the path
                    map[newR][newC] = "+"; 
                }
            }
        }

        return false; //no path found or cannot find coins :(
    }
    
    public static boolean Queue(String[][] map, int startX, int startY, int endX, int endY) {
        Queue<int[]> queue = new LinkedList<>(); //create a stack to store data/position/coordinate
        boolean[][] visited = new boolean[map.length][map[0].length];//track visited

        queue.add(new int[]{startX, startY}); //Start from the beginning
        visited[startX][startY] = true; //marked that this position has already visited

        while (!queue.isEmpty()) { //if stack isn't empty 
            //poll the last position
            //poll() = dequeue()
            int[] current = queue.poll();
            //current row and col
            int r = current[0];
            int c = current[1];

            if (r == endX && c == endY) { //check have we reached the coin or not
                return true; //YAY we reached the coin!!! 
            }
            
            //Check all direction - North, South, East, West
            for (int i = 0; i < 4; i++) {
                //new row and new col
                int newR = r + dRow[i]; 
                int newC = c + dCol[i];
                
                //check is the new row and new col is able to move
                if (isValidMove(map, newR, newC, visited)) {
                    //add to stack
                    queue.add(new int[]{newR, newC});
                    //mark that it is already visited, so we will not revisit it
                    visited[newR][newC] = true;
                    //mark the path
                    map[newR][newC] = "+"; 
                }
            }
        }

        return false; //no path found or cannot find coins :(
    }
    

    //check is it valid to move
    public static boolean isValidMove(String[][] map, int r, int c, boolean[][] visited) {
        if (r >= 0 && r < map.length && c >= 0 && c < map[0].length
                &&  map[r][c] != "@" && !visited[r][c]) { //check is it visited?
            return true;
        }
        return false;
    }


}
