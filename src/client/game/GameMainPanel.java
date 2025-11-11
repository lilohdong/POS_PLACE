package client.game;

import javax.swing.*;
import java.awt.*;

public class GameMainPanel extends JPanel {
    public GameMainPanel() {
        setSize(1016, 832);
        setLayout(new BorderLayout(0, 7));

        GameMainPanel TitlePanel = new GameMainPanel();
        add(TitlePanel,BorderLayout.NORTH);
    }
}
