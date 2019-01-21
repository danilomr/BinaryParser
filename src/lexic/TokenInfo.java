package lexic;

/**
 * Class that will determine the possible rules applied by the Tokenizer to identify a token.
 * @author danilo.melo.rocha
 *
 */
public class TokenInfo {
	
	/**
	 * the hex code which will define the type of the token
	 */
    public int token;
    /**
     * the amount of bytes the token consumes
     */
    public Integer amountBytes;
    /**
     * the hex code which will define when the token ends
     */
    public Integer delimiter;

    /**
     * 
     * @param token			the token identifier
     * @param amountBytes	the amount of bytes the token consumes (null, when there is no certain amount, but a delimiter)
     * @param delimiter		the hex code which will define when the token ends (null if there is a specific amount of bytes)
     */
    public TokenInfo(int token, Integer amountBytes, Integer delimiter) {
        this.token = token;
        this.amountBytes = amountBytes;
        this.delimiter = delimiter;
    }

}
