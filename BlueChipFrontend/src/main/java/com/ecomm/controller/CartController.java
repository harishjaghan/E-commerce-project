package com.ecomm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecomm.dao.CartDAO;
import com.ecomm.dao.ProductDAO;
import com.ecomm.model.Cart;
import com.ecomm.model.Product;



@Controller
public class CartController
{
	
@Autowired
CartDAO cartDao;

@Autowired
ProductDAO productDao;

@RequestMapping(value="/showCart")
public String showCart(Model m)
{
	
	String username="tarun";
	List<Cart>cartItemList=cartDao.listCartItems(username);
	m.addAttribute("cartItemList", cartItemList);
	m.addAttribute("grandTotal", this.getGrandTotal(cartItemList));
	 
	return "Cart";
	
	
}


@RequestMapping(value="/addToCart/{productId}")
public String addToCart(@PathVariable("productId")int productId,@RequestParam("quantity")int quantity,Model m)
{
	
	String username="tarun";
	Product product=productDao.getProduct(productId);
	Cart cartItem=new Cart();
	cartItem.setProductId(product.getProductId());
	cartItem.setProductName(product.getProductName());
	cartItem.setPrice(product.getPrice());
	cartItem.setQuantity(quantity);
	cartItem.setUsername(username);
	cartItem.setProductStatus("NP");
	
	cartDao.addCartItem(cartItem);
	List<Cart>cartItemList=cartDao.listCartItems(username);
	m.addAttribute("cartItemList", cartItemList);
	m.addAttribute("grandTotal", this.getGrandTotal(cartItemList));
	return "Cart";
}

@RequestMapping(value="/updateCartItem/{cartItemId}")
public String updateCartItem(@PathVariable("cartItemId")int cartItemId,@RequestParam("quantity")int quantity,Model m)
{
	
	String username="tarun";
	Cart cartItem=cartDao.getCartItem(cartItemId);
	cartItem.setQuantity(quantity);
	cartDao.updateCartItem(cartItem);

	List<Cart>cartItemList=cartDao.listCartItems(username);
	m.addAttribute("cartItemList", cartItemList);
	m.addAttribute("grandTotal", this.getGrandTotal(cartItemList));

	return "Cart";
	
}

@RequestMapping(value="/deleteCartItem/{cartItemId}")
public String updateCartItem(@PathVariable("cartItemId")int cartItemId,Model m)
{
	
	String username="tarun";
	Cart cartItem=cartDao.getCartItem(cartItemId);
	cartDao.deleteCartItem(cartItem);
	List<Cart>cartItemList=cartDao.listCartItems(username);
	m.addAttribute("cartItemList", cartItemList);
	m.addAttribute("grandTotal", this.getGrandTotal(cartItemList));
	return "Cart";

}

public int getGrandTotal(List<Cart>cartItemList)
{
	
	int grandTotal=0;
	int count=0;
	
while(count<cartItemList.size())
{
	Cart cartItem=cartItemList.get(count);
	grandTotal= grandTotal+(cartItem.getQuantity()*cartItem.getPrice());
	count++;
}
 
return grandTotal;

}

@RequestMapping(value="/checkOut")
public String showOderConfirm(Model m)
{
	
	String username="tarun";
	List<Cart>cartItemList=cartDao.listCartItems(username);
	m.addAttribute("cartItemList", cartItemList);
	m.addAttribute("grandtotal", this.getGrandTotal(cartItemList));
	
	return "OrderConfirm";
	
}
}