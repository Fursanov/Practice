import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import StoreService from './StoreService';
import '../styles/FormStyle.css';

function StoreUpdate() {
    const { id } = useParams();
    const [store, setStore] = useState(null);
    const [updatedStore, setUpdatedStore] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        StoreService.getStoreById(id).then(data => {
            setStore(data);
            setUpdatedStore(data);
        });
    }, [id]);


    const handleUpdate = async () => {
        console.log(updatedStore);
        try {
            await StoreService.updateStore(updatedStore);
            navigate('/stores');
        } catch (error) {
            console.error('Error updating store:', error);
        }
    };

    const handleBack = () => {
        navigate('/stores');
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setUpdatedStore(prevStore => ({
            ...prevStore,
            [name]: value
        }));
    };

    if (!store) return <div>Загрузка...</div>;

    return (
        <div className="form-container">
            <button className="form-back-btn" onClick={handleBack}>Назад</button>
            <h2 className="form-title">Имя магазина: {store.storeName}</h2>
            <div className="form-field">
                <label className="form-label" htmlFor="storeName">Имя:</label>
                <input
                    type="text"
                    name="storeName"
                    value={updatedStore.storeName}
                    onChange={handleInputChange}
                    className="form-input"
                />
                <label className="form-label" htmlFor="location">Местоположение:</label>
                <input
                    type="text"
                    name="location"
                    value={updatedStore.location}
                    onChange={handleInputChange}
                    className="form-input"
                />
            </div>
            <button className="form-button" onClick={handleUpdate}>Обновить магазин</button>
        </div>
    );
}

export default StoreUpdate;
