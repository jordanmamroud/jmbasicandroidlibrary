/* Copyright (c) 2012 Google Inc.
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

package ViewInstantiator;

/**
 * Exception thrown when something went wrong with in-app billing.
 * An PIabException has an associated PIabResult (an error).
 * To get the IAB result that caused this exception to be thrown,
 * call {@link #getResult()}.
 */
public class PIabException extends Exception {
    PIabResult mResult;

    public PIabException(PIabResult r) {
        this(r, null);
    }
    public PIabException(int response, String message) {
        this(new PIabResult(response, message));
    }
    public PIabException(PIabResult r, Exception cause) {
        super(r.getMessage(), cause);
        mResult = r;
    }
    public PIabException(int response, String message, Exception cause) {
        this(new PIabResult(response, message), cause);
    }

    /** Returns the IAB result (error) that this exception signals. */
    public PIabResult getResult() { return mResult; }
}