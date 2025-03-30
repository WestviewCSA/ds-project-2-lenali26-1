import java.util.LinkedList;
import java.util.Stack;
import java.util.*;

public class PathFinder {
	//instance variable for direction/rows
	//up, down, left, right
	private static final int[] dRow = {-1, 1, 0, 0}; // North, South, East, West
    private static final int[] dCol = {0, 0, -1, 1};
    private static int newRow;
    private static int newCol;
    
    public static boolean Stack(String[][] map, int startX, int startY, int endX, int endY, boolean outCoordinate) {
        Stack<int[]> stack = new Stack<>(); //create a stack to store data/position/coordinate
        boolean[][] visited = new boolean[map.length][map[0].length];//track visited
        //boolean found = false; 
        
        int[][][] parentFunction = new int[map.length][map[0].length][2];
        for(int[][] row : parentFunction) {
        	for(int[] cell : row) {
        		Arrays.fill(cell, -1); //a value for when the parent function does not exist
        	}
        }
        //implementing push 
        //push means that something is added to the beginning
        stack.push(new int[]{startX, startY}); 
        visited[startX][startY] = true; 
        //mark that the original starting point has already been visited
        
        while (!stack.isEmpty()) { //if stack is not empty 
        	//isEmpty is the only function that can check if it is empty so you have to fix the object to be not to check that it isn't empty 
        	
        	int[] current = stack.pop(); //pop the last position
        	
        	int row = current[0], col = current[1]; 
        	
        	if (row == endX && col == endY) { 
        		markPath(map, parentFunction, startX, startY, endX, endY, outCoordinate);
                return true;
        		//check if the end values are reached in each the column and the row 
//        		found = true; //the coin is found, the function can stop
//        		break; 
        		
        	}
        	
        	for(int i = 0; i < 4; i++) { //uses 4 because you need to check all the directions: N/E/S/W
        		
        		//set a new row and new column if the four directions are being checked 
        		int newRow = row + dRow[i]; 
        		int newCol = col + dCol[i];
        		
        		//check if the new position is on the map! 
        		if (isValidMove(map, newRow, newCol, visited)) {
        			
        			//uses the push command to add to the stack
        			stack.push(new int[]{newRow, newCol});
        			//mark that the position is already visited
        			//mark position in order to make sure that it is not revisited
        			visited[newRow][newCol] = true; 
        			//parentFunction[newRow][newCol] = new int[]{row, col};
        			//changing the value of the parent function to continue iterating
        			parentFunction[newRow][newCol][0] = row; 
        			parentFunction[newRow][newCol][1] = col; 
        			
        		}
        	}
        }
        return false;
    }
        

    
    //creating the optimal path!
    public static boolean Opt(String[][] map, int startX, int startY, int endX, int endY, boolean outCoordinate) {
    	
    	Queue<int[]> queue = new ArrayDeque<>(); //creating a new stack to store the data positions to find the optimal path
    	boolean[][] visited = new boolean[map.length][map[0].length]; //setting the visited tracks 
    	//boolean found = false; //initializing the found path, the original initializing needs to be false! 
    	
    	int[][][] parentFunction = new int[map.length][map[0].length][2];
    	//add the parent function in order to have multiple paths
    	//this is used to retrace the shortest path! 
    	
    	for (int[][] row : parentFunction) {
    		for (int[] cell: row) {
    			Arrays.fill(cell, -1);
    		}
    	}
    	
    	//implementing push 
        //push means that something is added to the beginning
    	//push is the same as add! 
        queue.add(new int[]{startX, startY}); 
        visited[startX][startY] = true; 
        //mark that the original starting point has already been visited
        
        boolean found = false; 
        
        while (!queue.isEmpty()) { //if stack is not empty 
        	//isEmpty is the only function that can check if it is empty so you have to fix the object to be not to check that it isn't empty 
        	
        	//dequeue the last position
        	//poll = dequeue 
        	int[] current = queue.poll(); 
        	
        	//initialize the current values
        	int row = current[0]; 
        	int col = current[1]; 
        	
        	if (row == endX && col == endY) { 
        		//check if the end values are reached in each the column and the row 
        		found = true; //the coin is found, the function can stop
        		break; 
        		
        	}
        	for(int i = 0; i < 4; i++) { //uses 4 because you need to check all the directions: N/E/S/W
        		
        		//set a new row and new column if the four directions are being checked 
        		int newRow = row + dRow[i]; 
        		int newCol = col + dCol[i];
        		
        		 //check is the new row and new col is able to move
        		if (isValidMove(map, newRow, newCol, visited)) {
                    
                	queue.add(new int[]{newRow, newCol});
                    //mark that it is already visited, so we will not revisit it
                    visited[newRow][newCol] = true;
                    
                    parentFunction[newRow][newCol][0] = row;
                    parentFunction[newRow][newCol][1] = col;
                	}
                }
            }
        

        if (!found) {
        	
        	return false;
        }else {
        	List<int[]> path = new ArrayList<>();
            int row = endX, col = endY;

            while (!(row == startX && col == startY)) {
                path.add(new int[]{row, col});
                int prevRow = parentFunction[row][col][0];
                int prevCol = parentFunction[row][col][1];
                row = prevRow;
                col = prevCol;
            }

            Collections.reverse(path);

            for (int[] cell : path) {
                int r = cell[0], c = cell[1];
                if (!map[r][c].equals("W") && !map[r][c].equals("$")) {
                    if (outCoordinate) {
                        System.out.println("+ " + r + " " + c + " 0");
                    } else {
                        map[r][c] = "+";
                    }
                }
            }
        }
		return true;

        

    }
    
    public static boolean Queue(String[][] map, int startX, int startY, int endX, int endY, boolean outCoordinate) {
    	Queue<int[]> queue = new LinkedList<>(); //create a new linked list to store data/position/coordinates of the Wolverine
    	boolean[][] visited = new boolean[map.length][map[0].length]; //setting the visited tracks 
    	//boolean found = false; //initializing the found path, the original initializing needs to be false! 
    	
    	int[][][] parentFunction = new int[map.length][map[0].length][2];
    	//add the parent function in order to have multiple paths
    	//this is used to retrace the shortest path! 
    	for (int[][] row : parentFunction) {
    		for (int[] cell: row) {
    			Arrays.fill(cell, -1);
    		}
    	}
    	//implementing push 
        //push means that something is added to the beginning
    	//push is the same as add! 
        queue.add(new int[]{startX, startY}); 
        visited[startX][startY] = true; 
        //mark that the original starting point has already been visited
        
        while (!queue.isEmpty()) { //if stack is not empty 
        	//isEmpty is the only function that can check if it is empty so you have to fix the object to be not to check that it isn't empty 
        	
        	//dequeue the last position
        	//poll = dequeue 
        	int[] current = queue.poll(); 
        	
        	//initialize the current values
        	int row = current[0]; 
        	int col = current[1]; 
        	
        	if (row == endX && col == endY) { 
        		//check if the end values are reached in each the column and the row 
//        		found = true; //the coin is found, the function can stop
//        		break; 
        		markPath(map, parentFunction, startX, startY, endX, endY, outCoordinate);
                return true;
        		 
        	}
        	for(int i = 0; i < 4; i++) { //uses 4 because you need to check all the directions: N/E/S/W
        		
        		//set a new row and new column if the four directions are being checked 
        		int newRow = row + dRow[i]; 
        		int newCol = col + dCol[i];
        		
        		//check if the new position is on the map! 
        		if (isValidMove(map, newRow, newCol, visited)) {
        			
        			//uses the push command to add to the stack
        			queue.add(new int[] {newRow, newCol});
        			//mark that the position is already visited
        			//mark position in order to make sure that it is not revisited
        			visited[newRow][newCol] = true; 
        			
        			//changing the value of the parent function to continue iterating
        			parentFunction[newRow][newCol][0] = row; 
        			parentFunction[newRow][newCol][1] = col; 
        			
        		}
        	}	
        }
        //if (!found) {
        	return false; // if a path is not found then false is returned! 
        
    }
     
    public static void markPath(String[][] map, int[][][] parentFunction, int startX, int startY, int endX, int endY, boolean outCoordinate) {

        int row = endX, col = endY;
        List<int[]> path = new ArrayList<>();

        while (!(row == startX && col == startY)) {
            path.add(new int[]{row, col});
            int prevRow = parentFunction[row][col][0];
            int prevCol = parentFunction[row][col][1];
            row = prevRow;
            col = prevCol;
        }

        Collections.reverse(path);

        for (int[] cell : path) {
        	if (!map[row][col].equals("W") && !map[row][col].equals("$")) {
        	    if (outCoordinate) {
        	        System.out.println("+ " + row + " " + col + " 0");
        	    } else {
        	        map[row][col] = "+";
        	    }
        	}
        }
    }
    
    public static boolean isValidMove(String[][] map, int row, int col, boolean[][] visited) {
    	//use recursion to check if the move is on the map! 
    	if(row >= 0 && row < map.length && col >= 0 && col < map[0].length
    			&& !map[row][col].equals("@") && !visited[row][col]) {
    		return true; 
    	}
    	return false; 
    }


}
