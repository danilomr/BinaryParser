package printer;

import abstracttree.DictionaryNode;
import abstracttree.KeyValueNode;
import abstracttree.ListNode;
import abstracttree.Node;
import abstracttree.NodeVisitor;
import abstracttree.NumberNode;
import abstracttree.StringNode;

import file.FileManager;

/**
 * Class used to visit the abstract tree and print the parsed JSON.
 * @author danilo.melo.rocha
 *
 */
public class PrettyPrinter implements NodeVisitor {

    private static String TAB = "    ";
    private static String LINEBREAK = "\r\n";

    /**
     * The root of the tree.
     */
    private Node node;
    /**
     * The current nested level, which is used to print the correct tabulation.
     */
    private int nestLevel;
    /**
     * Buffer used to print the pretty printed parsed JSON.
     */
    private StringBuffer textGenerated;

    public PrettyPrinter(Node node) {
        this.node = node;
        this.nestLevel = 0;
        this.textGenerated = new StringBuffer();

        this.node.accept(this);
    }

    public void print() {
        System.out.println(this.textGenerated.toString());
    }
    
    public void createFile(String folderLocation, String filename) {
        FileManager.createFile(this.textGenerated, folderLocation + filename);
    }

    private void ident() {
        for (int i = 0; i < this.nestLevel; i++) {
            this.textGenerated.append(TAB);
        }
    }

    private void linebreak() {
        this.textGenerated.append(LINEBREAK);
    }

    @Override
    public void visit(DictionaryNode node) {
        this.textGenerated.append(",");
        linebreak();
    }

    @Override
    public void visit(ListNode node) {
        this.textGenerated.append(",");
        linebreak();
    }

    @Override
    public void visit(NumberNode node) {
        this.textGenerated.append(node.getValue());
    }

    @Override
    public void visit(StringNode node) {
        String val = "'" + node.getValue() + "'";
        this.textGenerated.append(val);
    }

    @Override
    public void visit(KeyValueNode node) {
        this.textGenerated.append(": ");
    }

    @Override
    public void visitEnter(DictionaryNode node) {
        this.textGenerated.append("{");
        linebreak();
        this.nestLevel++;
    }

    @Override
    public void visitEnter(ListNode node) {
        this.textGenerated.append("[");
        linebreak();
        this.nestLevel++;
    }

    @Override
    public void visitIdent(DictionaryNode node) {
        ident();
    }

    @Override
    public void visitIdent(ListNode node) {
        ident();
    }

    @Override
    public void visitLeave(DictionaryNode node) {
        this.nestLevel--;
        linebreak();
        ident();
        this.textGenerated.append("}");
    }

    @Override
    public void visitLeave(ListNode node) {
        this.nestLevel--;
        linebreak();
        ident();
        this.textGenerated.append("]");
    }

}
