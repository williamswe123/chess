import javax.swing.*;
// player input1
public class in1 implements moveInterface{


    @Override
    public String Move() {
        return JOptionPane.showInputDialog("move1");
    }
}
