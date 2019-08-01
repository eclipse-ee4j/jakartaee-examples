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
package jakartaee.examples.jsf.applicationmap;

import java.io.Serializable;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ApplicationMap;
import javax.faces.annotation.FacesConfig;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * A request scoped bean for the injected ApplicationMap example.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named(value = "applicationMapBean")
@RequestScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class ApplicationMapBean implements Serializable {
    
    /**
     * Stores the application map.
     */
    @Inject
    @ApplicationMap
    private Map<String, Object> applicationMap;
    
    /**
     * Get the application map.
     * 
     * @return the application map.
     */
    public Map<String, Object> getApplicationMap() {
        return applicationMap;
    }
}
