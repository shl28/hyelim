import React from "react";

function Product({ product }) {
    return (
        <div>
            {product.name} - {product.price}원
        </div>
    );
}

export default Product;
