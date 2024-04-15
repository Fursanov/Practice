import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import HomePage from './HomePage';
import ProductList from './products/ProductList';
import ProductForm from './products/ProductForm';
import ProductDetail from './products/ProductUpdate';
import BrandList from './brands/BrandList';
import BrandForm from './brands/BrandForm';
import BrandUpdate from './brands/BrandUpdate';
import StoreList from './stores/StoreList';
import StoreForm from './stores/StoreForm';
import StoreUpdate from './stores/StoreUpdate';
import StoreProducts from './stores/StoreProducts'

function AppRouter() {
    return (
        <Router>
            <Routes>
                <Route path="/" exact element={<HomePage />} />
                <Route path="/products" exact element={<ProductList />} />
                <Route path="/products/create" element={<ProductForm />} />
                <Route path="/products/update/:id" element={<ProductDetail />} />
                <Route path="/brands" exact element={<BrandList />} />
                <Route path="/brands/create" element={<BrandForm />} />
                <Route path="/brands/update/:id" element={<BrandUpdate />} />
                <Route path="/stores" exact element={<StoreList />} />
                <Route path="/stores/create" element={<StoreForm />} />
                <Route path="/stores/update/:id" element={<StoreUpdate />} />
                <Route path="/stores/products/:id" element={<StoreProducts />} />
            </Routes>
        </Router>
    );
}

export default AppRouter;
