package com.dfzq.monday.controller;

import java.util.Random;

public class TestBank{
	public static void main(String[] args) throws Exception{

		Bank bank = new Bank();
		Random random =new Random();
		for(int i = 0; i < 50; i++) {
			Thread th = new Thread(new Runnable() {
				public void run() {
					int from = random.nextInt(100);
					int to = random.nextInt(100);
					int money = 100;
					bank.transfer(from, to, money);
				}
			});
			th.start();
		}
		Thread.sleep(1000*8);
		bank.getListInfo();
	}
}
