package CSCI446.Project4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by thechucklingatom on 12/7/16.
 */
public class WorldGenerator {
	private int worldHeight, worldWidth;
	private FileReader fileReader;
	World generatedWorld;

	public WorldGenerator(String filePath){
		generatedWorld = new World();
		try {
			fileReader = new FileReader(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void generateWorld(){
		BufferedReader reader = new BufferedReader(fileReader);
		try {
			String line1 = reader.readLine();
			String[] sizes = line1.split(",");
			worldHeight = Integer.valueOf(sizes[0]);
			worldWidth = Integer.valueOf(sizes[1]);
			Tile[][] world = new Tile[worldHeight][worldWidth];

			String currentLine;
			int rowCounter = 0;

			while((currentLine = reader.readLine()) != null){
				char[] charArray = currentLine.toCharArray();
				for (int i = 0; i < charArray.length; i++) {
					Tile toAdd;
					if(charArray[i] == '#') {
						toAdd = new Tile(generatedWorld, Tile.TileType.WALL, rowCounter, i);
					}else if(charArray[i] == 'S'){
						toAdd = new Tile(generatedWorld, Tile.TileType.START, rowCounter, i);
					}else if(charArray[i] == 'F'){
						toAdd = new Tile(generatedWorld, Tile.TileType.FINISH, rowCounter, i);
					}else{
						toAdd = new Tile(generatedWorld, Tile.TileType.SAFE, rowCounter, i);
					}
					world[rowCounter][i] = toAdd;
				}
				rowCounter++;
			}

			generatedWorld = new World(world);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
