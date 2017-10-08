import java.util.*;
import java.awt.*;
/**
 * Dispatcher tells the vehicle where to go and when.
 * @author Benjamin Miller
 */
public class Dispatcher implements Animated
{
    //-------------------- instance variables ---------------------
    private ArrayList<Hospital> _hospitals;
    private ArrayList<EmergencySite> _emergency;
    private FrameTimer _frametimer;
    private EmergencySite thisSite;
    private EMTVehicle _ambulance;
    private JLine _line;
    private int onoff;
    
    private boolean delay;
    private boolean reset;
    
    //------------------ constructor -----------------------------
    /** Dispatcher tells the EMT vehicle where to go.
      * @param harray is the array of hospitals.
      * @param ft1 is the frametimer that drives the animation.
      * @param emergencies is the collection of emergency sites
      * @param l1 is the line drawn when the object moves.
      * @param emt is the ambulance that moves.
      */
    
    public Dispatcher( 
                      ArrayList<Hospital> harray,
                      FrameTimer ft1,
                      ArrayList<EmergencySite> emergencies,
                      EMTVehicle emt,
                      JLine l1
                         
                      )
    {
        delay = false;
        _line = l1;
        _ambulance = emt;
        _hospitals = harray;
        _emergency = emergencies;
        _frametimer = ft1;
        
        
        
        _ambulance.setAnimated( true );
        onoff = 0;
        
        reset = false;
    }
    //---------------------- setNextSite() -------------------------
    /**
     * identify the next emergency site, if there is one.
     * @return next is the emergency site that the ambulance has to move to.
     */
    private EmergencySite getNextSite()
    {
        if( _emergency.size() > 0 ) 
        {
            
            EmergencySite next = _emergency.get( 0 );
            return next;
            
            
        } else 
        {
            return null;
        }
        //////////////////////////////////////////////////////////////
        // get next site from list of sites or from someone who has
        //    list of sites.
        //////////////////////////////////////////////////////////////
        
        
        
    }
    //--------------- getClosestHospital( Point ) --------------------
    /**
     * find the closest hospital site to the parameter
     *    This method is complete.
     * Note that we don't bother to compute the correct distance,
     *    we make the decision based on the square of the distance
     *    which saves the computation of the square root, a reasonably
     *    costly operation that serves no purpose for this test.
     * 
     * @param  loc  Point  location of emt vehicle
     * @return      Hospital closest hospital to vehicle at loc
     */
    private Hospital getClosestHospital( Point loc )
    {
        double distanceSq = Float.MAX_VALUE;
        Hospital   closest    = null;
        for ( Hospital h: _hospitals )
        {
            double d2 = loc.distanceSq( h.getLocation() );
            if ( d2 < distanceSq )
            {
                distanceSq = d2;
                closest = h;
            }
        }
        return closest;
    }
    
    
    /**
     * setDelay sets the delay whenever the ambulance arrives in a new location.
     *@param del is the boolean that you set delay to.
     */
    public void setDelay( boolean del )
    {
        delay = del;
    }
    /**
     * getOnOff returns the "mode" of the emt vehicle, like whether the vehicle
     * is moving to the hospital, the emergency site, or back "home". 
     * @return onoff is the int that is returned
     */
    public int getOnOff()
    {
        return onoff;
    }
    
    //++++++++++++++++++++++ Animated interface ++++++++++++++++++++++
    private boolean _animated = true;
    //---------------------- isAnimated() ----------------------------
    /** isAnimated gives a boolean telling whether or not the vehicle is
      * animated.
      * @return _animated is the boolean returned.
      */
    public boolean isAnimated()
    {
        return _animated;
    }
    //---------------------- setAnimated( boolean ) -----------------
    /** 
     * setAnimated sets the animation value in the ambulance.
     * @param onOff is the boolean given to the function.
     */
    public void setAnimated( boolean onOff )
    {
        _animated = onOff;
    }
    //---------------------- newFrame ------------------------------
    /**
     * invoked for each frame of animation; figure out what to do with
     *    the vehicle at this frame.
     */
    public void newFrame()
    {
        if( onoff == 2 )
        {
            _line.setVisible( false );
            if( reset == false ) 
            {
                _ambulance.travelTo( 10, 10, EMTApp.normalSpeed );
                
                if( _ambulance.getLocation().x == 10 )
                {
                    if( _ambulance.getLocation().y == 10 )
                    {
                        reset = true;
                    }
                }
            }
            
            _ambulance.isitthereReset();
            _ambulance.newFrame();
            if( _emergency.size() > 0 )
            {
                reset = false;
                onoff = 0;
            }
        }
        //////////////////////////////////////////////////////////////
        // If vehicle is moving, update its position (by calling its 
        //    newFrame method. 
        // Somehow, need to know if it has reached its goal position. 
        //    If so, figure out what the next goal should be.
        // 
        //    If previous goal was emergency site, new goal is hospital
        //    If previous goal was a hospital (or if it was at home,
        //       or if it was going home), new goal is the next
        //       emergency site, if there is one, or home if no more 
        //       emergencies.
        //////////////////////////////////////////////////////////////
        
        if( onoff == 3 )
        {
            
            Hospital h1 = this.getClosestHospital( _ambulance.getLocation() );
            _line.setPoints( _ambulance.getLocation().x  , 
                            _ambulance.getLocation().y , h1.getLocation().x, 
                            h1.getLocation().y );
            _line.setVisible( true );
            _ambulance.travelTo( h1.getLocation().x, 
                                h1.getLocation().y, EMTApp.highSpeed );
            _ambulance.newFrame();
        }
        if( _ambulance.isTargetReached() == true )
        {
            
            
            if( onoff == 0 )
            { 
                
                _frametimer.restart();
                boolean justGoHome = false;
                if( _emergency.size() > 0 ) 
                {
                    
                    _emergency.get( 0 ).setColor( Color.BLUE );
                    _emergency.get( 0 ).setVisible( false );
                    _emergency.remove( 0 ); 
                } 
                else if ( _emergency.size() == 0 )
                {
                    justGoHome = true;
                    onoff = 1;
                }
                
                onoff = 3;                
            } else if ( onoff == 1 )
            {
                
                _frametimer.restart();
                
                
                if( _emergency.size() > 0 )
                {
                    
                    onoff = 0;
                    
                } else 
                {
                    onoff = 2;
                    
                    
                }
            }
            else if ( onoff == 3 )
            {
                
                
                if( _emergency.size() > 0 )
                {
                    _frametimer.restart();
                    
                    onoff = 0;
                    
                }
                else 
                {
                    _frametimer.restart();
                    
                    onoff = 2;
                    
                    
                }
            }
        }
        
        
        if ( onoff == 0 )
        {
            
            if( this.getNextSite() != null )
            {
                EmergencySite e = this.getNextSite();
                thisSite = e;
            }
            thisSite.setNoDrag( true );
            
            int nextX = thisSite.getXLocation();
            int nextY = thisSite.getYLocation();
            
            
            _line.setPoints( _ambulance.getX() + _ambulance.getWidth() / 2 ,
                            _ambulance.getY() + _ambulance.getHeight() / 2 ,
                            nextX + thisSite.getWidth() / 2  , nextY +
                            thisSite.getHeight() / 2 );
            _line.setVisible( true );
            
            _ambulance.travelTo( thisSite.getXLocation()
                                    , thisSite.getYLocation(), 
                                EMTApp.highSpeed );
            
            _ambulance.newFrame();
            
        }
        else if( onoff == 1 )
        {
            _ambulance.newFrame();
        }
        
        
    }
    
}