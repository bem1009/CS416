/**
 * BoggleBoard.java - A skeleton class for implementing the board for
 *                    the game of Boggle
 *
 * @author Benjamin Miller
 * 
 *
 * 02/19/11 rdb: minor formatting and style edits
 */

import java.io.*;
import java.util.*;
/**
 * Boggleboard make a boggleboard.
 * @author Benjamin Miller
 */
public class BoggleBoard
{
    //--------------------- instance variables -------------------------
    Tile[][]          board;
    private boolean   onoff; 
    int               nCols;
    int               nRows;
    ArrayList<String> letters;
    private TreeSet<String> treeset1;
    
    
    //------------------ constructor -----------------------------------
    /**
     * Arguments.
     *   @param lettersOnBoard rows x cols letters to be shown 
     * on the board.
     *   @param rows number rows of letters.
     *   @param cols number columns of letters.
     */
    BoggleBoard( ArrayList<String> lettersOnBoard, int rows, int cols )
    {
        onoff = false;
        letters = lettersOnBoard;
        nCols = cols;
        nRows = rows;
        int n = 0;
        
        ////////////////////////////////////////////////////////////////
        // 1. Need to create the board and "populate" it with the letters
        //    passed in the ArrayList. Assign entries from letters by row!
        //    That is, do all of row 0, then row 1, etc.
        // 2. For each Tile in the board, need to create a list of its
        //    valid neighbors (in all 8 directions). Remember that tiles
        //    on the boundaries don't have 8 neighbors.
        /////////////////////////////////////////////////////////////////
        board = new Tile[nRows][nCols];
        for( int r = 0; r < nRows; r++ )
        {
            for( int c = 0; c < nCols; c++ )
            {
                String s = letters.get( n ).toString();
                Tile t1 = new Tile( r, c, s );
                board[r][c] = t1;  
                n += 1;
                
            }
            
        }
        for( int j = 0; j < board.length; j++ )
        {
            for( int i = 0; i < board[j].length; i++ )
            {
                Tile t1 = board[j][i];
                t1.setNeighbors( getNeighbors( j, i ) );
            }
        }
        
    }
    /**
     * getNeighbors compiles a list of all of the tiles next 
     * to the specific one.
     * @param rows is the row that the tile is in.
     * @param columns is the column that the tile is in.
     */
    public Vector<Tile> getNeighbors( int rows, int columns ) 
    {
        
        int posX = rows;
        int posY = columns;
        Vector<Tile> neighborList = new Vector<Tile>();
        
        if( rows - 1 != - 1 )
        {
            int rows1 = rows - 1;
            Tile t = board[ rows1][columns];
            neighborList.add( t );
        }
        if( columns + 1 != nCols )
        {
            int columns1 = columns + 1;
            Tile t = board[ rows][columns1];
            neighborList.add( t );
            
        }
        if( rows + 1 != nRows )
        {
            int rows1 = rows + 1;
            Tile t = board[rows1 ][columns];
            neighborList.add( t );
            
        }
        
        if( columns - 1 != - 1 )
        {
            int columns1 = columns - 1;
            
            Tile t = board[ rows][columns1];
            
            neighborList.add( t );
            
            
        }
        if( columns - 1 != - 1 && rows + 1 != nRows )
        {
            int columns1 = columns - 1;
            int rows1 = rows + 1;
            Tile t = board[ rows1][columns1];
            neighborList.add( t );
            
            
        }
        if( columns - 1 != - 1 && rows - 1 != - 1 )
        {
            int columns1 = columns - 1;
            int rows1 = rows - 1;
            
            Tile t = board[ rows1][columns1];
            neighborList.add( t );
        }
        
        if( columns + 1 != nCols && rows - 1 != - 1 )
        {
            int columns1 = columns + 1;
            int rows1 = rows - 1;
            Tile t = board[ rows1][columns1];
            neighborList.add( t );
            
            
        }
        if( columns + 1 != nCols && rows + 1 != nRows )
        {
            int columns1 = columns + 1;
            int rows1 = rows + 1;
            Tile t = board[ rows1][columns1];
            neighborList.add( t );
            
            
        }
        
        return neighborList;
        
    }
    
    
    
    
    
    //---------------------- getWordCount() -----------------------------
    /**
     * return the number of words found in the last solution.
     * if findWords has not yet been called, returns -1;
     * @return -1 
     * 
     */
    public int getWordCount()
    {
        /////////////////////////////////////////////////////////////////
        // return the number of words found in last call to findWords()
        //    or -1 if no call yet made
        /////////////////////////////////////////////////////////////////
        if( onoff == true )
        {
            return treeset1.size();
        }
        return -1;
    }
    
    //------------------------- findWords() -----------------------------
    /**
     * Find all the valid words in this board (based on current parameter
     *    settings).
     * As words are found, add them to a Java TreeSet object (which sorts
     *    them alphabetically for you) -- see Java API documentation.
     * Return the words in a single String, separated by commas with
     *    10 words per line (except last line).
     * Most of the work is done by the private recursive method,
     *    findWords( String, Tile )
     * @return the words that the program finds on the boggleboard.
     */
    public String findWords()
    {
        //////////////////////////////////////////////////////////////////
        // For each tile in the board
        //    findWords( TreeSet, "", tile ) to find all words that start there
        // Convert the TreeSet into a single String with 10 words per line,
        //    separated by commas.
        // return this string.
        //////////////////////////////////////////////////////////////////
        onoff = true;
        treeset1 = new TreeSet<String>();
        for( int i = 0; i < board.length; i++ )
        {
            for( int j = 0; j < board[ i ].length; j++ )
            {
                Tile t = board[i][j];
                findWords( treeset1 , "", t );
            }
        }
        
        int wordCount = 0;
        int wordTotal = 0;
        String newWord = "";
        boolean starterWord = true;
        for( String s : treeset1 ) 
        {
            wordTotal++;
            if( !starterWord ) 
            {
                newWord = newWord + s;
                if ( wordCount == 9 ) 
                {
                    wordCount = 0;
                    newWord = newWord + "\n";
                }
                if( wordTotal != treeset1.size() )
                {
                    newWord = newWord + ", ";
                }
                else 
                {
                    wordCount += 1;
                }
            } else 
            {
                starterWord = false;
                newWord = s + ", ";
                wordCount += 1;
            }
        }
        
        return newWord;
    }
    
    
    
    //---------------- findWords( TreeSet<String>, String, Tile ) -----------
    /**
     * Given a partial word ending at a neighbor of the tile passed in,
     *    add the tile's letter to the partial word, and check if it is a word
     *    and if it terminates the search along this path; recurse if not.
     * @param foundWords is where the words go.
     * @param word is the specific word that this method finds.
     * @para t is the tile that this word is found in.
     * 
     */
    private void findWords( TreeSet<String> foundWords, String word, Tile t )
    {
        
        if( t.getString().equals( "unvisited" ) )
        {
            t.setString( "visited" );
            word = word + t.getLetter();
            int val = Boggle.dictionary.search( word );
            if( val == 1  )
            {
                foundWords.add( word );
            }
            
            if( val == 1 || val == 0 )
            {
                Vector<Tile> tilez = t.getNeighbors();
                for( int i = 0; i < tilez.size(); i++ )
                {
                    
                    findWords( foundWords, word , tilez.get( i ) );
                }
            }
            t.setString( "unvisited" );
            
        }
        
    }
    //-------------------- toString() ---------------------------------------
    /**
     * convert the board to a String representation.
     * @return a string that the tile contains.
     * 
     */
    public String toString()
    {
        StringBuffer out = new StringBuffer();
        for ( int r = 0; r < nRows; r++ )
        {
            for ( int c = 0; c < nCols; c++ )
            {
                out.append( board[ r ][ c ].getLetter() + "\t" );
                if ( board[ r ][ c ] .getLetter().length() == 1 )
                    out.append( " " );        
            }
            out.append( "\n" );
        }
        return out.toString();
    }
    //+++++++++++++++++++++++ main: invoke application ++++++++++++++++++++++
    /**
     * main method.
     * @param args is the args.
     */
    public static void main( String [] args )
    {
        Boggle.main( args );
    }
}
