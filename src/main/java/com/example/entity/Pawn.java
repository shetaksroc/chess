package com.example.entity;

public class Pawn extends Piece {

    public String color;
    public boolean hasMoved;

    public Pawn(String color){
        this.color = color;
        this.hasMoved = false;
    }

    public boolean validateMove(Piece[][] board, int currentRow, int currentCol, int newRow, int newCol) {

        if(color.equals("white")){
            if(currentRow > newRow){
                return false;
            }
        }else{
            if(newRow > currentRow){
                return false;
            }
        }

        if(currentCol == newCol){
            if(color.equals("white")){
                if(board[currentRow + 1][currentCol] != null){
                    return false;
                }
            }else{
                if(board[currentRow - 1][currentCol] != null){
                    return false;
                }
            }

            if(Math.abs(newRow - currentRow) > 2){
                return false;
            }
        }else{
           return false;
        }

        return true;
    }

    public String getColor(){
        return this.color;
    }

    public String toString(){
        return color.charAt(0) + "p";

    }

}
