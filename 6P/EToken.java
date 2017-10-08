public abstract class EToken
{
    /** .
     * Non-standard toString.
     *
     * @return name of the string.
     */
    abstract public String printable();

    /** toString.
     * .
     * @return name of the string.
     */
    public String toString()
    {
        return printable();
    }

    abstract public String getop();

    public String op()
    {
        return getop();
    }

    //------------------ main unit test ------------------------
    /** .
     * test
     *
     * @param args String[]
     */
    public static void main( String[] args )
    {
        
    }
}