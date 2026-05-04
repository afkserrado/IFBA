import { BrowserRouter, Route, Routes } from 'react-router-dom';

// Páginas
import Home from '../pages/Home';
import Filme from '../pages/Filme';

export default function Rotas() {
    return(
        <BrowserRouter>
            <Routes>
                <Route path='/' element={<Home/>}/>
                <Route path='/filme/:id' element={<Filme/>}/>
            </Routes>
        </BrowserRouter>
    );
}