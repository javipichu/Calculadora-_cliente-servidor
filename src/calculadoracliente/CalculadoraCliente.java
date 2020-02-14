/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadoracliente;

import calculadoracliente.Calculadora;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author javi
 */
public class CalculadoraCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String ip=JOptionPane.showInputDialog(null,"Introduce la Ip deseada:","192.168.0.1");
        int puerto=Integer.parseInt(JOptionPane.showInputDialog(null,"Introduce el puerto deseado:","5555"));
            
        Calculadora obx=new Calculadora(ip,puerto);
        obx.setVisible(true);
    }
    
}
