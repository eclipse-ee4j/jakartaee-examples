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
package jakarta.tutorial.batch.webserverlog;

import java.util.Properties;

import jakarta.batch.api.chunk.ItemProcessor;
import jakarta.batch.runtime.context.JobContext;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.tutorial.batch.webserverlog.items.LogFilteredLine;
import jakarta.tutorial.batch.webserverlog.items.LogLine;

/* Processes items from the log file
 * Filters only those items from mobile or tablet browsers,
 * depending on the properties specified in the job definition file.
 */
@Dependent
@Named("LogLineProcessor")
public class LogLineProcessor implements ItemProcessor {

    private String[] browsers;
    private int nbrowsers = 0;
    @Inject
    private JobContext jobCtx;

    public LogLineProcessor() {
    }

    @Override
    public Object processItem(Object item) {
        /* Obtain a list of browsers we are interested in */
        if (nbrowsers == 0) {
            Properties props = jobCtx.getProperties();
            nbrowsers = Integer.parseInt(props.getProperty("num_browsers"));
            browsers = new String[nbrowsers];
            for (int i = 1; i < nbrowsers + 1; i++) {
                browsers[i - 1] = props.getProperty("browser_" + i);
            }
        }

        LogLine logline = (LogLine) item;
        /* Filter for only the mobile/tablet browsers as specified */
        for (int i = 0; i < nbrowsers; i++) {
            if (logline.getBrowser().equals(browsers[i])) {
                /* The new items have fewer fields */
                return new LogFilteredLine(logline);
            }
        }
        return null;
    }
}
