/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.snippets;

/*
 * ToolBar example snippet: place a drop down menu in a tool bar
 *
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 */
import java.util.Date;
import java.util.Random;

import org.eclipse.swt.*;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public class Snippet67x {

public static void main (String [] args) {
	final Display display = new Display ();
	final Shell shell = new Shell (display);
	final ToolBar toolBar = new ToolBar (shell, SWT.FLAT);
	toolBar.addMouseListener(new MouseAdapter() {
	
		public void mouseDown(MouseEvent e) {
			long time = e.time & 0xFFFFFFFFL;
			System.out.println("mousedown " + new Date(time));
		}

		public void mouseDoubleClick(MouseEvent e) {
			long time = e.time & 0xFFFFFFFFL;
			System.out.println("mousedblclick");
		}

		public void mouseUp(MouseEvent e) {
			long time = e.time & 0xFFFFFFFFL;
			System.out.println("mouseup " + new Date(time));
		}
	
	});
	toolBar.addKeyListener(new KeyAdapter() {
	
		public void keyPressed(KeyEvent e) {
			System.out.println("keypressed");
		}

		public void keyReleased(KeyEvent e) {
			System.out.println("keyreleased");
		}

	});
	final Menu menu = new Menu (shell, SWT.POP_UP);
	for (int i=0; i<8; i++) {
		MenuItem item = new MenuItem (menu, SWT.PUSH);
		item.setText ("Item " + i);
	}
	final ToolItem item = new ToolItem (toolBar, SWT.DROP_DOWN);
	item.addMenuDetectListener(new MenuDetectListener() {
	
		public void menuDetected(MenuDetectEvent e) {
			System.out.println("menudetectdropdown");
		}
	
	});
	item.setText("My Item");
	item.setMenu(menu);
	final ToolItem item2 = new ToolItem (toolBar, SWT.PUSH, 0);
	item2.setText("theone");
	item2.addSelectionListener(new SelectionListener() {
	
		public void widgetSelected(SelectionEvent e) {
			System.out.println("sel");
		}
	
		public void widgetDefaultSelected(SelectionEvent e) {
			System.out.println("default sel");
		}
	
	});
	item2.addMenuDetectListener(new MenuDetectListener(){

		public void menuDetected(MenuDetectEvent event) {
			System.out.println("menudetect");
			if (false) {
				event.doit = false;
			} else {
				if (item2.getMenu() != null) {
					item2.getMenu().dispose();
				}
				final Menu menu = new Menu (shell, SWT.POP_UP);
				for (int i=0; i<8; i++) {
					MenuItem item = new MenuItem (menu, SWT.PUSH);
					item.setText ("Item " + new Random().nextInt(10));
				}
				item2.setMenu(menu);
			}
		}

	});
	toolBar.pack ();
	
	Button btn = new Button(shell, SWT.PUSH);
	btn.setLocation(120, 00);
	btn.setText("Hello");
	btn.pack();

	shell.pack ();
	shell.open ();
	while (!shell.isDisposed ()) {
		if (!display.readAndDispatch ()) display.sleep ();
	}
	menu.dispose ();
	display.dispose ();
}
} 
