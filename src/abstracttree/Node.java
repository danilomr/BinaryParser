package abstracttree;

/**
 * Structure that will define the hierarchy of the JSON file.
 * @author danilo.melo.rocha
 *
 * @param <T>
 */
public interface Node<T> {
	
	public T getValue();
	
	public void accept(NodeVisitor visitor);

}
