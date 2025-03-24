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
	

    public p2(String[][] map, int row, int col) {
        this.row = row;
        this.col = col; 
        this.map = map; 
    }
    
	public void findLocation() {
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (map[r][c] == "W") {
					startX = r;
	    			startY = c;
	    		} else if (map[r][c] == "$") {
	    			endX = r;
	    			endY = c;
	            }
	        }
	      }

	 }
	public void printMap() {
		for(String[] newMap : map) {
			System.out.println(newMap);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("p2");
		
		readMap("src/TEST01"); //reads the map
//		readMap("src/TEST02");
//		readMap("src/TEST03");
//		readMap("src/TEST04");
		//readMap("src/TEST05");
	}

	public static void readMap(String filename) {
		
		try {
			File file = new File(filename); //implement the scanner
			Scanner scanner = new Scanner(file); //checks each element in the file
			
			//initiating values with the scanner obj
			int numRows  = scanner.nextInt();
			int numCols  = scanner.nextInt();
			int numRooms = scanner.nextInt();
			scanner.nextLine(); 
			
			System.out.println(numRows + " " + numCols + " " + numRooms);
			
			//initializing variables
			int rowIndex = 0;
			int roomIndex = 0;
			int colIndex = 0;
			
			//creating a 3-D array with the tiles
			Tile[][][] tiles = new Tile[numRows][numCols][numRooms];
			
			//process the map
			
//			while (scanner.hasNextLine()) {
//				
//				//grab a line (one row of the map)
//				String row = scanner.nextLine();
//				System.out.println(row);
//				
//				
//				
//				//check if out of bounds for the row
//				if (row.length()>0) {
//					//check if out of bounds for the column
//					for(colIndex = 0; colIndex < numCols && colIndex < row.length(); colIndex++) {
//						
//						for(roomIndex = 0; roomIndex < numRooms; roomIndex++) {
//							
//							//setting the index of the character to the position of the column 
//							char el = row.charAt(colIndex);
//							//setting a new tile object that implements in the index and position and the character
//							Tile obj = new Tile(rowIndex, colIndex, el);
//							//setting a new tile as it iterates
//							tiles[rowIndex][colIndex][roomIndex] = new Tile(rowIndex, colIndex, el);
//							//printing out the values
//							System.out.println("Tile at (" + tiles[rowIndex][colIndex][roomIndex].getRow()
//									+ ", " + tiles[rowIndex][colIndex][roomIndex].getCol() + ", "
//									+ tiles[rowIndex][colIndex][roomIndex].getType() + ")");
//							
//						}
//						
//					}
//					
//					rowIndex++; //adding to make the if loop iterate through it
//				}
//			}
//			
//		}catch (FileNotFoundException e){
//			System.out.println(e);
//		}
	    while(scanner.hasNextLine() && rowIndex < numRows) {
	    //grab a row
	    	String row = scanner.nextLine();
	    	row = row.substring(0, numCols);
	    	System.out.println(row);
	        /*for(int i = 0; i < numCols && i < row.length(); i++) {
	         * 		System.out.println(row.charAt(i));
	        }*/
	                rowIndex++;
	                
	     }
	    scanner.close();   
	    } catch(FileNotFoundException e) {
	    	System.out.println("File not found :(");
	    }

		
	}
	public static void coordinateReader(String filename) {
        String[][] maze;
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            
            int numRows = scanner.nextInt();
            int numCols = scanner.nextInt();
            int numRooms = scanner.nextInt();
            scanner.nextLine();
            
            System.out.println(numRows + " " + numCols + " " + numRooms);
            
            //grab each line and process each as one row of the map
            maze = new String[numRows][numCols];
            for (int r = 0; r < numRows; r++) {
                for (int c = 0; c < numCols; c++) {
                    maze[r][c] = "."; //fill all position with the dot
                }
            }
            
            int rowIndex = 0;
            while(scanner.hasNextLine()) {
                //grab a row
                String row = scanner.nextLine();
                //grab the first character in the row
                String c = row.substring(0,1);            
                //grab the x-coordinate by using substring
                int x = Integer.parseInt(row.substring(2,3));
                //grab the y-coordinate by using substring
                int y = Integer.parseInt(row.substring(4,5));
                
                //replace position with proper character 
                maze[x][y] = c;
                rowIndex++; //update the row
            }
            scanner.close();
            //change the 2d array to string 
             for (String[] row : maze) {
                    System.out.println(String.join("", row));
                }
            
        } catch(FileNotFoundException e) {
            System.out.println("File not found :(");
        }

    }

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
	
	 
	


