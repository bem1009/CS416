    /**
     * SymbolTable - this class  maintains a symbol table for variables.
     *
     * This functionality lends itself to a class that uses the Singleton pattern.
     * That is, it enforces a restriction that only 1 instance can ever be created.
     *
     */
    
    import java.util.*;

public class SymbolTable
{
   //--------------------- class variables -------------------------------
   private static SymbolTable _theTable = null;
   
   //--------------------- instance variables ----------------------------
   // Use a Hashtable or a HashMap to store information (value) using the id 
   // as the key
   ///////////////////////////////////////////////////////////

   private Hashtable<String , Number> table;

   //------------- constructor --------------------------------------------
   /**
    * Note the constructor is private!
    */
   private SymbolTable()
   {
      //////// Create the hashtable or hashmap ///////////////////

      Hashtable<String, Number> numbers
              = new Hashtable<String, Number>();
      table = numbers;
   }
   //------------- instance -----------------------------------------------
   /**
    * user code must call a static method to get the reference to the 
    * only allowed instance -- first call creates the instance.
    */
   public static SymbolTable instance()
   {
      if ( _theTable == null )
         _theTable = new SymbolTable();
      return _theTable;
   }
   
   //------------------------ setValue( String, float  ) ---------------------
   /**
    * Set an identifier's value to the specified value.
    */
   public void setValue( String var, float num )
   {
      /////////////////////////////////////////////////////// 
      //    Need to save information into the hash table
      //
      // You are allowed to change the signatures; for example, you
      //    can use Float or Number as the parameter type, both
      //    here and in getValue
      //////////////////////////////////////////////////////////
      Number n1 = new Number( num );

      table.put( var , n1 );
   }
   //------------------------ getValue( String ) ---------------------------
   /**
    * Look up an identifier in the hash table and return its value.
    * If the identifier is not in the table, add it with a value of 0
    * and return 0.
    */
   public float getValue( String var )
   {
      /////////////////////////////////////////////////////////////
      //  Use var as hash table key get value; return it as a float
      //    or a Number (Float)
      ////////////////////////////////////////////////////////////
      if( table.containsKey( var ) )
      {
         Number n1 = table.get( var );
         return n1.getToken();

      } else
      {
         setValue( var , 0 );
         return 0;
      }
   }
   //------------------------- toString() -------------------------------
   public String toString()
   {
      String print = "";
      for ( String key : table.keySet() )
      {
         print = print + "{ " + key + ":" + table.get( key ).getToken()
                 + " }" + "\n";
      }

      return print;
   }
   //--------------------------- main -----------------------------------
   /**
    * Simple unit testing for SymbolTable
    */
   public static void main( String[] args )
   {
      
   }
}