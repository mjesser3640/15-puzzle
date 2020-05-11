import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Board extends JComponent implements KeyListener {
	
	private int rows;
	private int cols;
	
	private int emptyRow;
	private int emptyCol;
	
	private Tile[][] tiles;
	
	private boolean solved;
	
	
	public Board(int r, int c, int width, int height) {
		//this.setSize(width, height);
		
		rows = r;
		cols = c;
		
		int tileWidth = width/cols;
		int tileHeight = height/rows;
		
		tiles = new Tile[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (i != rows - 1 || j != cols - 1) {
					tiles[i][j] = new Tile(this, i * cols + j, tileWidth, tileHeight);
				}
			}
		}
		emptyRow = rows - 1;
		emptyCol = cols - 1;
		
		scrambleBoard();
		
		//print();
		solved = false;
	}
	
	private void scrambleBoard() { //make 500 random moves
		ArrayList<Character> moves = new ArrayList<Character>(4);
		Random r = new Random();
		for (int i = 0; i < 1; i++) {
			//get available moves
			if (emptyRow != 0) {
				moves.add('d');
			}
			if (emptyRow != rows - 1) {
				moves.add('u');
			}
			if (emptyCol != 0) {
				moves.add('r');
			}
			if (emptyCol != cols - 1) {
				moves.add('l');
			}
			int moveIndex = r.nextInt(moves.size());
			char move = moves.get(moveIndex);
			switch (move) {
			case 'u':
				moveUp();
				break;
			case 'd':
				moveDown();
				break;
			case 'l':
				moveLeft();
				break;
			case'r':
				moveRight();
			}
			moves.clear();
		}
		repaint();
	}
	
	//direction indicates the movement of whatever tile moves, not the empty space
	public void moveUp() {
		if (emptyRow < rows - 1) {
			tiles[emptyRow][emptyCol] = tiles[emptyRow + 1][emptyCol];
			emptyRow++;
			tiles[emptyRow][emptyCol] = null;
			repaint();
		}
	}
	
	public void moveDown() {
		if (emptyRow > 0) {
			tiles[emptyRow][emptyCol] = tiles[emptyRow - 1][emptyCol];
			emptyRow--;
			tiles[emptyRow][emptyCol] = null;
			repaint();
		}
	}
	
	public void moveLeft() {
		if (emptyCol < cols - 1) {
			tiles[emptyRow][emptyCol] = tiles[emptyRow][emptyCol + 1];
			emptyCol++;
			tiles[emptyRow][emptyCol] = null;
			repaint();
		}
	}
	
	public void moveRight() {
		if (emptyCol > 0) {
			tiles[emptyRow][emptyCol] = tiles[emptyRow][emptyCol - 1];
			emptyCol--;
			tiles[emptyRow][emptyCol] = null;
			repaint();
		}
	}
	
	public boolean isSolved() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (i != rows - 1 || j != cols - 1) {
					if (tiles[i][j] == null || tiles[i][j].getVal() != i * cols + j) {
						return false;
					}
				}
			}
		}
		solved = true;
		return true;
	}
	
	public void print() { //for debugging
		String underString = " ";
		for (int k = 0; k < cols; k++) {
			underString += "__  ";
		}
		System.out.println(underString);
		for (int i = 0; i < rows; i++) {
			String rowString = "";
			for (int j = 0; j < cols; j++) {
				//rowString = rowString + (tiles[i][j] == null ? "|  |" : tiles[i][j].toString());
				if (tiles[i][j] != null) {
					rowString += tiles[i][j].toString();
				}
				else {
					rowString += "|  |";
				}
			}
			System.out.println(rowString);
			System.out.println(underString);
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (tiles[i][j] != null) {
					Tile tile = tiles[i][j];
					g.setColor(Color.red);
					g.fillRect(j * tile.getWidth()+1, i * tile.getHeight()+1, tile.getWidth()-2, tile.getHeight()-2);
					g.setColor(Color.black);
					g.setFont(new Font("TimesRoman", Font.BOLD, 24));
					g.drawString(String.valueOf(tile.getVal() + 1), j * tile.getWidth() + tile.getWidth() / 2, i * tile.getHeight() + tile.getHeight() / 2);
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_UP:
			moveUp();
			//print();
			break;
		case KeyEvent.VK_DOWN:
			moveDown();
			//print();
			break;
		case KeyEvent.VK_LEFT:
			moveLeft();
			//print();
			break;
		case KeyEvent.VK_RIGHT:
			moveRight();
			//print();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}
