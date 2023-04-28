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

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.batch.api.listener.JobListener;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Named;

@Dependent
@Named("InfoJobListener")
public class InfoJobListener implements JobListener {

    private static final Logger logger = Logger.getLogger("InfoJobListener");
    
    public InfoJobListener() { }
    
    @Override
    public void beforeJob() throws Exception {
        logger.log(Level.INFO, "The job is starting");
    }

    @Override
    public void afterJob() throws Exception {
        logger.log(Level.INFO, "The job has finished.");
    }
    
}
