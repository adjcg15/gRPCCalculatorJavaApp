package grpccalculator.client;

import javax.swing.JOptionPane;

import com.proto.calculator.CalculatorServiceGrpc;
import com.proto.calculator.Calculator.OperationParam;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8080;

        ManagedChannel channel = ManagedChannelBuilder
            .forAddress(host, port)
            .usePlaintext()
            .build();

        CalculatorServiceGrpc.CalculatorServiceBlockingStub stub = CalculatorServiceGrpc.newBlockingStub(channel);
        
        while (true) {
            String opt = JOptionPane.showInputDialog(
                "Cacular\n " +
                "Suma................(1)\n" +
                "Resta...............(2)\n" +
                "Multiplicacion......(3)\n" +
                "Division............(4)\n\n" +
                "Cancelar para salir"
            );

            if(opt == null) {
                break;
            }

            int a = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el primer numero"));
            int b = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el segundo numero"));

            OperationParam params = OperationParam.newBuilder().setA(a).setB(b).build();
            switch (opt) {
                case "1":
                    JOptionPane.showMessageDialog(
                        null, a + " + " + b + " = " + stub.add(params).getResult());
                    break;
                case "2":
                    JOptionPane.showMessageDialog(
                    null, a + " - " + b + " = " + stub.substract(params).getResult());
                    break;
                case "3":
                    JOptionPane.showMessageDialog(
                        null, a + " * " + b + " = " + stub.multiply(params).getResult());
                    break;
                case "4":
                    JOptionPane.showMessageDialog(
                        null, a + " / " + b + " = " + stub.divide(params).getResult());
                    break;
            }
        }

        System.out.println("Apagando...");
        channel.shutdown();
    }
}
