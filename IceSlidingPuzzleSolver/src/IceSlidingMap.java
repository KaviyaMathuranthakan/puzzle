import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class IceSlidingMap {

    private int width;
    private int height;
    private char[][] map;
    private int[] startPos;
    private int[] finishPos;

    public IceSlidingMap() {

    }

    public IceSlidingMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new char[height][width];
    }
    public IceSlidingMap(String filename) {
        this.map = readMazeFromFile(filename);
        this.height = this.map.length;
        this.width = this.map[0].length;
        this.startPos = findPosition(this.map,'S');
        this.finishPos = findPosition(this.map,'F');

    }

    public void setMap(char[][] map) {
        if (map.length == height && map[0].length == width) {
            this.map = map;
        } else {
            System.err.println("Invalid map dimensions.");
        }
    }

    public void loadMapFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int y = 0;
            while ((line = reader.readLine()) != null && y < height) {
                char[] row = line.toCharArray();
                if (row.length != width) {
                    System.err.println("Invalid map width at line " + (y + 1));
                    return;
                }
                for (int x = 0; x < width; x++) {
                    map[y][x] = row[x];
                }
                y++;
            }
            if (y != height) {
                System.err.println("Invalid map height.");
            }
        } catch (IOException e) {
            System.err.println("Error reading map file: " + e.getMessage());
        }
    }
    public static char[][] readMazeFromFile(String filePath) {
        char[][] maze = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                if (maze == null) {
                    maze = new char[line.length()][line.length()];
                }
                for (int col = 0; col < line.length(); col++) {
                    maze[row][col] = line.charAt(col);
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maze;
    }

    public List<String> pathFinder(int startX, int startY, int finishX, int finishY) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[height][width];
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right
        Map<String, String> parentMap = new HashMap<>(); // For tracking the path

        queue.offer(new int[]{startX, startY});
        visited[startY][startX] = true;

        List<String> path = new ArrayList<>();

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            if (x == finishX && y == finishY) {
                // Build the path from finish to start
                String currentKey = finishX + "," + finishY;
                while (!currentKey.equals(startX + "," + startY)) {
                    String parentKey = parentMap.get(currentKey);
                    int parentX = Integer.parseInt(parentKey.split(",")[0]);
                    int parentY = Integer.parseInt(parentKey.split(",")[1]);
                    if (parentX < x) {
                        path.add("Move right to (" + x + "," + y + ")");  //Moving Right
                    } else if (parentX > x) {
                        path.add("Move left to (" + x + "," + y + ")");   //Moving left
                    } else if (parentY < y) {
                        path.add("Move down to (" + x + "," + y + ")");     //Moving down
                    } else {
                        path.add("Move up to (" + x + "," + y + ")");   //Moving up
                    }
                    x = parentX;
                    y = parentY;
                    currentKey = parentKey;
                }
                path.add("Start at (" + startX + "," + startY + ")");
                break;
            }

            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                if (newX >= 0 && newX < width && newY >= 0 && newY < height
                        && !visited[newY][newX] && map[newY][newX] != '0') {
                    queue.offer(new int[]{newX, newY});
                    visited[newY][newX] = true;
                    parentMap.put(newX + "," + newY, x + "," + y);
                }
            }

        }
        Collections.reverse(path); // Reverse the List to get the order S to F
        return path;
    }
    public static int[] findPosition(char[][] maze, char target) {
        int[] position = new int[2];
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[row].length; col++) {
                if (maze[row][col] == target) {
                    position[0] = col;
                    position[1] = row;
                    return position;
                }
            }
        }
        return position;
    }

    public void printMapDetails() {
        System.out.println("Width: " + width);
        System.out.println("Height: " + height);
        System.out.println("Start Position: (" + startPos[0] + ", " + startPos[1] + ")");
        System.out.println("Finish Position: (" + finishPos[0] + ", " + finishPos[1] + ")");
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public char[][] getMap() {
        return map;
    }

    public int[] getStartPos() {
        return startPos;
    }

    public void setStartPos(int[] startPos) {
        this.startPos = startPos;
    }

    public int[] getFinishPos() {
        return finishPos;
    }

    public void setFinishPos(int[] finishPos) {
        this.finishPos = finishPos;
    }

}
