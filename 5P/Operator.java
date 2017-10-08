

/**
 * Created by Benjamin Miller on 3/28/2016.
 * @author Benjamin Miller 
 */
public class Operator extends EToken
{
    
    private String operator;
    /**
     * Operator constructor.
     * @param operate is the passed in string.
     */
    public Operator( String operate )
    {
        operator = operate;
    }
    /**
     * printable prints the string.
     * @return returns the string.
     */
    public String printable()
    {

        return "<" + operator + ">";
    }
}
