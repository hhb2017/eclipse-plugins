package ilg.gnuarmeclipse.templates.core.processes;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import org.eclipse.cdt.core.templateengine.TemplateCore;
import org.eclipse.cdt.core.templateengine.process.ProcessArgument;
import org.eclipse.cdt.core.templateengine.process.ProcessFailureException;
import org.eclipse.cdt.core.templateengine.process.ProcessRunner;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

public class SetPropertyToPluginResource extends ProcessRunner {

	@Override
	public void process(TemplateCore template, ProcessArgument[] args,
			String processId, IProgressMonitor monitor)
			throws ProcessFailureException {

		String bundleId = args[0].getSimpleValue();
		String relativePath = args[1].getSimpleValue();
		String propertyName = args[2].getSimpleValue();

		Bundle bundle = Platform.getBundle(bundleId);
		URL url = FileLocator.find(bundle, new Path(relativePath), null);
		String location;
		try {
			location = FileLocator.resolve(url).getPath();
			
			Map<String, String> values = template.getValueStore();
			if (values.containsKey(propertyName)) {
				values.put(propertyName, location);
			} else {
				System.out.println("Property " + propertyName + " not defined.");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}