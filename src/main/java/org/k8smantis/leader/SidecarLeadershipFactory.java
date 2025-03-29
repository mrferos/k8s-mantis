package org.k8smantis.leader;

import io.mantisrx.server.core.BaseService;
import io.mantisrx.server.core.CoreConfiguration;
import io.mantisrx.server.core.ILeaderElectorFactory;
import io.mantisrx.server.core.ILeaderMonitorFactory;
import io.mantisrx.server.core.ILeadershipManager;
import io.mantisrx.server.core.master.MasterMonitor;

public class SidecarLeadershipFactory implements ILeaderMonitorFactory, ILeaderElectorFactory {
    @Override
    public BaseService createLeaderElector(CoreConfiguration config, ILeadershipManager manager) {
        // this doesn't seem like it should ever be called on the agent
        return null;
    }

    @Override
    public MasterMonitor createLeaderMonitor(CoreConfiguration config) {
        return new SidecarMasterMonitor();
    }
}
