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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

import jakarta.batch.api.chunk.ItemReader;
import jakarta.batch.runtime.context.JobContext;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.tutorial.batch.webserverlog.items.LogLine;

/* Reads lines from the input log file */
@Dependent
@Named("LogLineReader")
public class LogLineReader implements ItemReader {

    private ItemNumberCheckpoint checkpoint;
    private String fileName;
    private BufferedReader breader;
    @Inject
    private JobContext jobCtx;

    public LogLineReader() {
    }

    @Override
    public void open(Serializable ckpt) throws Exception {
        /* Use the checkpoint if this is a restart */
        if (ckpt == null) {
            checkpoint = new ItemNumberCheckpoint();
        } else {
            checkpoint = (ItemNumberCheckpoint) ckpt;
        }

        /* Read from the log file included with the application
         * (webserverlog/WEB-INF/classes/log1.txt) */
        fileName = jobCtx.getProperties().getProperty("log_file_name");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream iStream = classLoader.getResourceAsStream(fileName);
        breader = new BufferedReader(new InputStreamReader(iStream));

        /* Continue where we left off if this is a restart */
        for (int i = 0; i < checkpoint.getLineNum(); i++) {
            breader.readLine();
        }
    }

    @Override
    public void close() throws Exception {
        breader.close();
    }

    @Override
    public Object readItem() throws Exception {
        /* Return a LogLine object */
        String entry = breader.readLine();
        if (entry != null) {
            checkpoint.nextLine();
            return new LogLine(entry);
        } else {
            return null;
        }
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return checkpoint;
    }
}
