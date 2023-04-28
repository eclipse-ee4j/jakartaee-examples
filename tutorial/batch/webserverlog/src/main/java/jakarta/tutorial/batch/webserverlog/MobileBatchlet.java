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
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import jakarta.batch.api.Batchlet;
import jakarta.batch.runtime.context.JobContext;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/* Batchlet artifact that counts the number of purchase page views
 * based on the filtered items. */
@Dependent
@Named("MobileBatchlet")
public class MobileBatchlet implements Batchlet {
    private BufferedReader breader;
    private String fileName;
    private String buyPage;
    private String fileOutName;
    private int totalVisits = 0;
    private int pageVisits = 0;
    @Inject
    JobContext jobCtx;
    
    public MobileBatchlet() { }
    
    /* What percentage of mobile or table users buy products? */
    @Override
    public String process() throws Exception {
        /* Get properties from the job definition file */
        fileName = jobCtx.getProperties().getProperty("filtered_file_name");
        buyPage = jobCtx.getProperties().getProperty("buy_page");
        fileOutName = jobCtx.getProperties().getProperty("out_file_name");
        
        /* Count from the output of the previous chunk step */
        breader = new BufferedReader(new FileReader(fileName));
        String line = breader.readLine();
        while (line != null) {
            String[] lineSplit = line.split(", ");
            if (buyPage.compareTo(lineSplit[1]) == 0)
                pageVisits++;
            totalVisits++;
            line = breader.readLine();
        }
        breader.close();
        
        /* Write the result */
        try (BufferedWriter bwriter = 
               new BufferedWriter(new FileWriter(fileOutName, false))) {
            double percent = 100.0 * (1.0 * pageVisits) / (1.0 * totalVisits);
            bwriter.write(String.format("%d, %d, %.02f", pageVisits,
                                                         totalVisits, percent));
        }
        return "COMPLETED";
    }

    @Override
    public void stop() throws Exception {
        breader.close();
    }
    
}
