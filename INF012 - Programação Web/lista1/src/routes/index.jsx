import { BrowserRouter, Route, Routes } from 'react-router-dom';

// Importa os componentes de cada página para as rotas
import Home from '../pages/Home';
import Error from '../pages/Error';
import Questoes from '../pages/Questoes';

// Define uma rota para cada componente importado
function Rotas() {
    return(
        <BrowserRouter>
            <Routes>
                <Route path='/' element={<Home/>}/>
                <Route path='*' element={<Error/>}/>
                <Route path='/questoes/:id' element={<Questoes/>}/>
            </Routes>
        </BrowserRouter>
    );
} export default Rotas;