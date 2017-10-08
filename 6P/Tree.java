/**
 * @author Benjamin Miller.
 */
public class Tree {

    private Node root;
    private int size;

    /** Tree.
     * constructor.
     *
     */
    public Tree(){
        root = null;
        size = 0;
    }

    /**
     * Secondary Constructor for tree. 
     * @param rootnode Node.
     */
    public Tree( Node rootnode ){
        root = rootnode;
        size = 1;
    }


}