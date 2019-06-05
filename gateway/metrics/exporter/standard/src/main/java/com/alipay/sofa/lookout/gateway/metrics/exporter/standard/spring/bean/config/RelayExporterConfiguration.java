/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.lookout.gateway.metrics.exporter.standard.spring.bean.config;

import com.alipay.lookout.api.Registry;

import com.alipay.sofa.lookout.gateway.core.common.MonitorComponent;
import com.alipay.sofa.lookout.gateway.core.prototype.exporter.ConditionalOnExporterComponent;
import com.alipay.sofa.lookout.gateway.core.prototype.exporter.chain.ExportChain;
import com.alipay.sofa.lookout.gateway.core.prototype.exporter.chain.ipml.StaticExportChain;
import com.alipay.sofa.lookout.gateway.metrics.exporter.standard.RelayMetricExporterProperties;
import com.alipay.sofa.lookout.gateway.metrics.exporter.standard.StandardMetricExporter;
import com.alipay.sofa.lookout.gateway.metrics.pipeline.model.Metric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiangfeng.xzc
 * @date 2019/1/15
 */
@ConditionalOnExporterComponent(value = "standard", type = MonitorComponent.METRIC)
@Configuration
@EnableConfigurationProperties(RelayMetricExporterProperties.class)
public class RelayExporterConfiguration {
    @Autowired
    Registry                      registry;
    @Autowired
    RelayMetricExporterProperties properties;

    @Bean
    StandardMetricExporter standardMetricExporter() {
        return new StandardMetricExporter(registry, properties.getUrl(), properties.isCompress());
    }

    @Bean
    public ExportChain<Metric> standardMetricExporterExportChain() {
        return new StaticExportChain<>(standardMetricExporter());
    }
}
