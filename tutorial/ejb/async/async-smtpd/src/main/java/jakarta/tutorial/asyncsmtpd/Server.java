/*
 * Copyright (c), Eclipse Foundation, Inc. and its licensors.
 *
 * All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v1.0, which is available at
 * https://www.eclipse.org/org/documents/edl-v10.php
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */
package jakarta.tutorial.asyncsmtpd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    
    private final Socket client;
    
    public static void main(String[] args) throws IOException {
        
        ServerSocket server = new ServerSocket(3025);
        System.out.println("[Test SMTP server listening on port 3025]");
        
        while (true) {
            Socket client = server.accept();
            Thread sthread = new Thread(new Server(client));
            sthread.start();
        }
    }
    
    public Server(Socket client) {
        this.client = client;
    }
    
    @Override
    public void run() {
        String inline;
        String msg = "";
        try {
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            InputStreamReader isr = new InputStreamReader(client.getInputStream());
            BufferedReader in = new BufferedReader(isr);
            
            System.out.println("[Client connected]");
            out.println("220 jakarta.tutorial.asyncsmtpd");
            inline = in.readLine();
            if (inline.split(" ")[0].compareTo("HELO") != 0 &&
                inline.split(" ")[0].compareTo("EHLO") != 0) {
                client.close(); return;
            }
            out.println("250 +OK SMTP server Ready");
            inline = in.readLine();
            if (inline.split(":")[0].compareTo("MAIL FROM") != 0) {
                client.close(); return;
            }
            out.println("250 +OK Sender OK");
            inline = in.readLine();
            if (inline.split(":")[0].compareTo("RCPT TO") != 0) {
                client.close(); return;
            }
            out.println("250 +OK Recipient OK");
            inline = in.readLine();
            if (!inline.contains("DATA")) {
                client.close(); return;
            }
            out.println("354 +OK Start mail input.");
            
            while ((inline = in.readLine()) != null) {
                if (inline.compareTo(".") == 0) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) { }
                    out.println("250 +OK Requested action completed.");
                    in.readLine();
                    client.close();
                    System.out.println("[Delivering message...]");
                    System.out.println(msg);
                    System.out.println("\n");
                } else 
                    msg = msg + inline + "\n";
            }
            
        } catch (IOException e) { }
    }

}
