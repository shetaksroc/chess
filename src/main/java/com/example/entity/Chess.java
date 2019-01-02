package com.example.entity;

import java.io.IOException;
import java.util.Scanner;
public class Chess {

    public static void main(String[] args) {

        Board board = new Board();
        String color = "white";
        while(true){

            System.out.println(board);
            System.out.println(color + " make a move: ");
            Scanner sc = new Scanner(System.in);
            String move = String.valueOf(modifyMove(sc.nextLine()));

            try {
                board.makeMove(move, color, true);
            } catch (IOException e) {
                System.out.println("Invalid input!");
                continue;
            }

            color = changeColor(color);

            if(board.isCheck(color)){
                System.out.println(color + " is in check.");
                if(board.checkMateCheck(move, color)){
                    System.out.println("Checkmate");
                }
            }

        }

    }

    private static StringBuilder modifyMove(String move) {
        String[] split=move.trim().split(" ");
        StringBuilder sb=new StringBuilder();
        return sb.append(split[0].trim().charAt(1)).append(split[0].trim().charAt(0)).append(" ").append(split[1].trim().charAt(1)).append(split[1].trim().charAt(0));
    }

    public static String changeColor(String color){
        if(color.equals("white")){
            return "black";
        }
        return "white";
    }

}
