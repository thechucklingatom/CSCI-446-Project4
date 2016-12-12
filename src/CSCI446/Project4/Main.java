package CSCI446.Project4;

import java.io.File;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by thechucklingatom on 12/6/2016.
 */
public class Main {
	public static void main(String[] args){
		WorldGenerator worldGenerator = new WorldGenerator("tracks" +
				File.separator +
				"L-track.txt");

		worldGenerator.generateWorld();
		System.out.println(worldGenerator);

		ValueIteration valueIteration = new ValueIteration(worldGenerator.generatedWorld);
		valueIteration.runValueIteration();

		System.out.println("Iteration done");

		/*QLearn qLearn = QLearn.getInstance(worldGenerator.generatedWorld,
				new State(worldGenerator.generatedWorld.startTile, new Velocity()));

		qLearn.runQLearn();*/
	}
}
