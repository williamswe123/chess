import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class board extends JPanel{

    public board() {
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getPreferredSize() {
        return new Dimension(800,800);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
// paints game board
        for(int y = 0; y<8; y++){
            for(int x = 0; x<8 ;x++){
                if(y%2==1)
                    g.setColor(Color.lightGray);
                else
                    g.setColor(Color.WHITE);
                g.fillRect(x*100,y*100,100,100);

                x++;

                if(y%2==0)
                    g.setColor(Color.lightGray);
                else
                    g.setColor(Color.WHITE);
                g.fillRect(x*100,y*100,100,100);
            }
        }
        // paints text
        g.setColor(Color.BLACK);
        for(int i = 1; i<9; i++){
            // 1-8
            g.drawString(""+i,820,750-(i-1)*100);
            //a-h
            g.drawString(""+(char)(96+i),50+(i-1)*100,820);
        }

// paints white pieces
        for(int i = 0; i<chess.whites.size(); i++){
                Image img = null;
                try {
                    img = ImageIO.read(new File("src\\" + chess.whites.get(i).side + chess.whites.get(i).type + ".png"));
                    g.drawImage(img,(chess.whites.get(i).x - 1)*100, (8 - chess.whites.get(i).y)*100, 100, 100, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

 //paints black pieces
        for(int i = 0; i<chess.blacks.size(); i++) {
                Image img = null;
                try {
                    img = ImageIO.read(new File("src\\" + chess.blacks.get(i).side + chess.blacks.get(i).type + ".png"));
                    g.drawImage(img,(chess.blacks.get(i).x - 1)*100, (8 - chess.blacks.get(i).y)*100, 100, 100, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


    }
}
