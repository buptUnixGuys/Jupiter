/*
 * Copyright (c) 2015 The Jupiter Project
 *
 * Licensed under the Apache License, version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jupiter.rpc.consumer.dispatcher;

import org.jupiter.rpc.ConsumerHook;
import org.jupiter.rpc.JClient;
import org.jupiter.rpc.model.metadata.MessageWrapper;
import org.jupiter.rpc.model.metadata.ServiceMetadata;
import org.jupiter.serialization.Serializer;
import org.jupiter.transport.channel.JChannel;

import java.util.List;
import java.util.Map;

/**
 * Dispatcher for consumer.
 *
 * jupiter
 * org.jupiter.rpc.consumer.dispatcher
 *
 * @author jiachun.fjc
 */
public interface Dispatcher {

    /**
     * Consumer消息派发, 不需要传入目标方法参数类型, 服务端会根据args具体类型按照JLS规则动态dispatch.
     */
    Object dispatch(JClient client, String methodName, Object[] args, Class<?> returnType);

    /**
     * Consumer指定channel消息派发, 不需要传入目标方法参数类型, 服务端会根据args具体类型按照JLS规则动态dispatch.
     */
    Object dispatch(
            JClient client, JChannel channel, String methodName, Object[] args, Class<?> returnType, long timeoutMillis);

    /**
     * Selects a {@link JChannel} from the load balancer.
     */
    JChannel select(JClient client, MessageWrapper message);

    ServiceMetadata getMetadata();

    Serializer getSerializer();

    ConsumerHook[] getHooks();

    void setHooks(List<ConsumerHook> hooks);

    long getTimeoutMillis();

    void setTimeoutMillis(long timeoutMillis);

    long getMethodSpecialTimeoutMillis(String methodName);

    void setMethodsSpecialTimeoutMillis(Map<String, Long> methodsSpecialTimeoutMillis);
}
