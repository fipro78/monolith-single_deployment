package org.fipro.client.commandline;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.fipro.service.modifier.StringModifier;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

/**
 * Immediate component that is activated only if launcher arguments are available.
 * As those are published only by the Bnd launcher, this component only gets activated in the executable jar. 
 */
@Component(immediate = true)
public class BndStarter {
	
	String[] launcherArgs;

	@Reference(target = "(launcher.arguments=*)")
	void setLauncherArguments(Object object, Map<String, Object> map) {
	    this.launcherArgs = (String[]) map.get("launcher.arguments");
	}

    
    @Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
    void registerStringModifier(StringModifier modifier) {
    	// we only want to execute the modification once in the commandline variant
    	// no need to store the reference
    	for (String arg : launcherArgs) {
    		System.out.println(modifier.modify(arg));
    	}
    }
    
    void unregisterStringModifier(StringModifier modifier) {
    	// do nothing
    }

    @Activate
    void activate() {
        // automatically shutdown the application after 1 second
    	// needed as we have a dynamic multi cardinality reference that is satisfied even without any StringModifier available
    	// delayed shutdown could be avoided using a design with a single reference as then this component would get satisfied in a more reliable way
    	Executors.newSingleThreadScheduledExecutor().schedule(() -> System.exit(0), 1, TimeUnit.SECONDS);
    }
}