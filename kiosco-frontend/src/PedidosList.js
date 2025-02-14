// src/PedidosList.js
import React, { useState, useEffect } from 'react';

function PedidosList() {
  const [pedidos, setPedidos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch('/pedidos')  // Gracias al proxy, no es necesario poner "http://localhost:8080"
      .then(response => {
        if (!response.ok) {
          throw new Error('Error en la respuesta de la API');
        }
        return response.json();
      })
      .then(data => {
        setPedidos(data);
        setLoading(false);
      })
      .catch(err => {
        setError(err);
        setLoading(false);
      });
  }, []);

  if (loading) return <p>Cargando pedidos...</p>;
  if (error) return <p>Error: {error.message}</p>;

  return (
    <div>
      <h2>Lista de Pedidos</h2>
      {pedidos.length === 0 ? (
        <p>No hay pedidos disponibles.</p>
      ) : (
        <ul>
          {pedidos.map(pedido => (
            <li key={pedido.id}>
              <strong>ID Pedido:</strong> {pedido.id} <br />
              {/* Ajusta según la estructura real del objeto pedido */}
              <span>Detalles: {pedido.detalle || 'Sin detalles'}</span>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default PedidosList;
