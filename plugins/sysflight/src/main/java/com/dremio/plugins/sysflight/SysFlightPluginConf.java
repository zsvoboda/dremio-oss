/*
 * Copyright (C) 2017-2019 Dremio Corporation
 *
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
 */
package com.dremio.plugins.sysflight;

import java.util.Collections;

import javax.inject.Provider;

import com.dremio.exec.catalog.StoragePluginId;
import com.dremio.exec.catalog.conf.ConnectionConf;
import com.dremio.exec.catalog.conf.SourceType;
import com.dremio.exec.proto.CoordinationProtos.NodeEndpoint;
import com.dremio.exec.server.SabotContext;
import com.dremio.exec.store.sys.SystemTable;

import io.protostuff.Tag;

/**
 * Connection config for Sys-flight
 */
@SourceType(value = "SYSFLIGHT", configurable = false)
public class SysFlightPluginConf extends ConnectionConf<SysFlightPluginConf, SysFlightStoragePlugin>{

  @Tag(1)
  public NodeEndpoint endpoint;

  @Override
  public SysFlightStoragePlugin newPlugin(SabotContext context, String name, Provider<StoragePluginId> pluginIdProvider) {
    return new SysFlightStoragePlugin(this, context, name, true,
      Collections.singletonList(SystemTable.DEPENDENCIES));
  }

  @Override
  public boolean isInternal() {
    return true;
  }
}
