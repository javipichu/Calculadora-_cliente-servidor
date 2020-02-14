package ejercicio2_calculadora_servidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author javi
 */
public class Ejercicio2_calculadora_servidor extends Thread{
    
    public Socket clienteSocket;
    public Ejercicio2_calculadora_servidor(Socket socket){
        clienteSocket=socket;
    }
    
    public void run(){
        try{
         System.out.println("Conexion recibida");
            InputStream is=clienteSocket.getInputStream();
            OutputStream os=clienteSocket.getOutputStream();
            
            float resultadoT=0;
            String numero1="",numero2="",operando="";
            byte[] mensaje=new byte[200];
            is.read(mensaje);
            String cuenta= new String(mensaje);
            char[]cadena;
            cadena=cuenta.toCharArray();
            for(int i=0;i<cadena.length;i++){
                char cifra=cadena[i];
                if(!Character.isDigit(cifra)&&operando.equals("")&&!Character.toString(cifra).equals(".")){
                    operando=Character.toString(cadena[i]);
                }
                 else if (!operando.equals(null)&&resultadoT!=0){
                        if(Character.isDigit(cifra)||Character.toString(cifra).equals(".")){
                            numero1=numero1+Character.toString(cadena[i]);
                        }   else if(!Character.isDigit(cifra)&&Character.toString(cifra).equals(".")){
                    numero1=numero1+Character.toString(cadena[i]);
                        }
                        else   
                            if(operando.equals("+")){
                            resultadoT=resultadoT+Float.parseFloat(numero1);
                            numero1="";
                            operando=Character.toString(cadena[i]);
                        }
                        else
                            if(operando.equals("-")){
                                resultadoT=resultadoT-Float.parseFloat(numero1);
                                numero1="";
                                operando=Character.toString(cadena[i]);
                            }
                        else
                            if(operando.equals("*")){
                                resultadoT=resultadoT*Float.parseFloat(numero1);
                                numero1="";
                                operando=Character.toString(cadena[i]);
                            }
                        else
                            if(operando.equals("/")){
                                resultadoT=resultadoT/Float.parseFloat(numero1);
                                numero1="";
                                operando=Character.toString(cadena[i]);
                            }
                        }
                else if(!Character.isDigit(cifra)&&operando.equals("")&&Character.toString(cifra).equals(".")){
                    numero1=numero1+Character.toString(cadena[i]);
                }
                else if(!Character.isDigit(cifra)&&!operando.equals("")&&Character.toString(cifra).equals(".")){
                    numero2=numero2+Character.toString(cadena[i]);
                }
                
                else if(Character.isDigit(cifra)&&!operando.equals("")){
                    numero2=numero2+Character.toString(cadena[i]);
                }
                else if(Character.isDigit(cifra)&&operando.equals("")){
                    numero1=numero1+Character.toString(cadena[i]);
                }
               
                else {
                    if(operando.equals("+")){
                        resultadoT=Float.parseFloat(numero1)+Float.parseFloat(numero2);
                        numero2="";
                        numero1="";
                        operando=Character.toString(cadena[i]);
                    }
                    else
                        if(operando.equals("-")){
                            resultadoT=Float.parseFloat(numero1)-Float.parseFloat(numero2);
                            numero2="";
                            numero1="";
                            operando=Character.toString(cadena[i]);
                        }
                    else
                        if(operando.equals("*")){
                            resultadoT=Float.parseFloat(numero1)*Float.parseFloat(numero2);
                            numero2="";
                            numero1="";
                            operando=Character.toString(cadena[i]);
                        }
                    else
                        if(operando.equals("/")){
                            resultadoT=Float.parseFloat(numero1)/Float.parseFloat(numero2);
                            numero2="";
                            numero1="";
                            operando=Character.toString(cadena[i]);
                        }
                }
            }
            
                System.out.println(resultadoT);
            
            String respuesta=String.valueOf(resultadoT);
            os.write(respuesta.getBytes());
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) throws IOException {
        int puerto=Integer.parseInt(JOptionPane.showInputDialog(null,"Introduce el puerto deseado:","5555"));
        System.out.println("Creando socket servidor");
        ServerSocket serverSocket=new ServerSocket();
        System.out.println("Realizando el bind");
        String respuesta=null;
        InetSocketAddress addr=new InetSocketAddress("192.168.0.1",puerto);
        serverSocket.bind(addr);
        while(serverSocket!=null){
            Socket newSocket= serverSocket.accept();
            Ejercicio2_calculadora_servidor hilo=new Ejercicio2_calculadora_servidor(newSocket);
            hilo.start();
        }
    }
}