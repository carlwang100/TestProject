package okhttp.chainmode;

public class main {

    static void main(){
       Handler controHandler1 = new ControHandler1();
       Handler controHandler2 = new ControHandler2();
        //指定handler2 为 handler1的下级引用
       controHandler1.setNextHandler(controHandler2);
       controHandler1.next();
    }
}
