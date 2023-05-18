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

import java.io.Serializable;

/* Checkpoint class for the chunk batch artifacts */
public class ItemNumberCheckpoint implements Serializable {
    
    private static final long serialVersionUID = 5999782131990251192L;
    private long itemNumber;
    private long numItems;
    
    public ItemNumberCheckpoint() {
        itemNumber = 0;
    }
    
    public ItemNumberCheckpoint(int numItems) {
        this();
        this.numItems = numItems;
    }
    
    public long getItemNumber() {
        return itemNumber;
    }
    
    public void setNumItems(long numItems) {
        this.numItems = numItems;
    }
    
    public long getNumItems() {
        return numItems;
    }
    
    public void nextItem() {
        itemNumber++;
    }
    
    public void setItemNumber(long item) {
        itemNumber = item;
    }
}
