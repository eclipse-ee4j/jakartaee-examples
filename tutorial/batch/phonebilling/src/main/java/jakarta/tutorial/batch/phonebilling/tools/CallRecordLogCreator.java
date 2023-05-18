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
package jakarta.tutorial.batch.phonebilling.tools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/* This class creates simulated call logs for the batch application.
 * This is just a supporting class.
 * 
 * Log entries look like this:
 * {"datetime":"03/01/2013 04:03","from":"555-0109",
 * "to":"555-0112","length":"05:39"}
 */
public class CallRecordLogCreator {
    
    private Calendar cal;
    private Random rnd;
    private String[] srcNumbers;
    private String[] dstNumbers;
    private String[] records;
    private static final Logger logger = Logger.getLogger("CalRecordLogCreator");
    
    public CallRecordLogCreator() {
        this(10, 1000);
    }
    
    public CallRecordLogCreator(int nSrcNumbers, int nCalls) {
        rnd = new Random();
        srcNumbers = new String[nSrcNumbers];
        dstNumbers = new String[nSrcNumbers];
        records = new String[nCalls];
        
        nSrcNumbers = Math.min(nSrcNumbers, 49);
        for (int i=0; i<nSrcNumbers; i++) {
            srcNumbers[i] = String.format("555-01%02d", i+1);
            dstNumbers[i] = String.format("555-01%02d", i+1+nSrcNumbers);
        }
        
        cal = Calendar.getInstance();
        cal.set(2013, 2, 1, 04, 01, 00);
        
        for (int i=0; i<nCalls; i++)
            records[i] = this.generateRecord();
    }
    
    private String generateRecord() {
        cal.add(Calendar.SECOND, rnd.nextInt(20) + 1);
        cal.add(Calendar.MINUTE, rnd.nextInt(10) + 1);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        String datetime = sdf.format(cal.getTime());
        String from = srcNumbers[rnd.nextInt(srcNumbers.length)];
        String to = dstNumbers[rnd.nextInt(dstNumbers.length)];
        String length = String.format("%02d:%02d", rnd.nextInt(7), rnd.nextInt(60));
        return String.format("{\"datetime\":\"%s\",\"from\":\"%s\",\"to\":\"%s\",\"length\":\"%s\"}", 
                             datetime, from, to, length);
    }
    
    public void writeToFile(String fileName) {
        BufferedWriter bwriter;
        try {
            bwriter = new BufferedWriter(new FileWriter(fileName));
            for (int i=0; i<records.length; i++) {
                bwriter.write(records[i]);
                bwriter.newLine();
            }
            bwriter.close();
        } catch (IOException e) {
            logger.log(Level.WARNING, e.toString());
        }
    }
    
    /*public static void main(String args[]) {
        CallRecordLogCreator c = new CallRecordLogCreator();
        for (int i=0; i<100; i++)
            System.out.println(c.generateRecord());
        c.writeToFile("C:/log2.txt");
    }*/
}
