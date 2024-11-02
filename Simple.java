
public class Simple {
	//Unimplemented as it is not required yet
    public static boolean gameOver(Player red, Player blue) {
    	if (red.getScore()>0 || blue.getScore()>0) {
    		return true;
    	}
    	return false;
    }
}
