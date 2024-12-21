/**
 * ���α׷� �ʱ� ȭ�� GUI Ŭ�����Դϴ�.
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
 *   <li>2024-12-21 : ���� ����, �ʱ�ȭ�� �⺻ GUI ����</li>
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
		this.setTitle("û�ִ��б� ��Ʋ����");
		this.setSize(600, 400);
		this.setLayout(new CardLayout());
		
		ImageIcon iconCJU = new ImageIcon("src/images/cju.png");
		this.setIconImage(iconCJU.getImage());
			
		showMainScreen();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}
	/**
	 * �ʱ� ȭ�鿡�� ������ ��ư�� �������ϴ�.
	 *	�� ��ư�� �۵� ��, ��Ʋ���� ��Ȳ, ���� ���� �� �ð�ǥ, ���й��� ������ �������� �гη� �Ѿ�ϴ�.
	 *	���� ��ư�� ����Ͽ� �ʱ� ȭ������ �ǵ��� �� �� �ֽ��ϴ�.
	 *
	 * {@code @created} 2024-12-21
	 * {@code @lastModified} 2024-12-22
	 *
	 *
	 * {@changelog}
	 * <ul>
	 *   <li>2024-12-21 : �޼ҵ� ���� ����</li>
	 * </ul>
	 */
	void showMainScreen() {
		// Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);
        
        // Buttons
        btnCampusShuttle = new JButton("��Ʋ���� ��Ȳ");
        btnInfo = new JButton("���� �ð�ǥ");
        btnSchoolBus = new JButton("���� ��Ʋ");

        btnCampusShuttle.setBounds(80, 150, 120, 50);
        btnInfo.setBounds(240, 150, 120, 50);
        btnSchoolBus.setBounds(400, 150, 120, 50);

        mainPanel.add(btnCampusShuttle);
        mainPanel.add(btnInfo);
        mainPanel.add(btnSchoolBus);

        // Sub Panels
        JPanel campusShuttlePanel = new JPanel();
        campusShuttlePanel.setLayout(null);
        btnBack1 = new JButton("����");
        btnBack1.setBounds(10, 10, 60, 40);
        campusShuttlePanel.add(btnBack1);
        
        JPanel shuttleInfoPanel = new JPanel();
        btnBack2 = new JButton("����");
        shuttleInfoPanel.add(btnBack2);
        
        JPanel externalShuttlePanel = new JPanel();
        btnBack3 = new JButton("����");
        externalShuttlePanel.add(btnBack3);
        
        campusShuttlePanel.setBackground(Color.WHITE);
        shuttleInfoPanel.setBackground(Color.WHITE);
        externalShuttlePanel.setBackground(Color.WHITE);

        
        this.add(mainPanel, "Main");
        this.add(campusShuttlePanel, "CampusShuttle");
        this.add(shuttleInfoPanel, "ShuttleInformation");
        this.add(externalShuttlePanel, "SchoolBus");

        CardLayout cardLayout = (CardLayout) this.getContentPane().getLayout();

        // ��Ʋ���� ��Ȳ ActionListener
        btnCampusShuttle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "CampusShuttle");
            }
        });
        
        // ��Ʋ���� �ð�ǥ ActionListener
        btnInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "ShuttleInformation");
            }
        });

        // ���й��� ���� ActionListener
        btnSchoolBus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "SchoolBus");
            }
        });
        
        // Back ��ư 
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
