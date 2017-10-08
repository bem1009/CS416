public class Node {

    EToken value = null; //what is contained in this node
    Node left;
    Node right;
    String printval = "";

    public Node(){

    }

    /** Node.
     *  Secondary constructor.
     *
     * @param v EToken.
     */
    public Node( EToken v ){
        value = v;
    }

    public static String evaluate( Node node , SymbolTable st ){



        if ( node.value instanceof Variable ) {
           float f1 = st.getValue( node.value.getop() );
            String s22 = String.valueOf( f1 );
            return s22;

        } else if( node.value instanceof  Number ){
            return node.value.op();
        } else {
            if( node.value.op().equals("=") ){

                String s1 = evaluate( node.right, st );
                Float f1 = Float.valueOf( s1 );
                st.setValue( node.left.value.op() , f1 );


                return s1;
            } else {

                Float v1 = 0f;
                Float v2 = 0f;
                
                if (node.left != null) {
                    String s1 = evaluate(node.left, st );
                    v1 = Float.valueOf(s1);

                }
                if (node.right != null) {
                    String s1 = evaluate(node.right, st );
                    v2 = Float.valueOf(s1);

                }
                String op = node.value.op();
                Float finalnumber = 0f;
                if (op.equals("*")) {
                    finalnumber = v1 * v2;
                } else if (op.equals("+")) {
                    finalnumber = v1 + v2;
                } else if (op.equals("-")) {
                    finalnumber = v1 - v2;
                } else if (op.equals("/")) {
                    finalnumber = v1 / v2;
                } else if (op.equals("%")) {
                    finalnumber = v1 % v2;
                }
                String returnit = String.valueOf( finalnumber );
                return returnit;
            }
        }


    }

    public void printTree( Node node, int depth ) {
        String space = "       ";
        String letters = "";
        for( int i = 0; i < depth; i++ ){
            letters = letters + space;
        }

      if( node.left != null ){
          printTree( node.left , depth + 1 );
      }
        
        printval = printval + "\n" + letters + node.value.op();
        if( node.right != null ){
            printTree( node.right , depth + 1 );
        }

    }

}