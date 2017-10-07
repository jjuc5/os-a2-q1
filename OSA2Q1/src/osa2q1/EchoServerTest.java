/**
 * This program is designed establish an echo server which read input from a
 * client and echo it back.
 *
 * Authors: Melanie Iarocci and John Urbanowicz
 * Student IDs: 991 374 561, 991 365 148
 * Title: Assignment 2 - Question 1
 * Course: SYST44288
 * Date: 10/07/2017
 */
package osa2q1;

import java.net.*;
import java.io.*;

public class EchoServerTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        echoServer();
        echoClient();
    }

    public static void echoServer() {
        (new Thread() {
            public void run() {

                try {
                    ServerSocket sock = new ServerSocket(6013);
                    while (true) {
                        Socket client = sock.accept();
                        PrintWriter pw = new PrintWriter(client.getOutputStream(), true);
                        pw.println(new java.util.Date().toString());
                        client.close();
                        Thread.sleep(200);
                    }
                } catch (UnknownHostException ex) {
                    System.out.println(ex);
                } catch (IOException ex) {
                    System.err.println(ex);
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }

            }
        }).start();
    }

    public static void echoClient() {
        (new Thread() {
            public void run() {
                     
                try {
                    Socket socket = new Socket("127.0.0.1", 6013);
                InputStream input = socket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(input));

                String readLine;
                while ((readLine = br.readLine()) != null) {
                    System.out.println(readLine);
                }

                socket.close();
            }
            catch (IOException ex) {
                    System.err.println(ex);
            }
        }

        }).start();
    }
}

