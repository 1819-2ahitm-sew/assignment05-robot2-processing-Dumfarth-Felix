package at.htl.robot.gui;

import at.htl.robot.model.Direction;
import at.htl.robot.model.Robot;
import at.htl.robot.model.Mode;
import processing.core.PApplet;

/**
 * Felix Dumfarth
 */

public class Main extends PApplet {
    int leftMargin = 20;
    int upperMargin = 50;
    int boxLenght = 50;
    Robot robot;
    Robot robot2;
    int kills = 0;


    // Hier die Member-Attribute eintragen

    public static void main(String[] args) {
        PApplet.main("at.htl.robot.gui.Main", args);
    }

    public void settings() {
        size(800, 800);
    }

    public void setup() {
        background(209); //https://processing.org/tutorials/color/
        robot = new Robot();
        robot.setX(1);
        robot.setY(1);
        robot2 = new Robot();
        robot2.setX(10);
        robot2.setY(10);

    }

    /**
     * Diese Methode wird iterativ durchlaufen (wie loop() beim Arduino)
     */
    public void draw() {

        textSize((int) (upperMargin * 0.5));
        strokeWeight(2);
        text("Roboter", 10, 30);

        for (int i = 0; i < 11; i++) {


            line(leftMargin, upperMargin + i * boxLenght, leftMargin + 10 * boxLenght, upperMargin + i * boxLenght);
            line(leftMargin + i * boxLenght, upperMargin, leftMargin + i * boxLenght, upperMargin + 10 * boxLenght);
            drawRobot(robot);
            drawRobot2(robot2);
            if (robot.getX() == robot2.getX() && robot.getY() == robot2.getY()) {
                kills++;
                deleteAll();
                robot.setDirection(Direction.SOUTH);
                robot2.setDirection(Direction.SOUTH);
                robot.setX(1);
                robot.setY(1);
                robot2.setX(10);
                robot2.setY(10);

            }
            text("Kills: " + kills, leftMargin, upperMargin + 11 * boxLenght);
            text("Player White: <F> Schritt nach Vorne  <L> ... Nach Links Drehen \nPlayer Red: <4> Schritt nach Vorne  <6> ... Nach Links Drehen \nAll:   <M> ... Modus ändern", leftMargin, upperMargin + 12 * boxLenght, 800, upperMargin + 14 * boxLenght);


        }
    }


    /**
     * Erstellen Sie eine eigene Methode, mittels der der Roboter am Bildschirm gezeichnet wird
     * Die Angabe zu Position des Roboters am Spielfeld erhalten Sie aus dem Roboter-Objekt, welches
     * als Parameter übergeben wird.
     *
     * @param robot Objekt des zu zeichnenden Roboters
     */
    public void drawRobot(Robot robot) {
        int boxCenterX = leftMargin + robot.getX() * boxLenght - boxLenght / 2;
        int boxCenterY = upperMargin + robot.getY() * boxLenght - boxLenght / 2;

        ellipse(boxCenterX,
                boxCenterY,
                (int) (boxLenght * 0.8),
                (int) (boxLenght * 0.8));

        writeMode(robot, boxCenterX, boxCenterY);

    }

    private void drawRobot2(Robot robot2) {
        int boxCenterX = leftMargin + robot2.getX() * boxLenght - boxLenght / 2;
        int boxCenterY = upperMargin + robot2.getY() * boxLenght - boxLenght / 2;
        fill(255, 0, 0);
        ellipse(boxCenterX,
                boxCenterY,
                (int) (boxLenght * 0.8),
                (int) (boxLenght * 0.8));

        writeMode2(robot2, boxCenterX, boxCenterY);
    }

    private void writeMode(Robot robot, int boxCenterX, int boxCenterY) {
        char actualMode;
        if (robot.getMode() == Mode.RESTRICT) {
            actualMode = 'R';
        } else {
            actualMode = 'T';
        }
        textSize((int) (boxLenght * 0.3));
        if (robot.getDirection() == Direction.NORTH) {
            fill(0, 102, 153);
            text(actualMode, boxCenterX - boxLenght / 12, boxCenterY);
            fill(255, 255, 255);
        } else if (robot.getDirection() == Direction.WEST) {
            fill(0, 102, 153);
            text(actualMode, boxCenterX - 2 * (boxLenght / 12), boxCenterY + boxLenght / 8);
            fill(255, 255, 255);

        } else if (robot.getDirection() == Direction.SOUTH) {
            fill(0, 102, 153);
            text(actualMode, boxCenterX - boxLenght / 12, boxCenterY + 2 * (boxLenght / 8));
            fill(255, 255, 255);

        } else if (robot.getDirection() == Direction.EAST) {
            fill(0, 102, 153);
            text(actualMode, boxCenterX + (boxLenght / 12), boxCenterY + boxLenght / 8);
            fill(255, 255, 255);

        }
    }

    private void writeMode2(Robot robot2, int boxCenterX, int boxCenterY) {
        char actualMode;
        if (robot2.getMode() == Mode.RESTRICT) {
            actualMode = 'R';
        } else {
            actualMode = 'T';
        }
        textSize((int) (boxLenght * 0.3));
        fill(255, 255, 255);
        if (robot2.getDirection() == Direction.NORTH) {

            text(actualMode, boxCenterX - boxLenght / 12, boxCenterY);

        } else if (robot2.getDirection() == Direction.WEST) {

            text(actualMode, boxCenterX - 2 * (boxLenght / 12), boxCenterY + boxLenght / 8);

        } else if (robot2.getDirection() == Direction.SOUTH) {

            text(actualMode, boxCenterX - boxLenght / 12, boxCenterY + 2 * (boxLenght / 8));


        } else if (robot2.getDirection() == Direction.EAST) {

            text(actualMode, boxCenterX + (boxLenght / 12), boxCenterY + boxLenght / 8);


        }
    }

    /**
     * Erstellen Sie eine eigene Methode zum Löschen des Bildschirms
     */
    public void deleteAll() {
        background(209);
    }

    /**
     * In dieser Methode reagieren Sie auf die Tasten
     */
    public void keyPressed() {
        println("pressed " + key + " " + keyCode);


        if (key == 'f' || key == 'F') {
            deleteAll();
            robot.stepForward();
        } else if (key == 'l' || key == 'L') {
            robot.rotateLeft();
        } else if (key == 'm' || key == 'M') {
            deleteAll();
            if (robot.getMode() == Mode.RESTRICT) {
                robot.setMode(Mode.TELEPORT);
            } else {
                robot.setMode(Mode.RESTRICT);
            }
        }
        if (key == '4') {
            deleteAll();
            robot2.stepForward();
        } else if (key == '6') {
            robot2.rotateLeft();
        } else if (key == 'm' || key == 'M') {
            deleteAll();
            if (robot2.getMode() == Mode.RESTRICT) {
                robot2.setMode(Mode.TELEPORT);
            } else {
                robot2.setMode(Mode.RESTRICT);
            }

        }

//    public void keyTyped() {
//        println("typed " + key + " " + keyCode);
//    }
//
//    public void keyReleased() {
//        println("released " + key + " " + keyCode);
//    }

    }
}
