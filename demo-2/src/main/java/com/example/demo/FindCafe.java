package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class State {
	int friend;
	int loc;
	
	public State(int friend, int loc) {
		this.friend = friend;		
		this.loc = loc;
	}
	
	public int hashCode() {
		return friend*loc;
	}
	
	public boolean equals(Object state) {
		return this.friend==((State)state).friend && this.loc==((State)state).loc; 
	}
}
public class FindCafe {

	public static void main(String[] args) {		
		int[] friends = {1,5,2};
		int[] cafes = {2,4,6,3};
		int[][] edges = {{1,2},{2,3},{3,4},{4,5}};		
		Map<Integer, List<Integer>> adjList = new HashMap();
		for(int i=0;i<edges.length;i++) {
			adjList.putIfAbsent(edges[i][0],new ArrayList());
			adjList.putIfAbsent(edges[i][1],new ArrayList());
			adjList.get(edges[i][0]).add(edges[i][1]);
			adjList.get(edges[i][1]).add(edges[i][0]);
		}		
		System.out.println(adjList);
		Queue<State> queue = new LinkedList();
		Set<State> visited = new HashSet();
		Map<Integer, Set<Integer>> cafeFriendsMap = new HashMap();
		for(int i=0;i<cafes.length;i++) {
			cafeFriendsMap.put(cafes[i], new HashSet());
			adjList.putIfAbsent(cafes[i],new ArrayList());
		}
		for(int i=0;i<friends.length;i++) {
			adjList.putIfAbsent(friends[i],new ArrayList());
			State state = new State(friends[i], friends[i]);
			if(cafeFriendsMap.containsKey(friends[i])) 
				cafeFriendsMap.get(friends[i]).add(friends[i]);
			visited.add(state);
			queue.add(state);
		}
		while(!queue.isEmpty()) {
			State temp = queue.poll();
			if(cafeFriendsMap.containsKey(temp.loc)) {
				cafeFriendsMap.get(temp.loc).add(temp.friend);
				if(cafeFriendsMap.get(temp.loc).size()==friends.length) {
					System.out.println(temp.loc);
					return;
				}				
			}
			for(int i=0;i<adjList.get(temp.loc).size();i++) {
				State newState = new State(temp.friend, adjList.get(temp.loc).get(i));
				if(!visited.contains(newState)) {
					visited.add(newState);
					queue.add(newState);
				}
			}
		}
		System.out.println(-1);
	}

}
