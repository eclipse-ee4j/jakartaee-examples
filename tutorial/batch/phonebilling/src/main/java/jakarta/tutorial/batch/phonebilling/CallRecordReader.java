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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;

import jakarta.batch.api.chunk.ItemReader;
import jakarta.batch.runtime.context.JobContext;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.tutorial.batch.phonebilling.items.CallRecord;

/* Reader batch artifact.
 * Reads call records from the input log file.
 */
@Dependent
@Named("CallRecordReader")
public class CallRecordReader implements ItemReader {

    private ItemNumberCheckpoint checkpoint;
    private String fileName;
    private BufferedReader breader;
    @Inject
    JobContext jobCtx;
    
    public CallRecordReader() { }
    
    @Override
    public void open(Serializable ckpt) throws Exception {
        /* Use the checkpoint provided if this is a restart */
        if (ckpt == null)
            checkpoint = new ItemNumberCheckpoint();
        else
            checkpoint = (ItemNumberCheckpoint) ckpt;
        
        /* Read the input file up to the checkpoint without processing */
        fileName = jobCtx.getProperties().getProperty("log_file_name");
        breader = new BufferedReader(new FileReader(fileName));
        for (int i=0; i<checkpoint.getItemNumber(); i++)
            breader.readLine();
    }

    @Override
    public void close() throws Exception {
        breader.close();
    }

    @Override
    public Object readItem() throws Exception {
        /* Read a line from the log file and 
         * create a CallRecord from JSON */
        String callEntryJson = breader.readLine();
        if (callEntryJson != null) {
            checkpoint.nextItem();
            return new CallRecord(callEntryJson);
        } else
            return null;
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return checkpoint;
    }
    
}
