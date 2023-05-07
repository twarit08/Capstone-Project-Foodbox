package com.foodbox.controller;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodbox.config.ImageUtil;
import com.foodbox.entities.CartItem;
import com.foodbox.entities.CartOrder;
import com.foodbox.entities.Product;
import com.foodbox.entities.ProductImage;
import com.foodbox.entities.ProductQuantity;
import com.foodbox.entities.UserOrder;
import com.foodbox.services.ProductService;
import com.foodbox.services.UserOrderService;

import jakarta.validation.Valid;

@RestController
public class UserOrderController {
	
	@Autowired
	private UserOrderService userOrderService;
	
	@Autowired
	private ProductService productService;
	
	@PreAuthorize("hasAuthority('CUSTOMER') or hasAuthority('ADMIN')")
	@PostMapping("/user/create/order")
	public ResponseEntity<?> createOrder(@Valid @RequestBody CartOrder cartOrder){
		UserOrder userOrder = new UserOrder();
		userOrder.setUsername(cartOrder.getUsername());
		userOrder.setFirstName(cartOrder.getFirstName());
		userOrder.setLastName(cartOrder.getLastName());
		userOrder.setAddress(cartOrder.getAddress());
		userOrder.setDistrict(cartOrder.getDistrict());
		userOrder.setState(cartOrder.getState());
		userOrder.setContact(cartOrder.getContact());
		userOrder.setPinCode(cartOrder.getPinCode());
		
		DateFormat df = DateFormat.getDateInstance();
		Calendar cl = Calendar.getInstance();
		String orderDate = df.format(cl.getTime());
		userOrder.setDate(orderDate);
		userOrder.setStatus("PLACED");
		userOrder.setPaidAmount(cartOrder.getPaidAmount());
		userOrder.setPaymentMode(cartOrder.getPaymentMode());
		
		Set<CartItem> cartItems = cartOrder.getCartItem();
		Set<ProductQuantity> pq = new HashSet<>();
		
		for(CartItem item : cartItems) {
			Product product = this.productService.getProductById(item.getPid());
			int quantity = item.getQuantity();
			ProductQuantity productQuantity = new ProductQuantity();
			productQuantity.setProduct(product);
			productQuantity.setQuantity(quantity);
			ProductQuantity saveProduct =  this.userOrderService.saveProductQuantity(productQuantity);
			pq.add(saveProduct);
		}
		userOrder.setProducts(pq);
		UserOrder orderCreated = this.userOrderService.saveOrder(userOrder);
		return ResponseEntity.ok(orderCreated);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/get/all/orders")
	public ResponseEntity<?> getAllOrders(){
		List<UserOrder> allOrders = this.userOrderService.getAllOrders();
		return ResponseEntity.ok(allOrders);
	}
	
	@PreAuthorize("hasAuthority('CUSTOMER')")
	@GetMapping("/get/orders/{username}")
	public ResponseEntity<?> getUserOrders(@PathVariable("username") String username){
		List<UserOrder> userOrders = this.userOrderService.getOrderByUsername(username);
		if(userOrders.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}else {
			return ResponseEntity.ok(userOrders);
		}
	}
	
	@PreAuthorize("hasAuthority('CUSTOMER') or hasAuthority('ADMIN')")
	@GetMapping("/get/order-invoice/{oid}")
	public ResponseEntity<?> getUserOrderById(@PathVariable("oid") Long oid){
		UserOrder order = this.userOrderService.getOrderById(oid);
		Set<ProductQuantity> products = order.getProducts();
		products.forEach(p -> {
			ProductImage img = new ProductImage();
			img.setImageData(ImageUtil.decompressImage(p.getProduct().getProductImage().getImageData()));
			img.setName(p.getProduct().getProductImage().getName());
			img.setImgId(p.getProduct().getProductImage().getImgId());
			img.setType(p.getProduct().getProductImage().getType());
			p.getProduct().setProductImage(img);
		});
		order.setProducts(products);
		return ResponseEntity.ok(order);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/delete/order/{oid}")
	public ResponseEntity<?> deleteOrderById(@PathVariable("oid") Long oid){
		this.userOrderService.deleteOrderById(oid);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
