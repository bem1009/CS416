//++++++++++++++++++++++++++++++++ PyramidNode ++++++++++++++++++++++++++
import java.awt.Point;

/**
 * Node class for pyramid data structure. 
 *     Pyramid level d as exactly d+1 nodes.
 *     Nodes have location and size information even though they
 *        are not themselves displayed. The idea is that the "data"
 *        object (of type T, which must implement the Geometry interface)
 *        are likely to be displayed. The size information comes from
 *        those objects -- although they should be the same size or 
 *        things won't work very well. The location information is 
 *        determined by the pyramid structure.
 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * The starting code for this class is barely enough to allow the
 * starting package to "appear" to play the game. This class is 
 * primarily used in the starter code to "save" the location in the
 * pyramid of a Card that has been played. This allows the "Undo" to
 * restore the card to its place in the pyramid.
 * 
 * You will surely need additional code here and may want to rewrite
 * some of what is here.
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * 
 * @author rdb
 * March 2014 -- derived from earlier TreeNode class. 
 * 
 * @param <T> extends Geometry
 */
class PyramidNode<T extends Geometry >  implements Geometry
{
    //--------------------- instance variables --------------------
    public PyramidNode<T>   _left;
    public PyramidNode<T>   _right;
    public PyramidNode<T>   _parentLeft, _parentRight;
    public PyramidNode<T>   _prevLeftParent, _prevRightParent;
    public int yHeight;
    public int xWidth; 
    
    

    private int       _xLoc;
    private int       _yLoc;
    private int       _width;
    private int       _height; 
    private int       _parentLeftHeight;
    private int       _parentRightHeight;
    
    private T         _data;        // 
    private boolean   _hasData;     // true if data object is in pyramid
    
    //----------------- constructors -----------------------------
    /**
     * Construct a PyramidNode with position and size information.
     * @param x int
     * @param y int
     * @param w int
     * @param h int
     */
    public PyramidNode( int x, int y, int w, int h )
    {
        _left = null;
        _right = null;
        _data = null;
        _hasData = false;
        _xLoc = x;
        _yLoc = y;
        _width = w;
        _height = w;
        
    }
    //------------------ setData( T content ) -------------------------
    /**
     * Define the data content to be saved in this node.
     * 
     * @param content T
     */
    public void setData( T content )
    {
        _data = content;
        _hasData = true;
    }
    //------------------ getData() -------------------------
    /**
     * Get the data content saved in this node.
     * @return T
     */
    public T getData()
    {
        return _data;
    }
    //------------------ toString() ------------------------------
    /**
     * Return a string representation for the node.
     * @return String
     */
    public String toString()
    {
        return "{." + _left + "." + _right + ":" + _data + "}";
    }
    //++++++++++++++++++++++ Geometry interface +++++++++++++++++++++
    //------------------ getX() ---------------
    /**
     * Return the x value of the object's location.
     * @return int
     */
    public int getX()
    {
        return _xLoc;
    }
    //------------------ getY() ---------------
    /**
     * Return the y value of the object's location.
     * @return int
     */
    public int getY()
    {
        return _yLoc;
    }
    //------------------ getLocation() ---------------
    /**
     * Return the object's location as a Point.
     * @return Point
     */    
    public Point getLocation()
    {
        return new Point( _xLoc, _yLoc );
    }
    //------------------ getHeight() ---------------
    /**
     * Return the height of the object's bounding box.
     * @return int
     */    
    public int getHeight()
    {
        return _height;
    }
    //------------------ getWidth() ---------------
    /**
     * Return the height of the object's bounding box.
     * @return int
     */    
    public int getWidth()
    {
        return _width;
    }
    /**
     * getLeft accessor. 
     * @return _left child.
     */
    public PyramidNode<T> getLeft()
    {
        return _left;
    }
    /**
     * getRight accessor.
     * @return _right child.
     */
    public PyramidNode<T> getRight()
    {
        return _right;
    }
    /**
     * getPyramidLeft accessor.
     * @return _parentLeft
     */
    public PyramidNode<T> getPyramidLeft()
    {
        return _parentLeft;
    }
    /**
     * getPyramidRight accessor.
     * @return _parentRight
     */
    public PyramidNode<T> getPyramidRight()
    {
        return _parentRight;
    }
    /**
     * Sets the left child of the node.
     * @param p is the node it's set to.
     */
    public void setLeft( PyramidNode<T> p )
    {
        _left = p;
    }
    /**
     * Sets the right child of the node.
     * @param p is the node it's set to.
     */
    public void setRight( PyramidNode<T> p )
    {
        _right = p;
    }
    /**
     * Sets the left parent of the node.
     * @param p is the node it's set to. 
     */
    public void setPyramidLeft( PyramidNode<T> p )
    {
        if( _parentLeft != null )
        {
            _prevLeftParent = _parentLeft;
        }
        _parentLeft = p;
    }
    /**
     * Sets the right parent of the node.
     * @param p is the node it's set to.
     */
    public void setPyramidRight( PyramidNode<T> p )
    {
        if( _parentLeft != null )
        {
            _prevRightParent = _parentRight;
        }
        _parentRight = p;
    }
    /**
     * Gets the height of the parent(s).
     * @return _parentLeftHeight
     */
    public int getParentHeight()
    {
        return _parentLeftHeight;
    }
    /**
     * Gets the width of the parent(s).
     * @return _parentRightHeight
     */
    public int getParentWidth()
    {
        return _parentRightHeight;
    }
    /**
     * Sets the height of the parent.
     * @param height is the height it's being set to.
     */
    public void setParentHeight( int height )
    {
        _parentLeftHeight = height;
    }
    /**
     * Sets the width of the parent.
     * @param width is the width it's being set to.
     */
    public void setParentWidth( int width )
    {
        _parentRightHeight = width;
    }
    
    

    
}
