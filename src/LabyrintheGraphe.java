import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.Queue;

public class LabyrintheGraphe extends Application {

    static class Case {
        int x, y;
        int value;

        public Case(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    private Group root = new Group();
    private int cellSize = 40;
    private int delay = 500; // Milliseconds to wait between steps

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Parcours en Largeur du Labyrinthe");
        Scene scene = new Scene(root, 500, 500, Color.WHITE);

        int[][] tableau = {
            {0, 0, 0, 0, 2, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 3, 1, 1, 3, 1, 1, 3, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 3, 1, 2, 3, 1, 4, 1, 0, 0},
            {0, 1, 0, 0, 1, 0, 0, 1, 0, 0},
            {0, 3, 1, 1, 3, 1, 1, 3, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 2, 0, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        trouverTresor(tableau, 1, 4);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void trouverTresor(int[][] tableau, int startX, int startY) {
        Queue<Case> file = new LinkedList<>();
        boolean[][] visite = new boolean[tableau.length][tableau[0].length];

        file.add(new Case(startX, startY, tableau[startX][startY]));

        while (!file.isEmpty()) {
            Case current = file.remove();
            int x = current.x;
            int y = current.y;
            visite[x][y] = true;

            if (current.value == 4) {
                System.out.println("Trésor trouvé à la position : (" + x + ", " + y + ")");
                continue;
            }

            drawCase(x, y, getColorByLevel(y)); // Couleur en fonction du niveau
            pause();

            // Seulement sur les intersections (valeur 3)
            if (current.value == 3) {
                if (x > 0 && tableau[x - 1][y] != 2 && !visite[x - 1][y]) {
                    file.add(new Case(x - 1, y, tableau[x - 1][y]));
                    visite[x - 1][y] = true; // Marquer comme visité
                }
                if (x < tableau.length - 1 && tableau[x + 1][y] != 2 && !visite[x + 1][y]) {
                    file.add(new Case(x + 1, y, tableau[x + 1][y]));
                    visite[x + 1][y] = true; // Marquer comme visité
                }
                if (y > 0 && tableau[x][y - 1] != 2 && !visite[x][y - 1]) {
                    file.add(new Case(x, y - 1, tableau[x][y - 1]));
                    visite[x][y - 1] = true; // Marquer comme visité
                }
                if (y < tableau[0].length - 1 && tableau[x][y + 1] != 2 && !visite[x][y + 1]) {
                    file.add(new Case(x, y + 1, tableau[x][y + 1]));
                    visite[x][y + 1] = true; // Marquer comme visité
                }
            }
        }
    }

    private void drawCase(int x, int y, Color color) {
        Circle circle = new Circle((x + 0.5) * cellSize, (y + 0.5) * cellSize, cellSize / 3);
        circle.setFill(color);
        root.getChildren().add(circle);
    }

    private Color getColorByLevel(int level) {
        int numLevels = 10; // Nombre de niveaux
        double hue = (360.0 / numLevels) * level;
        return Color.hsb(hue, 1.0, 1.0);
    }

    private void pause() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
