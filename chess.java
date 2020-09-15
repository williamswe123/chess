import javax.swing.*;
import java.util.ArrayList;


public class chess{

    static ArrayList<piece> blacks = new ArrayList<piece>();
    static ArrayList<piece> whites = new ArrayList<piece>();

    //sett up pieces for black and white
    public static void setup(){
        // white
        for(int i=1; i< 9;i++)
            whites.add(new piece("pawn","white",i,2,false));
        whites.add(new piece("rook","white",1,1,false));
        whites.add(new piece("rook","white",8,1,false));
        whites.add(new piece("horse","white",2,1,false));
        whites.add(new piece("horse","white",7,1,false));
        whites.add(new piece("bishop","white",3,1,false));
        whites.add(new piece("bishop","white",6,1,false));
        whites.add(new piece("queen","white",4,1,false));
        whites.add(new piece("king","white",5,1,false));

        // black
        for(int i=0; i< 9;i++)
            blacks.add(new piece("pawn","black",i,7,false));
        blacks.add(new piece("rook","black",1,8,false));
        blacks.add(new piece("rook","black",8,8,false));
        blacks.add(new piece("horse","black",2,8,false));
        blacks.add(new piece("horse","black",7,8,false));
        blacks.add(new piece("bishop","black",3,8,false));
        blacks.add(new piece("bishop","black",6,8,false));
        blacks.add(new piece("queen","black",4,8,false));
        blacks.add(new piece("king","black",5,8,false));
    }

    public static void main(String[] args){
  ///////////////////// game setup ////////////////////////////////////////
        setup();                                                         //
                                                                         //
        board b = new board();                                           //
        JFrame f = new JFrame();                                         //
        f.add(b);                                                        //
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);       //
        f.setSize(855,875);                                  //
        f.setVisible(true);                                              //
                                                                         //
        String whiteIn = "in1";//JOptionPane.showInputDialog("White");           //
        String blackIn = "in2";//JOptionPane.showInputDialog("Black");           //
                                                                         //
        ///////////////////////////////////////////////////////////////////

    ///////////////////// Game on /////////////////////////////////////////
        while(true){                                                     //
                                                                         //
                                                                         //
            movePiece(whiteIn,"white");                             //
            b.repaint();                                                 //
            b.revalidate();                                              //
            System.out.println("----------------");                      //
            movePiece(blackIn,"black");                             //
            b.repaint();                                                 //
            b.revalidate();                                              //
            System.out.println("----------------");                      //
        }                                                                //
                                                                         //
        ///////////////////////////////////////////////////////////////////

    }

    static moveInterface m;
    public static void movePiece(String clazz, String side){
        m = classify(clazz);

        piece p = null;

        while(p == null) {
            String InputString = m.Move();
            int[][] move = move = checkMove(InputString);
            if(move != null) {
                System.out.println(move[0][0]+" "+move[0][1]+"  " + move[1][0]+" "+move[1][1]);
                p = findPiece(move[0][0], move[0][1]);
                if(p==null)
                    System.out.println("piece not found");
                else {

                    if (side == "white") {
                        int ip = whites.indexOf(p);
                        if (ip > -1 && whites.get(ip).canGo(move[1][0], move[1][1])) {

                            piece o = findPiece(move[1][0], move[1][1]);
                            if (o != null) {
                                blacks.remove(o);
                            }

                            whites.set(ip, new piece(p.type, p.side, move[1][0], move[1][1],true)); // update piece
                        } else {
                            System.out.println("move not ok");
                            p = null;
                        }
                    } else if (side == "black") {
                        int ip = blacks.indexOf(p);

                        if (ip > -1 && blacks.get(ip).canGo(move[1][0], move[1][1])) {

                            piece o = findPiece(move[1][0], move[1][1]);
                            if (o != null) {
                                whites.remove(o);
                            }

                            blacks.set(ip, new piece(p.type, p.side, move[1][0], move[1][1],true));  // update piece
                        } else {
                            System.out.println("move not ok");
                            p = null;
                        }

                    }
                }
            }
        }
    }

    // finds and returns the piece at x & y
    // returns null if no piece att x & y
    public static piece findPiece(int x, int y){
        for(int i = 0; i< whites.size(); i++){
            if(whites.get(i).x == x && whites.get(i).y == y){
                return whites.get(i);
            }
        }
        for(int i = 0; i< blacks.size(); i++){
            if(blacks.get(i).x == x && blacks.get(i).y == y){
                return blacks.get(i);
            }
        }
        return null;
    }

    // check string if it is [ char1 int1 char2 int2 ]
    // if correct returns a int[][] == { {char1,int1} {char2,int2} }
    public static int[][] checkMove(String input){
        char[] inChar = input.toCharArray();
        if(inChar.length!=4)
            return null;
        if((int)inChar[0] < 97 || (int)inChar[0] > 104 ||
           (int)inChar[2] < 97 || (int)inChar[2] > 104 ||
           (int)inChar[1] < 48 || (int)inChar[1] > 56  ||
           (int)inChar[3] < 48 || (int)inChar[3] > 56) {
            return null;
        }
        int[][] out = new int[2][2];

        // current pos of piece
        out[0][0] = inChar[0]-96;
        out[0][1] = inChar[1]-48;
        // target pos of piece
        out[1][0] = inChar[2]-96;
        out[1][1] = inChar[3]-48;

        return out;
    }


    // convert string to moveInterface
    public static moveInterface classify(String whichClass) {
        try {
            Class clazz = Class.forName(whichClass);
            return (moveInterface) clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("Interface not found");
        return null;
    }

}
