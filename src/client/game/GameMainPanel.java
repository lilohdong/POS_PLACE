package client.game;

import javax.swing.*;
import java.awt.*;

public class GameMainPanel extends JPanel {
    public GameMainPanel() {
        initUI();
    }
    private void initUI() {
        setSize(1016, 832);
        setLayout(null);

        // 헤더 패널
        GameHeaderPanel headerPanel = new GameHeaderPanel();
        headerPanel.setBounds(0, 0, 1016, 74);
        add(headerPanel);

        // 인기게임 순위 패널
        PopularGameRankingPanel rankingPanel = new PopularGameRankingPanel();
        rankingPanel.setBounds(0, 74, 269, 758);
        add(rankingPanel);

        // 메인 게임 통계 패널
        MainGameStatistics mainStats = new MainGameStatistics();
        mainStats.setBounds(269, 74, 747, 758);
        add(mainStats);
    }
}
