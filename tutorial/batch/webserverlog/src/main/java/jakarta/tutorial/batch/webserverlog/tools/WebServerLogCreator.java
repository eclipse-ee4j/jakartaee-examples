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
package jakarta.tutorial.batch.webserverlog.tools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/* (This file is NOT required to test the sample)
 * - Uncomment the main method and run this class to generate
 * sample log files of arbitrary length to test the batch sample
 * with a larger file, if you wish to do so.
 * - Replace WEB-INF/classes/log1.txt with the new file. */
public class WebServerLogCreator {

    private Calendar cal;
    private String[] browsers;
    private String[] urls;
    private BufferedWriter bwriter;
    private static final Logger logger = Logger.getLogger("WebServerLogCreator");

    public WebServerLogCreator(String filename) {
        cal = Calendar.getInstance();
        cal.set(2013, 2, 1, 04, 01, 00);
        browsers = new String[5];
        browsers[0] = "Desktop Browser A";
        browsers[1] = "Desktop Browser B";
        browsers[2] = "Mobile Browser C";
        browsers[3] = "Tablet Browser D";
        browsers[4] = "Tablet Browser E";
        urls = new String[4];
        urls[0] = "/index.html";
        urls[1] = "/auth/login.html";
        urls[2] = "/auth/buy.html";
        urls[3] = "/auth/logout.html";
        try {
            bwriter = new BufferedWriter(new FileWriter(filename));
        } catch (IOException ex) {
            logger.log(Level.INFO, ex.toString());
        }
    }

    public String writeLogLine() {
        String line = "";
        Random rnd = new Random();
        cal.add(Calendar.SECOND, rnd.nextInt(20) + 1);
        cal.add(Calendar.MINUTE, rnd.nextInt(10) + 1);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String datetime = sdf.format(cal.getTime());
        String ipaddr = "" + 192
                + "." + 168
                + "." + (rnd.nextInt(254) + 1)
                + "." + (rnd.nextInt(254) + 1) + "";
        String browser = browsers[rnd.nextInt(5)];
        String url = urls[rnd.nextInt(4)];
        try {
            line = datetime + ", " + ipaddr + ", "
               + browser + ", " + url;
            bwriter.write(line);
            bwriter.newLine();
        } catch (IOException ex) {
            logger.log(Level.INFO, ex.toString());
        }
        return line;
    }

    public void close() {
        try {
            bwriter.flush();
            bwriter.close();
        } catch (IOException ex) {
            logger.log(Level.INFO, ex.toString());
        }
    }

    /*public static void main(String[] args) {
        WebServerLogCreator log = new WebServerLogCreator("C:/log1.txt");
        for (int i = 0; i < 500; i++) {
            log.writeLogLine();
        }
        log.close();
    }*/
}
