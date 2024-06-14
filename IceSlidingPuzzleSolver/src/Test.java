import java.util.List;

public class Test {
    public static void main(String[] args) {
//        IceSlidingMap map = new IceSlidingMap(10, 10);
        char[][] testMap = {
                {'.', '.', '.', '.', '0', '.', '.', '.','.', 'S'},
                {'.', '.', '.', '.', '0', '.', '.', '.','.', '0'},
                {'0', '.', '.', '.', '.', '.', '0', '.','.', '0'},
                {'.', '.', '.', '0', '.', '.', '.', '.','.', '0'},
                {'.', 'F', '.', '.', '.', '.', '.', '.','.', '0'},
                {'.', '0', '.', '.', '.', '.', '.', '.','.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.','.', '0'},
                {'0', '.', '0', '.', '0', '.', '.', '0','0', '0'},
                {'0', '.', '.', '.', '.', '.', '.', '.','.', '.'},
                {'.', '0', '0', '.', '.', '.', '.', '.','.', '0'}
        };
        IceSlidingMap map = new IceSlidingMap("IceSlidingPuzzleSolver/benchmark_series/puzzle_10.txt");
       map.printMapDetails();
        int startX = map.getStartPos()[0];
        int startY = map.getStartPos()[1];
        int finishX = map.getFinishPos()[0];
        int finishY = map.getFinishPos()[1];

        List<String> path = map.pathFinder(startX,startY,finishX,finishY);

        for (int i = 0; i < path.size(); i++) {
            System.out.println((i + 1) + ". " + path.get(i));
        }
        System.out.println((path.size() + 1) + ". Done!");

//        map.setMap(testMap);
//        map.loadMapFromFile("benchmark_series/puzzle_20.txt");

//        map.loadMapFromFile("examples/maze10_2.txt");
//        map.printMap();

//        List<String> path = map.pathFinder(0, 9, 3, 7);
//        // Printing the List
//        for (int i = 0; i < path.size(); i++) {
//            System.out.println((i + 1) + ". " + path.get(i));
//        }
//        System.out.println((path.size() + 1) + ". Done!");
    }
}
