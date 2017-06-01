/*******************************************************************************
 * Copyright (c) 2015 Liviu Ionescu.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Liviu Ionescu - initial version
 *******************************************************************************/

package ilg.gnumcueclipse.debug.gdbjtag.openocd.ui.preferences;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import ilg.gnumcueclipse.core.preferences.DirectoryNotStrictFieldEditor;
import ilg.gnumcueclipse.debug.gdbjtag.openocd.Activator;
import ilg.gnumcueclipse.debug.gdbjtag.openocd.PersistentPreferences;
import ilg.gnumcueclipse.debug.gdbjtag.openocd.ui.Messages;

/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself.
 * <p>
 * This page uses special filed editors, that get the default values from the
 * preferences store, but the values are from the variables store.
 */
public class GlobalOpenOcdPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	// ------------------------------------------------------------------------

	public static final String ID = "ilg.gnumcueclipse.debug.gdbjtag.openocd.globalPreferencePage";

	// ------------------------------------------------------------------------

	public GlobalOpenOcdPage() {
		super(GRID);

		if (Activator.getInstance().isDebugging()) {
			System.out.println("openocd.GlobalOpenOcdPage()");
		}
		// Explicit use of the workspace preferences.
		setPreferenceStore(new ScopedPreferenceStore(ConfigurationScope.INSTANCE, Activator.PLUGIN_ID));

		setDescription(Messages.GlobalOpenOCDPagePropertyPage_description);
	}

	// ------------------------------------------------------------------------

	// Contributed by IWorkbenchPreferencePage
	@Override
	public void init(IWorkbench workbench) {

		if (Activator.getInstance().isDebugging()) {
			System.out.println("openocd.GlobalOpenOcdPage.init()");
		}
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */
	@Override
	protected void createFieldEditors() {

		FieldEditor executable;
		executable = new StringFieldEditor(PersistentPreferences.EXECUTABLE_NAME, Messages.OpenOCDPage_executable_label,
				getFieldEditorParent());
		addField(executable);

		boolean isStrict;
		isStrict = PersistentPreferences.getFolderStrict();

		FieldEditor folder;
		folder = new DirectoryNotStrictFieldEditor(PersistentPreferences.INSTALL_FOLDER,
				Messages.OpenOCDPage_executable_folder, getFieldEditorParent(), isStrict);
		addField(folder);
	}

	// ------------------------------------------------------------------------
}