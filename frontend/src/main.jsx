import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter } from 'react-router-dom';

// 1. Estilos do Bootstrap
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-icons/font/bootstrap-icons.css';

// 2. Importando nosso Contexto de Autenticação (O Cérebro!)
import { AuthProvider } from './contexts/AuthContext';

// 3. Nossos componentes no padrão da aula
import Navbar from './components/Navbar';
import AppRoutes from './components/routes';

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    {/* O AuthProvider envolve tudo para distribuir os dados do usuário logado */}
    <AuthProvider>
      <BrowserRouter>
        <Navbar />
        
        <div className="conteudo-principal">
          <AppRoutes />
        </div>
      </BrowserRouter>
    </AuthProvider>
  </React.StrictMode>,
);