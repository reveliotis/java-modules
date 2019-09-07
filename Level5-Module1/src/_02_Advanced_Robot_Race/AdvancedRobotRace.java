package _02_Advanced_Robot_Race;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.jointheleague.graphical.robot.Robot;

public class AdvancedRobotRace {
	
	// Re-do the robot race recipe from level 3 module 0. 
	// This time, use threads to make all of the robots go at the same time.

	private static final int NUM_ROBOTS = 5;

	private static int nextPosition = 0;
	private static boolean robotReachedTop = false;

	private Thread[] raceThreads;
	
	

	public static void main(String[] args) {
		
		AdvancedRobotRace race = new AdvancedRobotRace();
		race.begin();
		
	}

	public AdvancedRobotRace() {
		
		raceThreads = new Thread[NUM_ROBOTS];
		
		

		for (int i = 0; i < raceThreads.length; i++) {
			long randomSeed = Math.abs(new Random().nextLong());
			raceThreads[i] = new Thread(new RunnableRobot(randomSeed));
		}
		
	}

	public void begin() {
		
		for (int i = 0; i < raceThreads.length; i++) {
			raceThreads[i].start();
		}
		
	}

	public class RunnableRobot implements Runnable {

		private Robot robot;
		private int position;
		private Random random;

		private RunnableRobot(long randomSeed) {
			position = nextPosition;
			robot = new Robot(100 + position * 100, 700);
			nextPosition++;
			random = new Random(randomSeed);
		}

		@Override
		public void run() {

			while (!robotReachedTop) {
					robot.move(random.nextInt(50));
	
				if (robot.getY() <= 0) {
					
					if (!robotReachedTop) {
						robotReachedTop = true;
						System.out.println("Robot " + position + " won!");
						robot.sparkle();
					}
				}
			}

		}

	}
}
