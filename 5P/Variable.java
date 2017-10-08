
/**
 * Variable class.
 * Created by Benjamin Miller on 3/28/2016.
 * @author Benjamin Miller
 */
public class Variable extends Operand
{
    private String varstring;
    /**
     * Variable constructor.
     * @param var is the input string.
     */
    public Variable( String var )
    {
        
        varstring = var; 

    }
    /**
     * printable method.
     * @return a new string.
     */
    public String printable()
    {
        return "@" + varstring;
    }
     /**
      * main method.
      * @param args is the args.
      */
    public static void main( String[] args )
    {
        
       
    }

}
