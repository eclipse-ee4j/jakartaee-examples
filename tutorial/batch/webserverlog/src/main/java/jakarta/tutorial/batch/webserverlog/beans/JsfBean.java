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
package jakarta.tutorial.batch.webserverlog.beans;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.batch.operations.JobOperator;
import jakarta.batch.runtime.BatchRuntime;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

/* Handles data for the Facelets pages and starts the batch job */
@Named
@SessionScoped
public class JsfBean implements Serializable {
    
    private long execID;
    private JobOperator jobOperator;
    private static final Logger logger = Logger.getLogger("JsfBean");
    private static final long serialVersionUID = 3712686178567411830L;
    
    /* Show to the user the log file that the batch job processes */
    public String getInputLog() {
        String inputLog = "";
        
        /* Read from the log file included with the application
         * (webserverlog/WEB-INF/classes/log1.txt) */
        BufferedReader breader;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream iStream = classLoader.getResourceAsStream("log1.txt");
        breader = new BufferedReader(new InputStreamReader(iStream));
        
        try {
            String line = breader.readLine();
            while (line != null) {
                inputLog += line + '\n';
                line = breader.readLine();
            }
        } catch (IOException ex) {
            logger.log(Level.INFO, ex.toString());
        }
        return inputLog;
    }
    
    /* --JSF navigation method--
     * Starts the batch job and navigates to the next page */
    public String startBatchJob() {
        jobOperator = BatchRuntime.getJobOperator();
        execID = jobOperator.start("webserverlog", null);
        return "jobstarted";
    }
    
    /* Show the current status of the job */
    public String getJobStatus() {
        return jobOperator.getJobExecution(execID).getBatchStatus().toString();
    }
    
    public boolean isCompleted() {
        return (getJobStatus().compareTo("COMPLETED") == 0);
    }
    
    /* Show the results */
    public String showResults() throws IOException {
        if (isCompleted()) {
            String returnStr;
            /* open file name for output, split comas */
            BufferedReader breader;
            breader = new BufferedReader(new FileReader("result1.txt"));
            String[] results = breader.readLine().split(", ");
            /* create output string */
            returnStr = String.format("%s purchases of %s tablet page views, (%s percent)", 
                         results[0], results[1], results[2]);
            return returnStr;
        } else {
            return "";
        }
    }
}
