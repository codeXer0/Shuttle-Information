/**
 * 프로그램 초기 화면 GUI 클래스입니다.
 *
 * @author DongJae Lee 
 * @version v1.0.0
 * @since v1.0.0
 *
 *
 * {@code @created} 2024-12-18
 * {@code @lastModified} 2024-12-21
 *
 *
 * {@changelog}
 * <ul>
 *   <li>2024-12-21 : 최초 생성, 초기화면 기본 GUI 구현</li>
 * </ul>
 */

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FirstGui extends JFrame {
	JButton btnCampusShuttle, btnInfo, btnSchoolBus, btnBack1, btnBack2, btnBack3;
	public FirstGui() {
		this.setTitle("청주대학교 셔틀정보");
		this.setSize(600, 400);
		this.setLayout(new CardLayout());
		
		ImageIcon iconCJU = new ImageIcon("src/images/cju.png");
		this.setIconImage(iconCJU.getImage());
			
		showMainScreen();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}
	/**
	 * 초기 화면에는 세개의 버튼이 보여집니다.
	 *	각 버튼을 작동 시, 셔틀버스 현황, 버스 정보 및 시간표, 통학버스 정보가 보여지는 패널로 넘어갑니다.
	 *	이전 버튼을 사용하여 초기 화면으로 되돌아 올 수 있습니다.
	 *
	 * {@code @created} 2024-12-21
	 * {@code @lastModified} 2024-12-22
	 *
	 *
	 * {@changelog}
	 * <ul>
	 *   <li>2024-12-21 : 메소드 최초 생성</li>
	 * </ul>
	 */
	void showMainScreen() {
		// Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);
        
        // Buttons
        btnCampusShuttle = new JButton("셔틀버스 현황");
        btnInfo = new JButton("버스 시간표");
        btnSchoolBus = new JButton("통학 셔틀");

        btnCampusShuttle.setBounds(80, 150, 120, 50);
        btnInfo.setBounds(240, 150, 120, 50);
        btnSchoolBus.setBounds(400, 150, 120, 50);

        mainPanel.add(btnCampusShuttle);
        mainPanel.add(btnInfo);
        mainPanel.add(btnSchoolBus);

        // Sub Panels
        JPanel campusShuttlePanel = new JPanel();
        campusShuttlePanel.setLayout(null);
        btnBack1 = new JButton("이전");
        btnBack1.setBounds(10, 10, 60, 40);
        campusShuttlePanel.add(btnBack1);
        
        JPanel shuttleInfoPanel = new JPanel();
        btnBack2 = new JButton("이전");
        shuttleInfoPanel.add(btnBack2);
        
        JPanel externalShuttlePanel = new JPanel();
        btnBack3 = new JButton("이전");
        externalShuttlePanel.add(btnBack3);
        
        campusShuttlePanel.setBackground(Color.WHITE);
        shuttleInfoPanel.setBackground(Color.WHITE);
        externalShuttlePanel.setBackground(Color.WHITE);

        
        this.add(mainPanel, "Main");
        this.add(campusShuttlePanel, "CampusShuttle");
        this.add(shuttleInfoPanel, "ShuttleInformation");
        this.add(externalShuttlePanel, "SchoolBus");

        CardLayout cardLayout = (CardLayout) this.getContentPane().getLayout();

        // 셔틀버스 현황 ActionListener
        btnCampusShuttle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "CampusShuttle");
            }
        });
        
        // 셔틀버스 시간표 ActionListener
        btnInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "ShuttleInformation");
            }
        });

        // 통학버스 정보 ActionListener
        btnSchoolBus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "SchoolBus");
            }
        });
        
        // Back 버튼 
        btnBack1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "Main");
            }
        });
        btnBack2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "Main");
            }
        });
        btnBack3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "Main");
            }
        });
	}
      	public static void main(String[] args) {
      		new FirstGui();
        }
}
