/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingsphere.core.event.connection;

import java.util.ServiceLoader;

/**
 * Connection event handler SPI loader.
 *
 * @author zhangliang
 */
public final class CloseConnectionEventHandlerSPILoader {
    
    private static final CloseConnectionEventHandlerSPILoader INSTANCE = new CloseConnectionEventHandlerSPILoader();
    
    private final ServiceLoader<CloseConnectionEventHandler> serviceLoader;
    
    private CloseConnectionEventHandlerSPILoader() {
        serviceLoader = ServiceLoader.load(CloseConnectionEventHandler.class);
    }
    
    /**
     * Get instance.
     *
     * @return instance
     */
    public static CloseConnectionEventHandlerSPILoader getInstance() {
        return INSTANCE;
    }
    
    /**
     * Handle close connection start event.
     *
     * @param event get connection start event
     */
    public void handle(final CloseConnectionStartEvent event) {
        for (CloseConnectionEventHandler each : serviceLoader) {
            each.handle(event);
        }
    }
    
    /**
     * Handle close connection finish event.
     *
     * @param event get connection finish event
     */
    public void handle(final CloseConnectionFinishEvent event) {
        for (CloseConnectionEventHandler each : serviceLoader) {
            each.handle(event);
        }
    }
}
