import React, {useEffect, useState} from 'react';
import StoreService from './StoreService';
import { useNavigate } from 'react-router-dom';
import '../styles/FormStyle.css';

function StoreForm() {
    const [formData, setFormData] = useState({
        storeName: '',
        location: ''
    });
    const navigate = useNavigate();

    const handleChange = e => {
        const { id, value } = e.target;
        setFormData({ ...formData, [id]: value });
    };

    const handleSubmit = async e => {
        e.preventDefault();
        try {
            console.log(formData);
            const createdStore = await StoreService.createStore(formData);
           } catch (error) {
            console.error('Error creating store:', error);
        }
        navigate('/stores');
    };

    const handleBack = () => {
        navigate('/stores');
    };

    return (
        <div className="form-container">
            <button className="form-back-btn" onClick={handleBack}>Назад</button>
            <h2 className="form-title">Создать Магазин</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-field">
                    <label className="form-label" htmlFor="storeName">Имя:</label>
                    <input
                        type="text"
                        id="storeName"
                        value={formData.storeName}
                        onChange={handleChange}
                        className="form-input"
                    />
                    <label className="form-label" htmlFor="location">Местоположение:</label>
                    <input
                        type="text"
                        id="location"
                        value={formData.location}
                        onChange={handleChange}
                        className="form-input"
                    />
                </div>
                <button type="submit" className="form-button">Создать Магазин</button>
            </form>
        </div>
    );
}

export default StoreForm;
