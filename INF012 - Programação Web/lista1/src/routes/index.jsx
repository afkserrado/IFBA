import { BrowserRouter, Route, Routes } from 'react-router-dom';

// Importa os componentes de cada página para as rotas
import Home from '../pages/Home';
import Q2 from '../pages/Questao2';
import Q3_4 from '../pages/Questao3-4';

// Define uma rota para cada componente importado
function Rotas() {
    return(
        <BrowserRouter>
            <Routes>
                <Route path='/' element={<Home/>}></Route>
                <Route path='/q2' element={<Q2/>}></Route>
                <Route path='/q3-4' element={<Q3_4/>}></Route>
            </Routes>
        </BrowserRouter>
    );
} export default Rotas;