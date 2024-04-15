import { BASE_URL } from '../config';

class StoreService {
    async getAllStores() {
        const response = await fetch(`${BASE_URL}/stores/all`);
        return response.json();
    }

    async getStoreById(id) {
        const response = await fetch(`${BASE_URL}/stores/get/${id}`);
        return response.json();
    }

    async createStore(storeData) {
        const response = await fetch(`${BASE_URL}/stores/new`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(storeData),
        });
        return response.json();
    }

    async updateStore(storeData) {
        const response = await fetch(`${BASE_URL}/stores/update`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(storeData),
        });
        return response.json();
    }

    async updateStoreProducts(storeData) {
        const response = await fetch(`${BASE_URL}/stores/products/update`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(storeData),
        });
        return response.json();
    }

    async deleteStore(storeId) {
        const response = await fetch(`${BASE_URL}/stores/delete/${storeId}`, {
            method: 'DELETE',
        });
        return response.json();
    }
}

export default new StoreService();
