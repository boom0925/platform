package com.dfzq.monday.controller;

import java.util.concurrent.locks.ReentrantLock;

import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForAbstractMethod;

public class Bank {
	private int [] accounts = new int [100];
	private Object object = new Object();
	private ReentrantLock reentrantLock = new ReentrantLock();//重入锁
	public Bank(){            //给所有账户置初始值1000元
		for(int i=0;i<100;i++){
			accounts[i] = 1000;
		}
	}
	public void transfer(int from ,int to ,int money){
		reentrantLock.lock();//锁上进程
		System.out.println("操作前前者"+accounts[from]+"后者"+accounts[to]);
		accounts[from] -= money;
		try {
			Thread.sleep(1);//模拟柜台操作时间
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		accounts[to] += money;
		System.out.println("操作后前者"+accounts[from]+"后者"+accounts[to] +"流程"+from+"向"+to+"转了"+money+"元"+"总金额"+getTotalMoney());
		reentrantLock.unlock();
	}
	public int getTotalMoney(){
		int ans = 0;
		for(int i=0;i<100;i++){
			ans += accounts[i];
		}
		return ans;
	}
	
	public void getListInfo(){
		int count=0;
		for (int i = 0; i < accounts.length; i++) {
			count+=accounts[i];
			System.out.println("第"+i+"个数值是"+accounts[i]);
		}
		System.out.println("总数是"+count);
	}
}


