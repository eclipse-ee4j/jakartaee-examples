/*
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR(S) DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR(S) BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER
 * RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT,
 * NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE
 * USE OR PERFORMANCE OF THIS SOFTWARE.
 */
package jakarta.tutorial.traffic.eis;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TrafficServer {
    
    private static List<Socket> clients;
    
    public static void main(String[] args) throws IOException {
        
        clients = new ArrayList<>();
        final ServerSocket server = new ServerSocket(4008);
        System.out.println("Traffic EIS accepting connections on port 4008");
        
        /* Accept connections */
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Socket client = server.accept();
                        synchronized (TrafficServer.class) {
                            clients.add(client);
                        }
                        System.out.println("Client connected");
                    } catch (IOException e) { }
                }
            }
        }).start();
        
        /* Send traffic information to all connected peers */
        PrintWriter out;
        String report;
        TrafficService tsvc = new TrafficService();
        while (true) {
            report = tsvc.getReport();
            for (Socket client : clients) {
                out = new PrintWriter(client.getOutputStream(), true);
                //System.out.println(report);
                out.println(report);
            }
            
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) { }
        }
    }

}
