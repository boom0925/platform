package com.dfzq.monday.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

	public static void main(String[] args) {
		int a[]=new int[6];
		a[0]=1;
		a[1]=2;
		a[2]=3;
		a[3]=4;
		a[4]=5;
		a[5]=6;
		List<Integer> list=Arrays.asList(1,2,3,4,5,6);
		List<Integer> evens = new ArrayList<>();
		for (final Integer num : a) {
		    if (num % 2 == 0) {
		        evens.add(num);
		    }
		}
		List<Integer> no = list.stream().filter(num -> num % 2 == 0).collect(Collectors.toList());
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
		List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
//		List<int[]> evens = int[].stream().filter(num -> num % 2 == 0).collect(Collectors.toList());
		for (int i = 0; i < filtered.size(); i++) {
			System.out.println(filtered.get(i));
		}
		for (int i = 0; i < no.size(); i++) {
			System.out.println(no.get(i));
		}
		no.forEach(num->evens.add(num));
		for (int i = 0; i < evens.size(); i++) {
			System.out.println(evens.get(i));
		}
		//ArrayList<int[]> arrayList = new ArrayList<int[]>(Arrays.asList(a));
		
	}

}
