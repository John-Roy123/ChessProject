package Chess.Engine;

import Chess.Engine.Player.*;

public enum Team {
    WHITE
 {
        @Override
        public int getDirection() {
            // TODO Auto-generated method stub
            return -1;
        }

        @Override
        public boolean isWhite() {
            // TODO Auto-generated method stub
           return true;
        }

        @Override
        public boolean isBlack() {
            // TODO Auto-generated method stub
            return false;
        }
        @Override
        public Player choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer){
            return whitePlayer;
        }
    },  
    BLACK
 {
        @Override
        public int getDirection() {
            // TODO Auto-generated method stub
            return 1;
        }

        @Override
        public boolean isWhite() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean isBlack() {
            // TODO Auto-generated method stub
            return true;
        }
        @Override
        public Player choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer){
            return blackPlayer;
        }
    };
   public abstract int getDirection();
   public abstract boolean isWhite();
   public abstract boolean isBlack();
   public abstract Player choosePlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer);
   
}
