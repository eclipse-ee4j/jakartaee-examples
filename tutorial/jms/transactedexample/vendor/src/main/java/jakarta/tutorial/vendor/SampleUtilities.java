/*
 * Copyright (c) 2014, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.vendor;

public class SampleUtilities {

    /**
     * Monitor class for asynchronous examples.  Producer signals
     * end of message stream; listener calls allDone() to notify
     * consumer that the signal has arrived, while consumer calls
     * waitTillDone() to wait for this notification.
     */
    public static class DoneLatch {
        boolean done = false;

        /**
         * Waits until done is set to true.
         */
        public void waitTillDone() {
            synchronized (this) {
                while (!done) {
                    try {
                        this.wait();
                    } catch (InterruptedException ie) {
                    }
                }
            }
        }

        /**
         * Sets done to true.
         */
        public void allDone() {
            synchronized (this) {
                done = true;
                this.notify();
            }
        }
    }
}
