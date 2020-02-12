package com.sargeraswang.util.ExcelUtil;

public class Test {
    public static void main(String[] args) {

        int curage = 41;
        int curyear = 21;
        int payyears = 10;
        if(curage<18){
            System.out.println(1);
        }else if (curage>=18&&curyear<=payyears+1){
            System.out.println(2);
        }else {
            System.out.println(3);
        }
    }
}
