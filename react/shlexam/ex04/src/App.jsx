import React from "react";
import ProductsList from "./ProductsList";

const products = [
    { id: 1, name: "노트북", price: 1000000 },
    { id: 2, name: "마우스", price: 20000 },
];

function App() {
    return (
        <div>
            <ProductsList products={products} />
        </div>
    );
}

export default App;
