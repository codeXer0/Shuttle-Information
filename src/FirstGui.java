/**
 * 프로그램 초기 화면 GUI 클래스입니다.
 *
 * @author DongJae Lee 
 * @version v1.0.0
 *
 *
 * {@code @created} 2024-12-18
 * {@code @lastModified} 2024-12-21
 *
 *
 * {@changelog}
 * <ul>
 *   <li>2024-12-21 : 최초 생성</li>
 * </ul>
 */

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class FirstGui extends JFrame{
	public JButton btnBusRoute, btnBusInformation;
	public FirstGui(){
		this.setTitle("청주대학교 셔틀정보");
		this.setSize(700, 500);
		
		ImageIcon iconCJU = new ImageIcon("src/images/cju.png");
		this.setIconImage(iconCJU.getImage());
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
