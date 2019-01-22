package abstracttree;

/**
 * Structure that will define the terminal symbol NUMBER.
 * @author danilo.melo.rocha
 *
 */
public class NumberNode implements Node {
	
	/**
	 * The actual value of the number tokenized from the binary file.
	 */
	private Integer value;

    public NumberNode(String value) {
        this.value = Integer.valueOf(value);
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

}
