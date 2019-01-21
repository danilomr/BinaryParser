package lexic;

/**
 * Class that will determine the existing tokens and store its content.
 * @author danilo.melo.rocha
 *
 */
public class Token {
	
	public static final int EPSILON = 0;

    public static final int NUMBER = 1;
    public static final int STRING = 2;
    public static final int LIST = 5;
    public static final int DICTIONARY = 6;

    /**
     * The bit which will define the token, specified in the documentation. 
     */
    public int token;
    /**
     * The data (actual value) correspondent to that token.
     */
    public String sequence;

    /**
     * Construct the token with its values
     * @param token 	the token identifier
     * @param sequence 	the string that the token was created from
     */
    public Token(int token, String sequence) {
        this.token = token;
        this.sequence = sequence;
    }

}
