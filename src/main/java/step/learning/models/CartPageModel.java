package step.learning.models;

import step.learning.dal.dto.CartItem;
import step.learning.dal.dto.ProductItem;

import java.util.List;

public class CartPageModel {
    private List<ProductItem> products;
    private List<CartItem> cartItems;

    public CartPageModel(List<ProductItem> products, List<CartItem> cartItems) {
        this.products = products;
        this.cartItems = cartItems;
    }

    public List<ProductItem> getProducts(){
        return products;
    }

    public List<CartItem> getCartItems(){
        return cartItems;
    }
}
