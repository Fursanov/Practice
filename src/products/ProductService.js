import { BASE_URL } from '../config';

class ProductService {
    async getAllProducts() {
        const response = await fetch(`${BASE_URL}/products/all`);
        return response.json();
    }

    async getProductById(id) {
        const response = await fetch(`${BASE_URL}/products/get/${id}`);
        return response.json();
    }

    async createProduct(productData) {
        const response = await fetch(`${BASE_URL}/products/new`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(productData),
        });
        return response.json();
    }

    async updateProduct(productData) {
        const response = await fetch(`${BASE_URL}/products/update`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(productData),
        });
        return response.json();
    }

    async deleteProduct(productId) {
        const response = await fetch(`${BASE_URL}/products/delete/${productId}`, {
            method: 'DELETE',
        });
        return response.json();
    }
}

export default new ProductService();
