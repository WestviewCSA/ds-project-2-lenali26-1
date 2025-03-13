import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class p2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("p2");
		
		readMap("src/Test01");
		readMap("src/Test02");
		readMap("src/Test03");
		readMap("src/Test04");
		readMap("src/Test05");
		
	}
	
	public static void readMap(String filename) {
		
		try {
			File file = new File(filename); 
			Scanner scanner = new Scanner(file);
			
			
			int numRows = scanner.nextInt(); 
			int numCols = scanner.nextInt(); 
			int numRooms = scanner.nextInt();
			
			Tile[][][] tiles = new Tile[numRows][numCols][numRooms];
			
			int rowIndex = 0;
			int colsIndex = 0; 
			int roomsIndex = 0; 
			
			//process the map 
			while(scanner.hasNextLine()) {
				//grab a line (one row of the map)
				String row = scanner.nextLine();
				System.out.println(row);
				
				if(row.length() > 0) 
					for(int i = 0; i < numCols && i < row.length(); i++) {
						char el = row.charAt(i); 
						Tile obj = new Tile(rowIndex, i, el); 
						
						
						if(obj.getType() == 'W') {
							System.out.println("(" + rowIndex + "," + i + ")");
						}
						
					}
					rowIndex++;
				}
			
		} catch (FileNotFoundException e) {
			//handle exception
			System.out.println(e);
		}
		
		
	}
	
	
}
