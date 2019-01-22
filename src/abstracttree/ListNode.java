package abstracttree;

import java.util.LinkedList;

/**
 * Structure that will define the non-terminal list of values.
 * @author danilo.melo.rocha
 *
 */
public class ListNode implements Node {

    private LinkedList<Node> value;

    public ListNode() {
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
