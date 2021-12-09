import java.util.*;

public class ValidArrangementOfPairs {
    private Map<Integer, List<Integer>> graph = new HashMap<>();
    private Map<Integer, Integer> degrees = new HashMap<>();

    public int[][] validArrangement(int[][] pairs) {
        buildGraphAndDegrees(pairs);

        int start = getRootNode(pairs[0][0]);

        List<Integer> traversed = getDFTPath(start);
        traversed = new ArrayList<>(traversed);
        int[][] arrangedPairs = new int[pairs.length][2];

        for (int i = 0; i < traversed.size() - 1; i++) {
            arrangedPairs[i] = new int[]{traversed.get(i), traversed.get(i + 1)};
        }
        return arrangedPairs;
    }

    private List<Integer> getDFTPath(int start) {
        List<Integer> traversed = new LinkedList<>();

        depthFirstTraversal(start, traversed);
        return traversed;
    }

    private void depthFirstTraversal(int start, List<Integer> traversed) {
        List<Integer> neighbours = graph.get(start);

        while(neighbours != null && !neighbours.isEmpty()) {
            Integer next = neighbours.get(0);
            neighbours.remove(0);
            depthFirstTraversal(next, traversed);
        }
        traversed.add(0, start);
    }

    private int getRootNode(int defaultIfNotFound) {
        int root = defaultIfNotFound;

        for (Map.Entry entry : degrees.entrySet()) {
            if ((int)entry.getValue() == -1) {
                root = (int)entry.getKey();
                break;
            }
        }
        return root;
    }

    private void buildGraphAndDegrees(int[][] pairs) {
        for (int[] pair : pairs) {
            int start = pair[0];
            int end = pair[1];

            graph.putIfAbsent(start, new LinkedList<>());
            graph.get(start).add(end);

            degrees.putIfAbsent(start, 0);
            degrees.put(start, degrees.get(start) - 1);

            degrees.putIfAbsent(end, 0);
            degrees.put(end, degrees.get(end) + 1);
        }
    }
}
