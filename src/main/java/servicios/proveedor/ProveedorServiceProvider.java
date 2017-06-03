package servicios.proveedor;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.commands.Operacion;
import domain.entity.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by alber on 5/24/2017.
 */
public class ProveedorServiceProvider  {

    public PaqueteConsulta consultarProveedor(Proveedor proveedor, Operacion operacion){
        try {
            URL url = new URL("http://localhost:9080/servicios/operacion");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            String input =
                    "{\"idReserva\":\""+ operacion.getIdReserva()+"\",\"tipoOperacion\":\""+ operacion.getTipoOperacion()+"\",\"tipoServicion\":\""+ operacion.getTipoServicion()+"\",\"destino\":\"" + operacion.getDestino() + "\",\"fechaInicial\":\"" + operacion.getFechaInicial() +"\",\"fechaFinal\":\"" + operacion.getFechaFinal() + "\"}";

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            StringBuffer sb;

            sb = new StringBuffer();
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            ObjectMapper mapper = new ObjectMapper();

            System.out.println(sb.toString());
            PaqueteConsulta respuesta = mapper.readValue(sb.toString(), PaqueteConsulta.class);

            conn.disconnect();
            return respuesta;
        } catch (JsonGenerationException e){
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PaqueteConsultaConID reservarProveedor(Proveedor proveedor, Operacion operacion){
        try {
            URL url = new URL("http://localhost:9080/servicios/operacion");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input =
                    "{\"idReserva\":\""+ operacion.getIdReserva()+"\",\"tipoOperacion\":\""+ operacion.getTipoOperacion()+"\",\"tipoServicion\":\""+ operacion.getTipoServicion()+"\",\"destino\":\"" + operacion.getDestino() + "\",\"fechaInicial\":\"" + operacion.getFechaInicial() +"\",\"fechaFinal\":\"" + operacion.getFechaFinal() + "\"}";

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            StringBuffer sb;

            sb = new StringBuffer();
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            ObjectMapper mapper = new ObjectMapper();
            System.out.println(sb.toString());
            PaqueteConsultaConID respuesta = mapper.readValue(sb.toString(), PaqueteConsultaConID.class);

            conn.disconnect();
            return respuesta;
        } catch (JsonGenerationException e){
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public RespuestaCancelacion cancelarReservaProveedor(Proveedor proveedor, Operacion operacion, long idReserva){
        try {
            URL url = new URL("http://localhost:9080/servicios/operacion");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input =
                    "{\"idReserva\":\""+ operacion.getIdReserva()+"\",\"tipoOperacion\":\""+ operacion.getTipoOperacion()+"\",\"tipoServicion\":\""+ operacion.getTipoServicion()+"\",\"destino\":\"" + operacion.getDestino() + "\",\"fechaInicial\":\"" + operacion.getFechaInicial() +"\",\"fechaFinal\":\"" + operacion.getFechaFinal() + "\"}";

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            StringBuffer sb;

            sb = new StringBuffer();
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            ObjectMapper mapper = new ObjectMapper();

            System.out.println(sb.toString());
            RespuestaCancelacion respuesta = mapper.readValue(sb.toString(), RespuestaCancelacion.class);

            conn.disconnect();
            return respuesta;
        } catch (JsonGenerationException e){
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
