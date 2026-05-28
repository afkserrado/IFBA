import { BrowserRouter, Route, Routes } from 'react-router-dom';

// Páginas
import Home from '../pages/Home';
import Sobre from '../pages/Sobre';
import Erro from '../pages/Erro';

function Rotas() {
    return(
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Home/>}/>
                <Route path="/sobre/:id?" element={<Sobre/>}/>
                <Route path="*" element={<Erro/>}/>
            </Routes>
        </BrowserRouter>
    );

} export default Rotas;