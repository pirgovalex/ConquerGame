import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TradeCorridorBuilder {

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    public static int countSteps(Point start, Point end) {
        return Math.abs(start.x - end.x) + Math.abs(start.y - end.y);
    }

    public static int main(char [] [] map) {
        Scanner scanner = new Scanner(System.in);
        char[][] grid = Game.map;

        // Find all occurrences of '@' and '$'
        List<Point> atPositions = new ArrayList<>();
        List<Point> dollarPositions = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '@') {
                    atPositions.add(new Point(i, j));
                } else if (grid[i][j] == '$') {
                    dollarPositions.add(new Point(i, j));
                }
            }
        }


        System.out.println("Available '@' positions:");
        for (int i = 0; i < atPositions.size(); i++) {
            System.out.println((i + 1) + ". " + atPositions.get(i));
        }

        System.out.println("Available '$' positions:");
        for (int i = 0; i < dollarPositions.size(); i++) {
            System.out.println((i + 1) + ". " + dollarPositions.get(i));
        }


        System.out.println("Enter the number of the starting '@' position:");
        int startChoice = scanner.nextInt();
        System.out.println("Enter the number of the ending '$' position:");
        int endChoice = scanner.nextInt();


        Point start = atPositions.get(startChoice - 1);
        Point end = dollarPositions.get(endChoice - 1);
        int steps = countSteps(start, end);
        System.out.println("Number of steps: " + steps);


        Game.budget+=10000;
        System.out.println("BIG MONEY");
        return steps;
    }
}

