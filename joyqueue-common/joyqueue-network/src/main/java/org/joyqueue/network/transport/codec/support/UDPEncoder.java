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
package org.joyqueue.network.transport.codec.support;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultAddressedEnvelope;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.joyqueue.network.transport.codec.Codec;
import org.joyqueue.network.transport.exception.TransportException;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * UdpEncoder
 *
 * author: gaohaoxiang
 * date: 2018/8/14
 */
public class UDPEncoder extends MessageToMessageEncoder<Object> {

    private Codec codec;

    public UDPEncoder(Codec codec) {
        this.codec = codec;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, List out) throws Exception {
        try {
            if (!(msg instanceof DatagramPacket) && !(msg instanceof DefaultAddressedEnvelope)) {
                throw new UnsupportedOperationException();
            }
            ByteBuf buffer = Unpooled.buffer();
            codec.encode(msg, buffer);

            if (msg instanceof DatagramPacket) {
                out.add(new DatagramPacket(buffer, ((DatagramPacket) msg).sender()));
            } else {
                out.add(new DatagramPacket(buffer, (InetSocketAddress) ((DefaultAddressedEnvelope) msg).sender()));
            }
        } catch (Exception e) {
            if (e instanceof TransportException.CodecException) {
                throw e;
            } else {
                throw new TransportException.CodecException(e.getMessage());
            }
        }
    }
}