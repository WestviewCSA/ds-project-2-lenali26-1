import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class p2 {
	
	//initializing instance variables
	private int row, col; 
	private int startX, startY, endX, endY;
	private String[][] map;
	
	//initiating the parameters for the class
    public p2(String[][] map, int row, int col) {
        this.row = row;
        this.col = col; 
        this.map = map; 
    }
    //creating nest for loops in order to iterate through every value
	public void findLocation() {
		for (int row1 = 0; row1 < row; row1++) { //first iterate through the rows
			for (int col1 = 0; col1 < col; col1++) { //next iterate through the cols
				if (map[row1][col1] == "W") { //setting the starting position
					startX = row1;
	    			startY = col1;
	    		} else if (map[row1][col1] == "$") { //sending the ending position
	    			endX = row1;
	    			endY = col1;
	            }
	         }
	      }
	}
	/*
	 * creating enhanced for loops to iterate through each value in the arrays 
	 * in order to create the new map
	 */
	public void printMap() {
		for(String[] row : map) {
			for(String cell : row) {
				System.out.println(cell);
			}
			System.out.println();
		}
	}
	
	public int getStartX() { return startX; }
	public int getStartY() { return startY; }
	public int getEndX() { return endX; }
	public int getEndY() { return endY; }
	
	public static void main(String[] args) {
		if(args.length < 2) {
			System.out.println("Missing required arguments! Use --Help for usage information!");
			System.exit(-1);
		}
		
		//initializing the values 
		//boolean arguments have an initial value of false! 
		boolean useStack = false, useQueue = false, useOpt = false, 
				printTime = false, inCoordinate = false, outCoordinate = false; 
		
		//initializing the filename(s)
		String filename = args[args.length - 1];
		
		//iterating through the list values
		for(int i = 0; i < args.length - 1; i++) {
			String arg = args[i];
			switch(arg) {
			//created the test cases for all of the values
			case "--Stack":
				useStack = true; 
				break;
			case "--Queue":
				useQueue = true; 
				break; 
			case "--Opt":
				useOpt = true; 
				break;
			case "--Time":
				printTime = true; 
				break;
			case "--Incoordinate":
				inCoordinate = true; 
				break;
			case "--Outcoordinate":
				outCoordinate = true; 
				break;
			case "--Help":
				System.out.println("Usage: java p2 [--Stack | --Queue | --Opt] [--Time] [--Incoordinate] [--Outcoordinate] filename");
				System.exit(0);
				break;
			default: 
				System.out.println("Unkown switch: " + arg);
				System.exit(-1);
			}
		}
		//if they are all used or all not used
		if((useStack && useQueue && useOpt) || (!useStack && !useQueue && !useOpt)) {
			System.out.println("Error: Must include exactly one of Stack or Queue or Opt.");
			System.exit(-1);
		}
		
		String[][] map = inCoordinate ? coordinateReader(filename) : readMap(filename); 
		if(map == null) {
			System.out.println("Error: Failed to load map.");
			System.exit(-1);
		} 
		
		//creating the mazeRunner object to iterate through the map
		p2 mazeRunner = new p2(map, map.length, map[0].length); 
		mazeRunner.findLocation(); 		
		
		long startTime = System.nanoTime(); //starting the time
		boolean found = false; //initializing a boolean found value to determine whether to use queue or stack
		
//		if (useQueue) { //if use queue is called, then use queue to solve
//			found = PathFinder.Queue(map,  mazeRunner.startX , mazeRunner.startY, mazeRunner.endX, mazeRunner.endY);
//		}else { //if use queue is not called, use the other option of the stacks
//			found = PathFinder.Stack(map,  mazeRunner.startX , mazeRunner.startY, mazeRunner.endX, mazeRunner.endY);
//		}
		if (useOpt) {
	        found = PathFinder.Opt(map, mazeRunner.getStartX(), mazeRunner.getStartY(), mazeRunner.getEndX(), mazeRunner.getEndY(), outCoordinate);
	    } else if (useQueue) {
	        found = PathFinder.Queue(map, mazeRunner.getStartX(), mazeRunner.getStartY(), mazeRunner.getEndX(), mazeRunner.getEndY(), outCoordinate);
	    } else {
	        found = PathFinder.Stack(map, mazeRunner.getStartX(), mazeRunner.getStartY(), mazeRunner.getEndX(), mazeRunner.getEndY(), outCoordinate);
	    }
		
		long endTime = System.nanoTime(); //ending the time
		
		//if a path is not found, that means the store is closed! 
		if (!found) {
		    System.out.println("Sorry! The Wolverine store is closed for today!");
		} else if (!outCoordinate) {
		    // Only print the map if not using --Outcoordinate
		    mazeRunner.printMap();
		}
		
		//printing the time!
		if (printTime) {
			double duration = (endTime - startTime) / 1e9; //dividing by 1e9 to change nano-seconds to seconds
			System.out.printf("Total Runtime: %.6f seconds", duration);
		}
		

	}

	public static String[][] readMap(String filename) {
		String[][] map = null; //initializing the map! 
		
		try {
			File file = new File(filename); //implement the scanner
			Scanner scanner = new Scanner(file); //checks each element in the file
			
			//initiating values with the scanner obj
			int numRows  = scanner.nextInt();
			int numCols  = scanner.nextInt();
			//int numRooms = scanner.nextInt();
			scanner.nextInt(); 
			scanner.nextLine(); 
			
			map = new String[numRows][numCols]; //initializing the map
			
			//System.out.println(numRows + " " + numCols + " " + numRooms);
			
//			//initializing variables
//			int rowIndex = 0;
//			int roomIndex = 0;
//			int colIndex = 0;
//			//creating a 3-D array with the tiles
//			Tile[][][] tiles = new Tile[numRows][numCols][numRooms];
//			
//			//grabbing each line and process each line as in one row of the map
//			int rowProcessed = 0;	
//			while(scanner.hasNextLine() && rowProcessed < numRows) {
//				//grab a row
//				String row = scanner.nextLine();
//				row = row.substring(0, numCols);
//				
//				//System.out.println(row);
//				
//				for(int col = 0; col < numCols; col++) {
//					//changing the values of the map according to the row it's iterating in 
//					map[rowProcessed][col] = String.valueOf(row.charAt(col)); 
//	        	}
//	            rowProcessed++;
//	                
//			}
//			scanner.close();   
			for (int row = 0; row < numRows; row++) {
				String rowLine = scanner.nextLine().trim();
				for (int col = 0; col < numCols && col < rowLine.length(); col++) {
					map[row][col] = String.valueOf(rowLine.charAt(col));
				}
			}
			scanner.close();
		
		//creating the value if the file is not found
	    } catch(FileNotFoundException e) {
	    	System.out.println("Sorry! File not found");
	    }
		return map;	
	}
	
	
	public static String[][] coordinateReader(String filename) {
        String[][] maze = null;
        try {
        	//initializing the file and scanner objects! 
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            
            //initializing values
            int numRows = scanner.nextInt();
            int numCols = scanner.nextInt();
            //int numRooms = scanner.nextInt();
            scanner.nextInt(); 
            scanner.nextLine();
            
            //System.out.println(numRows + " " + numCols + " " + numRooms);
            
            //grab each line and process each as one row of the map
            maze = new String[numRows][numCols];
            //creating a nested for loop to iterate through the rows and columns
            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < numCols; col++) {
                    maze[row][col] = "."; //fill all the position that have a dot
                }
            }
            
            //int rowIndex = 0;
            while(scanner.hasNextLine()) {
                //grab a row
                String row = scanner.nextLine();
                String line = scanner.nextLine().trim();
                
                if(!line.isEmpty()) {
                	String[] parts = row.split(" "); 
                	if (parts.length >= 3) {
                		String symbol = parts[0];
                		//grab the x-coordinate by using substring
                        int x = Integer.parseInt(parts[1]);
                        //grab the y-coordinate by using substring
                        int y = Integer.parseInt(parts[2]);
                        
                        if (x >= 0 && x < numRows && y >= 0 && y < numCols) {
                        //replace position with proper character 
                        maze[x][y] = symbol;
                        }
                	}
                }
                //rowIndex++; //update the row
            }
            scanner.close();
//            
//            //change the 2d array to string 
//             for (String[] row : maze) {
//                    System.out.println(String.join("", row));
//                }
            
        } catch(FileNotFoundException e) {
            System.out.println("Sorry! File not found");
        }
		return maze;
    }



	
//      public void shortestPath(Tile[][][] tiles, int wRow, int wCol, int wRoom){
//		
//		int numRows = tiles.length;
//        int numCols = tiles[0].length;
//        int numRooms = tiles[0][0].length;
//		
//		Queue<String> myQueue = new LinkedList<>();
//		
//		 boolean[][][] visited = new boolean[numRows][numCols][numRooms];
//	        Queue<Tile> queue = new LinkedList<>();
//	        
//	        queue.add(tiles[wRow][wCol][wRoom]);
//	        visited[wRow][wCol][wRoom] = true;
//	        
//	        while (!queue.isEmpty()) {
//	           
//	           
//	        }
//		
//	}
	
}
    
	


