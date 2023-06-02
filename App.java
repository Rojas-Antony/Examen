package com.pp2;

public class App {
	private static double[] distancias = {5.30,5.73,6.77,5.26,4.33,5.45,6.09,5.64,5.81,5.75};
    private static double media;
    private static double[] desviacion;
    
    public synchronized static void calcularMedia() {
        media = calcularMedia(distancias);
        System.out.println("Media: " + media);
    }
    
    private static double calcularMedia(double[] distancias) {
        double suma = 0;
        for (double distancia : distancias) {
            suma += distancia;
        }
        return suma / distancias.length;
    }

    public synchronized static void calcularDesviacion() {
        desviacion = calcularDesviacion(distancias, media);
        for (double desviacion : desviacion) {
            System.out.println(desviacion);
        }
    }
    
    private static double[] calcularDesviacion(double[] distancias, double mediaAritmetica) {
        double[] desviaciones = new double[distancias.length];
        for (int i = 0; i < distancias.length; i++) {
            desviaciones[i] = distancias[i] - mediaAritmetica;
        }
        return desviaciones;
    }

    public static void main(String[] args){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                    calcularMedia();
                }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                    calcularDesviacion();
                }
        });

        t1.start();
        try {
        	t1.join();
        } catch(InterruptedException e) {
        	e.printStackTrace();
        }
        

        t2.start();
        try {
        	t2.join();
        } catch(InterruptedException e) {
        	e.printStackTrace();
        }
    }
}
