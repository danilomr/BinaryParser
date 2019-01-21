package lexic;

import java.math.BigInteger;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Class that will be used to tokenize an binary array that represents a JSON file.
 * The output generate by this class is a list of tokens which represents the values
 * from the array. 
 * @author danilo.melo.rocha
 *
 */
public class Tokenizer {
	
	/**
	 * Information used to identify the different types of token.
	 */
	private LinkedList<TokenInfo> tokenInfos;
	/**
	 * The tokens found by the tokenization.
	 */
    private LinkedList<Token> tokens;

    public LinkedList<Token> getTokens() {
        return this.tokens;
    }

    protected void addTokenInfo(int token, Integer amountBytes, Integer delimiter) {
        this.tokenInfos.add(new TokenInfo(token, amountBytes, delimiter));
    }

    public Tokenizer() {
        this.tokenInfos = new LinkedList<TokenInfo>();
        this.tokens = new LinkedList<Token>();

        addTokenInfo(Token.NUMBER, 5, null);        //Number        4 bytes signed integer in big ending byte order.
        addTokenInfo(Token.STRING,null, 0);         //String        N ASCII characters terminated by 0x00.
        addTokenInfo(Token.LIST, 1, null);          //List          4 bytes signed integer in big ending byte order.
        addTokenInfo(Token.DICTIONARY, 1, null);    //Dictionary    Amount of items as a number followed by N key-value pairs
    }

    public void tokenize(byte inputArray[]) {
        tokens.clear();
        while (inputArray.length != 0) {
            byte currentByte = inputArray[0];
            int remaining = inputArray.length;

            for (TokenInfo info : tokenInfos) {
                if (info.token == currentByte) {

                    int bytesToBeConsumed = 0;
                    if (info.amountBytes != null) {
                        bytesToBeConsumed = info.amountBytes;
                    } else {
                        int indexWithDelimiter = 0;
                        do {
                            indexWithDelimiter++;
                        } while(inputArray[indexWithDelimiter] != info.delimiter);
                        bytesToBeConsumed += indexWithDelimiter + 1;
                    }

                    String token = getToken(info, bytesToBeConsumed, inputArray);

                    tokens.add(new Token(info.token, token));
                    if (bytesToBeConsumed == remaining) {
                        inputArray = new byte[0];
                    } else {
                        inputArray = Arrays.copyOfRange(inputArray, bytesToBeConsumed, remaining);
                    }

                    break;
                }
            }
        }
    }

    /**
     * Turn array of bytes into token.
     * @param info					The object with the information of how to turn the bytes into token.
     * @param bytesToBeConsumed		The amount of bytes that have to be consumed in order to create a token.
     * @param inputArray			The array of bytes representing the binary JSON. The whole array or the remaining to be tokenized.
     * @return
     */
    private String getToken(TokenInfo info, int bytesToBeConsumed, byte inputArray[]) {
        String token;
        switch (info.token) {
            case Token.NUMBER:
                Integer val = new BigInteger(Arrays.copyOfRange(inputArray, 1, 5)).intValue();
                token = val.toString();
                break;
            case Token.STRING:
                token = new String(Arrays.copyOfRange(inputArray, 1, bytesToBeConsumed-1));
                break;
            default:
                token = (Integer.valueOf(info.token)).toString();
                break;
        }
        return token;
    }

}
