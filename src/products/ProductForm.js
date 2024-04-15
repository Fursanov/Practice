import React, {useEffect, useState} from 'react';
import ProductService from './ProductService';
import BrandService from '../brands/BrandService';
import { useNavigate } from 'react-router-dom';
import '../styles/FormStyle.css';

function ProductForm() {
    const [formData, setFormData] = useState({
        name: '',
        description: '',
        price: 0,
        stockQuantity: 0,
        brandId: 0,
        storesId: []
    });
    const navigate = useNavigate();
    const [brands, setBrands] = useState(null);

    useEffect(() => {
        BrandService.getAllBrands().then(data => {
            setBrands(data);
        });
    });

    const handleChange = e => {
        const { id, value } = e.target;
        setFormData({ ...formData, [id]: value });
    };

    const handleChangeNum = e => {
        const { id, value } = e.target;
        setFormData({ ...formData, [id]: parseInt(value) });
    };

    const handleBrandChange = (e) => {
        const { value } = e.target;
        if (value !== ""){
        setFormData({
            ...formData,
            brandId: parseInt(value)
        });}
    };

    const handleSubmit = async e => {
        e.preventDefault();
        try {
            console.log(formData);
            const createdProduct = await ProductService.createProduct(formData);
           } catch (error) {
            console.error('Error creating product:', error);
        }
        navigate('/products');
    };

    const handleBack = () => {
        navigate('/products');
    };

    return (
        <div className="form-container">
            <button className="form-back-btn" onClick={handleBack}>Назад</button>
            <h2 className="form-title">Создать Продукт</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-field">
                    <label className="form-label" htmlFor="name">Имя:</label>
                    <input
                        type="text"
                        id="name"
                        value={formData.name}
                        onChange={handleChange}
                        className="form-input"
                    />
                    <label className="form-label" htmlFor="description">Описание:</label>
                    <input
                        type="text"
                        id="description"
                        value={formData.description}
                        onChange={handleChange}
                        className="form-input"
                    />
                    <label className="form-label" htmlFor="price">Цена:</label>
                    <input
                        type="number"
                        id="price"
                        value={formData.price}
                        onChange={handleChangeNum}
                        className="form-input"
                    />
                    <label className="form-label" htmlFor="stockQuantity">Стоковое количество:</label>
                    <input
                        type="number"
                        id="stockQuantity"
                        value={formData.stockQuantity}
                        onChange={handleChangeNum}
                        className="form-input"
                    />
                    <label className="form-label" htmlFor="brand">Бренд:</label>
                    <select
                        className="form-control select-dropdown"
                        id="brandId"
                        value={formData.brandId}
                        onChange={handleBrandChange}
                        name="brandId"
                        className="form-input"
                    >
                        <option value="">Выберите бренд</option>
                        {brands && brands.map(brand => (
                            <option key={brand.brandId} value={brand.brandId}>{brand.brandName}</option>
                        ))}
                    </select>
                </div>
                <button type="submit" className="form-button">Создать Продукт</button>
            </form>
        </div>
    );
}

export default ProductForm;
