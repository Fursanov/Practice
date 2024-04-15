import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import ProductService from './ProductService';
import BrandService from '../brands/BrandService';
import '../styles/FormStyle.css';

function ProductUpdate() {
    const { id } = useParams();
    const [product, setProduct] = useState(null);
    const [brands, setBrands] = useState(null);
    const [updatedProduct, setUpdatedProduct] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        BrandService.getAllBrands().then(data => {
            setBrands(data);
        });
        ProductService.getProductById(id).then(data => {
            setProduct(data);
            setUpdatedProduct(data);
        });
    }, [id]);

    const handleUpdate = async () => {
        console.log(updatedProduct);
        try {
            await ProductService.updateProduct(updatedProduct);
            navigate('/products');
        } catch (error) {
            console.error('Error updating product:', error);
        }
    };

    const handleBack = () => {
        navigate('/products');
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setUpdatedProduct(prevProduct => ({
            ...prevProduct,
            [name]: value
        }));
    };

    const handleBrandChange = (e) => {
        const { value } = e.target;
        const selectedBrand = brands.find(brand => brand.brandId === parseInt(value));
        setUpdatedProduct(prevProduct => ({
            ...prevProduct,
            brand: selectedBrand
        }));
    };


    if (!product) return <div>Загрузка...</div>;

    return (
        <div className="form-container">
            <button className="form-back-btn" onClick={handleBack}>Назад</button>
            <h2 className="form-title">Имя продукта: {product.name}</h2>
            <div className="form-field">
                <label className="form-label" htmlFor="name">Имя:</label>
                <input
                    type="text"
                    name="name"
                    value={updatedProduct.name}
                    onChange={handleInputChange}
                    className="form-input"
                />
                <label className="form-label" htmlFor="description">Описание:</label>
                <input
                    type="text"
                    name="description"
                    value={updatedProduct.description}
                    onChange={handleInputChange}
                    className="form-input"
                />
                <label className="form-label" htmlFor="price">Цена:</label>
                <input
                    type="number"
                    name="price"
                    value={updatedProduct.price}
                    onChange={handleInputChange}
                    className="form-input"
                />
                <label className="form-label" htmlFor="stockQuantity">Стоковое количество:</label>
                <input
                    type="number"
                    name="stockQuantity"
                    value={updatedProduct.stockQuantity}
                    onChange={handleInputChange}
                    className="form-input"
                />
                <label className="form-label" htmlFor="brand">Бренд:</label>
                <select
                    className="form-control select-dropdown"
                    id="brandId"
                    value={updatedProduct.brand.brandId}
                    onChange={handleBrandChange}
                    name="brandId"
                    className="form-input"
                >
                    {brands && brands.map(brand => (
                        <option key={brand.brandId} value={brand.brandId}>{brand.brandName}</option>
                    ))}
                </select>
            </div>
            <button className="form-button" onClick={handleUpdate}>Обновить продукт</button>
        </div>
    );
}

export default ProductUpdate;
