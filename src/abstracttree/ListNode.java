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

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitEnter(this);
        LinkedList<Node> children = this.value;
        for (int i = 0; i < children.size(); i++) {
            visitor.visitIdent(this);
            children.get(i).accept(visitor);
            if (i+1 < children.size()) {
                visitor.visit(this);
            }
        }
        visitor.visitLeave(this);
    }

}
