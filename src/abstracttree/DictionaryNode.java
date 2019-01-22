package abstracttree;

import java.util.LinkedList;

/**
 * Structure that will define the non-terminal dictionary.
 * @author danilo.melo.rocha
 *
 */
public class DictionaryNode implements Node {

    private LinkedList<Node> value;

    public DictionaryNode() {
        this.value = new LinkedList<Node>();
    }

    public void addValue(Node item) {
        this.value.add(item);
    }

    @Override
    public Object getValue() {
        return this.value;
    }

}
