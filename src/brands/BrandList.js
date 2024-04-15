import React, { useEffect, useState } from 'react';
import BrandService from './BrandService';
import { Link, useNavigate } from 'react-router-dom';
import '../styles/ListStyle.css';


function BrandList() {
    const [brands, setBrands] = useState([]);
    const navigate = useNavigate ();

    const handleBack = async () => {
        navigate('/');
    };

    const handleUpdate = async (brandId) => {
        navigate(`/brands/update/${brandId}`);
    };

    const handleDelete = async (brandId) => {
        try {
            await BrandService.deleteBrand(brandId);
        } catch (error) {
            console.error('Error deleting brand:', error);
        }
        const updatedBrands = await BrandService.getAllBrands();
        setBrands(updatedBrands);
    };

    const handleCreate = async () => {
        navigate(`/brands/create`);
    };

    useEffect(() => {
        BrandService.getAllBrands().then(data => setBrands(data));
    }, []);

    return (
        <div className="list-container">
            <h2 className="list-header">Бренды</h2>
            <button className="list-create-btn" onClick={() => handleCreate()}>
                Создать бренд
            </button>
            <table className="list-table">
                <thead>
                <tr>
                    <th>Бренд</th>
                    <th>Действия</th>
                </tr>
                </thead>
                <tbody>
                {brands.map(brand => (
                    <tr key={brand.brandId}>
                        <td>{brand.brandName}</td>
                        <td>
                            <button className="list-update-btn" onClick={() => handleUpdate(brand.brandId)}>
                                Изменить бренд
                            </button>
                            <button className="list-delete-btn" onClick={() => handleDelete(brand.brandId)}>
                                Удалить бренд
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

export default BrandList;
