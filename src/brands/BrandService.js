import { BASE_URL } from '../config';

class BrandService {
    async getAllBrands() {
        const response = await fetch(`${BASE_URL}/brands/all`);
        return response.json();
    }

    async getBrandById(id) {
        const response = await fetch(`${BASE_URL}/brands/get/${id}`);
        return response.json();
    }

    async createBrand(brandData) {
        const response = await fetch(`${BASE_URL}/brands/new`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(brandData),
        });
        return response.json();
    }

    async updateBrand(brandData) {
        const response = await fetch(`${BASE_URL}/brands/update`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(brandData),
        });
        return response.json();
    }

    async deleteBrand(brandId) {
        const response = await fetch(`${BASE_URL}/brands/delete/${brandId}`, {
            method: 'DELETE',
        });
        return response.json();
    }
}

export default new BrandService();
