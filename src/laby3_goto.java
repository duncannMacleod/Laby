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
	public static int[][] carte2 = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
									{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
									{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
									{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
									{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
									{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
									{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
									{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
									{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
									{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
									{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
									{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
									{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
									{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
									{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };

	final static int ROWS = carte.length;//lignes
	final static int COLS = carte[0].length;//collones

	

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
		
		public int avancerJusquaEvent(){ // TODO fait avancer le robot jusqu'au prochain évènement. Retourne la valeur de distance parcourrue
			
			//ColorSensor cs = new ColorSensor(SensorPort.S4);
			//Motor.A.setSpeed(150); 
			//Motor.B.setSpeed(150);
			//do{
				//Motor.A.Foward();
				//Motor.B.Foward();
			//}while(cs.getColorID()==7);
			
			return 0;

		}
		public ArrayList<Point> locatePathAtIntersection(){
			//TODO tourner sur soi meme et trouver les prochaines casses à explorer + étapes de vérif (encore présente dans laby3)
			return null;
		}
		public Point locatePathAtRoad(){
			//TODO en fonction de l'orientation du robot, trouver le prochain point à explorer (celui devant soi) + plus vérif comme d'hab
			return null;
		}
		public void goTo(Point pointGoal) {

			// TODO se déplacer jusqu'à une case + doit s'orienter vers la dirrection encore non visité (doit de rendre à la case parent pour s'orienter vers la case )
			
		}
		public boolean goToMandatory(int xGoal,int yGoal){//tester si le programme goto est obligatoire
			int diffX = xGoal - this.x;
			int diffY = yGoal - this.y;//utilise this pour plus de clarté
			if (diffX>1||diffY>1)
				return true;
			return false;
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
	public static Stack<Point> trouverTresor(int[][] carte, Robot r) {

		boolean[][] visited = new boolean[ROWS][COLS];
		int startX=0,startY=0;
		Point startPoint;
		switch (r.orientation) {
			case North:
				startX=carte.length-2;//length => last row + 1
				startY=carte.length/2;
				startPoint = new Point(startX + 1, startY, 1, null);
				break;
			case South:
				startX=1;
				startY=carte.length/2;
				startPoint = new Point(startX - 1, startY, 1, null);
				break;
			case East:
				startX=carte.length/2;
				startY=carte.length-2;
				startPoint = new Point(startX, startY - 1, 1, null);
				break;
			case West:
				startX=carte.length/2;
				startY=1;
				startPoint = new Point(startX, startY + 1, 1, null);
				break;
			default:
				startPoint = new Point(startX, startY, 1, null);
				break;
		}
		ArrayList<Point> queue = new ArrayList<>();
		Stack<Point> victorieuse = new Stack<>();

		queue.add(new Point(startX, startY, 0, startPoint));//premier point dans la queue

		while (!queue.isEmpty()) {
			
			Point current = queue.remove(0);
			int x = current.x;
			int y = current.y;

			if(r.goToMandatory(x,y)){
				r.goTo(current);//se rend au point étudié en parcourant le labyrinthe
			}
			int distanceParent=r.avancerJusquaEvent();
			ajoutCaseCarte(distanceParent,current,r);//écrit sur la carte l'avancée qui vient d'être faite


			if (carte[x][y] == 4) {//à remplacer pour fonctionner sans carte... +création carte en meme temps ATTENTION si on crée la carte avant dans l'absolu sa marcherai
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
				queue.addAll(r.locatePathAtIntersection());
			} else {
				queue.add(r.locatePathAtRoad());
			}
			triQueue(queue,r);
		}

		// Si le trésor n'est pas trouvé, renvoyer une pile vide
		return new Stack<>();
	}

	public static void main(String[] args) {

		Robot robot = initRobot();
		Stack<Point> victorieuse = trouverTresor(carte,robot);

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

	public static ArrayList<Point> triQueue(ArrayList<Point> oldQueue, Robot r) {
		// Utilisation d'un comparateur personnalisé pour trier les points par distance
		Collections.sort(oldQueue, new Comparator<Point>() {
			@Override
			public int compare(Point p1, Point p2) {
				double distP1 = Math.sqrt(Math.pow(p1.x - r.x, 2) + Math.pow(p1.y - r.y, 2));
				double distP2 = Math.sqrt(Math.pow(p2.x - r.x, 2) + Math.pow(p2.y - r.y, 2));
				return Double.compare(distP1, distP2);
			}
		});

		return oldQueue;
	}
	public static void ajoutCaseCarte(int distanceParent,Point caseEtudiee,Robot r){
		//TODO écrire sur la carte l'avancée que le robot à fait
	}
	public static Robot initRobot() {
		Robot r = new Robot();
		// choix des données de départ dans l'interface robot
		r.orientation = cardD.South;
		return r;
	}
}