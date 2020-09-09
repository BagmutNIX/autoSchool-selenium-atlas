package com.enums;

public enum HomePage {
    CARTLABEL("Cart");

    private final String cartLabel;

    HomePage(String cartLabel) {
        this.cartLabel = cartLabel;
    }

    public String getCartLabel() {
        return cartLabel;
    }
}
