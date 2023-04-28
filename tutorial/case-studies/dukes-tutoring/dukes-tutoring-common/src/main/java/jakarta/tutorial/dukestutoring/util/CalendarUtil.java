/*
 * Copyright (c) 2014, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.dukestutoring.util;

import java.util.Calendar;

/**
 *
 * @author ian
 */
public class CalendarUtil {

    public CalendarUtil() {
    }

    public static void stripTime(final Calendar cal) {
        // remove all the time information
        cal.clear(Calendar.AM_PM);
        cal.clear(Calendar.HOUR_OF_DAY);
        cal.clear(Calendar.HOUR);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);
    }
}
