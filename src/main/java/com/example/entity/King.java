package com.example.entity;

public class King extends Piece{
	
	public String color;

	public King(String color){
		this.color = color;
	}

	@Override
	public boolean validateMove(Piece[][] board, int currentRow, int currentCol, int newRow, int newCol) {
		if(Math.abs(newRow - currentRow) > 1 || Math.abs(newCol - currentCol) > 1){
			return false;
		}

		if(board[newRow][newCol]!= null && board[newRow][newCol].getColor().equalsIgnoreCase(board[currentRow][currentCol].getColor())){
			return false;
		}
		return true;
	}
	
	public String getColor(){
		return this.color;
	}

	public String toString(){
		return color.charAt(0) + "K";	
	}
	
}
