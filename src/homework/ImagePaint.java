package homework;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImagePaint extends Paint{
	private static final long serialVersionUID = 1L;

	BufferedImage bufferedImage;

	//@Override // 重绘方法
	public void paint(Graphics g) {
		g.drawImage(bufferedImage, 0, 0, null);
	}

	// 封装 BufferedImage的set方法
	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}
}
