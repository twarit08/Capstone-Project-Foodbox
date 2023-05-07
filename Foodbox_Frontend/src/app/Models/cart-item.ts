import { Product } from "./product";

export class CartItem {
    pid!: number;
    name!: string;
    price!: number;
    cuisineType!: string;
    dietType!: string;
    img!: any;
    quantity!: number;

    constructor(product: Product) {
        this.pid = product.pid;
        this.name = product.name;
        this.price = product.price;
        this.cuisineType = product.cuisineType;
        this.dietType = product.dietType;
        this.img = product.img;
        this.quantity = 1;
    }
}