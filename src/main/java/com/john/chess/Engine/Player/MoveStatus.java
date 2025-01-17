package com.john.chess.Engine.Player;

public enum MoveStatus {
    DONE {
        @Override
        public boolean isDone() {
            // TODO Auto-generated method stub
            return true;
        }
    },
    ILLEGAL_MOVE{
        @Override
        public boolean isDone() {return false;
        }
    },
    CHECKING_MOVE{
        @Override
        public boolean isDone(){
            return false;
        }
    };

    public abstract boolean isDone();
}
