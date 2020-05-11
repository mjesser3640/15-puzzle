
public class Tile {
	
	private int val;
	
	private int width;
	private int height;
	
	private Board board;
	
	public Tile(Board b, int value, int w, int h) {
		this.val = value;
		board = b;
		width = w;
		height = h;
	}
	
	public String toString() {
		return "|"+String.format("%02d", val)+"|";
	}
	
	public int getVal() {
		return val;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

}
