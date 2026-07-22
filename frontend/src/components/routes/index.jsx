import { Routes, Route } from 'react-router-dom';

// Importando as páginas
import Home from '../pages/Home';
import Acervo from '../pages/Acervo';
import Login from '../pages/Login';
import Emprestimos from '../pages/Emprestimos';
import AdminDashboard from '../pages/AdminDashboard'; // 1. IMPORTAMOS A AQUI!

import RotaProtegida from '../RotaProtegida';

export default function AppRoutes() {
  return (
    <Routes>
      <Route path="/login" element={<Login />} />

      <Route path="/" element={<RotaProtegida><Home /></RotaProtegida>} />
      <Route path="/acervo" element={<RotaProtegida><Acervo /></RotaProtegida>} />
      <Route path="/meus-emprestimos" element={<RotaProtegida><Emprestimos /></RotaProtegida>} />
      
      {/* 2. ROTA SUPER PROTEGIDA: Exige o cargo de ADMIN! */}
      <Route 
        path="/admin" 
        element={
          <RotaProtegida requireAdmin={true}>
            <AdminDashboard />
          </RotaProtegida>
        } 
      />
    </Routes>
  );
}