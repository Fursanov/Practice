import React, { useState } from 'react';
import BrandService from './BrandService';
import { useNavigate } from 'react-router-dom';
import '../styles/FormStyle.css';

function BrandForm() {
    const [formData, setFormData] = useState({
        brandName: '',
    });
    const navigate = useNavigate();

    const handleChange = e => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async e => {
        e.preventDefault();
        try {
            const createdBrand = await BrandService.createBrand(formData);
           } catch (error) {
            console.error('Error creating brand:', error);
        }
        navigate('/brands');
    };

    const handleBack = () => {
        navigate('/brands');
    };

    return (
        <div className="form-container">
            <button className="form-back-btn" onClick={handleBack}>Назад</button>
            <h2 className="form-title">Создать Бренд</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-field">
                    <label className="form-label" htmlFor="brandName">Имя:</label>
                    <input type="text"
                           id="brandName"
                           name="brandName"
                           value={formData.brandName}
                           onChange={handleChange}
                           className="form-input"
                    />
                </div>
                <button type="submit" className="form-button">Создать Бренд</button>
            </form>
        </div>
    );
}

export default BrandForm;
