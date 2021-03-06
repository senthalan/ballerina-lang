/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.ballerinalang.siddhi.core.executor.condition;

import org.ballerinalang.siddhi.core.event.ComplexEvent;
import org.ballerinalang.siddhi.core.event.state.StateEvent;
import org.ballerinalang.siddhi.core.event.stream.StreamEvent;
import org.ballerinalang.siddhi.core.executor.ExpressionExecutor;

/**
 * Executor class for IsNullStream condition. Condition evaluation logic is implemented within executor.
 */
public class IsNullStreamConditionExpressionExecutor extends ConditionExpressionExecutor {

    private int[] eventPosition;

    public IsNullStreamConditionExpressionExecutor(int[] eventPosition) {
        this.eventPosition = eventPosition;
    }

    public Boolean execute(ComplexEvent event) {
        if (eventPosition == null) {
            if (event == null) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        } else {
            StreamEvent streamEvent = ((StateEvent) event).getStreamEvent(eventPosition);
            if (streamEvent == null) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        }
    }

    @Override
    public ExpressionExecutor cloneExecutor(String key) {
        return new IsNullStreamConditionExpressionExecutor(eventPosition);
    }

}
