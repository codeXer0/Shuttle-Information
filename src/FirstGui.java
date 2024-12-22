/**
 * 프로그램 메인 홈 화면 & 각 버튼 클릭 시 생성되는 패널 안의 기능들과 GUI 클래스입니다.
 *
 * @author DongJae Lee 
 * @version v1.0.0
 * @since v1.0.0
 *
 *
 * {@code @created} 2024-12-18
 * {@code @lastModified} 2024-12-23
 *
 *
 * {@changelog}
 * <ul>
 *   <li>2024-12-21 : 최초 생성, 초기화면 기본 GUI 구현</li>
 *   <li>2024-12-22 : CampusShuttle 패널에 버스 이동 애니메이션 추가 및 노선도 구현</li>
 *   <li>2024-12-22 : 모든 버튼 기능 정상화, 노선도 수정, 이모티콘 기반 버스 사용</li>
 *   <li>2024-12-23 : 버스가 종점에서 반대로 왕복 이동 가능케 수정</li>
 *   <li>2024-12-23 : 버스 시간표 파일 읽기 및 JTable 표시 기능 추가</li>
 * </ul>
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FirstGui extends JFrame {
    JButton btnCampusShuttle, btnInfo, btnSchoolBus, btnBack1, btnBack2, btnBack3;
    JLabel cjubusA, cjubusB;
    int busAPosition = 30, busBPosition = 970; // 초기 위치
    int busADirection = 10, busBDirection = -10; // 이동 방향

    public FirstGui() {
        this.setTitle("청주대학교 셔틀정보");
        this.setSize(1080, 550);
        this.setLayout(new CardLayout());

        showMainScreen();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    void showMainScreen() {
        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);

        // Buttons
        btnCampusShuttle = new JButton("셔틀버스 현황");
        btnInfo = new JButton("버스 시간표");
        btnSchoolBus = new JButton("통학 셔틀");

        btnCampusShuttle.setBounds(100, 200, 200, 50);
        btnInfo.setBounds(400, 200, 200, 50);
        btnSchoolBus.setBounds(700, 200, 200, 50);

        mainPanel.add(btnCampusShuttle);
        mainPanel.add(btnInfo);
        mainPanel.add(btnSchoolBus);

        // Sub Panels
        JPanel campusShuttlePanel = createCampusShuttlePanel();
        JPanel shuttleInfoPanel = createInfoPanel();
        JPanel schoolBusPanel = createSchoolBusPanel();

        this.add(mainPanel, "Main");
        this.add(campusShuttlePanel, "CampusShuttle");
        this.add(shuttleInfoPanel, "ShuttleInformation");
        this.add(schoolBusPanel, "SchoolBus");

        CardLayout cardLayout = (CardLayout) this.getContentPane().getLayout();

        btnCampusShuttle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "CampusShuttle");
            }
        });

        btnInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "ShuttleInformation");
            }
        });

        btnSchoolBus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "SchoolBus");
            }
        });
    }

    JPanel createCampusShuttlePanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);

        // Back Button
        btnBack1 = new JButton("이전");
        btnBack1.setBounds(10, 10, 80, 40);
        panel.add(btnBack1);

        CardLayout cardLayout = (CardLayout) this.getContentPane().getLayout();
        btnBack1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "Main");
            }
        });

        // 노선도 선과 점 그리기
        JLabel routeLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLUE);

                // 정류장의 X 좌표 배열
                int[] stopsX = {30, 230, 430, 630, 830, 1030 };

                // 위쪽 노선
                g.drawLine(stopsX[0], 150, stopsX[stopsX.length - 1], 150);
                for (int x : stopsX) {
                    g.fillOval(x - 5, 145, 10, 10); // 위쪽 점
                }

                // 아래쪽 노선
                g.drawLine(stopsX[0], 350, stopsX[stopsX.length - 1], 350);
                for (int x : stopsX) {
                    g.fillOval(x - 5, 345, 10, 10); // 아래쪽 점
                }
            }
        };
        routeLabel.setBounds(0, 0, 1100, 600);
        panel.add(routeLabel);

        // 정류장 추가 (위쪽 노선)
        String[] stopsTop = {"학교 정문", "중앙도서관", "보건대학", "학생회관", "예술대학", "기숙사"};
        int[] stopsX = {30, 230, 430, 630, 830, 1030};
        for (int i = 0; i < stopsTop.length; i++) {
            JLabel stopLabel = new JLabel(stopsTop[i], SwingConstants.CENTER);
            stopLabel.setBounds(stopsX[i] - 50, 120, 100, 30);
            panel.add(stopLabel);
        }

        // 정류장 추가 (아래쪽 노선)
        String[] stopsBottom = {"기숙사", "예술대학", "학생회관", "보건대학", "중앙도서관", "학교 정문"};
        for (int i = 0; i < stopsBottom.length; i++) {
            JLabel stopLabel = new JLabel(stopsBottom[i], SwingConstants.CENTER);
            stopLabel.setBounds(stopsX[i] - 50, 320, 100, 30);
            panel.add(stopLabel);
        }

        // 버스 이모티콘 추가
        cjubusA = new JLabel("🚌");
        cjubusB = new JLabel("🚌");

        cjubusA.setBounds(busAPosition, 125, 40, 40); // 위쪽 버스 초기 위치
        cjubusB.setBounds(busBPosition, 325, 40, 40); // 아래쪽 버스 초기 위치

        panel.add(cjubusA);
        panel.add(cjubusB);

        // 애니메이션 타이머
        Timer timer = new Timer(300, new ActionListener() { // 속도를 느리게 설정
            @Override
            public void actionPerformed(ActionEvent e) {
                // 버스 A 이동
                busAPosition += busADirection;
                if (busAPosition >= 1030 || busAPosition <= 30) busADirection *= -1;
                cjubusA.setBounds(busAPosition, 125, 40, 40);

                // 버스 B 이동
                busBPosition += busBDirection;
                if (busBPosition <= 30 || busBPosition >= 1030) busBDirection *= -1;
                cjubusB.setBounds(busBPosition, 325, 40, 40);
            }
        });
        timer.start();

        return panel;
    }

    JPanel createInfoPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);

        // Back Button
        btnBack2 = new JButton("이전");
        btnBack2.setBounds(10, 10, 80, 40);

        
        btnBack2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
                cardLayout.show(getContentPane(), "Main");
            }
        });

        // ComboBox for selecting buses
        String[] busOptions = {"청대버스A", "청대버스B"};
        JComboBox<String> busSelector = new JComboBox<>(busOptions);

        // Table for displaying bus schedules
        String[] columnNames = {"시간"};
        JTable scheduleTable = new JTable(new Object[0][1], columnNames);
        JScrollPane tableScrollPane = new JScrollPane(scheduleTable);

        busSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedBus = (String) busSelector.getSelectedItem();
                TimeTable(scheduleTable, selectedBus);
            }
        });

        // Top panel containing the combo box
        JPanel topPanel = new JPanel();
        topPanel.add(busSelector);
        topPanel.add(btnBack2);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void TimeTable(JTable table, String busName) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/shuttleTime.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(busName)) {
                    String[] times = line.split(",")[1].trim().split(" ");
                    Object[][] data = new Object[times.length][1];
                    for (int i = 0; i < times.length; i++) {
                        data[i][0] = times[i];
                    }
                    table.setModel(new DefaultTableModel(data, new String[]{"시간"}));
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    JPanel createSchoolBusPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.LIGHT_GRAY);
        btnBack3 = new JButton("이전");
        btnBack3.setBounds(10, 10, 80, 40);
        
        btnBack3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
                cardLayout.show(getContentPane(), "Main");
            }
        });

        panel.add(btnBack3);

        JLabel label = new JLabel("통학 셔틀 정보 화면", SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);

        return panel;
    }

    public static void main(String[] args) {
        new FirstGui();
    }
}
