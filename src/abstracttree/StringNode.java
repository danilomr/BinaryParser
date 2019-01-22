package abstracttree;

/**
 * Structure that will define the terminal symbol STRING.
 * @author danilo.melo.rocha
 *
 */
public class StringNode implements Node {

	/**
	 * The actual value of the String tokenized from the binary file.
	 */
    private String value;

    public StringNode(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

}
