package ru.Yakovlev.Task7;

import java.math.BigInteger;
import java.util.Scanner;

public class Task7 {

    public static boolean MR(BigInteger n, BigInteger r){
        BigInteger d=n.subtract(BigInteger.ONE);
        BigInteger s=BigInteger.ZERO;
        while(d.mod(BigInteger.TWO)==BigInteger.ZERO){
            d=d.divide(BigInteger.TWO);
            s=s.add(BigInteger.ONE);
        }
        r=r.add(BigInteger.TWO);
        BigInteger a=BigInteger.TWO;
        boolean isprime=true;
        while(a.compareTo(r)==-1){
            BigInteger x=a;
            x=x.modPow(d, n);
            if(x.equals(BigInteger.ONE)||x.equals(n.subtract(BigInteger.ONE))){
                a=a.add(BigInteger.ONE);
                continue;
            }
            BigInteger i= BigInteger.ONE;
            while(i.compareTo(s)==-1){
                x=x.modPow(BigInteger.TWO,n);
                if(x.equals(BigInteger.ONE)){
                    isprime=false;
                    break;
                }
                if(x.equals(n.subtract(BigInteger.ONE))){
                    break;
                }
                i=i.add(BigInteger.ONE);
            }
            if(!x.equals(n.subtract(BigInteger.ONE))){
                isprime=false;
            }
            if(!isprime){
                break;
            }
            a=a.add(BigInteger.ONE);
        }
        return isprime;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число, с которого нужно начинасть: ");
        BigInteger n = scanner.nextBigInteger();
        System.out.print("Введите число тестов Миллера-Рабина: ");
        BigInteger r = scanner.nextBigInteger();
        while (!MR(n, r))
            n = n.add(BigInteger.ONE);
        System.out.println(n);
    }
}
