/**
 * Lexer.java - parses string input representing an infix arithmetic
 *              expression into tokens.
 *              The expression can use the operators =, +, -, *, /, %.
 *              and can contain arbitrarily nested parentheses.
 *              The = operator is assignment and must be absolutely lowest
 *              precedence.
 * March 2009
 * rdb
 * March 2016
 * ah: Simplified to do just lexical analysis.
 */

import javax.swing.*;
import java.util.*;
import java.io.*;




/**
 * Lexer constructor.
 * @author Benjamin Miller.
 */
public class Lexer //extends JFrame
{
    //----------------------  class variables  ------------------------
    
    //---------------------- instance variables ----------------------
    
    private SymbolTable  _symbolTable;
    
    //----------- constants
    private String   dashes = " ---------------------------- ";
    private String   endOutputBlock =
        "====================================================================";
    private File file;
    //--------------------------- constructor -----------------------
    /**
     * Create the Lexer.
     * If there is a command line argument use it as an input file.
     * otherwise invoke an interactive dialog.
     * @param args is the args.
     */
    public Lexer( String[] args )
    {
        _symbolTable = SymbolTable.instance();
        
        if ( args.length > 0 )
            processFile( args[ 0 ] );
        else
            interactive();
    }
    //--------------------- processFile -----------------------------
    /**
     * Given a String containing a file name, open the file and read it.
     * Each line should represent an expression to be parsed.
     * For each line, process it, and print the result to System.out.
     * @param fileName is the file name.
     */
    public void processFile( String fileName )
    {
        if( file == null )
        {
            System.out.println( "File does not exist" );
        }
        else 
        {
            try 
            {
                Scanner scan = new Scanner( file );
                String result = "";
                while ( scan.hasNextLine() )
                {
                    String s = scan.nextLine();
                    if( s != null )
                    {
                        System.out.println( processLine( s ) );
                    }
                }
                System.out.println( processLine( result ) );
            } catch ( IOException ioe ) 
            {
                System.out.println( "IO Exception" );
            }
        }
    }
    
    //--------------------- processLine -----------------------------
    /**
     * Parse each input line and do the right thing; return the
     *    "results" which can be null.
     * @return line is a string.
     * @param line is the string.
     */
    public String processLine( String line )
    {
        System.out.println( "Input: " + line );
        String trimmed = line.trim();
        EToken et = TokenFactory.makeToken( line );
        
        if ( trimmed.length() == 0 || trimmed.charAt( 0 ) == '#' )
            return line;
        else
            return processExpr( trimmed );
    }
    //--------------------- interactive -----------------------------
    /**
     * Use a file chooser to get an file name interactively, then
     * go into a loop prompting for expressions to be entered one
     * at a time.
     */
    public void interactive()
    {
        JFileChooser fChooser = new JFileChooser( "." );
        fChooser.setFileFilter( new TextFilter() );
        int choice = fChooser.showDialog( null, "Pick a file of expressions" );
        if ( choice == JFileChooser.APPROVE_OPTION )
        {
            file = fChooser.getSelectedFile();
            if ( file != null )
                processFile( file.getName() );
        }
        
        String prompt = "Enter an arithmetic expression: ";
        String expr = JOptionPane.showInputDialog( null, prompt );
        while ( expr != null && expr.length() != 0 )
        {
            String result = processLine( expr );
            JOptionPane.showMessageDialog( null, expr + "\n" + result );
            expr = JOptionPane.showInputDialog( null, prompt );
        }
    }
    //------------------ processExpr( String ) -------------------------
    /**
     * Get all fields in the expression, and
     * generate tokens for all of them, and
     * return a String representation of all of them.
     * @param line is a string.
     * @return result is the result.
     */
    public String processExpr( String line )
    {
        Scanner scan = new Scanner( line );
        String result = "";
        while( scan.hasNext() )
        {
            EToken t = TokenFactory.makeToken( scan.next() );
            
            result += t.printable() + ", " ;
            
        }
        System.out.println( "Result: " + result );
        return result;
    }
    
    //---------------------------- TextFilter -----------------------------
    /**
     * This class is used with FileChooser to limit the choice of files.
     * to those that end in *.txt.
     */
    public class TextFilter extends javax.swing.filechooser.FileFilter
    {
        /**
         * accept accepts a file.
         * @param f is the file.
         * @return false is the boolean.
         */
        public boolean accept( File f )
        {
            if ( f.isDirectory() || f.getName().matches( ".*txt" ) )
                return true;
            return false;
        }
        /**
         * getDescription gets the description.
         * @return the txt files.
         */
        public String getDescription()
        {
            return "*.txt files";
        }
    }
    //--------------------- main -----------------------------------------
    /**
     * main method.
     * @param args is the args.
     */
    public static void main( String[] args )
    {
        Lexer app = new Lexer( args );
        
        
    }
}
