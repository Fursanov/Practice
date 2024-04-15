import React, { useEffect, useState } from 'react';
import StoreService from './StoreService';
import ProductService from '../products/ProductService'
import { Link, useNavigate, useParams } from 'react-router-dom';
import '../styles/ListStyle.css';


function StoreProducts() {
    const { id } = useParams();
    const [store, setStore] = useState();
    const [products, setProducts] = useState([]);
    const navigate = useNavigate ();

    const handleBack = async () => {
        navigate('/stores');
    };

    const addProduct = async (productId) => {
        const updatedStore = store;
        const selectedProduct = products.find(product => product.productId === parseInt(productId));
        updatedStore.products.push(selectedProduct);
        try {
            await StoreService.updateStoreProducts(updatedStore);
        } catch (error) {
            console.error('Error updating store:', error);
        }
        StoreService.getStoreById(id).then(data => setStore(data));
        ProductService.getAllProducts().then(data => setProducts(data));
    };

    const removeProduct = async (productId) => {
        const updatedStore = store;
        const selectedProduct = products.find(product => product.productId === parseInt(productId));
        updatedStore.products.splice(updatedStore.products.indexOf(selectedProduct), 1);
        try {
            await StoreService.updateStoreProducts(updatedStore);
        } catch (error) {
            console.error('Error updating store:', error);
        }
        StoreService.getStoreById(id).then(data => setStore(data));
        ProductService.getAllProducts().then(data => setProducts(data));
    };

    useEffect(() => {
        StoreService.getStoreById(id).then(data => setStore(data));
        ProductService.getAllProducts().then(data => setProducts(data));
    }, []);

    if (!store || !products) return <div>Загрузка...</div>;

    return (
    <div>
        <div className="list-container">
            <h2 className="list-header">Сток</h2>
            <button className="list-back-btn" onClick={handleBack}>Назад</button>
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
                {products && products
                    .filter(product => !store.products.some(storeProduct => storeProduct.productId === product.productId))
                    .map(product => (
                    <tr key={product.productId}>
                        <td>{product.name}</td>
                        <td>{product.description}</td>
                        <td>{product.price}</td>
                        <td>{product.stockQuantity}</td>
                        <td>{product.brand.brandName}</td>
                        <td>
                            <button className="list-delete-btn" onClick={() => addProduct(product.productId)}>
                                Добавить продукт в магазин
                            </button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>

        <div className="list-container">
            <h2 className="list-header">{store.storeName}</h2>
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
                {store.products && store.products.map(product => (
                    <tr key={product.productId}>
                        <td>{product.name}</td>
                        <td>{product.description}</td>
                        <td>{product.price}</td>
                        <td>{product.stockQuantity}</td>
                        <td>{product.brand.brandName}</td>
                        <td>
                            <button className="list-delete-btn" onClick={() => removeProduct(product.productId)}>
                                Удалить продукт из магазина
                            </button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    </div>

    );
}

export default StoreProducts;
