

public class piece {

    public String type;
    public String side;
    public int x;
    public int y;
    public boolean moved;

    public piece(String type, String side, int x, int y, boolean mov){
        this.type = type;
        this.side = side;
        this.x = x;
        this.y = y;
        this.moved = mov;
    }



    public boolean canGo(int x, int y){
        // pawn
        if(type == "pawn"){

            if(side == "white"){
                if(y==this.y+1) {
                    if(this.x==x) // go strait
                        return true;
                    else if(this.x+1 == x || this.x-1 == x) { // attack
                        piece p = chess.findPiece(x,y);
                        if(p != null)
                            if(p.side=="black")
                                return true;
                    }
                }
                else if(y==this.y+2 && !this.moved){
                    piece p = chess.findPiece(x,y);
                    if(p == null)
                        return true;
                }
            }
            if(side == "black"){
                if(y==this.y-1) {
                    if(this.x==x) // go strait
                        return true;
                    else if(this.x+1 == x || this.x-1 == x) { //attack
                        piece p = chess.findPiece(x,y);
                        if(p != null)
                            if(p.side=="white")
                                return true;
                    }
                }
                else if(y==this.y-2 && !this.moved){
                    piece p = chess.findPiece(x,y);
                    if(p == null)
                        return true;
                }
            }
        }

        // rook & queen
        if(type == "rook" || type == "queen"){

            if(this.y == y){

                for(int i = this.x+1; i<x;){

                    piece p = chess.findPiece(i,y);

                    if(p != null) {
                        return false;
                    }
                    if(x>this.x)
                        i++;
                    else
                        i--;
                }

                piece p = chess.findPiece(x,y);
                if(p!=null)
                    if(p.side == this.side)
                        return false;

                return true;
            }
            else if(this.x == x){

                for(int i = this.y+1; i<y;){

                    piece p = chess.findPiece(x,i);
                    if(p != null)
                        return false;

                    if(y>this.y)
                        i++;
                    else
                        i--;
                }

                piece p = chess.findPiece(x,y);
                if(p!=null)
                    if(p.side == this.side)
                        return false;

                return true;
            }
        }

        // bishop & queen
        if(type == "bishop" || type == "queen"){
            if(this.x==x||this.y==y)
                return false;

            int xi=1,yi=1;
            if(this.x>x)
                xi=-1;
            if(this.y>y)
                yi=-1;

            int tx=this.x, ty=this.y;

            for(;  tx > 0 && tx < 9 && ty > 0 && ty < 9 ;  ){
                tx += xi;
                ty += yi;

                piece p = chess.findPiece(tx,ty);
                if(tx == x && ty == y){
                    if( p!=null && p.side==this.side)
                        return false;
                    return true;
                }
                if(p != null)
                    return false;

            }
        }

        // horse
        if(type == "horse"){

            if(((this.x + 2 == x || this.x -2 == x) && (this.y + 1 == y ||this.y - 1 == y))
               ||(this.y + 2 == y || this.y -2 == y) && (this.x + 1 == x ||this.x - 1 == x)){

                piece p = chess.findPiece(x,y);
                if( p!=null && p.side==this.side)
                    return false;
                return true;

                }
            }

        // king
        if(type == "king"){
            if(this.x == x && this.y == y){
                return false;
            }
            if( (this.x + 1 == x || this.x - 1 == x || this.x == x) && (this.y + 1 == y || this.y - 1 == y || this.y == y)){

                piece p = chess.findPiece(x,y);
                if( p!=null && p.side==this.side)
                    return false;
                if(this.side == "white"){
                    for( piece a : chess.blacks){
                        if(a.canGo(x,y)) {
                            System.out.println("("+ a.type +")");
                            return false;
                        }

                    }
                    return true;
                }
                if(this.side == "black"){
                    for( piece a : chess.whites){
                        if(a.canGo(x,y))
                            return false;

                    }
                }
            }
        }



        



        return false;
    }



}
