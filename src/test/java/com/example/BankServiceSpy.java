package com.example;

public class BankServiceSpy implements BankService{
    public boolean payCalled = false;
    @Override
    public void pay(String id, double amount) {
        payCalled = true;
    }
}
