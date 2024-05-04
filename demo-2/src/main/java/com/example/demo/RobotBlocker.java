package com.example.demo;

import java.io.*;
import java.util.*;

public class RobotBlocker {
    public static void main(String[] args) {
        System.out.println("Hello, World");
        int[][] location = {{'O','E','E','E','X'},{'E','O','X','X','X'},{'E','E','E','E','E'},{'X','E','O','E','E'},{'X','E','X','E','X'}};
        int[] query = {1,1,1,1};
        int[][] left = new int[location.length][location[0].length];
        int[][] right = new int[location.length][location[0].length];
        int[][] top = new int[location.length][location[0].length];
        int[][] bottom = new int[location.length][location[0].length];
        

        
        int dist=0;
        for(int i=0;i<location.length;i++) {            
            dist=0;
            for(int j=0;j<location[0].length;j++) {                                
                if(location[i][j]=='X') {
                    dist=0;
                    left[i][j]=dist;
                }
                else {
                    left[i][j]=++dist;
                }                                
            }
        }
        
        
        for(int i=0;i<location.length;i++) {            
            dist=0;
            for(int j=location[0].length-1;j>=0;j--) {                                
                if(location[i][j]=='X') {
                    dist=0;
                    right[i][j]=dist;
                }
                else {
                    right[i][j]=++dist;
                }                                
            }
        }
        
        
        for(int i=0;i<location[0].length;i++) {        
            dist=0;
            for(int j=0;j<location.length;j++) {                                
                if(location[j][i]=='X') {
                    dist=0;
                    top[j][i]=dist;
                }
                else {
                    top[j][i]=++dist;
                }                                
            }
        }
        
        for(int i=0;i<location[0].length;i++) {
            dist=0;
            for(int j=location.length-1;j>=0;j--) {                                
                if(location[j][i]=='X') {
                    dist=0;
                    bottom[j][i]=dist;
                }
                else {
                    bottom[j][i]=++dist;
                }                                
            }
        }
        
        List<List<Integer>> ans = new ArrayList();
        for(int i=0;i<location.length;i++) {
        	for(int j=0;j<location[0].length;j++) {
                if(location[i][j]=='O') {
                    if(left[i][j]>=query[0] && top[i][j]>=query[1] && bottom[i][j]>=query[2] && right[i][j]>=query[3]) {
                        List<Integer> points = new ArrayList();
                        points.add(i);
                        points.add(j);
                        ans.add(points);                        
                    }
                }    
            }
        }
        for(int i=0;i<ans.size();i++) {
            System.out.println(ans.get(i));
        }
    }
}