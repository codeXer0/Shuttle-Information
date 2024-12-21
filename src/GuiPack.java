/**
 * Gui 담당 클래스입니다.
 *
 * @author 이동재 
 * @version v1.0.0
 *
 *
 * {@code @created} 2024-12-18
 * {@code @lastModified} 2024-12-21
 *
 * {@changelog}
 * <ul>
 *   <li>2024-12-18: 최초 생성</li>
 * </ul>
 */

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GuiPack extends JFrame{
	public GuiPack() {
		this.setTitle("청주대학교 셔틀정보");
		this.setSize(500, 500);
		
		ImageIcon iconCJU = new ImageIcon("src/images/cju.png");
		this.setIconImage(iconCJU.getImage());
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
