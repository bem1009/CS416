/**
 * Interpreter.java - parses string input representing an infix arithmetic
 *                 expression into tokens, and builds an expression tree.
 *                 The expression can use the operators =, +, -, *, /, %.
 *                 and can contain arbitrarily nested parentheses.
 *                 The = operator is assignment and must be absolutely lowest
 *                 precedence.
 * March 2013
 * rdb
 */

import javax.swing.*;
import java.util.*;
import java.io.*;



public class Interpreter //extends JFrame
{
   //----------------------  class variables  ------------------------

   //---------------------- instance variables ----------------------
   private boolean      _printTree = true;  // if true print tree after each
                                           //    expression tree is built
    private boolean _printToggle = false;

   //if its a valuable we look it up in the symbol tree

    private String treememory = "";

   private SymbolTable _symbolTable;
   //----------- constants
   
   //--------------------------- constructor -----------------------
   /**
    * If there is a command line argument use it as an input file.
    * otherwise invoke an interactive dialog.
    */
   public Interpreter( String[] args ) 
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
    */
   public void processFile( String fileName )
   {

      try
      {
         Scanner s1 = new Scanner( new File( fileName ) );
         while( s1.hasNextLine() )
         {
            String s = s1.nextLine();
            //System.out.println( s );
           String s11 =  processLine( s );
            System.out.println( s11 );
             if( _printTree == true ){
                 System.out.println( treememory );
             }
         }


      } catch ( FileNotFoundException fe )
      {
         System.out.println( "Processing File did not work" );
      }


      //infix to tree algorithm

   }
   //--------------------- processLine -----------------------------
   /**
    * Parse each input line -- it shouldn't matter whether it comes from
    * the file or the popup dialog box. It might be convenient to return
    * return something to the caller in the form of a String that can
    * be printed or displayed.
    */
   public String processLine( String line )
   {
      //System.out.println(  "Input: " + line );
      String s = line.trim();
      if ( s.length() == 0 || s.charAt( 0 ) == '#' ) {
          return line;
      } else if( s.charAt( 0 ) == '@'){
          String s2= "";
          Scanner s1 = new Scanner( s );
          String command1 = s1.next();
          String command2 = "";
          if( s1.hasNext() ) {
              command2 = s1.next();
          }
          if( command1.equals( "@print" ) ){
              if( command2.equals("on") || command2.equals("ON") )
              {
                  _printTree = true;
                  s2= "Print Command On";
              } else if( command2.equals("off") || command2.equals("OFF") )
              {
                  _printTree = false;
                  s2= "Print Command Off";
              } else if( command2.equals("") )
              {
                  s2= " Toggling on for previous tree";
                  s2= s2+ "\n" + treememory;
              } else
              {
                  s2= "Unrecognizable Command for Print";
              }

          } else if( command2.equals( "@lookup" ) ){

              if( command2.equals("") )
              {
                  s2= "Full Symbol Table:";
                 String table =  _symbolTable.toString();
                  s2= s2+ "\n" + table;
                  
              } else 
              {
                  Float f2 = _symbolTable.getValue( command2 );
                  String results = "";
                  results = "{ " + command2 + " : " + f2 + "}";
                  s2= "Lookup for " + command2 + ",";
                  while( s1.hasNext() )
                  {
                      String snext = s1.next();
                      s2= s2+ snext + ",";
                     Float f1 = _symbolTable.getValue( snext );
                      results = results + "\n" + "{ " + snext + " : " + f1 + "}";

                  }
                  s2= s2+ "\n" + results;
              }

          } else {
              s2= "Unreconignizable Command";
          }
          return line + s2;
      }
      else
      {
          System.out.println( "Processing: " + s );
         return processExpr( s );

      }
   }

   //------------------ processExpr( String ) -------------------------
   /**
    * Get all fields in the expression, and
    * generate tokens for all of them, and
    * return a String representation of all of them.
    *
    * @param line String.
    * @return s String.
    */
   public String processExpr( String line )
   {
      Scanner s1 = new Scanner( line );
       String answer = "";
       //Pre Test To ensure everything is good
       while( s1.hasNext() )
       try{
           EToken e5 = TokenFactory.makeToken( s1.next() );
       } catch ( Exception e1){
           System.out.println( e1.getMessage() );
           return "";
       }


      Scanner s2 = new Scanner( line );
      String message = "";

      Stack<EToken> opStack = new Stack<EToken>();
      Stack<Node> randStack = new Stack<Node>();
      String l1 = line.trim();
      String stuff = "";
      while( s2.hasNext() ){
          EToken et1 = null;
          String s21 = "";
          try {
              s21 = s2.next();
              et1 = TokenFactory.makeToken( s21 );
         
          } catch (Exception e) {
              System.out.println( "Error Creating Token: " + s21 );
              break;
          }

          if (et1 instanceof Operand) {
              Node n = new Node( et1 );
              randStack.push(n);
          } else if ( et1.op().equals("(") )
          {

              opStack.push( et1 );
          } else if (et1.op().equals(")")) {
              while (!opStack.top().op().equals("(")) 
              {
                  Node n = new Node(opStack.pop());
                  
                  n.right = randStack.pop();
                  n.left = randStack.pop();
                  randStack.push(n);
                  //System.out.println( n );
              }
              opStack.pop(); 

          } else {
              while (!opStack.isEmpty() && proc( opStack.look(0) ) >= proc( et1 ))
              {
                  Node n = new Node(opStack.pop());
                  n.right = randStack.pop();
                  n.left = randStack.pop();
                  randStack.push(n);
              }
              opStack.push(et1);


          }

          }
       while( opStack.size() > 0 ){
           Node n1 = new Node( opStack.pop() );
           n1.right = randStack.pop();
           n1.left  = randStack.pop();
           randStack.push( n1 );

       }
       
       randStack.top().printTree( randStack.top() , 0 );
       treememory = randStack.top().printval;

       answer = "Output: " + randStack.top().evaluate( randStack.top() , _symbolTable);
       

      return answer;
   }

    public void inOrderTraverse( Node root, int k ){

        String space = " ";
        String letters = "";
        for( int i = 0; i < k; i++ ){
            letters = letters + space;
        }
        System.out.println( letters + root.value.op() );

        if( root.left != null ){
            inOrderTraverse( root.left , k + 1);
        }
        if( root.right != null ){
            inOrderTraverse( root.right , k + 1 );
        }
    }

public int proc( EToken e ){
   String highpriority = "*/%";
   String medpriority = "+-";
   String lowpriority = "=";
   if( highpriority.contains(  e.op()  )){
      return 3;
   }
   else if( medpriority.contains(  e.op()  )){
      return 2;
   }
   else if( lowpriority.contains(  e.op()  )){
      return 1;
   }

   else{
      return -1;
   }
}

   //--------------------- interactive -----------------------------
   /**
    * Use a file chooser to get a file name interactively, then 
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
         File file = fChooser.getSelectedFile();
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

   //+++++++++++++++++++++++++ inner class +++++++++++++++++++++++++++++++
   //---------------------------- TextFilter -----------------------------
   /**
    * This class is used with FileChooser to limit the choice of files
    * to those that end in *.txt
    */
   public class TextFilter extends javax.swing.filechooser.FileFilter
   {
      public boolean accept( File f ) 
      {
         if ( f.isDirectory() || f.getName().matches( ".*txt" ) )
            return true;
         return false;
      }
      public String getDescription()
      {
         return "*.txt files";
      }
   }
   //--------------------- main -----------------------------------------
   public static void main( String[] args )
   {
      Interpreter app = new Interpreter( args );
   }
}