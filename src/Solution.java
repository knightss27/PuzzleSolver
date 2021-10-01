import java.util.List;

public class Solution {

    public List<SearchNode> path;
    public SearchNode start;
    public SearchNode end;

    public Solution(List<SearchNode> solutionPath) {
        path = solutionPath;
        start = path.get(0);
        end = path.get(path.size()-1);
    }

}
