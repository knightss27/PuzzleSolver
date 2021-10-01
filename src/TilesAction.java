public class TilesAction implements Action {

    public int place;
    public int num;

    public TilesAction(int _place, int _num) {
        place = _place;
        num = _num;
    }

    @Override
    public void display() {
        System.out.println("Change place" + place +  " to " + num);
    }

    @Override
    public int getCost() {
        return 0;
    }
}
