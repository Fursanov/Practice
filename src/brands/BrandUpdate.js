import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import BrandService from './BrandService';
import '../styles/FormStyle.css';

function BrandUpdate() {
    const { id } = useParams();
    const [brand, setBrand] = useState(null);
    const [updatedBrand, setUpdatedBrand] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        BrandService.getBrandById(id).then(data => {
            setBrand(data);
            setUpdatedBrand(data); // Устанавливаем начальное значение
        });
    }, [id]);

    const handleUpdate = async () => {
        try {
            await BrandService.updateBrand(updatedBrand);
            navigate('/brands');
        } catch (error) {
            console.error('Error updating brand:', error);
        }
    };

    const handleBack = () => {
        navigate('/brands');
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setUpdatedBrand(prevBrand => ({
            ...prevBrand,
            [name]: value
        }));
    };

    if (!brand) return <div>Загрузка...</div>;

    return (
        <div className="form-container">
            <button className="form-back-btn" onClick={handleBack}>Назад</button>
            <h2 className="form-title">Имя бренда: {brand.brandName}</h2>
            <div className="form-field">
                <label className="form-label" htmlFor="brandName">Имя:</label>
                <input
                    type="text"
                    name="brandName"
                    value={updatedBrand.brandName}
                    onChange={handleInputChange}
                    className="form-input"
                />
            </div>
            <button className="form-button" onClick={handleUpdate}>Обновить бренд</button>
        </div>
    );
}

export default BrandUpdate;
