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

package viewsystem.ViewInstantiator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a block of information about in-app items.
 * An PInventory is returned by such methods as {@link ViewInstantiatorHelper#queryInventory}.
 */
public class PInventory {
    Map<String,PDetails> mSkuMap = new HashMap<String,PDetails>();
    Map<String,PPurchases> mPurchaseMap = new HashMap<String,PPurchases>();

    PInventory() { }

    /** Returns the listing details for an in-app product. */
    public PDetails getSkuDetails(String sku) {
        return mSkuMap.get(sku);
    }

    /** Returns purchase information for a given product, or null if there is no purchase. */
    public PPurchases getPurchase(String sku) {
        return mPurchaseMap.get(sku);
    }

    /** Returns whether or not there exists a purchase of the given product. */
    public boolean hasPurchase(String sku) {
        return mPurchaseMap.containsKey(sku);
    }

    /** Return whether or not details about the given product are available. */
    public boolean hasDetails(String sku) {
        return mSkuMap.containsKey(sku);
    }

    /**
     * Erase a purchase (locally) from the inventory, given its product ID. This just
     * modifies the PInventory object locally and has no effect on the server! This is
     * useful when you have an existing PInventory object which you know to be up to date,
     * and you have just consumed an item successfully, which means that erasing its
     * purchase data from the PInventory you already have is quicker than querying for
     * a new PInventory.
     */
    public void erasePurchase(String sku) {
        if (mPurchaseMap.containsKey(sku)) mPurchaseMap.remove(sku);
    }

    /** Returns a list of all owned product IDs. */
    List<String> getAllOwnedSkus() {
        return new ArrayList<String>(mPurchaseMap.keySet());
    }

    /** Returns a list of all owned product IDs of a given type */
    List<String> getAllOwnedSkus(String itemType) {
        List<String> result = new ArrayList<String>();
        for (PPurchases p : mPurchaseMap.values()) {
            if (p.getItemType().equals(itemType)) result.add(p.getSku());
        }
        return result;
    }

    /** Returns a list of all purchases. */
    List<PPurchases> getAllPurchases() {
        return new ArrayList<PPurchases>(mPurchaseMap.values());
    }

    void addSkuDetails(PDetails d) {
        mSkuMap.put(d.getSku(), d);
    }

    void addPurchase(PPurchases p) {
        mPurchaseMap.put(p.getSku(), p);
    }
}
