package ir.maktabsharif115.jpa.service;

public class MyMath {

    private final MyService myService;

    public MyMath(MyService myService) {
        this.myService = myService;
    }

    public int add(int a, int b) {
        return a + b + myService.test();
    }

    public int divide(int a, int b) {
        return a / b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

}
