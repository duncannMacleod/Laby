import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class laby2 extends Application {

    private Group root = new Group();
    private int cellSize = 40;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Parcours en Largeur du Labyrinthe");
        Scene scene = new Scene(root, 500, 500, Color.WHITE);
        boolean tresorTrouve=false;
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

        Robot robot = new Robot(1, 4, "Sud");
        Queue<Case> file = new LinkedList<>(); 
        List<Case> visitees = new ArrayList<>();
        file.add(new Case(robot.getX(), robot.getY()));

        while (!tresorTrouve) {
            //Case current = file.remove();
            //int x = current.getX();
            //int y = current.getY();
        	int x = robot.getX();
            int y = robot.getY();
            if (tableau[x][y] == 4) {
                System.out.println("Trésor trouvé à la position : (" + x + ", " + y + ")");
                tresorTrouve=true;
                break; // Sort de la boucle une fois le trésor trouvé
            }

            robot.setPosition(x, y);

            drawRobot(robot.getX(), robot.getY(), robot.getOrientation(), getColorByLevel(y)); // Dessine la position actuelle du robot
            //pause();

            // Essaie de se déplacer dans les 3 directions
            Robot avancer = robot.avancer();
            Robot droite = robot.tournerDroite();
            Robot gauche = robot.tournerGauche();

            if (peutAvancer(tableau, avancer, visitees)) {
                //file.add(new Case(avancer.getX(), avancer.getY()));
                visitees.add(new Case(avancer.getX(), avancer.getY()));
                robot.setPosition(avancer.getX(),avancer.getY());
            }
            else if (peutAvancer(tableau, droite, visitees)) {
                //file.add(new Case(droite.getX(), droite.getY()));
                visitees.add(new Case(droite.getX(), droite.getY()));
                robot.setPosition(droite.getX(),droite.getY());
            }
            else if (peutAvancer(tableau, gauche, visitees)) {
                //file.add(new Case(gauche.getX(), gauche.getY()));
                visitees.add(new Case(gauche.getX(), gauche.getY()));
                robot.setPosition(gauche.getX(),gauche.getY());
            }
            
        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public boolean peutAvancer(int[][] tableau, Robot robot,List<Case> visitees) {
        if (robot == null) return false;
        int x = robot.getX();
        int y = robot.getY();

        return x >= 0 && x < tableau.length && y >= 0 && y < tableau[0].length &&
                tableau[x][y] != 2 && tableau[x][y] != 0 && !visitees.contains(new Case(x,y));
    }

    private void drawRobot(int x, int y, String orientation, Color color) {
        Circle circle = new Circle((x + 0.5) * cellSize, (y + 0.5) * cellSize, cellSize / 3);
        circle.setFill(color);
        root.getChildren().add(circle);

        double arrowSize = 10;
        switch (orientation) {
            case "Nord":
                root.getChildren().add(new Line((x + 0.5) * cellSize, (y + 0.5) * cellSize,
                        (x + 0.5) * cellSize, (y + 0.5) * cellSize - arrowSize));
                break;
            case "Sud":
                root.getChildren().add(new Line((x + 0.5) * cellSize, (y + 0.5) * cellSize,
                        (x + 0.5) * cellSize, (y + 0.5) * cellSize + arrowSize));
                break;
            case "Est":
                root.getChildren().add(new Line((x + 0.5) * cellSize, (y + 0.5) * cellSize,
                        (x + 0.5) * cellSize + arrowSize, (y + 0.5) * cellSize));
                break;
            case "Ouest":
                root.getChildren().add(new Line((x + 0.5) * cellSize, (y + 0.5) * cellSize,
                        (x + 0.5) * cellSize - arrowSize, (y + 0.5) * cellSize));
                break;
        }
    }

    private Color getColorByLevel(int level) {
        int numLevels = 10; // Nombre de niveaux
        double hue = (360.0 / numLevels) * level;
        return Color.hsb(hue, 1.0, 1.0);
    }

    static class Robot {
        private int x, y;
        private String orientation; // Nord, Sud, Est, Ouest

        public Robot(int x, int y, String orientation) {
            this.x = x;
            this.y = y;
            this.orientation = orientation;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public String getOrientation() {
            return orientation;
        }

        public void setPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Robot avancer(String action) {
            switch (action) {
                case "Avancer":
                    return avancer();
                case "Droite":
                    return tournerDroite();
                case "Gauche":
                    return tournerGauche();
            }
            return null;
        }

        private Robot avancer() {
            switch (orientation) {
                case "Nord":
                    return new Robot(x - 1, y, "Nord");
                case "Sud":
                    return new Robot(x + 1, y, "Sud");
                case "Est":
                    return new Robot(x, y + 1, "Est");
                case "Ouest":
                    return new Robot(x, y - 1, "Ouest");
            }
            return null;
        }

        private Robot tournerDroite() {
            switch (orientation) {
                case "Nord":
                    return new Robot(x, y+1, "Est");
                case "Sud":
                    return new Robot(x, y-1, "Ouest");
                case "Est":
                    return new Robot(x+1, y, "Sud");
                case "Ouest":
                    return new Robot(x-1, y, "Nord");
            }
            return null;
        }

        private Robot tournerGauche() {
            switch (orientation) {
                case "Nord":
                    return new Robot(x, y-1, "Ouest");
                case "Sud":
                    return new Robot(x, y+1, "Est");
                case "Est":
                    return new Robot(x-1, y, "Nord");
                case "Ouest":
                    return new Robot(x+1, y, "Sud");
            }
            return null;
        }
    }

    static class Case {
        private int x, y;

        public Case(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
