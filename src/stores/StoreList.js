import React, { useEffect, useState } from 'react';
import StoreService from './StoreService';
import { Link, useNavigate } from 'react-router-dom';
import '../styles/ListStyle.css';


function StoreList() {
    const [stores, setStores] = useState([]);
    const navigate = useNavigate ();

    const handleBack = async () => {
        navigate('/');
    };

    const handleUpdate = async (storeId) => {
        navigate(`/stores/update/${storeId}`);
    };

    const handleProducts = async (storeId) => {
        navigate(`/stores/products/${storeId}`);
    };

    const handleDelete = async (storeId) => {
        try {
            await StoreService.deleteStore(storeId);
        } catch (error) {
            console.error('Error deleting store:', error);
        }
        const updatedStores = await StoreService.getAllStores();
        setStores(updatedStores);
    };

    const handleCreate = async () => {
        navigate(`/stores/create`);
    };

    useEffect(() => {
        StoreService.getAllStores().then(data => setStores(data));
    }, []);

    return (
        <div className="list-container">
            <h2 className="list-header">Магазины</h2>
            <button className="list-create-btn" onClick={() => handleCreate()}>
                Создать магазин
            </button>
            <table className="list-table">
                <thead>
                <tr>
                    <th>Имя</th>
                    <th>Местоположение</th>
                    <th>Действия</th>
                </tr>
                </thead>
                <tbody>
                {stores.map(store => (
                    <tr key={store.storeId}>
                        <td>{store.storeName}</td>
                        <td>{store.location}</td>
                        <td>
                            <button className="list-update-btn" onClick={() => handleProducts(store.storeId)}>
                                Посмотреть продукты
                            </button>
                            <button className="list-update-btn" onClick={() => handleUpdate(store.storeId)}>
                                Изменить магазин
                            </button>
                            <button className="list-delete-btn" onClick={() => handleDelete(store.storeId)}>
                                Удалить магазин
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

export default StoreList;
