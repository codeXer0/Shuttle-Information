/**
 * Gui ��� Ŭ�����Դϴ�.
 *
 * @author �̵��� 
 * @version v1.0.0
 *
 *
 * {@code @created} 2024-12-18
 * {@code @lastModified} 2024-12-21
 *
 * {@changelog}
 * <ul>
 *   <li>2024-12-18: ���� ����</li>
 * </ul>
 */

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GuiPack extends JFrame{
	public GuiPack() {
		this.setTitle("û�ִ��б� ��Ʋ����");
		this.setSize(500, 500);
		
		ImageIcon iconCJU = new ImageIcon("src/images/cju.png");
		this.setIconImage(iconCJU.getImage());
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
