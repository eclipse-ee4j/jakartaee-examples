/*
 * Copyright (c), Eclipse Foundation, Inc. and its licensors.
 *
 * All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v1.0, which is available at
 * https://www.eclipse.org/org/documents/edl-v10.php
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */
package jakarta.tutorial.batch.webserverlog;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.List;

import jakarta.batch.api.chunk.ItemWriter;
import jakarta.batch.runtime.context.JobContext;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.tutorial.batch.webserverlog.items.LogFilteredLine;

/* Write the filtered items */
@Dependent
@Named("LogFilteredLineWriter")
public class LogFilteredLineWriter implements ItemWriter {

    private String fileName;
    private BufferedWriter bwriter;
    @Inject 
    private JobContext jobCtx;

    @Override
    public void open(Serializable ckpt) throws Exception {
        
        fileName = jobCtx.getProperties().getProperty("filtered_file_name");
        /* If the job was restarted, continue writing at the end of the file.
         * Otherwise, overwrite the file. */
        if (ckpt != null)
            bwriter = new BufferedWriter(new FileWriter(fileName, true));
        else
            bwriter = new BufferedWriter(new FileWriter(fileName, false));
    }

    @Override
    public void close() throws Exception {
        bwriter.close();
    }

    @Override
    public void writeItems(List<Object> items) throws Exception {
        /* Write the filtered lines to the output file */
        for (int i = 0; i < items.size(); i++) {
            LogFilteredLine filtLine = (LogFilteredLine) items.get(i);
            bwriter.write(filtLine.toString());
            bwriter.newLine();
        }
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return new ItemNumberCheckpoint();
    }
}
