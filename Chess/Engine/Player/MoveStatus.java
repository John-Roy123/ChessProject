package Chess.Engine.Player;

public enum MoveStatus {
    DONE {
        @Override
        boolean isDone() {
            // TODO Auto-generated method stub
            return true;
        }
    },
    ILLEGAL_MOVE{
        @Override
        boolean isDone() {return false;
        }
    },
    CHECKING_MOVE{
        @Override
        boolean isDone(){
            return false;
        }
    };

    abstract boolean isDone();
}
