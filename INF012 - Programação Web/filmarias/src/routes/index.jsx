import { BrowserRouter, Route, Routes } from 'react-router-dom';

// Páginas
import Home from '../pages/Home';
import Filme from '../pages/Filme';
import Favoritos from '../pages/Favoritos';

export default function Rotas() {
    return(
        <BrowserRouter>
            <Routes>
                <Route path='/' element={<Home/>}/>
                <Route path='/filme/:id' element={<Filme/>}/>
                <Route path='/favoritos' element={<Favoritos/>}/>
            </Routes>
        </BrowserRouter>
    );
}