/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
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
