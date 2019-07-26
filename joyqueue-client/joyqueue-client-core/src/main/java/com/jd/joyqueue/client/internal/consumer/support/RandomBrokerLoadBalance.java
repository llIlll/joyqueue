/**
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
package com.jd.joyqueue.client.internal.consumer.support;

import com.jd.joyqueue.client.internal.consumer.BrokerLoadBalance;
import com.jd.joyqueue.client.internal.consumer.coordinator.domain.BrokerAssignment;
import com.jd.joyqueue.client.internal.consumer.coordinator.domain.BrokerAssignments;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;

/**
 * RandomBrokerLoadBalance
 * author: gaohaoxiang
 * email: gaohaoxiang@jd.com
 * date: 2018/12/12
 */
public class RandomBrokerLoadBalance implements BrokerLoadBalance {

    public static final String NAME = "random";

    @Override
    public BrokerAssignment loadBalance(BrokerAssignments brokerAssignments) {
        List<BrokerAssignment> assignments = brokerAssignments.getAssignments();
        return assignments.get(RandomUtils.nextInt(0, assignments.size()));
    }

    @Override
    public String type() {
        return NAME;
    }

}