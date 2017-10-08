/**
 * EToken - an abstract class representing a token in an expression.
 *             subclasses are Operator and Operand.
 * @author Benjamin Miller
 */



public abstract class EToken
{
    /**
     * Non-standard toString.
     * @return printable.
     */
    abstract public String printable();
    /**
     * toString puts the Etoken in a string.
     * @return printable
     */
    public String toString()
    {
        return printable();
    }
    
    //------------------ main unit test ------------------------
    /**
     * Some basic unit tests.
     * @param args is the args.
     */
    public static void main( String[] args )
    {
        try 
        {
            EToken plus  = TokenFactory.makeToken( "+" );
            EToken times = TokenFactory.makeToken( "*" );
            EToken a     = TokenFactory.makeToken( "a" );
            EToken one   = TokenFactory.makeToken( "1" );
            
            System.out.println( a + " " + plus + " " + one + " "
                                   + times + " " + a );
        }
        catch ( Exception e ) 
        {
            System.out.println( "Bad token: " + e );
        }
    }
}
