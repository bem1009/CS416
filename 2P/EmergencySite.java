
/**
 * EmergencySite -- represents an emergency site. 
 *   Dragging (mousePressed followed by mouseDragged) repositions it
 *     Not allowed to drag if its the current EmergencySite, easiest
 *     way to do that is make that site not draggable
 *  MouseClicked -- if clicked, this site is no longer an emergency 
 *     site; remove it from the set of sites or somehow make it known
 *     that it is not real emergency site any more.
 * 
 * @author Benjamin Miller
 * Last edited: 01/28/14  
 */
import javax.swing.*;
import java.lang.reflect.Array;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 * Emergency site is a emergency site that once clicked on, 
 * places a red square, and lets the dispatcher know that the 
 * ambulance must travel there.
 * @author Benjamin Miller
 */
public class EmergencySite extends JRectangle
    implements MouseListener, MouseMotionListener, Draggable
{
    //--------------------- class variables -------------------------
    private static int    size = 10;
    
    //--------------------- instance variables ----------------------
    private boolean     _isitthere = false;
    
    //--------------------- constants -------------------------------
    private Color  fillColor = Color.RED;
    private Color  lineColor = Color.BLACK;
    private boolean one;
    private ArrayList<EmergencySite> badstuffhappening;
    private Dispatcher _dispatch1;
    
    private boolean _dragornah;
    
    //---------------------- constructors ---------------------------
    /**
     * Generate a site at the specified location.
     * 
     * @param p Point   location of emergency
     */
    public EmergencySite( Point p )
    {
        super( p.x, p.y );
        setFillColor( fillColor );
        setFrameColor( lineColor );
        setSize( size, size );
        one = false;
        _dragornah = false;
        
        addMouseListener( this );
        addMouseMotionListener( this );
    }
    //+++++++++++++++++++ Draggable interface methods ++++++++++++++
    private boolean   _draggable = true; // true if obj can be dragged
    /** 
     * setDraggable makes the object either not draggable or 
     * draggable.
     * @param onOff is the boolean that decides whether it's draggable
     * or not.
     */
    public void setDraggable( boolean onOff )
    {
        _draggable = onOff;
    }
    /**
     * isDraggable returns a boolean whether or not the object.
     * is draggable.
     * @return _draggable returns a boolean.
     */
    public boolean isDraggable()
    {
        return _draggable;
    }
    /**
     * Contains sees if the the point contains.
     * @param point is the point to see if it contains.
     * @return the point of getbounds.
     * 
     */
    public boolean contains( java.awt.geom.Point2D point )
    {
        return getBounds().contains( point );
    }
    
    //++++++++++++++++ mouse methods / instance variables ++++++++++++
    private Point _saveMouse;   // last mouse position
    //   used for dragging      
    //+++++++++++++++++++++++++ mouseListener methods +++++++++++++++
    //-------------- mousePressed -----------------------------------
    /**
     * mousePressed makes.
     * @param me is the mouse event 
     */
    public void mousePressed( MouseEvent me )
    {
        /////////////////////////////////////////////////////////////
        // me.getPoint(), which we've used before is the location of 
        // mouse "inside" the JComponent; this won't work.
        //
        // We need position of mouse in the container that holds the
        // JComponent: getParent().getMousePosition()
        //
        // Assign it to the instance variable, _saveMouse
        /////////////////////////////////////////////////////////////
        
        _saveMouse = getParent().getMousePosition();
        
        
    }
    private EMTPanel em2;
    /**
     * setList sets the list of emergencysite.
     * @param a is the list of emergency sites.
     */
    public void setList( ArrayList<EmergencySite> a )
    {
        badstuffhappening = a;
    }
    /**
     * oneSet returns a boolean to see if 
     * the set is together.
     * @param t is the boolean returned.
     */
    public void oneSet( boolean t )
    {
        one = t;
    }
    //-------------- mouseClicked -----------------------------------
    /** 
     * mouseClicked gets the dispatcher and tells the emergency site 
     * that a new emergencysite has come up. 
     * @param me is the mouse event.
     */
    public void mouseClicked( MouseEvent me )
    {
        //////////////////////////////////////////////////////////////
        // On mouse click, this site should be removed from the
        //    sites array.
        // Figure out a way for this class to 
        //    - know about the sites array (so it can remove itself), OR
        //    - for this class to be able to call somebody that it 
        //      knows about who knows about the sites array, OR
        //    - to put information into this object so that some other 
        //      object will know this site is no longer a real site
        //      and should not be visited by the emt vehicle.
        //////////////////////////////////////////////////////////////
        if( one == false ) 
        {
            badstuffhappening.remove( this );
        }
        this.setColor( Color.BLUE );
        
    }
    /** dispatch returns the dispatcher.
      * @param d is the dispatcher.
      */
    public void dispatch( Dispatcher d )
    {
        _dispatch1 = d;
    }
    /** setArray sets the array of the emergency sites.
      * @param site is the list of emergency sites.
      */
    public void setArray( ArrayList<EmergencySite> site )
    {
        badstuffhappening = site;
    }
    //--------------- unimplemented mouse badstuffhappeningener methods ------
    /** 
     * This method just implemented.
     * @param me is mouse event.
     */
    public void mouseReleased( MouseEvent me )
    {
    }
     /** 
     * This method just implemented.
     * @param me is mouse event.
     */
    public void mouseEntered( MouseEvent me )
    { 
    }
     /** 
     * This method just implemented.
     * @param me is mouse event.
     */
    public void mouseExited( MouseEvent me )
    { 
    }
    /** setnotDrag returns a boolean to make no drag.
      * @param t is the boolean for a drag;
      */
    public void setNoDrag ( boolean t )
    {
        _dragornah = t;
    }
    
    //+++++++++++++++++++ mouseMotionListener methods ++++++++++++++++
    //---------------- mouseDragged ----------------------------------
    /** 
     * mousedragged method.
     * @param me is the mouse event.
     */
    public void mouseDragged( MouseEvent me )
    {
        if( _dragornah == false ) 
        {
            
            //////////////////////////////////////////////////////////////
            //  IF this object is draggable
            //     Get new position of mouse:
            //         getParent().getMousePosition()
            //     For each of x and y coordinates, compute
            //       dX = newX - oldX (stored in _saveMouse.x)
            //       dY = newY - oldY (stored in _saveMouse.y)
            //     invoke moveBy( dX, dY )
            //     Save new position in _saveMouse
            //     getParent().repaint()
            //////////////////////////////////////////////////////////////
            if ( one == false ) 
            {
                boolean nodrag = false;
                if ( badstuffhappening.size() > 0 ) 
                {
                    if ( badstuffhappening.get( 0 ) == this ) 
                    {
                        nodrag = true;
                    }
                }
                if ( _dispatch1.getOnOff() != 0 && nodrag == true ) 
                {
                    
                }
                else 
                {
                    int dX = getParent().getMousePosition().x - _saveMouse.x;
                    int dY = getParent().getMousePosition().y - _saveMouse.y;
                    this.moveBy( dX, dY );
                    _saveMouse = getParent().getMousePosition();
                    
                    getParent().repaint();
                }
            } else if ( one == true ) 
            {
                int dX = getParent().getMousePosition().x - _saveMouse.x;
                int dY = getParent().getMousePosition().y - _saveMouse.y;
                this.moveBy( dX, dY );
                _saveMouse = getParent().getMousePosition();
                
                getParent().repaint();
            }
            
        }
    }
    //----------------- mouseMoved not implemented -------------------
     /** 
     * This method just implemented.
     * @param me is mouse event.
     */
    public void mouseMoved( MouseEvent me )
    {
    }
    //+++++++++++++++++ end MouseMotionListeners +++++++++++++++++++++
    //------------- paintComponent( Graphics ) ----------------------
    
    
    
    //--------------------- main -----------------------------------
    /**
     * unit test.
     * @param args is the args
     */
    public static void main( String[] args )
    {     
        
    }
}