package abstracttree;

/**
 * Interface used by the pretty printer to visit
 * abstract tree.
 * @author danilo.melo.rocha
 *
 */
public interface NodeVisitor {

	// DictionaryNode and ListNode have nested 
	// elements. The pretty printer needs to print 
	// characters before and after the visit to 
	// the the children. 
	// We also need to print identation and comma 
	// betwwe the children. 
	// That is why it needs multiple visit calls.
	// "visitEnter" and "visitLeave" to print
	// characters before and after the children.
	// "visitIdent" and "visit" to print characters
	// regarding to the identation and layout 
	// between the children.
	public void visitIdent(DictionaryNode node);
	public void visitEnter(DictionaryNode node);
	public void visit(DictionaryNode node);
	public void visitLeave(ListNode node);
    
    public void visitIdent(ListNode node);
    public void visitEnter(ListNode node);
    public void visit(ListNode node);
    public void visitLeave(DictionaryNode node);
    
    
    public void visit(KeyValueNode node);
    public void visit(NumberNode node);
    public void visit(StringNode node);

}
