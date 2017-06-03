package main;

import domain.commands.Operacion;
import servicios.proveedor.ProveedorServiceProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by AHernandezS on 3/06/2017.
 */
public class Main {


    public void main() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.print("Enter Command : ");
                String input = br.readLine();
                if ("q".equals(input)) {
                    System.out.println("Exit!");
                    System.exit(0);
                }
                System.out.println("input : " + input);

                validarComando(input);

                System.out.println("-----------\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    ProveedorServiceProvider service =  new ProveedorServiceProvider();

    public void validarComando(String input){


        String[] comando = input.split(" ");

        if (comando.length > 4 && comando.length < 7) {

            Operacion operacion = new Operacion();

            operacion.setTipoOperacion(comando[0]);
            operacion.setTipoServicion(comando[1]);
            operacion.setDestino(comando[2]);
            operacion.setFechaInicial(comando[3]);
            operacion.setFechaFinal(comando[4]);

            if (comando.length > 5)
                operacion.setIdReserva(comando[5]);

            if (operacion.getTipoOperacion().equals("cot")) {
                service.consultarProveedor(null, operacion);
            }else if (operacion.getTipoOperacion().equals("res")) {
                service.reservarProveedor(null, operacion);
            }else if (operacion.getTipoOperacion().equals("can")) {
                service.cancelarReservaProveedor(null, operacion, Integer.parseInt(operacion.getIdReserva()));
            }else {
                System.out.println("TIPO DE OPERACION NO VALIDA");
            }
        } else {
            System.out.println("COMANDO INVALIDO");
        }
    }

    public static void main (String[] args) {
        new Main().main();
    }
}
