public class Ejer6hilo {
    static int caja = 200;
    static boolean pasar=true;

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            new Ingreso("10").start();
            Ingreso.sleep(1000);
        }
        for (int i = 0; i < 5; i++) {
            new Extraccion("5").start();
            Extraccion.sleep(1000);
        }
    }

    public static class Ingreso extends Thread {
        public Ingreso(String name) {
            super(name);
        }

        public void run() {

            if (pasar==false){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            pasar=true;
            caja += Integer.parseInt(getName());
            System.out.println("Ingreso realizada con éxito, total en caja : " + caja);
            pasar=true;
            try{
                notify();
            }catch(IllegalMonitorStateException e){

            }

        }
    }

    public static class Extraccion extends Thread {
        public Extraccion(String name) {
            super(name);
        }

        public void run() {

            if (pasar==false){

                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            pasar=false;
            caja -= Integer.parseInt(getName());
            System.out.println("Extracción  realizada con éxito, total en caja : " + caja);
            pasar = true;
            try{
                notify();
            }catch(IllegalMonitorStateException e){

            }
        }
    }
}

