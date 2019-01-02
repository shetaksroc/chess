package com.example.entity;

import java.io.IOException;

public class Board {

    public Piece[][] board = new Piece[8][8];

    public Board(){
        this.init();
    }

    private void init(){
        for(int x = 0; x<board.length; x++){
            for(int y = 0; y<board[0].length; y++){
                board[x][y] = null;
            }
        }

        // White pawns
        for(int x=0; x<8; x++){
            board[1][x] = new Pawn("white");
        }

        // Black pawns
        for(int x=0; x<8; x++){
            board[6][x] = new Pawn("black");
        }

        //Rooks
        board[0][0] = new Rook("white");
        board[0][7] = new Rook("white");
        board[7][7] = new Rook("black");
        board[7][0] = new Rook("black");

        //Knights
        board[0][1] = new Knight("white");
        board[0][6] = new Knight("white");
        board[7][6] = new Knight("black");
        board[7][1] = new Knight("black");

        //Bishops
        board[0][2] = new Bishop("white");
        board[0][5] = new Bishop("white");
        board[7][2] = new Bishop("black");
        board[7][5] = new Bishop("black");

        //Queens
        board[0][3] = new Queen("white");
        board[7][3] = new Queen("black");

        //Kings
        board[0][4] = new King("white");
        board[7][4] = new King("black");

    }


    public boolean isCheck(String color){
        int[] kingPos = getKingPos(color);
        int row = kingPos[0];
        int col = kingPos[1];

        for(int x = 0; x<board.length; x++){
            for(int y = 0; y<board[0].length; y++){
                if(board[x][y] != null){
                    if(board[x][y].validateMove(board, x, y, row, col) && !board[x][y].getColor().equals(color)){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void makeMove(String move, String color, boolean actuallyMove) throws IOException{
        int[] moveArray = parseInput(move);
        makeMove(color, actuallyMove, moveArray);
    }

    private void makeMove(String color, boolean actuallyMove, int[] moveArray) throws IOException {
        if(board[moveArray[0]][moveArray[1]] == null){
            throw new IOException();
        }

        if(!board[moveArray[0]][moveArray[1]].getColor().equals(color)){
            throw new IOException();
        }

        if(board[moveArray[2]][moveArray[3]] != null){
            if(board[moveArray[2]][moveArray[3]].getColor().equals(color)){
                throw new IOException();
            }
        }

        if(board[moveArray[0]][moveArray[1]].validateMove(board, moveArray[0], moveArray[1], moveArray[2], moveArray[3])){
            if(actuallyMove){
                board[moveArray[2]][moveArray[3]] = board[moveArray[0]][moveArray[1]];
                board[moveArray[0]][moveArray[1]] = null;
            }
        }else{
            throw new IOException();
        }
    }

    private static int[] parseInput(String move){
        int[] returnArray = new int[4];
        String[] split = move.split(" ");
        returnArray[1] = charToInt(Character.toLowerCase(split[0].charAt(0)));
        returnArray[0] = Integer.parseInt(move.charAt(1) + "") - 1;
        returnArray[3] = charToInt(Character.toLowerCase(split[1].charAt(0)));
        returnArray[2] = Integer.parseInt(split[1].charAt(1) + "") - 1;
        return returnArray;

    }

    public static int charToInt(char ch){
        switch(ch){
            case 'a': return 0;
            case 'b': return 1;
            case 'c': return 2;
            case 'd': return 3;
            case 'e': return 4;
            case 'f': return 5;
            case 'g': return 6;
            case 'h': return 7;
            default: return 8;
        }
    }

    private int[] getKingPos(String color){
        int row = 0, col = 0;

        for(int x = 0; x<board.length; x++){
            for(int y = 0; y<board[0].length; y++){
                if(board[x][y] != null){
                    if(board[x][y].getClass().isInstance(new King("white")) && board[x][y].getColor().equals(color)){
                        row = x;
                        col = y;
                    }
                }
            }
        }
        int[] returnArray = new int[2];
        returnArray[0] = row;
        returnArray[1] = col;

        return returnArray;

    }

    public String toString(){
        String string = "";
        for(Piece[] pieces: board){
            for(Piece piece: pieces){
                if(piece==null){
                    string += "  ";
                }else{
                    string += piece;
                }
                string += " ";
            }
            string += "\n";
        }

        String reverseString = "";

        reverseString += "  a  b  c  d  e  f  g  h \n";
        String[] stringSplit = string.split("\n");
        for(int x = stringSplit.length-1; x >= 0; x--){
            reverseString += x+1 + " " + stringSplit[x] + "\n";
        }

        return reverseString;
    }

    public boolean checkMateCheck(String move, String color) {
        Piece[][] oldBoard = board.clone();
        for(int x = 0; x<board.length; x++){
            for(int y = 0; y<board[0].length; y++){
                for(int w = 0; w<board.length; w++){
                    for(int z = 0; z<board[0].length; z++){
                        try{
                            if(board[x][y] != null){
                                if(board[x][y].getColor().equals(color)){
                                    int[] moveArray={x, y, w, z};
                                    makeMove( board[x][y].getColor(), false, moveArray);
                                    board = oldBoard;
                                    return true;
                                }
                            }
                            board = oldBoard;
                        } catch(Exception e){
                            board = oldBoard;
                        }
                    }
                }
            }
        }

        board = oldBoard;
        return false;
    }
}
