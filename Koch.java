
//importando las clases que necesitaremos
import java.awt.*;
import java.util.Scanner;
import java.math.*;

public class Koch {
    public static int LMIN = 8;

    public static void curvaDeKoch(Turtle tortuga, int L) {

        tortuga.setPenDown();
        //caso base
        if (L <= LMIN) {
            tortuga.goForward(L);
        }
        else {
            int L1= L/3;
            //lado 1
            curvaDeKoch(tortuga,L1); //es el forward
            //lado 2
            tortuga.turnLeft(60.0);
            curvaDeKoch(tortuga,L1);
            //lado 3
            tortuga.turnRight(120.0);
            curvaDeKoch(tortuga,L1);
            //lado 4
            tortuga.turnLeft(60.0);
            curvaDeKoch(tortuga,L1);
        }


    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); //Se crea el lector
        System.out.println("Por favor ingrese el \u00e1ngulo de rotaci\u00f3n del copo de nieve de Koch");
        //angulos para cada tortuga
        double a0 = sc.nextInt();
        double a1= a0+60.0;
        double a2= a0+300.0;
        double a3= a0+180.0;
        //largo de la curva de Koch
        double b=5;
        int L0 =(int) Math.pow(3,b); //debe ser potencia de 3, sino piede precisión al pasar a int (100/3= 33)
        double lado= L0;
        //posiciones donde partirá cada tortuga
        double x0 = 0.0;
        double y0 = 0.0;

        double x1 = x0+(lado*(Math.cos(Math.toRadians(a1))));
        double y1 = y0+(lado*(Math.sin(Math.toRadians(a1))));

        double x2 = x0 + (lado*Math.cos(Math.toRadians(a0)));
        double y2 = y0 + (lado*Math.sin(Math.toRadians(a0)));

        Turtle tortuga1 = new Turtle(x0, y0, a1, 1000, 600);
        Turtle tortuga2 = new Turtle(x1, y1, a2, 1000, 600);
        Turtle tortuga3 = new Turtle(x2,y2,a3,1000,600);
        //copo de nieve
        //lado 1
        tortuga1.setCanvasSize(1000,600);
        tortuga1.setXscale(-1000.0,1000.0);
        tortuga1.setYscale(-600.0,600.0);
        tortuga1.setPenColor(Color.BLUE);
        curvaDeKoch(tortuga1, L0);
        //lado 2
        tortuga2.setPenColor(Color.MAGENTA);
        curvaDeKoch(tortuga2,L0);
        //lado 3
        tortuga3.setPenColor(Color.CYAN);
        curvaDeKoch(tortuga3,L0);

    }
}



