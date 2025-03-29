package org.k8smantis.leader;

import io.mantisrx.server.core.BaseService;
import io.mantisrx.server.core.master.MasterDescription;
import io.mantisrx.server.core.master.MasterMonitor;
import org.jetbrains.annotations.Nullable;
import rx.Observable;

public class SidecarMasterMonitor extends BaseService implements MasterMonitor {
    @Override
    public void start() {

    }

    @Override
    public Observable<MasterDescription> getMasterObservable() {
        return Observable.just(getLatestMaster());
    }

    @Nullable
    @Override
    public MasterDescription getLatestMaster() {
        return new MasterDescription(
                "localhost",
                "127.0.0.1",
                10081,
                10082,
                10083,
                "",
                0,
                0L
        );
    }
}
