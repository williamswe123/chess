import javax.swing.*;
// player input 2
public class in2 implements moveInterface{

    @Override
    public String Move() {
        return JOptionPane.showInputDialog("move2");
    }
}
