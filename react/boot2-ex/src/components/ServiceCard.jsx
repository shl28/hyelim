import React from "react";
import { FaCheckCircle } from "react-icons/fa";
import "./ServiceCard.css";

function ServiceCard({ title, price, desc, features, recommended }) {
  return (
    <div className="col-md-4">
      <div className="card h-100 shadow-sm p-2 border-0">
        {recommended && (
          <span className="badge bg-primary mb-3 align-self-start">추천</span>
        )}

        <h5 className="fw-semibold mb-1">{title}</h5>

        <h2 className="text-primary fw-bold mb-1 display-6">{price}</h2>

        <p className="text-muted mb-1 small">{desc}</p>

        <ul className="list-unstyled mt-3">
          {features.map((item, index) => (
            <li key={index} className="mb-1 d-flex align-items-center">
              <span className="text-success me-2">
                <FaCheckCircle />
              </span>
              <span className="feature-text">{item}</span>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}

export default ServiceCard;
