import java.io.*;
import java.util.*;
/** Boggle Board.
 * BoggleBoardTest.java - A skeleton class for implementing the board for
 *                    the game of Boggle
 *
 * @author Benjamin Miller 
 * Summer 2010
 *
 * 02/19/11 rdb: minor formatting and style edits
 */
public class BoggleBoardTest
{
    //--------------------- instance variables -------------------------
    private Tile[][]          board;
    private int               nCols;
    private int               nRows;
    private ArrayList<String> letters;
    private TreeSet<String> words;
    private boolean call;
    
    
    //------------------ constructor -----------------------------------
    /**sentence.
     * Arguments:
     *        lettersOnBoard: rows x cols letters to be shown on the board
     *        rows: number rows of letters
     *        cols: number columns of letters
     *
     *        @param lettersOnBoard ArrayList<string>
     *        @param rows int
     *        @param cols int
     */
    BoggleBoardTest( ArrayList<String> lettersOnBoard, int rows, int cols )
    {
        call = false;
        letters = lettersOnBoard;
        nCols = cols;
        nRows = rows;
        board = new Tile[ rows ][ cols ];
        
        int currentRow = 0;
        int currentCol = 0;
        for( int i = 0; i < lettersOnBoard.size(); i++ )
        {
            int c1 = currentCol;
            Tile t = new Tile( currentRow, c1, lettersOnBoard.get( i ) );
            
            board[ currentRow ][currentCol] = t;
            
            if( currentRow == rows - 1 )
            {
                currentRow = 0;
                currentCol = currentCol + 1;
            } else 
            {
                currentRow++;
            }
            
            
        }
        
        ////////////////////////////////////////////////////////////////
        // 1. Need to create the board and "populate" it with the letters
        //    passed in the ArrayList. Assign entries from letters by row!
        //    That is, do all of row 0, then row 1, etc.
        // 2. For each Tile in the board, need to create a list of its
        //    valid neighbors (in all 8 directions). Remember that tiles
        //    on the boundaries don't have 8 neighbors.
        /////////////////////////////////////////////////////////////////
        for( int q = 0; q < board.length; q++ )
        {
            for( int k = 0; k < board[q].length; k++ )
            {
                Tile t = board[q][k];
                t.setNeighbors( getNeighbors( q, k ) );
            }
        }
        
        
    }
    
    /**
     * getNeighbors().
     * @param x int.
     * @param y int.
     * @return temp Vector<Tile>.
     */
    public Vector<Tile> getNeighbors( int x, int y ) 
    {
        Vector<Tile> temp = new Vector<Tile>();
        int posX = x;
        int posY = y;
        //get int and row around it
        if( x - 1 != - 1 )
        {
            int x1 = x - 1;
            Tile t = board[ x1][y];
            temp.add( t );
        }
        if( x + 1 != nRows )
        {
            int x1 = x + 1;
            Tile t = board[x1 ][y];
            temp.add( t );
            //System.out.println( x1 + "x" + y);
        }
        if( y + 1 != nCols )
        {
            int y1 = y + 1;
            Tile t = board[ x][y1];
            temp.add( t );
            //System.out.println( x + "x" + y1);
        }
        if( y - 1 != - 1 )
        {
            int y1 = y - 1;
            // Tile t = board[ y1][x];
            Tile t = board[ x][y1];
            
            temp.add( t );
            //System.out.println( x + "x" + y1);
            
        }
        if( y - 1 != - 1 && x - 1 != - 1 )
        {
            int y1 = y - 1;
            int x1 = x - 1;
            //  Tile t = board[ y1][x1];
            Tile t = board[ x1][y1];
            temp.add( t );
        }
        if( y - 1 != - 1 && x + 1 != nRows )
        {
            int y1 = y - 1;
            int x1 = x + 1;
            Tile t = board[ x1][y1];
            temp.add( t );
            
            //System.out.println("Diagnoal " + x1+"x"+y1);
        }
        if( y + 1 != nCols && x - 1 != - 1 )
        {
            int y1 = y + 1;
            int x1 = x - 1;
            Tile t = board[ x1][y1];
            temp.add( t );
            
            //System.out.println("Diagnoal " + x1+"x"+y1);
        }
        if( y + 1 != nCols && x + 1 != nRows )
        {
            int y1 = y + 1;
            int x1 = x + 1;
            Tile t = board[ x1][y1];
            temp.add( t );
            
            //System.out.println("Diagnoal " + x1+"x"+y1);
        }
        
        return temp;
        
    }
    
    
    //---------------------- getWordCount() -----------------------------
    /**
     * return the number of words found in the last solution.
     * if findWords has not yet been called, returns -1;
     * 
     * @return words.size int.
     */
    public int getWordCount()
    {
        /////////////////////////////////////////////////////////////////
        // return the number of words found in last call to findWords()
        //    or -1 if no call yet made
        /////////////////////////////////////////////////////////////////
        if( call )
        {
            return words.size();
        } else 
        {
            return -1;
        }
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
     * 
     * @return returner String
     */
    public String findWords()
    {
        call = true;
        words = new TreeSet<String>();
        //////////////////////////////////////////////////////////////////
        // For each tile in the board
        //    findWords( TreeSet, "", tile ) to find all words that start there
        // Convert the TreeSet into a single String with 10 words per line,
        //    separated by commas.
        // return this string.
        //////////////////////////////////////////////////////////////////
        for( int i = 0; i < board.length; i++ )
        {
            for( int j = 0; j < board[ i ].length; j++ )
            {
                Tile t = board[i][j];
                findWords( words , "", t );
            }
        }
        String returner = "";
        int count = 0;
        int totalCount = 0;
        boolean first = true;
        for( String w : words ) 
        {
            totalCount++;
            if( !first ) 
            {
                returner = returner  + w ;
                if( totalCount != words.size() )
                {
                    returner = returner + ", ";
                }
                if ( count == 9 ) 
                {
                    count = 0;
                    returner = returner + "\n";
                } else 
                {
                    count++;
                }
            } else 
            {
                first = false;
                returner = w + ", ";
                count++;
            }
        }
        
        return returner;
    }
    
    //---------------- findWords( TreeSet<String>, String, Tile ) -----------
    /**
     * Given a partial word ending at a neighbor of the tile passed in,
     *    add the tile's letter to the partial word, and check if it is a word
     *    and if it terminates the search along this path; recurse if not.
     *
     *    @param foundWords TreeSet<String>.
     *    @param word String.
     *    @param t Tile.
     */
    private void findWords( TreeSet<String> foundWords, String word, Tile t )
    {

        ///////////////////////////////////////////////////////////////////
        //  if tile has not been visited (on this path)
        //     set tile's status as visited
        //     add tile's letter to word
        //     lookup word using search method of Boggle.dictionary
        //     if it is a word
        //        add it to the TreeSet
        //     if it is a word or it might begin another word
        //        get neighbor tiles of this tile
        //        for each neighbor of this tile
        //           invoke findWords(...) recursively
        //     reset the tiles visited flag to false
        ////////////////////////////////////////////////////////////////////
        
        if( t.getStatus().equals( "unvisited" ) )
        {
            t.setStatus( "visited" );
            word = word + t.getLetter();
            int val = Boggle.dictionary.search( word );
            if( val == 1  )
            {
                foundWords.add( word );
            }
            
            if( val == 1 || val == 0 )
            {
                Vector<Tile> nTiles = t.getNeighbors();
                for( int i = 0; i < nTiles.size(); i++ )
                {
                    
                    findWords( foundWords, word , nTiles.get( i ) );
                }
            }
            t.setStatus( "unvisited" );
            
        }
        
    }
    //-------------------- toString() ---------------------------------------
    /**
     * convert the board to a String representation.
     * @return out.toString String
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
    /**main.
     * 
     * @param args String[]
     */ 
    public static void main( String [] args )
    {
        Boggle.main( args );
    }
}