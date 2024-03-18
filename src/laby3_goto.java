import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class laby3_goto {
	enum cardD {
		North,
		South,
		East,
		West
	}

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_PURPLE = "\033[0;35m";
	public static final int rayon = 25;//mm
	public static int[][] carte = { { 0, 0, 0, 0, 2, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
			{ 0, 3, 1, 1, 3, 1, 1, 3, 0, 0 },
			{ 0, 1, 0, 0, 0, 0, 0, 1, 0, 0 },
			{ 0, 3, 1, 2, 3, 1, 4, 1, 0, 0 },
			{ 0, 1, 0, 0, 1, 0, 0, 1, 0, 0 },
			{ 0, 3, 1, 1, 3, 1, 1, 3, 0, 0 },
			{ 0, 0, 0, 0, 1, 0, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 2, 0, 0, 2, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

	final static int ROWS = carte.length;
	final static int COLS = carte[0].length;

	public static ArrayList<Point> goTo(cardD direction) {

		// TODO avancer dans la direction donnée et trouver
		return null;
	}

	static class Point {
		int x;
		int y;
		int dist;
		Point parent;

		public Point(int x, int y, int dist, Point parent) {
			this.x = x;
			this.y = y;
			// this.dist = dist;
			this.parent = parent;
		}

	}

	static class Robot {
		int x;
		int y;
		cardD orientation;

		public Robot() {
			x = 0;
			y = 0;
			orientation = cardD.South;
		}

		public Robot(int x, int y, cardD orientation) {
			this.x = x;
			this.y = y;
			this.orientation = orientation;
		}
		
		public int avancerJusquaEvent(){ //fait avancer le robot jusqu'au prochain évènement. Retourne la valeur de distance parcourrue
			
			//ColorSensor cs = new ColorSensor(SensorPort.S4);
			//Motor.A.setSpeed(150); 
			//Motor.B.setSpeed(150);
			//do{
				//Motor.A.Foward();
				//Motor.B.Foward();
			//}while(cs.getColorID()==7);
			
			return 0;
			
		}
	}

	public static boolean isValid(int x, int y, int[][] tableau) {
		return (x >= 0 && x < ROWS && y >= 0 && y < COLS) && tableau[x][y] != 2 && tableau[x][y] != 0;
	}

	public static Queue<String> convertirEnInstructions(Stack<Point> victorieuse) {
		Queue<String> instructions = new LinkedList<>();

		while (!victorieuse.isEmpty()) {
			Point current = victorieuse.pop();
			Point parent = current.parent;

			if (parent == null) {
				continue; // Point de départ
			}

			int diffX = current.x - parent.x;
			int diffY = current.y - parent.y;

			if (diffX == 0 && diffY == 1) {
				instructions.add("Droite");
			} else if (diffX == 0 && diffY == -1) {
				instructions.add("Gauche");
			} else if (diffX == 1 && diffY == 0) {
				instructions.add("Bas");
			} else if (diffX == -1 && diffY == 0) {
				instructions.add("Haut");
			}
		}

		return instructions;
	}

	/**
	 * @param carte
	 * @return
	 */
	public static Stack<Point> trouverTresor(int[][] carte, int startX, int startY, Robot r) {

		boolean[][] visited = new boolean[ROWS][COLS];
		Point startPoint;
		switch (r.orientation) {
			case North:
			startPoint = new Point(startX+1, startY, 1, null);
				break;
			case South:
			startPoint = new Point(startX-1, startY, 1, null);	
				break;
			case East:
			startPoint = new Point(startX, startY-1, 1, null);	
				break;
			case West:
			startPoint = new Point(startX, startY+1, 1, null);	
				break;
			default:
			startPoint = new Point(startX, startY, 1, null);
				break;
		}
		ArrayList<Point> queue = new ArrayList<>();
		Stack<Point> victorieuse = new Stack<>();

		//queue.add(new Point(startX, startY, 0, startPoint));//premier point dans la queue
		
		while (!queue.isEmpty()) {
			Point current = queue.remove(0);
			int x = current.x;
			int y = current.y;

			if (carte[x][y] == 4) {
				// Construire la branche victorieuse
				while (current != null) {
					victorieuse.push(current);
					current = current.parent;
				}
				return victorieuse;
			}
			if (carte[x][y] == 2 || visited[x][y]) {
				continue;
			}
			visited[x][y] = true;

			// Vérifier s'il y a un croisement
			boolean isIntersection = false;
			if (carte[x][y] == 3) {
				isIntersection = true;
			}

			if (isIntersection) {
				// TODO recherche intersection
			} else {
				// TODO recherche même direction
			}
			triQueue(queue, x, y);
		}

		// Si le trésor n'est pas trouvé, renvoyer une pile vide
		return new Stack<>();
	}

	public static void main(String[] args) {

		Robot robot = initRobot();
		Stack<Point> victorieuse = trouverTresor(carte, 1, 4, robot);

		if (!victorieuse.isEmpty()) {
			System.out.println("Les mouvements pour atteindre le trésor et y repartir sont :");
			Queue<String> instructions = convertirEnInstructions(victorieuse);
			while (!instructions.isEmpty()) {
				System.out.println(instructions.poll());
			}
		} else {
			System.out.println("Le trésor n'a pas été trouvé.");
		}
	}

	public static ArrayList<Point> triQueue(ArrayList<Point> oldQueue, int x, int y) {
		// Utilisation d'un comparateur personnalisé pour trier les points par distance
		Collections.sort(oldQueue, new Comparator<Point>() {
			@Override
			public int compare(Point p1, Point p2) {
				double distP1 = Math.sqrt(Math.pow(p1.x - x, 2) + Math.pow(p1.y - y, 2));
				double distP2 = Math.sqrt(Math.pow(p2.x - x, 2) + Math.pow(p2.y - y, 2));
				return Double.compare(distP1, distP2);
			}
		});

		return oldQueue;
	}

	public static Robot initRobot() {
		Robot r = new Robot();
		// choix des données de départ dans l'interface robot
		r.orientation = cardD.South;
		r.x = 1;
		r.y = 4;
		return r;
	}
}