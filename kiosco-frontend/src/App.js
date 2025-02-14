// src/App.js
import React from 'react';
import './App.css';
import PedidosList from './PedidosList';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>Software Inteligente para Kioscos</h1>
      </header>
      <main>
        <PedidosList />
      </main>
    </div>
  );
}

export default App;
