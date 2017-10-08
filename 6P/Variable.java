
/**
 * Created by Benjamin Miller on 4/4/2016.
 */
public class Variable extends Operand
{

    private String s1;
    /** Variable.
     *
     * @param s String.
     * */
    public Variable( String s )
    {
        s1 = s;
    }

    /** printable.
     *
     * @return returnstring String.
     * */
    public String printable()
    {
        //System.out.println("PRINTER");
        String returnstring = "@" +  s1;
        return returnstring;
    }

    public String getop(){
        return s1;
    }
    
    public static void main( String[] args )
    {
        
    }
}

