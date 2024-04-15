import React, { useEffect, useState } from 'react';
import ProductService from './ProductService';
import { Link, useNavigate } from 'react-router-dom';
import '../styles/ListStyle.css';


function ProductList() {
    const [products, setProducts] = useState([]);
    const navigate = useNavigate ();

    const handleBack = async () => {
        navigate('/');
    };

    const handleUpdate = async (productId) => {
        navigate(`/products/update/${productId}`);
    };

    const handleDelete = async (productId) => {
        try {
            await ProductService.deleteProduct(productId);
        } catch (error) {
            console.error('Error deleting product:', error);
        }
        const updatedProducts = await ProductService.getAllProducts();
        setProducts(updatedProducts);
    };

    const handleCreate = async () => {
        navigate(`/products/create`);
    };

    useEffect(() => {
        ProductService.getAllProducts().then(data => setProducts(data));
    }, []);

    return (
        <div className="list-container">
            <h2 className="list-header">Продукты</h2>
            <button className="list-create-btn" onClick={() => handleCreate()}>
                Создать продукт
            </button>
            <table className="list-table">
                <thead>
                <tr>
                    <th>Имя</th>
                    <th>Описание</th>
                    <th>Цена</th>
                    <th>Стоковое количество</th>
                    <th>Бренд</th>
                    <th>Действия</th>
                </tr>
                </thead>
                <tbody>
                {products.map(product => (
                    <tr key={product.productId}>
                        <td>{product.name}</td>
                        <td>{product.description}</td>
                        <td>{product.price}</td>
                        <td>{product.stockQuantity}</td>
                        <td>{product.brand.brandName}</td>
                        <td>
                            <button className="list-update-btn" onClick={() => handleUpdate(product.productId)}>
                                Изменить продукт
                            </button>
                            <button className="list-delete-btn" onClick={() => handleDelete(product.productId)}>
                                Удалить продукт
                            </button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
            <button className="list-back-btn" onClick={handleBack}>Назад</button>
        </div>

    );
}

export default ProductList;
