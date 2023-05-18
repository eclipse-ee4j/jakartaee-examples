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
package jakarta.tutorial.batch.phonebilling;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

import jakarta.batch.api.chunk.ItemWriter;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Named;
import jakarta.tutorial.batch.phonebilling.items.CallRecord;
import jakarta.tutorial.batch.phonebilling.items.PhoneBill;

/* Writer artifact.
 * Write each bill to a text file.
 */
@Dependent
@Named("BillWriter")
public class BillWriter implements ItemWriter {
    
    @Override
    public void open(Serializable ckpt) throws Exception { }

    @Override
    public void close() throws Exception { }

    @Override
    public void writeItems(List<Object> list) throws Exception {
        for (Object billObject : list) {
            PhoneBill bill = (PhoneBill) billObject;
            FileWriter fwriter = new FileWriter(bill.getPhoneNumber()+".txt");
            try (BufferedWriter bwriter = new BufferedWriter(fwriter)) {
                bwriter.write("DUKE WIRELESS - ACCCOUNT " + bill.getPhoneNumber());
                bwriter.newLine();
                bwriter.write(" ");
                bwriter.newLine();
                bwriter.write("Date            \tFrom    \tTo      \tLength\tPrice");
                bwriter.newLine();
                for (CallRecord call : bill.getCalls()) {
                    SimpleDateFormat dformat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                    String date = dformat.format(call.getDatetime());
                    bwriter.write(String.format("%s\t%s\t%s\t%02d:%02d\t%.2f", 
                                                date,
                                                call.getFromNumber(),
                                                call.getToNumber(),
                                                call.getMinutes(),
                                                call.getSeconds(),
                                                call.getPrice()));
                    bwriter.newLine();
                }
                bwriter.write(" ");
                bwriter.newLine();
                bwriter.write(String.format("Base    \t%.2f", bill.getAmountBase()));
                bwriter.newLine();
                bwriter.write(String.format("Tax rate\t%.2f%%", bill.getTaxRate().doubleValue()*100));
                bwriter.newLine();
                bwriter.write(String.format("Tax     \t%.2f", bill.getTax()));
                bwriter.newLine();
                bwriter.write(String.format("Total   \t%.2f", bill.getAmountTotal()));
            }
        }
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return new ItemNumberCheckpoint();
    }
    
}
