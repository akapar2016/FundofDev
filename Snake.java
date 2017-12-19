/*
 * Author:  Aayush Kapar, akapar@my.fit.edu
 * Course:  CSE 1002, Section 01, Fall 2017
 * Project: Snake
 */

//package Snake;

package Snake;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.Random;

import Walk.StdDraw;

class Snake {
    int[] xCord;
    int[] yCord;
    private Color head;
    private Color body;
    int snakeLength;
    double snakeWidth;
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int ONEZERO = 10;
    static final double POINTFIVE = .5;
    //define snake color, length, food quantity according to difficulty
    int defineSnake (final int difficulty) {
        snakeWidth = (double) PlaySnake.size / (ONEZERO * FIVE);
        System.out.println("WIDTH = " + snakeWidth);
        if (difficulty == ONE) {
            body = StdDraw.GREEN;
            head = StdDraw.BLUE;
            snakeLength = (PlaySnake.size) / TWO;
            return FIVE;
        } else if (difficulty == TWO) {
            body = StdDraw.BLUE;
            head = StdDraw.RED;
            snakeLength = (PlaySnake.size) / THREE;
            return THREE;
        } else {
            body = StdDraw.RED;
            head = StdDraw.GREEN;
            snakeLength = (PlaySnake.size) / FOUR;
            return ONE;
        }
    }
    //setup snakeCord for start
    void start () {
        int drawX = PlaySnake.size / TWO;
        int drawY = PlaySnake.size / TWO;
        xCord[ZERO] = drawX;
        yCord[ZERO] = drawY;
        for (int count = ONE; count < snakeLength; count++) {
            if (drawX > ZERO) {
                drawX--;
            } else {
            drawY--;
            }
            xCord[count] = drawX;
            yCord[count] = drawY;
        }
    }
    //check for collision
    boolean collision () {
        //check if there is collision when snake touches itself
        //this can be anywhere in its width
        final int closeToHead = (int) ((int) snakeWidth * snakeWidth);
        //System.out.println("CLOSETOHEAD = " + closeToHead);
        for (int count = snakeLength; count > closeToHead; count--) {
            //System.out.println(xCord[count] + " " + yCord[count]);
            if ((xCord[ZERO] <= xCord[count] + snakeWidth
                    && xCord[ZERO] >= xCord[count] - snakeWidth)
                    && (yCord[ZERO] <= yCord[count] + snakeWidth
                    && yCord[ZERO] >= yCord[count] - snakeWidth)) {
                //System.out.println("FALSE");
                return false;
            }
        }
        // See if head hits borders
        if (yCord[ZERO] >= PlaySnake.size - snakeWidth) {
            return false;
        }
        if (yCord[ZERO] <= ZERO + snakeWidth) {
            return false;
        }
        if (xCord[ZERO] <= ZERO + snakeWidth) {
            return false;
        }
        if (xCord[ZERO] >= PlaySnake.size - snakeWidth) {
            return false;
        }
        return true;
    }
    //Draw snake
    void drawSnake () {
        StdDraw.setPenColor(body);
        for (int count = ONE; count < snakeLength; count++) {
            StdDraw.filledCircle(xCord[count], yCord[count], snakeWidth);
        }
        StdDraw.setPenColor(head);
        StdDraw.filledCircle(xCord[ZERO], yCord[ZERO], snakeWidth);
    }
    //move snakeCord for next movement
    void moveCord (final int direction) {
        for (int count = snakeLength; count >= ONE; count--) {
            xCord[count] = xCord[count - ONE];
            yCord[count] = yCord[count - ONE];
        }
        if (direction == ONE) {
            xCord[ZERO] = xCord[ZERO];
            yCord[ZERO] = yCord[ZERO] + ONE;
        }
        if (direction == TWO) {
            xCord[ZERO] = xCord[ZERO];
            yCord[ZERO] = yCord[ZERO] - ONE;
        }
        if (direction == THREE) {
            xCord[ZERO] = xCord[ZERO] - ONE;
            yCord[ZERO] = yCord[ZERO];
        }
        if (direction == FOUR) {
            xCord[ZERO] = xCord[ZERO] + ONE;
            yCord[ZERO] = yCord[ZERO];
        }
    }

    public static void main (final String[] args) {
        PlaySnake.playGame();
    }

    static class PlaySnake {

        private static final int ZERO = 0;
        private static final int ONE = 1;
        private static final int TWO = 2;
        private static final int THREE = 3;
        private static final int FOUR = 4;
        private static final int FIVE = 5;
        private static final int SIX = 6;
        private static final int SEVEN = 7;
        private static final int EIGHT = 8;
        private static final int NINE = 9;
        private static final int ONEZERO = 10;
        private static final double POINTFIVE = .5;
        private static final Font HEADER =
                new Font("Times New Roman", Font.BOLD, 20);
        private static final Font BODY =
                new Font("Times New Roman", Font.BOLD, 15);
        private static boolean dirRight = true;
        private static boolean dirLeft = false;
        private static boolean dirUp = false;
        private static boolean dirDown = false;
        private static int direction;
        static int size = ONEZERO;
        static int score, highScore;
        private static Food[] foodArr;

        static int userDifficulty () {
            final String easy = "EASY";
            final String intermediate = "INTERMEDIATE";
            final String hard = "HARD";
            defineGrid(true);
            StdDraw.clear(StdDraw.DARK_GRAY);
            StdDraw.setPenColor(StdDraw.GREEN);
            StdDraw.filledRectangle(TWO, EIGHT, TWO, ONE);
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setFont(HEADER);
            StdDraw.text(TWO, EIGHT, easy);
            StdDraw.filledRectangle(TWO, FIVE, TWO, ONE);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.text(TWO, FIVE, intermediate);
            StdDraw.filledRectangle(TWO, TWO, TWO, ONE);
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.text(TWO, TWO, hard);
            StdDraw.setFont(BODY);
            StdDraw.text(SEVEN, NINE, "LONGER STARTING SNAKE");
            StdDraw.text(SEVEN, EIGHT, "SLOW MOVEMENT");
            StdDraw.text(SEVEN, SEVEN, "MORE FOOD");
            StdDraw.text(SEVEN, THREE, "SHORT SNAKE");
            StdDraw.text(SEVEN, TWO, "FASTEST MOVEMENT");
            StdDraw.text(SEVEN, ONE, "FUSTRATION");
            while (true) {
                if (StdDraw.isMousePressed()
                        && StdDraw.mouseX() > ZERO
                        && StdDraw.mouseX() < FOUR) {

                    if (StdDraw.mouseY() > SEVEN && StdDraw.mouseY() < NINE) {
                        StdDraw.clear();
                        return 1;
                    }
                    if (StdDraw.mouseY() > FOUR && StdDraw.mouseY() < SIX) {
                        StdDraw.clear();
                        return 2;
                    }
                    if (StdDraw.mouseY() > ONE && StdDraw.mouseY() < THREE) {
                        StdDraw.clear();
                        return THREE;
                    }
                }
            }
        }
        static int askSize (final int dif) {
            StdDraw.clear(StdDraw.LIGHT_GRAY);
            if (dif == ONE) {
                StdDraw.setPenColor(StdDraw.GREEN);
            }
            if (dif == TWO) {
                StdDraw.setPenColor(StdDraw.BLUE);
            }
            if (dif == THREE) {
                StdDraw.setPenColor(StdDraw.RED);
            }
            StdDraw.filledRectangle(FIVE, FIVE, THREE, TWO);
            StdDraw.setFont(HEADER);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(FIVE, SIX, "TYPE IN PLAY AREA SIZE");
            StdDraw.setFont(BODY);
            StdDraw.text(FIVE, (double) FIVE + POINTFIVE, "Then Press enter. ");
            StdDraw.text(FIVE, FOUR, "size must be between 10 and 999");
            StdDraw.text(FIVE, (double) THREE + POINTFIVE,
                    "otherwise, size will be default to 50");

            getSize();
            //System.out.println(size);
            if (size < ONEZERO * ONEZERO * ONEZERO && size > ONEZERO) {
            return size;
            } else {
                return ONEZERO * FIVE;
            }
        }
        static void getSize () {
            size = ZERO;
            final int[] number = new int[THREE];
            int count = ZERO;
            while (true) {
                if (!StdDraw.isKeyPressed(KeyEvent.VK_ENTER) && count < THREE) {
                    if (StdDraw.hasNextKeyTyped()) {
                        number[count] = Character.getNumericValue(StdDraw.nextKeyTyped());
                        //System.out.println(count + " " + number[count]);
                        count++;
                    }
                } else {
                    break;
                }
            }
            StdDraw.clear();
            for (int combine = ZERO; combine < count; combine++) {
                size = ONEZERO * size + number[combine];
            }
        }

        static void defineGrid (final boolean print) {
            StdDraw.setScale(ZERO, size);
            if (print) {
                StdDraw.setPenRadius((double) ONE / (size * ONEZERO * ONEZERO));
                StdDraw.setPenColor(StdDraw.BLACK);
                for (int count = ZERO; count < size; count += ONEZERO) {
                    StdDraw.line(ZERO, count, size, count); // horizontal
                    StdDraw.line(count, ZERO, count, size); // vertical
                }
            }
        }
        static boolean doPrintGrid () {
            StdDraw.clear(StdDraw.GRAY);
            StdDraw.setFont(HEADER);
            StdDraw.setPenColor(StdDraw.YELLOW);
            StdDraw.setScale(ZERO, ONEZERO);
            StdDraw.filledRectangle(THREE, FIVE, TWO, TWO);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(THREE, FIVE, "PRINT GRID");
            StdDraw.setPenColor(StdDraw.PINK);
            StdDraw.filledRectangle(SEVEN, FIVE, TWO, TWO);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(SEVEN, FIVE, "DONT PRINT GRID");
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.text(FIVE, TWO, "ALL SIDES ARE WALLS");
            while (true) {
                if (StdDraw.isMousePressed()) {
                    if (StdDraw.mouseY() > THREE && StdDraw.mouseY() < SEVEN) {
                        if (StdDraw.mouseX() > ONE && StdDraw.mouseX() < FIVE) {
                            StdDraw.clear();
                            return true;
                        }
                        if (StdDraw.mouseX() > FIVE && StdDraw.mouseX() < NINE) {
                            StdDraw.clear();
                            return false;
                        }
                    }
                }
            }
        }
        static boolean printScore (final Snake snake) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledSquare(snake.snakeWidth * TWO,
                    snake.snakeWidth * TWO, snake.snakeWidth * TWO);
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.text(snake.snakeWidth * TWO,
                    snake.snakeWidth * TWO, Integer.toString(score));
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledSquare(snake.snakeWidth * TWO,
                    size - (snake.snakeWidth * TWO), snake.snakeWidth * TWO);
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.text(snake.snakeWidth * TWO,
                    size - (snake.snakeWidth * TWO), Integer.toString(score - highScore));
            if (score == highScore) {
                return true;
            }
            return false;
        }
        //figure out with direction is snake going
        static void direction () {
            if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN) && (!dirUp)) {
                dirDown = true;
                dirRight = false;
                dirLeft = false;
                direction = TWO;
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_UP) && (!dirDown)) {
                dirUp = true;
                dirRight = false;
                dirLeft = false;
                direction =  ONE;
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT) && (!dirLeft)) {
                dirRight = true;
                dirDown = false;
                dirUp = false;
                direction = FOUR;
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT) && (!dirRight)) {
                dirLeft = true;
                dirDown = false;
                dirUp = false;
                direction = THREE;
            }
        }
        static void over (final boolean win, final int difficulty) {
            StdDraw.clear();
            StdDraw.setFont(HEADER);
            StdDraw.setScale(ZERO, ONEZERO);
            if (win) {
                switch (difficulty) {
                    case 1:
                        StdDraw.clear(StdDraw.GREEN);
                        StdDraw.setPenColor(StdDraw.BLUE);
                        StdDraw.text(FIVE, FIVE, "YOU BEAT THE LEVEL");
                        StdDraw.text(FIVE, FOUR, "but you were on easy");
                        StdDraw.text(FIVE, FOUR + POINTFIVE, "go try a harder level");
                    case 2:
                        StdDraw.clear(StdDraw.BLUE);
                        StdDraw.setPenColor(StdDraw.RED);
                        StdDraw.text(FIVE, FIVE, "YOU BEAT THE LEVEL");
                        StdDraw.text(FIVE, FOUR, "you were on intermediate");
                        StdDraw.text(FIVE, FOUR + POINTFIVE, "go attempt hard");
                    case THREE:
                        StdDraw.clear(StdDraw.RED);
                        StdDraw.setPenColor(StdDraw.RED);
                        StdDraw.text(FIVE, FIVE, "YOU BEAT THE LEVEL");
                        StdDraw.text(FIVE, FOUR, "YOU BEAT IT ON HARD");
                        StdDraw.text(FIVE, FOUR + POINTFIVE, "YOU ARE AWESOME");
                   default:
                       StdDraw.clear(StdDraw.CYAN);
                       StdDraw.setPenColor(StdDraw.RED);
                       StdDraw.text(FIVE, FIVE, "ERROR");
                }
                return;
            }
            //System.out.println("GAME OVER");
            StdDraw.clear(StdDraw.LIGHT_GRAY);
            //System.out.println("GAME OVER");
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.text(FIVE, FIVE, "FAILURE");
            StdDraw.text(FIVE, FOUR, "Your score was " + Integer.toString(score));
            StdDraw.text(FIVE, FOUR + POINTFIVE,
                    "you had " + Integer.toString(highScore)
                    + " more to go to beat the game");
            StdDraw.text(FIVE, TWO, "-_- Loser -_-");
            //StdDraw.show();
        }
        @SuppressWarnings("deprecation")
        public static void playGame () {
            //Object Snake snake = position of head of snake and length
            final Snake snake = new Snake();

            //difficulty defines speed and starting length
            final int difficulty = userDifficulty();
            System.out.println("DIFFICULTY = " + difficulty);
            StdDraw.pause(ONEZERO * ONEZERO * TWO);

            //ask if user wants to print grid + define grid
            final boolean printGrid = doPrintGrid();
            System.out.println("PRINT GRID = " + printGrid);

            //ask for size of grid
            size = askSize(difficulty) * ONEZERO;
            System.out.println("SIZE = " + size);

            //total instances of x and y cord (size)
            snake.xCord = new int[size * size];
            snake.yCord = new int[size * size];

            //define snake also returns food quantity
            final int foodQuantity = snake.defineSnake(difficulty);
            //Snake.food.time = size * FIVE;
            foodArr = new Food[foodQuantity];
            System.out.println("QUANTITY OF FOOD = " + foodQuantity);
            for (int count = ZERO; count < foodQuantity; count++) {
                foodArr[count] = new Food();
            }

            //highscore
            final double totalArea = size * size;
            final double snakeArea = FOUR * snake.snakeWidth * snake.snakeWidth;

            highScore = (int) ((totalArea / snakeArea) - ((snake.snakeLength / ONEZERO)));
            System.out.println("HIGHSCORE = " + highScore);

            //speed of movement
            final int speed = (ONEZERO * ONEZERO * ONEZERO) / (size / FIVE * difficulty);
            System.out.println("SPEED = " + speed);

            //setup starting snake
            StdDraw.clear(StdDraw.LIGHT_GRAY);
            defineGrid(printGrid);
            snake.start();
            snake.drawSnake();
            boolean isAlive = true;
            boolean win = false;
            score = ZERO;
            while (true) {
                if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)
                        || StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)
                        || StdDraw.isKeyPressed(KeyEvent.VK_UP)
                        || StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
                    StdDraw.show(speed);
                    break;
                }
            }
            while (isAlive && !win) {
                StdDraw.clear();
                defineGrid(printGrid);
                win = printScore(snake);
                snake.drawSnake();
                direction();
                snake.moveCord(direction);
                Food.checkArray(foodArr, snake);
                StdDraw.show(speed);
                isAlive = snake.collision();
            }
            StdDraw.pause(ONEZERO * ONEZERO);
            StdDraw.disableDoubleBuffering();
            over(win, difficulty);
            System.out.println("OVER");
        }
    }

    static class Food {
        private static final Random RNG = new Random
                (Long.getLong ("seed", System.nanoTime()));
        private static final int ZERO = 0;
        private static final int FIVEZERO = 50;
        private static final int ONEZERO = 10;
        private static final double PEIGHT = .8;
        private static final double PONE = .1;
        private static final int TIME = 5000;
        private double timeS, timeE;
        private int foodX;
        private int foodY;
        private double width;

        Food() {
                   // Check to make sure that apple is not outside of border
            while (!(foodX > ZERO && foodX < PlaySnake.size)
                    && !(foodY > ZERO && foodY < PlaySnake.size)) {
                foodX = RNG.nextInt((int)
                        (PEIGHT * PlaySnake.size)) + (int) (PlaySnake.size * PONE);
                foodY = RNG.nextInt((int)
                        (PEIGHT * PlaySnake.size)) + (int) (PlaySnake.size * PONE);
                timeS = (System.currentTimeMillis());
                timeE = (TIME * foodX + System.currentTimeMillis());
            }
            width = (double) PlaySnake.size / (FIVEZERO);
        }
        void drawFood () {
            StdDraw.setPenColor(StdDraw.BLACK);
            if (System.currentTimeMillis()
                    >= timeS && System.currentTimeMillis() <= timeE) {
                StdDraw.filledCircle(foodX, foodY, width);
            }
        }
        boolean checkFood (final Snake snake) {
            if ((snake.xCord[ZERO] <= foodX + width
                    && snake.xCord[ZERO] >= foodX - width)
                    && (snake.yCord[ZERO] <= foodY + width
                    && snake.yCord[ZERO] >= foodY - width)) {
                PlaySnake.score++;
                snake.snakeLength = snake.snakeLength + ONEZERO;
                return true;
            }
            if (System.currentTimeMillis() >= timeE) {
                return true;
            }
            return false;
        }
        static void checkArray (final Food[] food, final Snake snake) {
            for (int count = ZERO; count < food.length; count++) {
                food[count].drawFood();
                final boolean isHit = food[count].checkFood(snake);
                if (isHit) {
                    food[count] = new Food();
                }
            }
        }
    }
}




