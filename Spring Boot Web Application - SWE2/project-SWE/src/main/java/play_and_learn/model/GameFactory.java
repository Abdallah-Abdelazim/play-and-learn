package play_and_learn.model;

/**
 * >> Factory DP
 * Creates objects without exposing the instantiation logic to
 * the client and Refers to the newly created object through a common interface.
 * @author Abdallah Abdelazim
 *
 */
public class GameFactory {
	
	public static IGame getGame(String criteria)
	  {
	    if ( criteria.equals("Q/A") ) {
	    	return new Game();
	    }
	      
	    return null;
	  }

}
