/**
 * SymbolTable - this class  maintains a symbol table for variables.
 * 
 * This functionality lends itself to a class that uses the Singleton pattern.
 * That is, it enforces a restriction that only 1 instance can ever be created.
 * 
 *
 */
import java.util.*;


/**
 * SymbolTable.
 * @author Benjamin Miller 
 */
public class SymbolTable
{
    //--------------------- class variables -------------------------------
    private static SymbolTable theTable = null;
    
    
    //--------------------- instance variables ----------------------------
    // Use a Hashtable or a HashMap to store information (value) using the id 
    // as the key
    ///////////////////////////////////////////////////////////
    private Hashtable<String, Number> ht;
    
    
    
    
    //------------- constructor --------------------------------------------
    /**
     * Note the constructor is private!
     * 
     */
    private SymbolTable()
    {
        //////// Create the hashtable or hashmap ///////////////////
        ht = new Hashtable<String,Number>();
    }
    //------------- instance -----------------------------------------------
    /**
     * user code must call a static method to get the reference to the 
     * only allowed instance -- first call creates the instance.
     * @return the SymbolTable.
     */
    public static SymbolTable instance()
    {
        if ( theTable == null )
            theTable = new SymbolTable();
        return theTable;
    }
    
    //------------------------ setValue( String, float  ) ---------------------
    /**
     * Set an identifier's value to the specified value.
     * @param var is the key.
     * @param num is the value.
     */
    public void setValue( String var, Number num )
    {
        /////////////////////////////////////////////////////// 
        //    Need to save information into the hash table
        //
        // You are allowed to change the signatures; for example, you
        //    can use Float or Number as the parameter type, both
        //    here and in getValue
        //////////////////////////////////////////////////////////
        ht.put( var, num );
    }
    //------------------------ getValue( String ) ---------------------------
    /**
     * Look up an identifier in the hash table and return its value.
     * If the identifier is not in the table, add it with a value of 0
     * and return 0.
     * @return num is the number.
     * @param var is the key.
     */
    public Number getValue( String var )
    {
        /////////////////////////////////////////////////////////////
        //  Use var as hash table key get value; return it as a float
        //    or a Number (Float)
        ////////////////////////////////////////////////////////////
        Number flo = ht.get( var );
        return flo;
    }
    //------------------------- toString() -------------------------------
    /**
     * toString method puts it in a string.
     * @return a string.
     */
    public String toString()
    {
        return ht.toString();
        
    }
    
    
    //--------------------------- main -----------------------------------
    /**
     * Simple unit testing for SymbolTable.
     * @param args is the args.
     */
    public static void main( String[] args )
    {
        SymbolTable st = SymbolTable.instance();
        st.setValue( "a", new Number( 4.0f ) );
        
        Number val = st.getValue( "a" );
        System.out.println( "Test: should print 4: " + val );
        
        st.setValue( "b", new Number( 0.0f ) );
        val = st.getValue( "b" );
        System.out.println( "Test: should print 0: " + val );
        
        st.setValue( "a", new Number( 6.0f ) );
        val = st.getValue( "a" );
        System.out.println( "Test: should print 6: " + val );
        
        System.out.println( "toString prints: " + st.toString() );
        
        
    }
}
