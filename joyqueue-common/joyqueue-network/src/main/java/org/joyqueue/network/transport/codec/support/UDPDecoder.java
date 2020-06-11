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
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.joyqueue.network.transport.codec.Codec;
import org.joyqueue.network.transport.command.Command;
import org.joyqueue.network.transport.exception.TransportException;

import java.util.List;

/**
 * UdpDecoder
 *
 * author: gaohaoxiang
 * date: 2018/8/14
 */
public class UDPDecoder extends MessageToMessageDecoder<DatagramPacket> {

    private Codec codec;

    public UDPDecoder(Codec codec) {
        this.codec = codec;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, DatagramPacket in, List<Object> out) throws Exception {
        try {
            ByteBuf buffer = in.content();
            Object payload = codec.decode(buffer);
            if (payload != null) {
                if (payload instanceof Command) {
                    ((Command) payload).setUdp(true);
                    ((Command) payload).setAttachment(in);
                }
                out.add(payload);
            }
        } catch (Exception e) {
            if (e instanceof TransportException.CodecException) {
                throw e;
            } else {
                throw new TransportException.CodecException(e);
            }
        }
    }
}