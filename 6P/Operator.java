

/**
 * Created by Benjamin Miller on 4/4/2016.
 */

public class Operator extends EToken
{
    
    private String operator;
    
    /** Operator.
      * 
      * @param s String.
      * */
    public Operator( String s )
    {
        operator = s;
    }

    /** getop.
     *
     * @return operator String.
     */
    public String getop(){
        return operator;
    }
    
    /** printable().
      * 
      * @return printable version of the string.
      * */
    public String printable()
    {
        
        String printversion = "<" + operator + ">";
        
        return printversion;
    }
    
    
}

