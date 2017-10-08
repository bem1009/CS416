/**
 * TokenFactory: a factory class, which makes tokens from String fields.
 * This is an example of a Factory pattern.
 *
 */

import java.util.*;


/**
 * TokenFactory a factory class. 
 * @author Benjamin Miller.
 */
public class TokenFactory
{
    /**
     * EToken makes a token and sees what kind it is.
     * @param s is the input string.
     * @return either a number, variable or an operator.
     */
    public static EToken makeToken( String s )
    {
        
        String operators = "+-/*%()=";
        if( s != null )
        {
            try
            {
                Float value;
                value = Float.parseFloat( s );
                return new Number( value );
            }
            catch( NumberFormatException nfe )
            {
                if ( operators.contains( s ) && s.length() == 1 )
                {
                    return new Operator( s );
                }
                char c = s.charAt( 0 );
                if( Character.isJavaIdentifierStart( c ) == true )
                {
                    return new Variable( s );
                }
                else if( Character.isJavaIdentifierStart( c ) == false
                            && operators.contains( s )  )
                {
                    System.out.println( "Not Token" );
                }
            }
            
            
            
        }
        
        
        
        
        
        return null;
    }
    
    
}
