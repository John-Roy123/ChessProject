package Chess.Engine.Board;

public class BoardUtils {

    public static final boolean[] FIRST_COLUMN = null;
    public static final boolean[] SECOND_COLUMN = null;
    public static final boolean[] SEVENTH_COLUMN = null;
    public static final boolean[] EIGHTH_COLUMN = null;

private BoardUtils(){
    throw new RuntimeException("This class cannot be instatiated");
}

    public static boolean isValidCoordinate(int coordinate){
        return (coordinate < 64 && coordinate >= 0);
    }
}
