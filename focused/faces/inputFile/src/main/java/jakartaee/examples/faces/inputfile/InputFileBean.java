/*
 * Permission to use, copy, modify, and/or distribute this software for any 
 * purpose with or without fee is hereby granted.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR(S) DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR(S) BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION
 * OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN
 * CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */
package jakartaee.examples.faces.inputfile;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.servlet.http.Part;

/**
 * A request scoped bean for the h:inputFile example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named(value = "inputFileBean")
@RequestScoped
public class InputFileBean {
    
    /**
     * Stores the part.
     */
    private Part part;

    /**
     * Get the file (part).
     * 
     * @return the time.
     */
    public Part getFile() {
        return part;
    }
    
    /**
     * Set the file.
     * 
     * @param part the part.
     */
    public void setFile(Part part) {
        this.part = part;
    }
    
    /**
     * Submit.
     * 
     * @return ""
     */
    public String submit() {
        return "";
    }
}
