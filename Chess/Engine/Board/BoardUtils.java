package Chess.Engine.Board;

public class BoardUtils {

private BoardUtils(){
    throw new RuntimeException("This class cannot be instatiated");
}

    public static boolean isValidCoordinate(int coordinate){
        return (coordinate < 64 && coordinate >= 0);
    }
}
