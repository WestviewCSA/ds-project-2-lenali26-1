import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class p2 {
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
			
			//initializing variables
			int rowIndex = 0;
			int roomIndex = 0;
			int colIndex = 0;
			
			//creating a 3-D array with the tiles
			Tile[][][] tiles = new Tile[numRows][numCols][numRooms];
			
			//process the map
			
			while (scanner.hasNextLine()) {
				
				//grab a line (one row of the map)
				String row = scanner.nextLine();
				System.out.println(row);
				
				//check if out of bounds for the row
				if (row.length()>0) {
					//check if out of bounds for the column
					for(colIndex = 0; colIndex < numCols && colIndex < row.length(); colIndex++) {
						
						//setting the index of the character to the position of the column 
						char el = row.charAt(colIndex);
						//setting a new tile object that implements in the index and position and the character
						Tile obj = new Tile(rowIndex, colIndex, el);
						//setting a new tile as it iterates
						tiles[rowIndex][colIndex][roomIndex] = new Tile(rowIndex, colIndex, el);
						//printing out the values
						System.out.println("Tile at (" + tiles[rowIndex][colIndex][roomIndex].getRow()
								+ ", " + tiles[rowIndex][colIndex][roomIndex].getCol() + ", "
								+ tiles[rowIndex][colIndex][roomIndex].getType() + ")");
						
					}
					
					rowIndex++; //adding to make the if loop iterate through it
				}
			}
			
		}catch (FileNotFoundException e){
			System.out.println(e);
		}
		
	}
	 
	
}

