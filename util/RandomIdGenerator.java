package dipalma.parcheggioautomatico.util;

import java.util.Random;

public class RandomIdGenerator {
	
	public static int randInt(int min, int max) 
	{
		  Random rand = new Random();
		  int randomNum = rand.nextInt((max - min) + 1) + min;
		  return randomNum;
	}
}
