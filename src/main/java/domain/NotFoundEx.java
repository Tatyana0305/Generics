package domain;

public class NotFoundEx extends Exception{
    public NotFoundEx(String msg){

        super(msg);
        System.out.println(msg);
    }
}
