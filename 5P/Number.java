

/**
 * Created by Benjamin Miller on 3/28/2016.
 * @author Benjamin Miller.
 */
public class Number extends Operand
{
    private Float token;
    /**
     * Number constructor.
     * @param f is a float.
     */
    public Number( Float f )
    {
        token = f;
        
        
        
    }
    /**
     * getToken returns a float.
     * @return token is a float.
     */
    public Float getToken()
    {
        return token;
    }
    /**
     * printable prints the token.
     * @return returnstring is the string returned.
     */
    public String printable()
    {
        int tokenint =  Math.round( token );
        String returnstring = "@" +  tokenint;
        
        return returnstring;
    }
}
