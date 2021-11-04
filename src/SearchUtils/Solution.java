package SearchUtils;

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


    public int getPathLength() {
        // subtract 1 as the path includes the starting state
        return path.size()-1;
    }

    public void displayPath() {
        for (SearchNode action : path) {
            if (action.creatingAction != null) {
                action.creatingAction.display();
            }
            action.state.display();
        }
    }
}
