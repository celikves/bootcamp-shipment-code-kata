package com.trendyol.shipment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {
    private List<Product> products;
    private static final int THRESHOLD_FOR_COUNT = 3;

    public ShipmentSize getShipmentSize() {

        final Map<ShipmentSize, ShipmentSize> sizeForBasket = new HashMap<>();
        //Put shipment sizes as keys and put the next closest as value.
        sizeForBasket.put(ShipmentSize.SMALL, ShipmentSize.MEDIUM);
        sizeForBasket.put(ShipmentSize.MEDIUM, ShipmentSize.LARGE);
        sizeForBasket.put(ShipmentSize.LARGE, ShipmentSize.X_LARGE);

        Map<ShipmentSize, Integer> shipmentSizeCounts = new HashMap<>();

        //If the count is bigger than threshold get the value of this size from sizeForBasket HashMap.
        for (Product product : products) {
            ShipmentSize size = product.getSize();
            shipmentSizeCounts.put(size, shipmentSizeCounts.getOrDefault(size, 0) + 1);
            if (shipmentSizeCounts.get(size) >= THRESHOLD_FOR_COUNT) {
                return sizeForBasket.getOrDefault(size, ShipmentSize.X_LARGE);
            }
        }
        return getBiggestShipmentSize();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    private ShipmentSize getBiggestShipmentSize() {
        //Initialize with the smallest size
        ShipmentSize biggestShipmentSize = ShipmentSize.SMALL;
        //Iterate through products and get the biggest one
        for (Product product : products) {
            if (product.getSize().compareTo(biggestShipmentSize) > 0) {
                biggestShipmentSize = product.getSize();
            }
        }
        return biggestShipmentSize;
    }
}
