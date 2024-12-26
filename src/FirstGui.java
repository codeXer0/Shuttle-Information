import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 * 프로그램 메인 홈 화면 & 각 버튼 클릭 시 생성되는 패널 안의 기능들과 GUI 클래스입니다.
 *
 * @author DongJae Lee 
 * @version v1.0.0
 * @since v1.0.0
 *
 * {@code @created} 2024-12-18
 * {@code @lastModified} 2024-12-24
 *
 * {@changelog}
 * <ul>
 *   <li>2024-12-21 : 최초 생성, 초기화면 기본 GUI 구현</li>
 *   <li>2024-12-21 : showMainScreen() 메소드 생성</li>
 *   <li>2024-12-22 : 모든 버튼 기능 정상화</li>
 *   <li>2024-12-22 : backGroundImg() 메소드 생성</li>
 * </ul>
 */
public class FirstGui extends JFrame {
    JButton btnCampusShuttle, btnInfo, btnBack1, btnBack2;
    JLabel cjubusA, cjubusB;
    int busAPosition = 30, busBPosition = 970; // 초기 위치
    int busADirection = 10, busBDirection = -10; // 이동 방향
    ImageIcon Cjulogo;
    public FirstGui() {
        this.setTitle("청주대학교 셔틀정보");
        this.setSize(1080, 550);
        this.setLayout(new CardLayout());

        ImageIcon iconCJU = new ImageIcon("src/images/cju.png");
        this.setIconImage(iconCJU.getImage());

        this.setResizable(false); // 사이즈 수정 불가
        this.setLocationRelativeTo(null); // 화면에 출력시킬때 위치 가운데로 고정

        showMainScreen();
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * 홈 화면의 GUI를 구성하는 showMainScreen()메소드입니다.
     *
     * @author DongJae Lee 
     * @version v1.0.0
     * @since v1.0.0
     *
     * {@code @created} 2024-12-21
     * {@code @lastModified} 2024-12-23
     *
     * {@changelog}
     * <ul>
     *   <li>2024-12-21 : 최초 생성, 메인화면 기본 GUI 구현</li>
     *   <li>2024-12-23 : 버튼 디자인 수정</li>
     *   <li>2024-12-24 : 통학셔틀버스 버튼 삭제</li>
     *   <li>2024-12-24 : 우측 상단에 청주대학교 로고 생성</li>
     *   <li>2024-12-25 : 배경화면에 이미지&로그 삽입</li>
     * </ul>
     */
    void showMainScreen() {
        // Main Panel
        JPanel mainPanel = new JPanel(null);

        // 배경 이미지 추가
        ImageIcon originalBackground = new ImageIcon("src/images/청대정문.jpg"); // 원본 이미지 경로
        Image scaledImage = originalBackground.getImage().getScaledInstance(1080, 550, Image.SCALE_SMOOTH); // 프레임 크기에 맞게 조정
        ImageIcon scaledBackground = new ImageIcon(scaledImage);
        JLabel backgroundLabel = new JLabel(scaledBackground);
        backgroundLabel.setBounds(0, 0, 1080, 550); // 프레임 크기와 일치
        mainPanel.add(backgroundLabel);

        // 청대 로고 추가
        Cjulogo = new ImageIcon("src/images/청대로고.png");
        JLabel labelicon = new JLabel(Cjulogo);
        labelicon.setBounds(800, 10, 300, 50); // 우측 상단에 로고 배치
        mainPanel.add(labelicon);

        JLabel ProgramName = new JLabel("청주대학교 셔틀버스 앱");
        ProgramName.setBounds(30, 30, 200, 50);
        
        mainPanel.add(ProgramName);
       
        // 초기화면 버튼
        btnCampusShuttle = new JButton("셔틀버스 현황");
        btnInfo = new JButton("버스 시간표");

        btnCampusShuttle.setBackground(new Color(49, 98, 199));
        btnCampusShuttle.setForeground(Color.WHITE);
        btnCampusShuttle.setFont(new Font("Aharoni 굵게", Font.BOLD, 20));

        btnInfo.setBackground(new Color(49, 98, 199));
        btnInfo.setForeground(Color.WHITE);
        btnInfo.setFont(new Font("Aharoni 굵게", Font.BOLD, 20));

        btnCampusShuttle.setFocusPainted(false);
        btnInfo.setFocusPainted(false);

        // 버튼 위치 설정
        btnCampusShuttle.setBounds(250, 200, 200, 50);
        btnInfo.setBounds(650, 200, 200, 50);

        // 버튼 추가
        mainPanel.add(btnCampusShuttle);
        mainPanel.add(btnInfo);

        // Z-order 설정으로 배경 이미지를 뒤로 보냄
        mainPanel.setComponentZOrder(btnCampusShuttle, 0);
        mainPanel.setComponentZOrder(btnInfo, 0);
        mainPanel.setComponentZOrder(labelicon, 0);
        mainPanel.setComponentZOrder(backgroundLabel, mainPanel.getComponentCount() - 1); // 맨 아래 배치

        // 교내 셔틀버스 현황 패널 & 셔틀버스 정보 패널 생성
        JPanel campusShuttlePanel = createCampusShuttlePanel();
        JPanel shuttleSchedulePanel = createSchedulePanel();

        this.add(mainPanel, "Main");
        this.add(campusShuttlePanel, "CampusShuttle");
        this.add(shuttleSchedulePanel, "ShuttleSchedule");

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
                cardLayout.show(getContentPane(), "ShuttleSchedule");
            }
        });
    }



    /**
     * 셔틀버스 현황을 나타내는 createCampusShuttlePanel() 메소드입니다.
     *
     * @author DongJae Lee 
     * @version v1.0.0
     * @since v1.0.0
     *
     * {@code @created} 2024-12-22
     * {@code @lastModified} 2024-12-23
     *
     * {@changelog}
     * <ul>
     *   <li>2024-12-22 : 최초 생성</li>
     *   <li>2024-12-22 : CampusShuttle 패널에 버스 이동 애니메이션 추가 및 노선도 구현</li>
     *   <li>2024-12-22 : 노선도 수정, 이모티콘 기반 버스 사용</li>
     *   <li>2024-12-23 : 버스가 종점에서 반대로 왕복 이동 가능케 수정</li>
     *   <li>2024-12-24 : 우측 상단에 청주대학교 로고 생성</li>
     * </ul>
     */
    JPanel createCampusShuttlePanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);

        Cjulogo = new ImageIcon("src/images/청대로고.png");
        JLabel labelicon = new JLabel(Cjulogo);
        labelicon.setBounds(800, 10, 300, 50);
        
        panel.add(labelicon);
        // Back Button
        btnBack1 = new JButton("🏠");
        btnBack1.setBounds(10, 10, 80, 40);

        btnBack1.setBackground(new Color(49, 98, 199));
        btnBack1.setForeground(Color.WHITE);
        btnBack1.setFont(new Font("Aharoni 굵게", Font.BOLD, 20));

        btnBack1.setFocusPainted(false);

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
                int[] stopsX = {30, 230, 430, 630, 830, 1030};

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
    
    /**
     * 버스 시간표를 보여주는 createSchedulePanel() 메소드입니다.
     *
     * @author DongJae Lee 
     * @version v1.0.0
     * @since v1.0.0
     *
     * {@code @created} 2024-12-24
     * {@code @lastModified} 2024-12-24
     *
     * {@changelog}
     * <ul>
     *   <li>2024-12-21 : 최초 메소드 생성</li>
     *   <li>2024-12-24 : 셔틀버스 시간표 패널 새로 생성</li>
     *   <li>2024-12-24 : 시간표 텍스트파일을 읽어와 ArrayList에 저장, JTable 생성</li>
     *   <li>2024-12-24 : 버스 A, B별 시간표 변경 시 오류 수정</li>
     *   <li>2024-12-24 : 우측 상단에 청주대학교 로고 생성</li>
     * </ul>
     */
    JPanel createSchedulePanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);

        Cjulogo = new ImageIcon("src/images/청대로고.png");
        JLabel labelicon = new JLabel(Cjulogo);
        labelicon.setBounds(800, 10, 300, 50);
        
        panel.add(labelicon);
        // 홈으로 돌아가기 버튼
        JButton btnBack2 = new JButton("🏠");
        btnBack2.setBounds(10, 10, 80, 40);
        
        btnBack2.setBackground(new Color(49, 98, 199));
        btnBack2.setForeground(Color.WHITE);
        btnBack2.setFont(new Font("Aharoni 굵게", Font.BOLD, 20));

        btnBack2.setFocusPainted(false);
        
        panel.add(btnBack2);

        btnBack2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
                cardLayout.show(getContentPane(), "Main");
            }
        });

        // 제목 라벨
        JLabel titleLabel = new JLabel("< 셔틀버스 시간표 >", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Aharoni 굵게", Font.BOLD, 24));
        titleLabel.setBounds(300, 20, 480, 50);
        panel.add(titleLabel);

        // 콤보박스
        JComboBox<String> busSelector = new JComboBox<>(new String[]{"청대버스 A", "청대버스 B"});
        busSelector.setBounds(480, 80, 120, 30);
        panel.add(busSelector);

        // 테이블 및 모델 설정
        String[] columnNames = {"기점", "배차", "종점"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100, 140, 880, 300);
        panel.add(scrollPane);

        // 시간표 데이터를 저장할 ArrayList
        ArrayList<String[]> aBusSchedule = loadScheduleFromFile("src\\a_bus_schedule.txt");
        ArrayList<String[]> bBusSchedule = loadScheduleFromFile("src\\b_bus_schedule.txt");

        // 초기 데이터 로딩
        for (String[] row : aBusSchedule) {
            tableModel.addRow(row);
        }

        // 콤보박스 이벤트 리스너
        busSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.setRowCount(0); // 기존 데이터 삭제
                ArrayList<String[]> selectedSchedule = "청대버스 A".equals(busSelector.getSelectedItem()) ? aBusSchedule : bBusSchedule;
                for (String[] row : selectedSchedule) {
                    tableModel.addRow(row);
                }
            }
        });

        return panel;
    }


    /**
     * 텍스트 파일에서 시간표 데이터를 읽어오는 메소드입니다.
     * @param filename ( a_bus_schedule.txt & b_bus_schedule.txt )
     * @return 시간표 데이터를 담은 ArrayList
     * 
     *
     * {@code @created} 2024-12-24
     * {@code @lastModified} 2024-12-24
     * 
     * {@changelog}
     * <ul>
     *   <li>2024-12-24 : 최초 메소드 생성 및 기능 구현</li>
     * </ul>
     */
    private ArrayList<String[]> loadScheduleFromFile(String filename) {
        ArrayList<String[]> schedule = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(","); // 콤마로 구분된 데이터 파싱
                if (parts.length == 3) {
                    schedule.add(parts);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return schedule;
    }


    public static void main(String[] args) {
        new FirstGui();
    }
}
