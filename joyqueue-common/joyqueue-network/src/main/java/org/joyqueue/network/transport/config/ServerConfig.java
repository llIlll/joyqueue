/**
 * Copyright 2019 The JoyQueue Authors.
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
package org.joyqueue.network.transport.config;


/**
 * ServerConfig
 *
 * author: gaohaoxiang
 * date: 2018/8/13
 */
public class ServerConfig extends TransportConfig {
    public static final int DEFAULT_TRANSPORT_PORT = 50088;
    private int port = DEFAULT_TRANSPORT_PORT;

    public ServerConfig() {
    }

    public ServerConfig copy() {
        ServerConfig result = new ServerConfig();
        result.setHost(getHost());
        result.setAcceptThread(getAcceptThread());
        result.setAcceptThreadName(getAcceptThreadName());
        result.setIoThread(getIoThread());
        result.setIoThreadName(getIoThreadName());
        result.setMaxIdleTime(getMaxIdleTime());
        result.setReuseAddress(isReuseAddress());
        result.setSoLinger(getSoLinger());
        result.setTcpNoDelay(isTcpNoDelay());
        result.setKeepAlive(isKeepAlive());
        result.setSoTimeout(getSoTimeout());
        result.setSocketBufferSize(getSocketBufferSize());
        result.setFrameMaxSize(getFrameMaxSize());
        result.setBacklog(getBacklog());
        result.setMaxOneway(getMaxOneway());
        result.setNonBlockOneway(isNonBlockOneway());
        result.setNonBlockAsync(isNonBlockAsync());
        result.setMaxAsync(getMaxAsync());
        result.setCallbackThreads(getCallbackThreads());
        result.setSendTimeout(getSendTimeout());
        result.setMaxRetrys(getMaxRetrys());
        result.setMaxRetryDelay(getMaxRetryDelay());
        result.setRetryDelay(getRetryDelay());
        result.setUseExponentialBackOff(isUseExponentialBackOff());
        result.setBackOffMultiplier(getBackOffMultiplier());
        result.setExpireTime(getExpireTime());
        result.setRetryPolicy(getRetryPolicy());
        result.setClearInterval(getClearInterval());
        result.setUdp(isUdp());
        return result;
    }

    public void setPort(int port) {
        this.port = port;
    }


    public int getPort() {
        return port;
    }

    @Override
    public int getAcceptThread() {
        return super.getAcceptThread();
    }

    @Override
    public void setAcceptThread(int acceptThread) {
        super.setAcceptThread(acceptThread);
    }

    @Override
    public int getIoThread() {
        return super.getIoThread();
    }

    @Override
    public void setIoThread(int ioThread) {
        super.setIoThread(ioThread);
    }
}