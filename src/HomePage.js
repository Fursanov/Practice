import React from 'react';
import { Link } from 'react-router-dom';
import HappyGif from './Happy.gif';
import './styles/HomeStyle.css';

const HomePage = () => {
    return (
        <div>
            <h1>Добро пожаловать на начальную страницу!</h1>
            <ul>
                <li><Link to="/brands">Просмотреть бренды</Link></li>
                <li><Link to="/products">Просмотреть продукты</Link></li>
                <li><Link to="/stores">Просмотреть магазины</Link></li>
            </ul>
            <img src={HappyGif} alt="GIF Image"/>
        </div>
    );
};

export default HomePage;
