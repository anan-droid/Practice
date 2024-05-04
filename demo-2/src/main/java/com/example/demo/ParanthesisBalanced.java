package com.example.demo;

public class ParanthesisBalanced {
	
	public static void main(String[] args) {
		//String input = "{}{}{()[]()";
		//String input = "{}{}{()[](){}{}]]{}[]";
		String input = "{}{}(){}(){()[]()";
		int len=0;
		int maxLen=0;		
		if(input.length()<2) {
			System.out.println("input error");
			return;
		}
		//char[] ch = input.toCharArray();
		int size = input.length();
		char prev; 
		for(int i=1;i<size;) {
			prev = input.charAt(i-1);
			if(prev=='{' || prev=='[' || prev=='(') {
				char curr = input.charAt(i);
				if((curr=='}' && prev=='{') || (curr==']' && prev=='[') || (curr==')' && prev=='(')) {
					len +=2;					
					i+=2;
				}
				else {
					maxLen = Math.max(maxLen, len);
					len=0;
					i++;
				}
			}
			else {
				maxLen = Math.max(maxLen, len);
				len=0;
				i++;
			}						
		}
		maxLen = Math.max(maxLen, len);
		System.out.println(maxLen);
	}

}
