package CSCI446.Project4;

import java.io.File;

/**
 * Created by thechucklingatom on 12/6/2016.
 */
public class Main {
	public static void main(String[] args){
		WorldGenerator worldGenerator = new WorldGenerator("tracks" +
				File.separator +
		"L-track.txt");

		worldGenerator.generateWorld();
	}
}
