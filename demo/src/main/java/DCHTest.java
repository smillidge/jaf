/*
 * Copyright (c) 1997, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

import java.io.*;
import javax.activation.*;
import java.awt.datatransfer.*;

public class DCHTest {
    private FileDataSource fds = null;
    private DataHandler dh = null;
    private DataContentHandlerFactory dchf = null;
    /**
     * main function
     */
    public static void main(String args[]) {
	DCHTest test = new DCHTest();
	
	if(args.length == 0) {
	    System.out.println("usage: DCHTest file.txt");
	    System.exit(1);
	}
	
	// first let's get a DataSource

	test.setFile(args[0]);
	
	test.doit();
    }

    private void doit() {
	DataFlavor xfer_flavors[];
	Object content = null;

	// now let's create a DataHandler
	dh = new DataHandler(fds);
	System.out.println("DCHTest: DataHandler created");

	// now lets set a DataContentHandlerFactory
	dchf = new SimpleDCF("text/plain:PlainDCH\n");
	System.out.println("DCHTest: Simple dchf created");
	
	// now let's set the dchf in the dh
	dh.setDataContentHandlerFactory(dchf);
	System.out.println("DCHTest: DataContentHandlerFactory set in DataHandler");
	
	// get the dataflavors
	xfer_flavors = dh.getTransferDataFlavors();
	System.out.println("DCHTest: dh.getTransferDF returned " +
			   xfer_flavors.length + " data flavors.");

	// get the content:
        try {
	   content = dh.getContent();
        } catch (Exception e) { e.printStackTrace(); }

	if(content == null)
	    System.out.println("DCHTest: no content to be had!!!");
	else
	    System.out.println("DCHTest: got content of the following type: " +
			       content.getClass().getName());
	
    }

    /**
     * set the file
     */
    public void setFile(String filename) {
	fds = new FileDataSource(filename);
	System.out.println("DCHTest: FileDataSource created");
	if(!fds.getContentType().equals("text/plain")) {
	    System.out.println("Type must be text/plain");
	    System.exit(1);
	}
    }
	    
}


