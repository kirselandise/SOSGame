
public class Simple extends Game{
	Player red;
	Player blue;
	int size;
	GUI.Type type;
	
	public Simple (Player red, Player blue, int size, GUI.Type type) {
		super(size, type);
		this.red = red;
		this.blue = blue;
		this.size = size;
		this.type = type;
	}
	
    public static boolean gameOver(Player red, Player blue) {
    	if (red.getScore()>0 || blue.getScore()>0) {
    		return true;
    	}
    	return false;
    }
}
