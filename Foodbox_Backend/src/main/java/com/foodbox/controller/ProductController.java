package com.foodbox.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodbox.config.ImageUtil;
import com.foodbox.entities.Product;
import com.foodbox.entities.ProductImage;
import com.foodbox.services.ProductService;
import jakarta.validation.Valid;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	//add new product
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/add/new-product")
	public ResponseEntity<?> addNewProduct(@RequestParam("product") String product, @RequestParam("image") MultipartFile file) throws IOException{
		ProductImage img = new ProductImage();
		img.setName(file.getOriginalFilename());
		img.setType(file.getContentType());
		img.setImageData(ImageUtil.compressImage(file.getBytes()));
		Product newProduct = null;
		
		try {
			newProduct = objectMapper.readValue(product, Product.class);
			newProduct.setProductImage(img);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		Product saveProduct = this.productService.addProduct(newProduct);
		return ResponseEntity.ok(saveProduct);
	}
	
	//update product details
	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/update/product/{pid}")
	public ResponseEntity<?> updateProduct(@PathVariable("pid") Long pid,@Valid @RequestBody Product product){
		this.productService.updateProduct(pid, product);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	//set product availability
	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/set-availability/product/{pid}")
	public ResponseEntity<?> setAvailability(@PathVariable("pid") Long pid,@RequestBody Product product){
		this.productService.setAvailability(pid, product);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	//show all products
	@GetMapping("/get/all-products")
	public ResponseEntity<?> getAllProducts(){
		List<Product> products = this.productService.getAllProducts();
		products.forEach(p -> {
			ProductImage image = new ProductImage();
			image.setImageData(ImageUtil.decompressImage(p.getProductImage().getImageData()));
			image.setImgId(p.getProductImage().getImgId());
			image.setName(p.getProductImage().getName());
			image.setType(p.getProductImage().getType());
			p.setProductImage(image);
		});
		if(products.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}else {
			return ResponseEntity.ok(products);
		}
	}
	
	//show products by name
	@GetMapping("/get/product/{name}")
	public ResponseEntity<?> getProductByName(@PathVariable("name") String name){
		List<Product> products = this.productService.getAllByName(name);
		products.forEach(p -> {
			ProductImage image = new ProductImage();
			image.setImageData(ImageUtil.decompressImage(p.getProductImage().getImageData()));
			image.setImgId(p.getProductImage().getImgId());
			image.setName(p.getProductImage().getName());
			image.setType(p.getProductImage().getType());
			p.setProductImage(image);
		});
		if(products.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}else {
			return ResponseEntity.ok(products);
		}
	}
	
	//get product by cuisine type
	@GetMapping("/get/cuisine-type/{cuisine}")
	public ResponseEntity<?> getProductByCuisineType(@PathVariable("cuisine") String cuisine){
		List<Product> products = this.productService.getAllByCuisine(cuisine);
		products.forEach(p -> {
			ProductImage image = new ProductImage();
			image.setImageData(ImageUtil.decompressImage(p.getProductImage().getImageData()));
			image.setImgId(p.getProductImage().getImgId());
			image.setName(p.getProductImage().getName());
			image.setType(p.getProductImage().getType());
			p.setProductImage(image);
		});
		if(products.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}else {
			return ResponseEntity.ok(products);
		}
	}
	
	//get product by id
	@GetMapping("/get/product/id/{pid}")
	public ResponseEntity<?> getProductById(@PathVariable("pid") Long pid){
		Product product = this.productService.getProductById(pid);
		ProductImage img =  new ProductImage();
		img.setImageData(ImageUtil.decompressImage(product.getProductImage().getImageData()));
		img.setImgId(product.getProductImage().getImgId());
		img.setName(product.getProductImage().getName());
		img.setType(product.getProductImage().getType());
		product.setProductImage(img);
		return ResponseEntity.ok(product);
	}
	
	//delete product by id
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/delete/product/id/{pid}")
	public ResponseEntity<?> deleteProduct(@PathVariable("pid") Long pid){
		this.productService.deleteProductById(pid);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
