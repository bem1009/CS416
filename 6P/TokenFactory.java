

/**
 * Created by Benjamin Miller on 4/4/2016.
 */
public class TokenFactory
{

    /** makeToken.
     *
     * @param s String.
     * @return e1 Etoken.
     * @throws Exception e.
     * */
    public static EToken makeToken(String string ) throws Exception
    {
        //test what kind of thing this is
        String operators = "+-/*%()=";
        //EToken e = new Number();

        String token = string;
        float value;
        try
        {
            value = Float.parseFloat( token );
            EToken e1 = new Number( value );
            return e1;
        }
        catch ( NumberFormatException e )
        {
            
            if( operators.contains( string ) && string.length() == 1 )
            {
                EToken e2 = new Operator( string );
                return e2;
            } else
            {
                Boolean isIdentifer = Character.isJavaIdentifierStart(
                        string.charAt( 0 ) );
                int stringAmount = string.length();
                boolean valid = true;
              
                for( int i = 1; i < stringAmount; i++ )
                {
                    
                    boolean temp = Character.isJavaIdentifierPart(
                            string.charAt( i ) );
                    if( temp == false )
                    {
                        valid = false;
                    }
                }

                if( isIdentifer && valid )
                {
                    EToken et = new Variable( string );
                    return et;
                } else
                {
                    boolean notvariable = false;
                    int stringAmount2 = string.length();
                    boolean containsOp = false;
                    for( int i = 0; i < stringAmount; i++ )
                    {
                        String word = string.substring( 0 + i, 1 + i );
                        boolean op = operators.contains( word  );
                        if( op )
                        {
                            notvariable= true;
                        }

                    }

                    boolean numfirst = false;
                    boolean num1 = false;
                    boolean num2 = false;

                            if( isNumber( string.substring( 0 , 1 ) ) ){
                                num1 = true;
                            }
                    String letters = "abcdfghijklmnopqrstuvwzyz";
                    char[] c1 = letters.toCharArray();
                    for( int i = 0; i < c1.length; i++ ){
                        if( string.contains( String.valueOf( c1[i]) ) ){
                            num2 = true;
                        }
                    }
                    if( num1 == true && num2 == true ){
                        numfirst = true;
                    }


                    boolean letterorno = false;
                    String token2 = string;
                    float value2;
                    try
                    {
                        value2 = Float.parseFloat( token2 );
                        for( int i = 0; i < stringAmount; i++ )
                        {
                            String word = string.substring(0 + i, 1 + i);
                            if( letters.contains( word ) )
                            {
                                letterorno = true;
                            }

                        }

                    }
                    catch ( NumberFormatException nfe )
                    {

                    }
                    if( notvariable )
                    {
                        
                        throw new Exception( "Token Value is " + " ' " + string
                                + " ' " + " is not a valid token." +
                                " Too many operators." +
                                " and additional characters, operators must be seperate. Check Tokens Spacing" );
                    } else if( letterorno )
                    {
                        throw new Exception( "Token Value is " + " ' " + string
                                + " ' " + " is not a valid token." +
                                " Token must follow Java Identifier Rules"
                                + "and only e can be in numbers"
                        );
                    } else if( numfirst ){
                        throw new Exception( "Token Value is " + " ' " + string
                                + " ' " + " is not a valid token." +
                                " Tokens must follow Java Identifer Rules");

                    }
                    else
                    {
                        throw new Exception( "Token Value is " + " ' " + string
                                + " ' " + " is not a valid token" );
                    }
                }

            }


        }




    }

    public static boolean isNumber(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}