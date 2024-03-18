import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class laby3 {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_PURPLE = "\033[0;35m";
	
	static final int[][] carte = { { 0, 0, 0, 0, 2, 0, 0, 0, 0, 0 },
									{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
									{ 0, 3, 1, 1, 3, 1, 1, 3, 0, 0 },
									{ 0, 1, 0, 0, 0, 0, 0, 1, 0, 0 },
									{ 0, 3, 1, 2, 3, 1, 4, 1, 0, 0 },
									{ 0, 1, 0, 0, 1, 0, 0, 1, 0, 0 },
									{ 0, 3, 1, 1, 3, 1, 1, 3, 0, 0 },
									{ 0, 0, 0, 0, 1, 0, 0, 1, 0, 0 },
									{ 0, 0, 0, 0, 2, 0, 0, 2, 0, 0 },
									{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

	static final int[][] carte2 = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
									{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
									{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
									{ 0, 0, 0, 0, 3, 1, 3, 0, 0, 0 },
									{ 2, 0, 0, 0, 1, 0, 1, 0, 0, 0 },
									{ 1, 0, 3, 1, 3, 0, 4, 2, 0, 0 },
									{ 1, 0, 1, 0, 1, 0, 0, 1, 0, 0 },
									{ 3, 1, 3, 1, 3, 1, 1, 3, 0, 0 },
									{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
									{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

	static final int ROWS = carte.length;
	static final int COLS = carte[0].length;

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

	public static Stack<Point> trouverTresor(int[][] carte,int startX,int startY) {
		boolean[][] visited = new boolean[ROWS][COLS];

		Point startPoint = new Point(startX - 1, startY, 0, null); // Le point au dessus de (1,4)

		ArrayList<Point> queue = new ArrayList<>();
		queue.add(new Point(startX, startY, 0, startPoint));

		Stack<Point> victorieuse = new Stack<>();

		while (!queue.isEmpty()) {
			Point current = queue.remove(0);
			int x = current.x;
			int y = current.y;
			int dist = current.dist;

			for (int i = 0; i < 8; ++i)
				System.out.println();
			System.out.println("  0   1   2   3   4   5   6   7   8   9");
			for (int i = 0; i < ROWS; i++) {
				System.out.print(i);
				for (int j = 0; j < COLS; j++) {
					if (i == x && j == y)
						System.out.print("[" + ANSI_PURPLE + "X" + ANSI_RESET + "] ");
					else if (visited[i][j] == true)
						System.out.print("[" + ANSI_GREEN + "X" + ANSI_RESET + "] ");
					else if (carte[i][j] == 0)
						System.out.print("[ ] ");
					else if (carte[i][j] == 1)
						System.out.print("[" + carte[i][j] + "] ");
					else if (carte[i][j] == 2)
						System.out.print("[" + ANSI_RED + carte[i][j] + ANSI_RESET + "] ");
					else if (carte[i][j] == 3)
						System.out.print("[" + ANSI_CYAN + carte[i][j] + ANSI_RESET + "] ");
					else if (carte[i][j] == 4)
						System.out.print("[" + ANSI_YELLOW + carte[i][j] + ANSI_RESET + "] ");

				}
				System.out.println();
			}
			System.out.println();

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
				// Droit
				if (isValid(x, y + 1, carte) && !visited[x][y + 1] && (carte[x][y + 1] != 0 || isIntersection)) {
					queue.add(new Point(x, y + 1, dist + 1, current));
				}

				// Gauche
				if (isValid(x, y - 1, carte) && !visited[x][y - 1] && (carte[x][y - 1] != 0 || isIntersection)) {
					queue.add(new Point(x, y - 1, dist + 1, current));
				}

				// Bas
				if (isValid(x + 1, y, carte) && !visited[x + 1][y] && (carte[x + 1][y] != 0 || isIntersection)) {
					queue.add(new Point(x + 1, y, dist + 1, current));
				}

				// Haut
				if (isValid(x - 1, y, carte) && !visited[x - 1][y] && (carte[x - 1][y] != 0 || isIntersection)) {
					queue.add(new Point(x - 1, y, dist + 1, current));
				}
			} else {
				// Chemin normal
				Point parent = current.parent;
				int diffX = x - parent.x;
				int diffY = y - parent.y;

				if (diffX == 0 && diffY == -1) {
					// Aller à gauche
					queue.add(new Point(x, y - 1, dist + 1, current));
				} else if (diffX == 0 && diffY == 1) {
					// Aller à droite
					queue.add(new Point(x, y + 1, dist + 1, current));
				} else if (diffX == -1 && diffY == 0) {
					// Aller en haut
					queue.add(new Point(x - 1, y, dist + 1, current));
				} else if (diffX == 1 && diffY == 0) {
					// Aller en bas
					queue.add(new Point(x + 1, y, dist + 1, current));
				}
			}
			triQueue(queue, x, y);
		}

		// Si le trésor n'est pas trouvé, renvoyer une pile vide
		return new Stack<>();
	}

	public static void main(String[] args) {
		Stack<Point> victorieuse = trouverTresor(carte2,6,7);
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
}
