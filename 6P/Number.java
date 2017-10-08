public class Number extends Operand
{

    private Float token;

    /** Number class.
     * 
     * @param s float.
     * */
    public Number( float f )
    {
        
        token = f;
    }

    /** getToken().
     *
     *
     * @return token float.
     */
    public float getToken()
    {
        return token;
    }

    
    /** printable().
     * 
     * @return returnstring String.
     * */
    public String printable() 
    {
        int number =  Math.round( token );
        String numberstring = "@" +  number;

        return numberstring;
    }
    public String getop(){
        String floatreturn = String.valueOf( token );
        return floatreturn;
    }
}