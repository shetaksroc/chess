package com.example.entity;

public class Queen extends Piece{

	public String color;
	
	public Queen(String color){
		this.color = color;
	}

	@Override
	public boolean validateMove(Piece[][] board, int currentRow, int currentCol, int newRow, int newCol) {
		return new Rook(color).validateMove(board, currentRow, currentCol, newRow, newCol) || new Bishop(color).validateMove(board, currentRow, currentCol, newRow, newCol);
	}
	
	public String getColor(){
		return this.color;
	}
	
	public String toString(){
		return color.charAt(0) + "Q";
		
	}
	
}
