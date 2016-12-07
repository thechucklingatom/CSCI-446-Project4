package CSCI446.Project4;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by thechucklingatom on 12/7/16.
 */
public class WorldGenerator {
	private int worldHeight, worldWidth;
	private FileReader fileReader;
	World generatedWorld;

	public WorldGenerator(String filePath){
		try {
			fileReader = new FileReader(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void generateWorld(){

	}
}
