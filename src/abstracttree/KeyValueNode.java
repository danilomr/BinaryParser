package abstracttree;

/**
 * Structure that will define a pair of key-value.
 * @author danilo.melo.rocha
 *
 */
public class KeyValueNode implements Node {

    private Node key;
    private Node value;

    public KeyValueNode(Node key, Node value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        this.key.accept(visitor);
        visitor.visit(this);
        this.value.accept(visitor);
    }

}
