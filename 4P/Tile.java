import java.util.*;
/**
 * Tile for BoggleBoard -- skeleton.
 *
 * Knows its letter and its location on the board, but you
 *    may want to revise the the "letter" handling to
 *    account for the fact that "q" is really "qu".
 *
 * Needs to know about its visited string
 * and who its neighboring tiles are.
 *
 * @author Benjamin Miller
 * Summer 2010
 *
 * 02/19/11 rdb: Formatting and style edits
 */
public class Tile
{
    //---------------------- instance variables -------------------------
    private String          letter;
    private int             row, col;
    private Vector<Tile> neighbors;
    private String string;

    //------------------ constructor --------------------------------------

    /**Tile.
     * Constructor.
     * @param r int
     * @param c int
     * @param let int
     */
    public Tile( int r, int c, String let )
    {
        row = r;
        col = c;
        letter = let;
        string = "unvisited";
    }

    //------------------ getCol() --------------------------------------
    /**
     * returns the col location.
     *
     * @return col int
     */
    public int getCol()
    {
        return col;
    }
    //------------------ getRow() --------------------------------------
    /**
     * returns the row location.
     *
     * @return row int.
     */
    public int getRow()
    {
        return row;
    }
    
    //------------------ getLetter( ) ----------------------------
    /**getLetter.
     * Returns the letter this tile contains
     *
     * @return letter String.
     */
    public String getLetter()
    {
        return letter;
    }
    //--------------------------- toString() -------------------------

    /** toString().
     *
     * @return letter String.
     */
    public String toString()
    {
        return letter;
    }

    /** setNeighbors.
     *
     * @param n Vector<Tile>.
     */
    public void setNeighbors( Vector<Tile> n )
    {
        neighbors = n;
    }

    /**setStatus.
     *
     * @param s String.
     */
    public void setString( String s )
    {
        string = s;
    }

    /** getStatus.
     *
     * @return string String.
     */
    public String getString()
    {
        return string;
    }

    /** getNeighbors.
     *
     * @return neighbors Vector<Tile> .
     */
    public Vector<Tile> getNeighbors()
    {
        return neighbors;
    }
}